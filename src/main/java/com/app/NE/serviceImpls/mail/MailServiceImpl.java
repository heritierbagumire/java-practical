package com.app.NE.serviceImpls.mail;

import com.app.NE.exceptions.EmailException;
import com.app.NE.exceptions.NotFoundException;
import com.app.NE.models.Employee;
import com.app.NE.models.Message;
import com.app.NE.models.User;
import com.app.NE.repositories.IEmployeeRepository;
import com.app.NE.repositories.IMessageRepository;
import com.app.NE.repositories.IUserRepository;
import com.app.NE.services.mail.IMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements IMailService {
    private final TemplateEngine templateEngine;
    private final IEmployeeRepository employeeRepository;
    private final JavaMailSender mailSender;
    private final Environment env;

    @Override
    public void sendMail(Message msg) {
        // get the user and message
        Employee e = employeeRepository.findByCode(msg.getEmployee().getCode()).orElseThrow(() -> new NotFoundException("Employee not found"));

        try {
            MimeMessage m = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(m, true, "UTF-8");

            // Prepare the evaluation context
            Context context = new Context();
            context.setVariable("name", e.getFirstName());
            context.setVariable("data", msg.getMessage());

            // Process the HTML template
            String htmlContent = templateEngine.process("message", context);

            // Set email properties
            helper.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
            helper.setTo(e.getEmail());
            helper.setSubject("Pay slip notification");
            helper.setText(htmlContent, true); // true = isHtml

            mailSender.send(m);
        } catch (MessagingException ex) {
            throw new EmailException("Failed to send verification email", ex);
        }
    }

}
