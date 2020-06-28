package services.app.emailservice.dto;

import lombok.*;

import java.io.Serializable;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO implements Serializable {
    String email;
    String subject;
    String message;
}
