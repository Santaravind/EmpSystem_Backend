package emp.EMSystem.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequest {
    private  String to;
    private String subject;
    private String message;
}
