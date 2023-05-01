package co.istad.springapi.api.user;

import co.istad.springapi.api.user.web.CreateUserDto;
import co.istad.springapi.api.user.web.UpdateUserDto;
import co.istad.springapi.api.user.web.UserDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;

    // create
    @Override
    public UserDto createUserDto(CreateUserDto createUserDto) {
        User user = userMapStruct.createUserDtoToUser(createUserDto);
        userMapper.insert(user);
        log.info("User = {}", user.getId());

//        return userMapStruct.userToUserDto(user);
        return this.findUserById(user.getId());
    }

    //find
    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with %d id not found", id)));
        return userMapStruct.userToUserDto(user);
    }

    @Override
    public PageInfo<UserDto> findAllUsers(int page, int limit) {
//        PageHelper.startPage(page,limit);
        PageInfo<User> userPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(userMapper::select);

        return userMapStruct.userPageInfoToUserDtoPageInfo(userPageInfo);
    }

    // delete
    @Override
    public Integer deleteUserById(Integer id) {
        boolean isFound = userMapper.existsById(id);
        if (isFound) {
            userMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with %d id not found", id));
    }

    //Update
    @Override
    public Integer updateIsDeletedStatusById(Integer id, boolean status) {
        boolean isExited = userMapper.existsById(id);
        if (isExited) {
            userMapper.updateIsDeletedStatusById(id, status);
            return id;
        }
        return null;
    }

    @Override
    public UserDto updateUserById(Integer id, UpdateUserDto updateUserDto) {
        if (userMapper.existsById(id)) {
            User user = userMapStruct.updateUserDtoToUser(updateUserDto);
            user.setId(id);
            userMapper.updateById(user);
            return this.findUserById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with %d id not found", id));
    }
}







