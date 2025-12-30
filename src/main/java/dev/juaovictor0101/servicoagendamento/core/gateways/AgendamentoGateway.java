package dev.juaovictor0101.servicoagendamento.core.gateways;

import dev.juaovictor0101.servicoagendamento.core.entities.Agendamento;

public interface AgendamentoGateway {

    Agendamento criarAgendamento (Agendamento agendamento);
    Agendamento buscarAgendamentoPorId(Long id);
    Agendamento atualizarAgendamento(Agendamento agendamento);
    Agendamento cancelarAgendamento(Long id);
    Agendamento concluirAgendamento(Long id);
}
