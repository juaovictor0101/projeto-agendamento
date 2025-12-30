package dev.juaovictor0101.servicoagendamento.infrastructure.mapper;


import dev.juaovictor0101.servicoagendamento.core.entities.Agendamento;
import dev.juaovictor0101.servicoagendamento.core.enums.StatusAgendamento;
import dev.juaovictor0101.servicoagendamento.infrastructure.dtos.AgendamentoCreateRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoCreateMapper {

    public AgendamentoCreateRequest toDto(Agendamento agendamento) {
        return new AgendamentoCreateRequest(
                agendamento.titulo(),
                agendamento.descricao(),
                agendamento.dataInicio(),
                agendamento.dataFim(),
                agendamento.usuario()
        );
    }

    public Agendamento toEntity(AgendamentoCreateRequest agendamentoCreateRequest) {
        return new Agendamento(
          null,
          agendamentoCreateRequest.titulo(),
          agendamentoCreateRequest.descricao(),
          agendamentoCreateRequest.dataInicio(),
          agendamentoCreateRequest.dataFim(),
          StatusAgendamento.AGENDADO,
          agendamentoCreateRequest.usuario(),
          LocalDateTime.now(),
          LocalDateTime.now()
        );
    }
}
