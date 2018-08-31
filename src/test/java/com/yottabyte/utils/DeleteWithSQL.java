package com.yottabyte.utils;

import com.yottabyte.stepDefs.BeforeRunning;

import java.util.ArrayList;
import java.util.List;

public class DeleteWithSQL {
    static BeforeRunning run = new BeforeRunning();

    /**
     * 删除用户以及对应分组信息
     * @param userNames 用户名
     */
    public static void user(List<String> userNames) {
        StringBuffer searchSql = new StringBuffer("SELECT id FROM Account WHERE name in (");
        for (String s:userNames) {
            searchSql.append("'" + s + "',");
        }
        String selectSql = searchSql.toString().substring(0, searchSql.length() - 1) + ")";
        List list = JdbcUtils.query(selectSql);
        run.delete("AccountGroup_Account", "account_id", list );
        run.delete("Account", "name", userNames );
    }

    public static void userGroup(List<String> userGroups) {
        StringBuffer searchSql = new StringBuffer("SELECT id FROM AccountGroup WHERE name in (");
        for (String s:userGroups) {
            searchSql.append("'" + s + "',");
        }
        String selectSql = searchSql.toString().substring(0, searchSql.length() - 1) + ")";
        List list = JdbcUtils.query(selectSql);
        run.delete("AccountGroup_Role", "account_group_id", list );
        run.delete("AccountGroup", "name", userGroups );
    }


    public static void role(List<String> roleNames) {
        StringBuffer searchSql = new StringBuffer("SELECT id FROM Role WHERE name in (");
        for (String s:roleNames) {
            searchSql.append("'" + s + "',");
        }
        String selectSql = searchSql.toString().substring(0, searchSql.length() - 1) + ")";
        System.out.println(selectSql);
        List list = JdbcUtils.query(selectSql);
        run.delete("Role_Permission", "role_id", list );
        run.delete("Role", "name", roleNames );
    }

    public static void alert(List<String> alertNames) {
        StringBuffer searchSql = new StringBuffer("SELECT id FROM Alert WHERE name in (");
        for (String s:alertNames) {
            searchSql.append("'" + s + "',");
        }
        String selectSql = searchSql.toString().substring(0, searchSql.length() - 1) + ")";
        System.out.println(selectSql);
        List list = JdbcUtils.query(selectSql);
        run.delete("ResourceGroup_Resource", "resource_id", list );
        run.delete("Alert", "name", alertNames );
    }






    public static void main(String args[]) {
        List<String> list = new ArrayList<>();
        list.add("212");
        alert(list);
    }
}
