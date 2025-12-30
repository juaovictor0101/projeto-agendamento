package dev.juaovictor0101.servicoagendamento.infrastructure.mapper;

import dev.juaovictor0101.servicoagendamento.core.entities.Agendamento;
import dev.juaovictor0101.servicoagendamento.infrastructure.dtos.AgendamentoResponse;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoResponseMapper {

    public AgendamentoResponse toResponse(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.id(),
                agendamento.titulo(),
                agendamento.descricao(),
                agendamento.dataInicio(),
                agendamento.dataFim(),
                agendamento.status(),
                agendamento.usuario(),
                agendamento.criadoEm(),
                agendamento.atualizadoEm()
        );
    }

    public Agendamento toEntity(AgendamentoResponse agendamentoResponse) {
        return new Agendamento(
          agendamentoResponse.id(),
          agendamentoResponse.titulo(),
          agendamentoResponse.descricao(),
          agendamentoResponse.dataInicio(),
          agendamentoResponse.dataFim(),
          agendamentoResponse.status(),
          agendamentoResponse.usuario(),
          agendamentoResponse.criadoEm(),
          agendamentoResponse.atualizadoEm()
        );
    }
}
