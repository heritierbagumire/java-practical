package com.app.NE.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MemoryHealthIndicator implements HealthIndicator {

    private static final double MEMORY_THRESHOLD = 0.9; // 90% threshold

    @Override
    public Health health() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long maxMemory = runtime.maxMemory();

        long usedMemory = totalMemory - freeMemory;
        double memoryUsagePercent = (double) usedMemory / maxMemory;

        if (memoryUsagePercent > MEMORY_THRESHOLD) {
            return Health.down()
                    .withDetail("total_memory_mb", totalMemory / 1024 / 1024)
                    .withDetail("free_memory_mb", freeMemory / 1024 / 1024)
                    .withDetail("used_memory_mb", usedMemory / 1024 / 1024)
                    .withDetail("max_memory_mb", maxMemory / 1024 / 1024)
                    .withDetail("memory_usage_percent", String.format("%.2f%%", memoryUsagePercent * 100))
                    .withDetail("threshold_percent", String.format("%.2f%%", MEMORY_THRESHOLD * 100))
                    .withDetail("error", "Memory usage is above threshold")
                    .build();
        }

        return Health.up()
                .withDetail("total_memory_mb", totalMemory / 1024 / 1024)
                .withDetail("free_memory_mb", freeMemory / 1024 / 1024)
                .withDetail("used_memory_mb", usedMemory / 1024 / 1024)
                .withDetail("max_memory_mb", maxMemory / 1024 / 1024)
                .withDetail("memory_usage_percent", String.format("%.2f%%", memoryUsagePercent * 100))
                .build();
    }
}