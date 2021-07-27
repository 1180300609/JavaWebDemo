package po;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private int rid;
    private int active;
    private String phone;
    private String email;
}
