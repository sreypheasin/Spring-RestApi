package co.istad.springapi.api.user;

import co.istad.springapi.api.auth.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private  Integer id;
    private String name;
    private String gender;
    private String oneSignalId;
    private String studentCardId;
    private Boolean IsStudent;
    private  Boolean IsDeleted;
    // Auth

    private String email;
    private String password;
    private Boolean isVerified;
    private String verifiedCode;

    private List<Role> roles;
}
