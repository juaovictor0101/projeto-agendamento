package dev.juaovictor0101.servicoagendamento.infrastructure.mapper;

import dev.juaovictor0101.servicoagendamento.core.entities.Agendamento;
import dev.juaovictor0101.servicoagendamento.core.enums.StatusAgendamento;
import dev.juaovictor0101.servicoagendamento.infrastructure.dtos.AgendamentoUpdateRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoRequestMapper {

    public AgendamentoUpdateRequest toDto(Agendamento agendamento) {
        return new AgendamentoUpdateRequest(
                agendamento.titulo(),
                agendamento.descricao(),
                agendamento.dataInicio(),
                agendamento.dataFim()
        );
    }

    public Agendamento merge(Agendamento agendamento, AgendamentoUpdateRequest req) {
        return new Agendamento(
                agendamento.id(),
                req.titulo() != null ? req.titulo() : agendamento.titulo(),
                req.descricao() != null ? req.descricao() : agendamento.descricao(),
                req.dataInicio() != null ? req.dataInicio() : agendamento.dataInicio(),
                req.dataFim() != null ? req.dataFim() : agendamento.dataFim(),
                agendamento.status(),
                agendamento.usuario(),
                agendamento.criadoEm(),
                LocalDateTime.now()
        );
    }

    public Agendamento toEntity(AgendamentoUpdateRequest agendamentoUpdateRequest) {
        return new Agendamento(
                null,
                agendamentoUpdateRequest.titulo(),
                agendamentoUpdateRequest.descricao(),
                agendamentoUpdateRequest.dataInicio(),
                agendamentoUpdateRequest.dataFim(),
                StatusAgendamento.AGENDADO,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
