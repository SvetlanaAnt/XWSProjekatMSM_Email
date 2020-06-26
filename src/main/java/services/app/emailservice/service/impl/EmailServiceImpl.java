package services.app.emailservice.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import services.app.emailservice.conf.RabbitMQConfiguration;
import services.app.emailservice.dto.EmailDTO;
import services.app.emailservice.service.intf.EmailService;

public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    @Override
    @Async
    @RabbitListener(queues = RabbitMQConfiguration.QUEUE_NAME)
    public void sendMailTo(EmailDTO emailDTO) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(emailDTO.getEmail());
        mail.setFrom(environment.getProperty("spring.mail.username"));
        mail.setSubject(emailDTO.getSubject());
        mail.setText(emailDTO.getMessage());
        javaMailSender.send(mail);

    }
}
