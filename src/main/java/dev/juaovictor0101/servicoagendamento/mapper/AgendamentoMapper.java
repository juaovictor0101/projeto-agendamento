package dev.juaovictor0101.servicoagendamento.mapper;

import dev.juaovictor0101.servicoagendamento.infrastructure.dtos.AgendamentoCreateRequest;
import dev.juaovictor0101.servicoagendamento.infrastructure.dtos.AgendamentoResponse;
import dev.juaovictor0101.servicoagendamento.infrastructure.dtos.AgendamentoUpdateRequest;
import dev.juaovictor0101.servicoagendamento.infrastructure.persistence.AgendamentoEntity;
import dev.juaovictor0101.servicoagendamento.core.enums.StatusAgendamento;

import java.time.LocalDateTime;

public class AgendamentoMapper {


    public static AgendamentoEntity toEntity(AgendamentoCreateRequest req) {
        return AgendamentoEntity.builder()
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

    public static void merge (AgendamentoEntity entity, AgendamentoUpdateRequest req){
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

    public static AgendamentoResponse toResponse(AgendamentoEntity e) {
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
