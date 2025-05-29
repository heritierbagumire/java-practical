package com.app.NE.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public Health health() {
        try {
            // Check cache status
            boolean cacheStatus = checkCacheHealth();

            if (!cacheStatus) {
                return Health.down()
                        .withDetail("app", "Not Running")
                        .withDetail("cache", "Cache system is not responding")
                        .withDetail("error", "Cache system failure")
                        .build();
            }

            // Add more application-specific checks here
            return Health.up()
                    .withDetail("app", "Running")
                    .withDetail("cache", "Operational")
                    .withDetail("uptime", System.currentTimeMillis())
                    .withDetail("version", "1.0") // You can make this dynamic using @Value
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("app", "Not Running")
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }

    private boolean checkCacheHealth() {
        try {
            // Get all cache names
            cacheManager.getCacheNames();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}