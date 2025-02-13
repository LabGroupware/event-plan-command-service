package org.cresplanex.api.event.plancommandservice.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditAware implements AuditorAware<String> {

    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("PLAN_COMMAND_MS");
    }
}
