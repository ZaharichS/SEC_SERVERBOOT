package reg.example.SecServer.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    protected boolean status;
    protected String status_text;
}
