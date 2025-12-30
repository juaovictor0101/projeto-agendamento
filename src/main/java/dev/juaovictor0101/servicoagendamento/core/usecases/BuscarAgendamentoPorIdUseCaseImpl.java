package dev.juaovictor0101.servicoagendamento.core.usecases;

import dev.juaovictor0101.servicoagendamento.core.entities.Agendamento;
import dev.juaovictor0101.servicoagendamento.core.gateways.AgendamentoGateway;

public class BuscarAgendamentoPorIdUseCaseImpl implements BuscarAgendamentoPorIdUseCase {

    private final AgendamentoGateway gateway;

    public BuscarAgendamentoPorIdUseCaseImpl(AgendamentoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Agendamento execute(Long id) {

        var agendamento = gateway.buscarAgendamentoPorId(id);
        if(agendamento == null) {
            throw new IllegalArgumentException("Agendamento não encontrado por esse ID");
        }

        return agendamento;
    }
}
