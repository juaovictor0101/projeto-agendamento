package dev.juaovictor0101.servicoagendamento.core.usecases;

import dev.juaovictor0101.servicoagendamento.core.entities.Agendamento;
import dev.juaovictor0101.servicoagendamento.core.gateways.AgendamentoGateway;

public class CriarAgendamentoUseCaseImpl implements CriarAgendamentoUseCase {

    private final AgendamentoGateway gateway;

    public CriarAgendamentoUseCaseImpl(AgendamentoGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public Agendamento execute(Agendamento agendamento) {
        return gateway.criarAgendamento(agendamento);
    }
}
