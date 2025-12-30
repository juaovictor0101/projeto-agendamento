package dev.juaovictor0101.servicoagendamento.core.usecases;

import dev.juaovictor0101.servicoagendamento.core.entities.Agendamento;
import dev.juaovictor0101.servicoagendamento.core.gateways.AgendamentoGateway;

import java.time.LocalDateTime;

public class AtualizarAgendamentoUseCaseImpl implements AtualizarAgendamentoUseCase {


    private final AgendamentoGateway gateway;

    public AtualizarAgendamentoUseCaseImpl(AgendamentoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Agendamento execute(Agendamento agendamento) {
        var existente = gateway.buscarAgendamentoPorId(agendamento.id());

        if (existente == null) {
            throw new IllegalArgumentException("Agendamento não encontrado");
        }

        return gateway.atualizarAgendamento(new Agendamento(
                existente.id(),
                existente.titulo(),
                existente.descricao(),
                existente.dataInicio(),
                existente.dataFim(),
                existente.status(),
                existente.usuario(),
                existente.criadoEm(),
                LocalDateTime.now()
        ));
    }
}
