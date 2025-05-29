package com.app.NE.serviceImpls.mail;

import com.app.NE.exceptions.NotFoundException;
import com.app.NE.models.Notification;
import com.app.NE.models.User;
import com.app.NE.repositories.INotificationRepository;
import com.app.NE.repositories.IUserRepository;
import com.app.NE.services.mail.IMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements IMailService {
    private final INotificationRepository notificationRepository;
    private final IUserRepository userRepository;
    private final JavaMailSender mailSender;

    @Override
    public void sendMail(Notification notification) {
        // get the user and message
        User user = userRepository.findByMeterNumber(notification.getMeterNumber()).orElseThrow(() -> new NotFoundException(String.format("User with meter number %s not found", notification.getMeterNumber())));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("New Notification");
        message.setText(notification.getMessage());
        mailSender.send(message);
    }
}
