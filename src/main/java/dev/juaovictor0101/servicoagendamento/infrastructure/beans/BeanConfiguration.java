package dev.juaovictor0101.servicoagendamento.infrastructure.beans;


import dev.juaovictor0101.servicoagendamento.core.gateways.AgendamentoGateway;
import dev.juaovictor0101.servicoagendamento.core.usecases.*;
import dev.juaovictor0101.servicoagendamento.infrastructure.gateways.AgendamentoRepositoryGateway;
import dev.juaovictor0101.servicoagendamento.infrastructure.mapper.AgendamentoEntityMapper;
import dev.juaovictor0101.servicoagendamento.infrastructure.persistence.AgendamentoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CriarAgendamentoUseCase criarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new CriarAgendamentoUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public BuscarAgendamentoPorIdUseCase buscarAgendamentoPorId(AgendamentoGateway agendamentoGateway) {
        return new BuscarAgendamentoPorIdUseCaseImpl(agendamentoGateway);
    }


    @Bean
    public AtualizarAgendamentoUseCase atualizarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new AtualizarAgendamentoUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public ConcluirAgendamentoUseCase concluirAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new ConcluirAgendamentoUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public CancelarAgendamentoUseCase cancelarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        return new CancelarAgendamentoUseCaseImpl(agendamentoGateway);
    }

    @Bean
    public AgendamentoGateway agendamentoGateway (
            AgendamentoRepository repository,
            AgendamentoEntityMapper mapper
    ) {
                return new AgendamentoRepositoryGateway(repository,mapper);
    }
}
