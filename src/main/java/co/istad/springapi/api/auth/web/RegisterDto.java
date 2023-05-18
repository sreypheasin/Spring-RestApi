package co.istad.springapi.api.auth.web;

import co.istad.springapi.api.user.validator.email.EmailUnique;
import co.istad.springapi.api.user.validator.password.Password;
import co.istad.springapi.api.user.validator.role.RoleIdConstrain;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegisterDto(@NotBlank(message = "Email is required...")
                          @EmailUnique
                          String email,
                          @NotBlank(message = "Password is required...")
                          @Password
                          String password,
                          @NotBlank(message = "Confirmed password is required...")
                          @Password
                            String confirmedPassword,
                          @NotNull(message ="Role is required...")
                          @RoleIdConstrain
                          List<Integer> roleIds
                          ) {
}
