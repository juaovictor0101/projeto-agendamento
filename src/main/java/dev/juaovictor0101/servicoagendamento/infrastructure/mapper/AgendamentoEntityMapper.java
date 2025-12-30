package dev.juaovictor0101.servicoagendamento.infrastructure.mapper;


import dev.juaovictor0101.servicoagendamento.core.entities.Agendamento;
import dev.juaovictor0101.servicoagendamento.core.enums.StatusAgendamento;
import dev.juaovictor0101.servicoagendamento.infrastructure.persistence.AgendamentoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendamentoEntityMapper {

    public AgendamentoEntity toEntity(Agendamento agendamento) {
        return AgendamentoEntity.builder()
                .titulo(agendamento.titulo())
                .descricao(agendamento.descricao())
                .dataInicio(agendamento.dataInicio())
                .dataFim(agendamento.dataFim())
                .status(StatusAgendamento.AGENDADO)
                .usuario(agendamento.usuario())
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();
    }

    public Agendamento toDomain (AgendamentoEntity agendamentoEntity) {
        return new Agendamento(
                agendamentoEntity.getId(),
                agendamentoEntity.getTitulo(),
                agendamentoEntity.getDescricao(),
                agendamentoEntity.getDataInicio(),
                agendamentoEntity.getDataFim(),
                agendamentoEntity.getStatus(),
                agendamentoEntity.getUsuario(),
                agendamentoEntity.getCriadoEm(),
                agendamentoEntity.getAtualizadoEm()

        );
    }
}
