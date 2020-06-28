package services.app.emailservice.service.intf;

import com.fasterxml.jackson.core.JsonProcessingException;
import services.app.emailservice.dto.EmailDTO;

public interface EmailService {
    public void sendMailTo(String msg);
}