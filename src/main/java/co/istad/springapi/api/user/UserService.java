package co.istad.springapi.api.user;

import co.istad.springapi.api.user.web.CreateUserDto;
import co.istad.springapi.api.user.web.UpdateUserDto;
import co.istad.springapi.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Select;

public interface UserService {
    UserDto createUserDto(CreateUserDto createUserDto);
    UserDto findUserById(Integer id);
    PageInfo<UserDto> findAllUsers(int page, int limit);
    Integer deleteUserById(Integer id);
    Integer updateIsDeletedStatusById(Integer id,boolean status);
    UserDto updateUserById(Integer id, UpdateUserDto updateUserDto);

}
