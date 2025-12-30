package dev.juaovictor0101.servicoagendamento.infrastructure.presentation;


import dev.juaovictor0101.servicoagendamento.core.entities.Agendamento;
import dev.juaovictor0101.servicoagendamento.core.usecases.*;
import dev.juaovictor0101.servicoagendamento.infrastructure.dtos.AgendamentoCreateRequest;
import dev.juaovictor0101.servicoagendamento.infrastructure.dtos.AgendamentoResponse;
import dev.juaovictor0101.servicoagendamento.infrastructure.dtos.AgendamentoUpdateRequest;
import dev.juaovictor0101.servicoagendamento.infrastructure.mapper.AgendamentoCreateMapper;
import dev.juaovictor0101.servicoagendamento.infrastructure.mapper.AgendamentoRequestMapper;
import dev.juaovictor0101.servicoagendamento.infrastructure.mapper.AgendamentoResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/agendamentos")
public class AgendamentoController {

    private final CriarAgendamentoUseCase criarAgendamento;
    private final BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase;
    private final AtualizarAgendamentoUseCase atualizarAgendamento;
    private final CancelarAgendamentoUseCase cancelarAgendamento;
    private final ConcluirAgendamentoUseCase concluirAgendamento;
    private final AgendamentoCreateMapper agendamentoCreateMapper;
    private final AgendamentoResponseMapper agendamentoResponseMapper;
    private final AgendamentoRequestMapper agendamentoUpdateRequestMapper;
    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final CancelarAgendamentoUseCase cancelarAgendamentoUseCase;

    public AgendamentoController(CriarAgendamentoUseCase criarAgendamento, BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase, AtualizarAgendamentoUseCase atualizarAgendamento, CancelarAgendamentoUseCase cancelarAgendamento, ConcluirAgendamentoUseCase concluirAgendamento, AgendamentoCreateMapper agendamentoCreateMapper, AgendamentoResponseMapper agendamentoResponseMapper, AgendamentoRequestMapper agendamentoUpdateRequestMapper, CriarAgendamentoUseCase criarAgendamentoUseCase, CancelarAgendamentoUseCase cancelarAgendamentoUseCase) {
        this.criarAgendamento = criarAgendamento;
        this.buscarAgendamentoPorIdUseCase = buscarAgendamentoPorIdUseCase;
        this.atualizarAgendamento = atualizarAgendamento;
        this.cancelarAgendamento = cancelarAgendamento;
        this.concluirAgendamento = concluirAgendamento;
        this.agendamentoCreateMapper = agendamentoCreateMapper;
        this.agendamentoResponseMapper = agendamentoResponseMapper;
        this.agendamentoUpdateRequestMapper = agendamentoUpdateRequestMapper;
        this.criarAgendamentoUseCase = criarAgendamentoUseCase;
        this.cancelarAgendamentoUseCase = cancelarAgendamentoUseCase;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> criarAgendamento(AgendamentoCreateRequest request){
        Agendamento criado = criarAgendamentoUseCase.execute(agendamentoCreateMapper.toEntity(request));

        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento criado com sucesso");
        response.put("agendamento", agendamentoResponseMapper.toResponse(criado));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(agendamentoResponseMapper.toResponse(buscarAgendamentoPorIdUseCase.execute(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarAgendamento(@PathVariable Long id, @RequestBody AgendamentoUpdateRequest request){
        Agendamento existente = buscarAgendamentoPorIdUseCase.execute(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        Agendamento atualizado = atualizarAgendamento.execute(agendamentoUpdateRequestMapper.merge(existente,request));
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento atualizado com sucesso");
        response.put("agendamento", agendamentoResponseMapper.toResponse(atualizado));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Map<String, Object>> cancelarAgendamento(@PathVariable Long id){
        Agendamento existente = buscarAgendamentoPorIdUseCase.execute(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        Agendamento cancelado = cancelarAgendamentoUseCase.execute(id);
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento cancelado com sucesso");
        response.put("agendamento", agendamentoResponseMapper.toResponse(cancelado));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/conluir")
    public ResponseEntity<Map<String, Object>> concluirAgendamento(@PathVariable Long id){
        Agendamento existente = buscarAgendamentoPorIdUseCase.execute(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        Agendamento conluido = concluirAgendamento.execute(id);
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Agendamento cancelado com sucesso");
        response.put("agendamento", agendamentoResponseMapper.toResponse(conluido));
        return ResponseEntity.ok(response);
    }
}
