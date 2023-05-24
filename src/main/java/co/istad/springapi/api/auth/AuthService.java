package co.istad.springapi.api.auth;

import co.istad.springapi.api.auth.web.AuthDto;
import co.istad.springapi.api.auth.web.LogInDto;
import co.istad.springapi.api.auth.web.RegisterDto;

public interface AuthService {
    AuthDto login(LogInDto logInDto);
    void register(RegisterDto registerDto);

    void verify(String email);
    void checkVerify(String email, String verifiedCode);
}
