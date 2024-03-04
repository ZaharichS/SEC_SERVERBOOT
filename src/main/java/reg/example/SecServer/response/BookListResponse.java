package reg.example.SecServer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reg.example.SecServer.entity.BookEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookListResponse extends BaseResponse {

    private Iterable<BookEntity> data;

    public BookListResponse(boolean status, String status_text, Iterable<BookEntity> data) {
        super(status, status_text);
        this.data = data;
    }
}
