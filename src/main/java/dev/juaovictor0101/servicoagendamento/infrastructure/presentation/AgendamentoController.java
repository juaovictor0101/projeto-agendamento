package dev.juaovictor0101.servicoagendamento.infrastructure.presentation;


import dev.juaovictor0101.servicoagendamento.core.usecases.*;
import dev.juaovictor0101.servicoagendamento.infrastructure.dtos.AgendamentoUpdateRequest;
import dev.juaovictor0101.servicoagendamento.infrastructure.mapper.AgendamentoCreateMapper;
import dev.juaovictor0101.servicoagendamento.infrastructure.mapper.AgendamentoRequestMapper;
import dev.juaovictor0101.servicoagendamento.infrastructure.mapper.AgendamentoResponseMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public AgendamentoController(CriarAgendamentoUseCase criarAgendamento, BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase, AtualizarAgendamentoUseCase atualizarAgendamento, CancelarAgendamentoUseCase cancelarAgendamento, ConcluirAgendamentoUseCase concluirAgendamento, AgendamentoCreateMapper agendamentoCreateMapper, AgendamentoResponseMapper agendamentoResponseMapper, AgendamentoRequestMapper agendamentoUpdateRequestMapper) {
        this.criarAgendamento = criarAgendamento;
        this.buscarAgendamentoPorIdUseCase = buscarAgendamentoPorIdUseCase;
        this.atualizarAgendamento = atualizarAgendamento;
        this.cancelarAgendamento = cancelarAgendamento;
        this.concluirAgendamento = concluirAgendamento;
        this.agendamentoCreateMapper = agendamentoCreateMapper;
        this.agendamentoResponseMapper = agendamentoResponseMapper;
        this.agendamentoUpdateRequestMapper = agendamentoUpdateRequestMapper;
    }
}
