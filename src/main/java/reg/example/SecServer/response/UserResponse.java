package reg.example.SecServer.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import reg.example.SecServer.entity.UserEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserResponse extends BaseResponse{
    private UserEntity user;

    public UserResponse(boolean status, String status_text, UserEntity user) {
        super(status, status_text);
        this.user = user;
    }
    public UserResponse(UserEntity user) {
        super(true,"User data");
    }
}
