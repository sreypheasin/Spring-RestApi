package co.istad.springapi.api.user.web;

import co.istad.springapi.api.base.BaseRest;
import co.istad.springapi.api.user.User;
import co.istad.springapi.api.user.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserRestController {
    private  final UserService userService;
    //
    @GetMapping
    public  BaseRest<?> findAllUsers(@RequestParam(name="page",required = false,defaultValue = "1") int page,
                                     @RequestParam(name= "limit",required = false,defaultValue = "20")int limit){
        PageInfo<UserDto> userDtoPageInfo = userService.findAllUsers(page,limit);
        return
                BaseRest.builder()
                        .status(true)
                        .code(HttpStatus.OK.value())
                        .message("Recode Have find.")
                        .timestamp(LocalDateTime.now())
                        .data(userDtoPageInfo)
                        .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id){
        UserDto userDto = userService.findUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())  //200
                .message("User has been Found  ")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody @Valid CreateUserDto createUserDto){
        log.info("Dto ={}",createUserDto);
        UserDto userDto = userService.createUserDto(createUserDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .message("Create user success")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
    // delete
    @DeleteMapping("/{id}")
    public BaseRest<?> deleteById(@PathVariable Integer id){
        Integer deleted = userService.deleteUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())  //200
                .message("User has been Delete ")
                .timestamp(LocalDateTime.now())
                .data(deleted)
                .build();
    }


    @PutMapping("/{id}/is-deleted")
    public BaseRest<?> updateIsDeletedStatusById(@PathVariable Integer id, @RequestBody IsDeletedDto dto) {
        Integer deletedId = userService.updateIsDeletedStatusById(id, dto.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been deleted successfully.")
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> updateUserById(@PathVariable("id") Integer id, @RequestBody UpdateUserDto updateUserDto){
        UserDto userDto = userService.updateUserById(id, updateUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been updated successfully.")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }


}
