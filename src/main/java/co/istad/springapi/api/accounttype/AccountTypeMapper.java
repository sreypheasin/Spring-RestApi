package co.istad.springapi.api.accounttype;

import lombok.Setter;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AccountTypeMapper {
    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> select();
    @Select("SELECT EXISTS(SELECT * FROM account_types WHERE id = #{id})")
    boolean isAccountTypeExist(@Param("id") Integer id);

    //TODO: create
    @InsertProvider(type = AccountTypeProvider.class, method = "buildInsertAccountTypeSql")
    void inertAccountTypeNew(@Param("u") AccountType accountType);
    //TODO: Se
    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectAccountTypeByIdSql")
    Optional<AccountType> selectAccountTypeById(@Param("id") Integer id);

    @UpdateProvider(type = AccountTypeProvider.class, method = "buildUpdateAccountTypeByIdSql")
    void updateAccountTypeById(@Param("a") AccountType accountType);

    @DeleteProvider(type = AccountTypeProvider.class, method = "buildDeleteAccountTypeSql")
    void deleteAccountTypeById(@Param("id") Integer id);




}
