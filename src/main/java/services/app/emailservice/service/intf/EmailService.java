package services.app.emailservice.service.intf;

import services.app.emailservice.dto.EmailDTO;

public interface EmailService {
    public void sendMailTo(EmailDTO emailDTO);
}