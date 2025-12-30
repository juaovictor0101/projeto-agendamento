package dev.juaovictor0101.servicoagendamento.core.usecases;

import dev.juaovictor0101.servicoagendamento.core.entities.Agendamento;
import dev.juaovictor0101.servicoagendamento.core.gateways.AgendamentoGateway;

public class CancelarAgendamentoUseCaseImpl implements CancelarAgendamentoUseCase {

    private final AgendamentoGateway gateway;

    public CancelarAgendamentoUseCaseImpl(AgendamentoGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public Agendamento execute(Long id) {

        if(gateway.buscarAgendamentoPorId(id) == null) {
            throw new IllegalArgumentException("Agendamento não encontrado");
        }
        return gateway.cancelarAgendamento(id);
    }
}
