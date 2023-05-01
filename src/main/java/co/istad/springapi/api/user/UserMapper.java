package co.istad.springapi.api.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserMapper {
    // Insert
    @InsertProvider(type = UserProvider.class,method = "buildInsertSql")
    // get generic from database
    @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")

    void insert(@Param("u")User user);

    @Results(id = "userResultMap", value = {
            @Result(column = "is_student", property = "isStudent"),
            @Result(column = "student_card_id", property = "studentCardId")
    })
    @SelectProvider(type = UserProvider.class,method = "buildSelectByIdSql")
    Optional<User> selectById(@Param("id")Integer id);

    @ResultMap("userResultMap")
    @SelectProvider(type= UserProvider.class, method = "buildSelectSql")
    List <User> select();

    //delete
    @Select("SELECT EXISTS(SELECT * FROM users WHERE id = #{id})")
    boolean existsById(@Param("id") Integer id);

    @DeleteProvider(type = UserProvider.class,method="buildDeleteByIdSql")
    void deleteById(@Param("id") Integer id);

    @UpdateProvider(type = UserProvider.class,method = "buildUpdateIsDeletedByIdSql")
    void updateIsDeletedStatusById(Integer id, boolean status);

    @UpdateProvider(type = UserProvider.class ,method = "buildUpdateByIdSql")
    void updateById(@Param("u")User user);
}
