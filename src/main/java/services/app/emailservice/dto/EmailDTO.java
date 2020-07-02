package services.app.emailservice.dto;

import lombok.*;

@Builder
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    private String email;
    private String subject;
    private String message;
}
