package dev.juaovictor0101.servicoagendamento.service;


import dev.juaovictor0101.servicoagendamento.dto.AgendamentoCreateRequest;
import dev.juaovictor0101.servicoagendamento.dto.AgendamentoResponse;
import dev.juaovictor0101.servicoagendamento.dto.AgendamentoUpdateRequest;
import dev.juaovictor0101.servicoagendamento.entity.Agendamento;
import dev.juaovictor0101.servicoagendamento.entity.StatusAgendamento;
import dev.juaovictor0101.servicoagendamento.mapper.AgendamentoMapper;
import dev.juaovictor0101.servicoagendamento.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repo;


    public AgendamentoService(AgendamentoRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public AgendamentoResponse criar(@Valid AgendamentoCreateRequest req) {

        validarIntervalo(req.dataInicio(), req.dataFim());
        checkConflito(req.usuario(), req.dataInicio(), req.dataFim(), null);

        Agendamento entity = AgendamentoMapper.toEntity(req);
        entity = repo.save(entity);

        return AgendamentoMapper.toResponse(entity);
    }

    @Transactional
    public AgendamentoResponse atualizar(Long id, @Valid AgendamentoUpdateRequest req) {
        Agendamento entity = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado na base de dados."));
        AgendamentoMapper.merge(entity, req);

        validarIntervalo(req.dataInicio(), req.dataFim());
        checkConflito(entity.getUsuario(), req.dataInicio(), req.dataFim(), entity.getId());
        repo.save(entity);

        return AgendamentoMapper.toResponse(entity);
    }

    @Transactional
    public AgendamentoResponse cancelar(Long id) {
        Agendamento entity = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado na base de dados."));
        entity.setStatus(StatusAgendamento.CANCELADO);
        repo.save(entity);
        return AgendamentoMapper.toResponse(entity);
    }

    @Transactional
    public AgendamentoResponse concluir(Long id) {
        Agendamento entity = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado na base de dados."));
        entity.setStatus(StatusAgendamento.CONCLUÍDO);
        repo.save(entity);
        return AgendamentoMapper.toResponse(entity);
    }

    public AgendamentoResponse buscarPorId(Long id) {
        Agendamento a = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado na base de dados."));
        return AgendamentoMapper.toResponse(a);
    }

    private void validarIntervalo(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null || fim == null || !inicio.isBefore(fim)) {
            throw new IllegalArgumentException("Intervalo invalido: dataInicio deve ser anterior a dataFim do agendamento");
        }
    }

    private void checkConflito(String usuario, LocalDateTime inicio, LocalDateTime fim, Long id) {
        if (repo.existsConflict(usuario, inicio, fim, id)) {
            throw new IllegalArgumentException("Conflito na agenda. Já existe um agendamento aberto com esses dados.");
        }
    }
}
