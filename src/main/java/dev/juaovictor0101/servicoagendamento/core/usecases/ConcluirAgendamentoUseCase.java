package dev.juaovictor0101.servicoagendamento.core.usecases;

import dev.juaovictor0101.servicoagendamento.core.entities.Agendamento;

public interface ConcluirAgendamentoUseCase {

    Agendamento execute(Long id);
}
