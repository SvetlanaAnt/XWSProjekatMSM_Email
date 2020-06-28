package services.app.emailservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import services.app.emailservice.conf.RabbitMQConfiguration;
import services.app.emailservice.dto.EmailDTO;
import services.app.emailservice.service.intf.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Async
    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_NAME)
    public void sendMailTo(String msg) {
        try {
            EmailDTO emailDTO = objectMapper.readValue(msg, EmailDTO.class);
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(emailDTO.getEmail());
            mail.setFrom(environment.getProperty("spring.mail.username"));
            mail.setSubject(emailDTO.getSubject());
            mail.setText(emailDTO.getMessage());
            javaMailSender.send(mail);
        } catch (JsonProcessingException exception) {
            return;
        }

    }
}
