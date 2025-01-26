package org.cresplanex.api.event.plancommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PlanCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanCommandServiceApplication.class, args);
    }

}
