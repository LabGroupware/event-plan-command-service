package org.cresplanex.api.event.plancommandservice.config;

import org.cresplanex.api.event.plancommandservice.audit.AuditAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class SpringSecurityAuditorAwareConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditAware();
    }
}
