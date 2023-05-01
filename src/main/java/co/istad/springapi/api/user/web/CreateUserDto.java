package co.istad.springapi.api.user.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
        @NotBlank(message = "Name is required...!") String name,
        @NotBlank(message = "gender is required") String gender,
        String oneSignalId,
        String studentCardId,
        @NotNull (message = "you need to confirm, are you a student? ")Boolean isStudent) {
}
