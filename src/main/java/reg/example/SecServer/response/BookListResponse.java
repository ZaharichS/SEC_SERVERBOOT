package reg.example.SecServer.response;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reg.example.SecServer.entity.BookEntity;

import java.util.List;

@Hidden
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookListResponse extends BaseResponse {

    private List<BookEntity> data;

    public BookListResponse(boolean status, String status_text, List<BookEntity> data) {
        super(status, status_text);
        this.data = data;
    }
}
