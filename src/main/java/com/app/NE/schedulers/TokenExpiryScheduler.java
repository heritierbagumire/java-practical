package com.app.NE.schedulers;

import com.app.NE.enums.ETokenStatus;
import com.app.NE.models.Notification;
import com.app.NE.models.PurchasedToken;
import com.app.NE.models.User;
import com.app.NE.repositories.INotificationRepository;
import com.app.NE.repositories.IPurchasedTokenRepository;
import com.app.NE.repositories.IUserRepository;
import com.app.NE.services.mail.IMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenExpiryScheduler {
    private final IPurchasedTokenRepository purchasedTokenRepository;
    private final IUserRepository userRepository;
    private final INotificationRepository notificationRepository;
    private final TaskExecutor executor;
    private final IMailService mailService;

    @Scheduled(cron = "0 0 * * * *")
    public void expireTokens() {
        List<PurchasedToken> tokens = purchasedTokenRepository.findByStatus(ETokenStatus.NEW);
        int expiredCount = 0;

        for (PurchasedToken token : tokens) {
            LocalDateTime expiryDate = token.getPurchasedDate().plusDays(token.getTokenValueDays());

            if (LocalDateTime.now().isAfter(expiryDate)) {
                token.setStatus(ETokenStatus.EXPIRED);
                expiredCount++;
            }
        }

        if (expiredCount > 0) {
            purchasedTokenRepository.saveAll(tokens);
            log.info("Marked {} tokens as EXPIRED", expiredCount);
        } else {
            log.info("No expired tokens found today.");
        }
    }

    @Scheduled(cron = "0 0 * * * *")
    public void checkExpiringTokens() {
        List<PurchasedToken> expiringTokens = purchasedTokenRepository.findTokensExpiringInFiveHours();

        for (PurchasedToken token : expiringTokens) {
            User customer = userRepository.findByMeterNumber(token.getMeterNumber())
                    .orElseThrow(() -> new RuntimeException("Customer not found for meter number: " + token.getMeterNumber()));

            String message = String.format(
                    "Dear %s, REG is pleased to remind you that the token in the meter %d is going to expire in 5 hours. Please purchase a new token.",
                    customer.getNames(),
                    token.getMeterNumber()
            );

            Notification notification = new Notification();
            notification.setMeterNumber(token.getMeterNumber());
            notification.setMessage(message);
            notification.setIssuedDate(LocalDateTime.now());

            Notification n = notificationRepository.save(notification);
            // send the notification
            executor.execute(() -> {
                try {
                    mailService.sendMail(n);
                    log.info("Email sent successfully.");
                } catch(Exception e) {
                    log.error("Failed to send verification email", e);
                }
            });
        }
    }
}

