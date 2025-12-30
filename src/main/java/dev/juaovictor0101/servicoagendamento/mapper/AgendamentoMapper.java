package dev.juaovictor0101.servicoagendamento.mapper;

import dev.juaovictor0101.servicoagendamento.dto.AgendamentoCreateRequest;
import dev.juaovictor0101.servicoagendamento.dto.AgendamentoResponse;
import dev.juaovictor0101.servicoagendamento.dto.AgendamentoUpdateRequest;
import dev.juaovictor0101.servicoagendamento.entities.Agendamento;
import dev.juaovictor0101.servicoagendamento.core.enums.StatusAgendamento;

import java.time.LocalDateTime;

public class AgendamentoMapper {


    public static Agendamento toEntity(AgendamentoCreateRequest req) {
        return Agendamento .builder()
                .titulo(req.titulo())
                .descricao(req.descricao())
                .dataInicio(req.dataInicio())
                .dataFim(req.dataFim())
                .usuario(req.usuario())
                .status(StatusAgendamento.AGENDADO)
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();
    }

    public static void merge (Agendamento entity, AgendamentoUpdateRequest req){
        if (req.titulo() != null) {
            entity.setTitulo(req.titulo());
        }
        if (req.descricao() != null) {
            entity.setDescricao(req.descricao());
        }
        if (req.dataInicio() != null) {
            entity.setDataInicio(req.dataInicio());
        }
        if (req.dataFim() != null) {
            entity.setDataFim(req.dataFim());
        }
    }

    public static AgendamentoResponse toResponse(Agendamento e) {
        return new AgendamentoResponse(
                e.getId(),
                e.getTitulo(),
                e.getDescricao(),
                e.getDataInicio(),
                e.getDataFim(),
                e.getStatus(),
                e.getUsuario(),
                e.getCriadoEm(),
                e.getAtualizadoEm()
        );
    }
}
