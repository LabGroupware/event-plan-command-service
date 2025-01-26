package org.cresplanex.api.event.plancommandservice.saga.dispatcher;

import lombok.AllArgsConstructor;

import org.cresplanex.api.event.plancommandservice.saga.handler.TaskSagaCommandHandlers;
import org.cresplanex.api.state.common.saga.SagaCommandChannel;
import org.cresplanex.api.state.common.saga.dispatcher.BaseSagaCommandDispatcher;
import org.cresplanex.core.saga.participant.SagaCommandDispatcher;
import org.cresplanex.core.saga.participant.SagaCommandDispatcherFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class TaskSagaCommandDispatcher extends BaseSagaCommandDispatcher {

    @Bean
    public SagaCommandDispatcher organizationSagaCommandHandlersDispatcher(
            TaskSagaCommandHandlers taskSagaCommandHandlers,
            SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
        return sagaCommandDispatcherFactory.make(
                this.getDispatcherId(SagaCommandChannel.PLAN, "Task"),
                taskSagaCommandHandlers.commandHandlers());
    }
}
