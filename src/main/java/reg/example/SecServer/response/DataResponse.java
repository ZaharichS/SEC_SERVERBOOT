package reg.example.SecServer.response;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Hidden
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse<T> extends BaseResponse {
    private T data;

    public DataResponse(boolean status, String status_text, T data) {
        super(status, status_text);
        this.data = data;
    }

    public DataResponse(boolean status, String status_text) {
        super(status, status_text);
    }
}
