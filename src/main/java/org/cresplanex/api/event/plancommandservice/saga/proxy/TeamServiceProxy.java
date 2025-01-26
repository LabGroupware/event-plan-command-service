package org.cresplanex.api.event.plancommandservice.saga.proxy;

import org.cresplanex.api.state.common.saga.SagaCommandChannel;
import org.cresplanex.api.state.common.saga.validate.team.TeamExistValidateCommand;
import org.cresplanex.core.saga.simpledsl.CommandEndpoint;
import org.cresplanex.core.saga.simpledsl.CommandEndpointBuilder;
import org.springframework.stereotype.Component;

@Component
public class TeamServiceProxy {

    public final CommandEndpoint<TeamExistValidateCommand> teamExistValidate
            = CommandEndpointBuilder
            .forCommand(TeamExistValidateCommand.class)
            .withChannel(SagaCommandChannel.TEAM)
            .withCommandType(TeamExistValidateCommand.TYPE)
            .build();
}
