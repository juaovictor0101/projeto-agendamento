package dev.juaovictor0101.servicoagendamento.infrastructure.gateways;

import dev.juaovictor0101.servicoagendamento.core.entities.Agendamento;
import dev.juaovictor0101.servicoagendamento.core.enums.StatusAgendamento;
import dev.juaovictor0101.servicoagendamento.core.gateways.AgendamentoGateway;
import dev.juaovictor0101.servicoagendamento.infrastructure.mapper.AgendamentoEntityMapper;
import dev.juaovictor0101.servicoagendamento.infrastructure.persistence.AgendamentoEntity;
import dev.juaovictor0101.servicoagendamento.infrastructure.persistence.AgendamentoRepository;

import java.time.LocalDateTime;

public class AgendamentoRepositoryGateway implements AgendamentoGateway {

    private final AgendamentoRepository repository;
    private final AgendamentoEntityMapper mapper;

    public AgendamentoRepositoryGateway(AgendamentoRepository repository, AgendamentoEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public Agendamento criarAgendamento(Agendamento agendamento) {

        validarIntervalo(agendamento.dataInicio(), agendamento.dataFim());
        checarConflito(agendamento.usuario(),agendamento.dataInicio(), agendamento.dataFim(), null);

        AgendamentoEntity agendamentoEntity = repository.save(mapper.toEntity(agendamento));
        return mapper.toDomain(agendamentoEntity);
    }

    @Override
    public Agendamento buscarAgendamentoPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public Agendamento atualizarAgendamento(Agendamento agendamento) {
        return mapper.toDomain(repository.save(mapper.toEntity(agendamento)));
    }

    @Override
    public Agendamento cancelarAgendamento(Long id) {
        var existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado um agendamento com ese ID para ser cancelado"));
        existente.setStatus(StatusAgendamento.CANCELADO);
        existente.setAtualizadoEm(LocalDateTime.now());

        return mapper.toDomain(repository.save(existente));
    }

    @Override
    public Agendamento concluirAgendamento(Long id) {
        var existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado um agendamento com ese ID para ser concluido"));
        existente.setStatus(StatusAgendamento.CONCLUÍDO);
        existente.setAtualizadoEm(LocalDateTime.now());

        return mapper.toDomain(repository.save(existente));
    }

    private void validarIntervalo(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null || fim == null || !inicio.isBefore(fim)) {
            throw new IllegalArgumentException("Intervalo invalido: dataInicio deve ser anterior a dataFim do agendamento");
        }
    }

    private void checarConflito(String usuario, LocalDateTime inicio, LocalDateTime fim, Long ignoreId) {
        if (repository.existsConflict(usuario, inicio, fim, ignoreId)) {
            throw new IllegalArgumentException("Conflito na agenda. Já existe um agendamento aberto com esses dados.");
        }
    }
}
