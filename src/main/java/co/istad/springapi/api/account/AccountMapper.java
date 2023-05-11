package co.istad.springapi.api.account;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountMapper {
    //TODO: find all
    @Results(
            id = "accountResultMap",
            value = {
                    @Result(column = "account_no",property = "accountNo"),
                    @Result(column = "account_name",property = "accountName"),
                    @Result(column = "phone_number",property = "phoneNumber"),
                    @Result(column = "transfer_limit", property = "transferLimit"),
//                    @Result(column = "account_type",property = "accountType")

            }
    )
    @SelectProvider(type= AccountProvider.class, method = "buildSelectAllSql")
    List<Account> select();
}
