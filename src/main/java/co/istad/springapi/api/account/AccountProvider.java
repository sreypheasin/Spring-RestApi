package co.istad.springapi.api.account;

import org.apache.ibatis.jdbc.SQL;

public class AccountProvider {
    private final String tableName = "accounts";
    public String buildSelectAllSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }
}
