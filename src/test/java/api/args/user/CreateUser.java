package api.args.user;

import api.enums.UserRole;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.SuperCallHandle;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {
    private String username;
    private String password;
    private String name;
    private String email;
    private UserRole role;
}
