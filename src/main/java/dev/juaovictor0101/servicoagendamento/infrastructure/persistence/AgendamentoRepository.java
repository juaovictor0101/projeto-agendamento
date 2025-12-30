package dev.juaovictor0101.servicoagendamento.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {


    @Query("""
                SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
                        FROM AgendamentoEntity a 
                        WHERE a.usuario = :usuario
                              AND a.status = dev.juaovictor0101.servicoagendamento.core.enums.StatusAgendamento.AGENDADO
                              AND (a.dataInicio < :fim AND a.dataFim > :inicio)
                              AND (:ignoreId is NULL OR a.id <> :ignoreId)                               
            
            """)
    boolean existsConflict(@Param("usuario") String usuario,
                           @Param("inicio") LocalDateTime inicio,
                           @Param("fim") LocalDateTime fim,
                           @Param("ignoreId") Long ignoreId);
}
