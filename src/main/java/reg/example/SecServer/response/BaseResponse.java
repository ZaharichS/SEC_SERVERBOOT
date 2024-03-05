package reg.example.SecServer.response;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Hidden
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    protected boolean status;
    protected String status_text;
}
