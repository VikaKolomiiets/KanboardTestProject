package api.args.user;

import api.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.TimeZone;

@Data
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseUser {
    private String id;
    private String username;
    private String password;
    private int is_ldap_user;
    private UserRole role;              //check type in the documentation
    private String name;
    private String email;
    private int notifications_enabled;  //check type in the documentation
    private TimeZone timezone = null;   //check type in the documentation
    private String language;
    private String google_id;
    private String github_id;
    private int nb_failed_login;        //check type in the documentation
    private int is_active;              //check type in the documentation
}
