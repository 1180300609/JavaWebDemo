package po;

import lombok.Data;

@Data
public class UserRead {
    private String username;
    private int rid;
    private int active;
    private String phone;
    private String email;
}
