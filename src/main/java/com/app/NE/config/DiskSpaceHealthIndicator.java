package com.app.NE.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DiskSpaceHealthIndicator implements HealthIndicator {

    @Value("${spring.application.name}")
    private String applicationName;

    private final long THRESHOLD_BYTES = 10 * 1024 * 1024; // 10MB threshold

    @Override
    public Health health() {
        try {
            File root = new File("/");
            long freeSpace = root.getFreeSpace();
            long totalSpace = root.getTotalSpace();
            double freeSpacePercent = ((double) freeSpace / totalSpace) * 100;

            if (freeSpace < THRESHOLD_BYTES) {
                return Health.down()
                        .withDetail("application", applicationName)
                        .withDetail("total_space_mb", totalSpace / 1024 / 1024)
                        .withDetail("free_space_mb", freeSpace / 1024 / 1024)
                        .withDetail("free_space_percent", String.format("%.2f%%", freeSpacePercent))
                        .withDetail("threshold_mb", THRESHOLD_BYTES / 1024 / 1024)
                        .withDetail("error", "Disk space is below threshold")
                        .build();
            }

            return Health.up()
                    .withDetail("application", applicationName)
                    .withDetail("total_space_mb", totalSpace / 1024 / 1024)
                    .withDetail("free_space_mb", freeSpace / 1024 / 1024)
                    .withDetail("free_space_percent", String.format("%.2f%%", freeSpacePercent))
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("application", applicationName)
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
} 