package co.istad.springapi.api.user.web;

public record UserDto(String name,
                      String gender,
                      String studentCardId,
                      Boolean isStudent) {

}
