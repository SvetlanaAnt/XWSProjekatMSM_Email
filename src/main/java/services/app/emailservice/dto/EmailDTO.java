package services.app.emailservice.dto;

import lombok.*;

@Builder
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    String email;
    String subject;
    String message;
}
