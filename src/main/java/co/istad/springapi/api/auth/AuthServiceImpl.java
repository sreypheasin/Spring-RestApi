package co.istad.springapi.api.auth;

import co.istad.springapi.api.auth.web.RegisterDto;
import co.istad.springapi.api.user.User;
import co.istad.springapi.api.user.UserMapStruct;
import co.istad.springapi.utils.MailUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthMapper authMapper;
    private final UserMapStruct userMapStruct;
    private final PasswordEncoder encoder;
    private final MailUtil mailUtil;

    @Value("${spring.mail.username}")
    private String appMail;

//    TODO: Register
    @Override
    public void register(RegisterDto registerDto) {

        User user = userMapStruct.registerDtoToUser(registerDto);
//        log.info("Users: {}",user.getId());
        user.setIsVerified(false);
        user.setPassword(encoder.encode(user.getPassword()));
        log.info("User: {}", user.getEmail());

        if(authMapper.register(user)){
            for(Integer role:registerDto.roleIds()){
                authMapper.CreateUserRole(role,user.getId());
            }
        }
    }

    //TODO: Verify
    @Override
    public void verify(String email) {
//        System.out.println(email);
        User user = authMapper.selectByEmail(email).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Email has not been found"));

        String verifiedCode = UUID.randomUUID().toString();
        if(authMapper.updateVerifiedCode(email,verifiedCode)){
            user.setVerifiedCode(verifiedCode);
        }else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "User cannot be verify...!");
        }
//        user.setVerifiedCode(UUID.randomUUID().toString());
        MailUtil.Meta<?> meta = MailUtil.Meta.builder()
                .to(email)
                .from(appMail)
                .subject("Account Verification")
                .templateUrl("auth/verify")
                .data(user)
                .build();
        try {
            mailUtil.send(meta);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }
    @Override
    public void checkVerify(String email, String verifiedCode) {
        User user = authMapper.selectByEmailAndVerifiedCode(email, verifiedCode)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User is not exist int the database"));
        if (!user.getIsVerified()){
            authMapper.verify(email, verifiedCode);
        }
    }
}
