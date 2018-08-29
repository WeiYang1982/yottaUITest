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
        StringBuffer insertSql = new StringBuffer("SELECT id FROM Account WHERE name in (");
        for (String s:userNames) {
            insertSql.append("'" + s + "',");
        }
        String selectSql = insertSql.toString().substring(0, insertSql.length() - 1) + ")";
        List list = JdbcUtils.query(selectSql);
        run.delete("AccountGroup_Account", "account_id", list );
        run.delete("Account", "name", userNames );
    }

    public static void userGroup(List<String> userGroups) {
        StringBuffer insertSql = new StringBuffer("SELECT id FROM AccountGroup WHERE name in (");
        for (String s:userGroups) {
            insertSql.append("'" + s + "',");
        }
        String selectSql = insertSql.toString().substring(0, insertSql.length() - 1) + ")";
        List list = JdbcUtils.query(selectSql);
        run.delete("AccountGroup_Role", "account_group_id", list );
        run.delete("AccountGroup", "name", userGroups );
    }


    public static void main(String args[]) {
        List<String> list = new ArrayList<>();
        list.add("dsa");
        userGroup(list);
    }
}
