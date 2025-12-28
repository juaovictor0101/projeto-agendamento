package dev.juaovictor0101.servicoagendamento.dto;

import dev.juaovictor0101.servicoagendamento.entity.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoResponse(

        Long id,
        String titulo,
        String descricao,
        String dataInicio,
        String dataFim,
        StatusAgendamento status,
        String usuario,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
