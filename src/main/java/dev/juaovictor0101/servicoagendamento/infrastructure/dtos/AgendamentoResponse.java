package dev.juaovictor0101.servicoagendamento.infrastructure.dtos;

import dev.juaovictor0101.servicoagendamento.core.enums.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoResponse(

        Long id,
        String titulo,
        String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        StatusAgendamento status,
        String usuario,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {
}
