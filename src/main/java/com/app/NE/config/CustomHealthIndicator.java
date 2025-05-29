package com.app.NE.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        try {
            // Add your custom health check logic here
            // For example, check database connectivity, external services, etc.
            return Health.up()
                    .withDetail("app", "Running")
                    .withDetail("error", "None")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("app", "Not Running")
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}