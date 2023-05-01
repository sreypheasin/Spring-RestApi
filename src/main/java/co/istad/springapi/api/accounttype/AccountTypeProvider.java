package co.istad.springapi.api.accounttype;


import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    private static final String tableName = "account_types";
    //TODO: select all
    public String buildSelectSql(){
        return  new SQL(){{
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }
    //TODO: select by id
    public String buildSelectAccountTypeByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM( tableName);
            WHERE("id = #{id}");
        }}.toString();
    }
    //TODO: create new
    public String buildInsertAccountTypeSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name", "#{u.name}");
        }}.toString();
    }
    //TODO: update
    public String buildUpdateAccountTypeByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("name = #{a.name}");
            WHERE("id = #{a.id}");
        }}.toString();
    }
    //TODO: delete
    public String buildDeleteAccountTypeSql(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();
    }

}
