package co.istad.springapi.api.auth;

import co.istad.springapi.api.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface AuthMapper {
    @InsertProvider(type = AuthProvider.class, method = "buildRegisterSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean register(@Param("u") User user);

//    @InsertProvider(type = AuthProvider.class, method = "buildCreateUserRoleSql")
//    boolean createUserRole(@Param("UserId") Integer userId, @Param("roleId") Integer roleId);

    @Select("SELECT * FROM users WHERE email= #{email} AND is_deleted = FALSE")
    @Results(id = "authResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "is_verified", property = "isVerified"),
            @Result(column = "verified_code", property = "verifiedCode"),
            @Result(column = "id", property = "roles", many = @Many(select = "loadUserRoles"))
    })
    Optional<User> selectByEmail(@Param("email") String email);
    @SelectProvider(type = AuthProvider.class, method = "buildSelectByEmailAndVerifySql")
    @ResultMap(value = "authResultMap")
    Optional<User> selectByEmailAndVerifiedCode(@Param("email") String email, @Param("verifiedCode") String verifiedCode);

    @Select("SELECT * FROM users WHERE email=#{email} AND is_deleted = FALSE AND is_verified = TRUE")
    @ResultMap(value = "authResultMap")
    Optional<User> loadUserByUserName(@Param("email") String email);

    @SelectProvider(type = AuthProvider.class, method = "buildLoadUserRolesSql")
    List<Role> loadUserRoles(@Param("userId") Integer useId);

    @UpdateProvider(type = AuthProvider.class, method = "buildVerifiedSql")
    void verify(@Param("email") String email, @Param("verifiedCode") String verifiedCode);

    @UpdateProvider(type = AuthProvider.class, method = "buildUpdateVerifiedCodeSql")
    boolean updateVerifiedCode(@Param("email") String email, @Param("verifiedCode") String verifiedCode);
    @InsertProvider(value = AuthProvider.class, method = "buildInsertRoleSql")
    void CreateUserRole(@Param("roleId") Integer role, @Param("userId") Integer user);


}