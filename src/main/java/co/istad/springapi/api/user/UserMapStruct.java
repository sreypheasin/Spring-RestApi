package co.istad.springapi.api.user;

import co.istad.springapi.api.user.web.CreateUserDto;
import co.istad.springapi.api.user.web.UpdateUserDto;
import co.istad.springapi.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    User createUserDtoToUser(CreateUserDto createUserDto);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    User updateUserDtoToUser(UpdateUserDto updateUserDto);

    PageInfo<UserDto> userPageInfoToUserDtoPageInfo(PageInfo<User> userPageInfo);


}
