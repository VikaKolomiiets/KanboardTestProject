package api.args.user;

import api.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUser {
    private Integer id;
    private String username;
    private String name;
    private String email;
    private UserRole role;
}
