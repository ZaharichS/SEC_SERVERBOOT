package reg.example.SecServer.response;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Hidden
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse<T> extends BaseResponse {
    private List<T> data;

    public ListResponse(boolean status, String status_text, List<T> data) {
        super(status, status_text);
        this.data = data;
    }
}
