package com.yottabyte.utils;

import com.yottabyte.stepDefs.BeforeRunning;

import java.util.List;

public class DeleteWithSQL {
    /**
     * 删除用户以及对应分组信息
     * @param userNames 用户名
     */
    public static void deleteAUser(List<String> userNames) {
        BeforeRunning run = new BeforeRunning();
        StringBuffer insertSql = new StringBuffer("SELECT id FROM Account WHERE name in (");
        for (String s:userNames) {
            insertSql.append("'" + s + "',");
        }
        String selectSql = insertSql.toString().substring(0, insertSql.length() - 1) + ")";
        List list = JdbcUtils.query(selectSql);
        run.delete("AccountGroup_Account", "account_id", list );
        run.delete("Account", "name", userNames );
    }

}
