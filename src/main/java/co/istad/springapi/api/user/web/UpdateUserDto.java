package co.istad.springapi.api.user.web;

import lombok.Builder;

@Builder
public record UpdateUserDto(String name , String gender) {
    
}
