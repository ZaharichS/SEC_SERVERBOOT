package reg.example.SecServer.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import reg.example.SecServer.entity.AuthorEntity;
import reg.example.SecServer.entity.UserEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorResponse extends BaseResponse{
    private AuthorEntity author;

    public AuthorResponse(boolean status, String status_text, AuthorEntity author) {
        super(status, status_text);
        this.author = author;
    }
    public AuthorResponse(AuthorEntity author) {
        super(true,"Author data");
    }
}
