package reg.example.SecServer.response;


import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import reg.example.SecServer.entity.AuthorEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorListResponse extends BaseResponse {
    private Iterable<AuthorEntity> authors_data;

    public AuthorListResponse(Iterable<AuthorEntity> authors_data) {
        super(true, "Авторы");
        this.authors_data = authors_data;
    }
}
