package com.yottabyte.utils;

import com.yottabyte.stepDefs.BeforeRunning;

import java.util.ArrayList;
import java.util.List;

public class DeleteWithSQL {
    static BeforeRunning run = new BeforeRunning();

    /**
     * 删除用户以及对应分组信息
     *
     * @param userNames 用户名
     */
    public static void user(List<String> userNames) {
        List list = select(userNames, "Account");
        run.delete("AccountGroup_Account", "account_id", list);
        run.delete("Account", "name", userNames);
    }

    public static void userGroup(List<String> userGroups) {
        List list = select(userGroups, "AccountGroup");
        run.delete("AccountGroup_Role", "account_group_id", list);
        run.delete("AccountGroup", "name", userGroups);
    }


    public static void role(List<String> roleNames) {
        List list = select(roleNames, "Role");
        run.delete("Role_Permission", "role_id", list);
        run.delete("Role", "name", roleNames);
    }

    public static void alert(List<String> alertNames) {
        List list = select(alertNames, "Alert");
        run.delete("ResourceGroup_Resource", "resource_id", list);
        run.delete("Alert", "name", alertNames);
    }

    public static void resourceGroup(List<String> resourceGroupName) {
        run.delete("ResourceGroup", "name", resourceGroupName);
    }


    private static List select(List<String> userNames, String tableName) {
        StringBuffer searchSql = new StringBuffer("SELECT id FROM " + tableName + " WHERE name in (");
        for (String s : userNames) {
            searchSql.append("'" + s + "',");
        }
        String selectSql = searchSql.toString().substring(0, searchSql.length() - 1) + ")";
        List list = JdbcUtils.query(selectSql);
        return list;
    }


    public static void main(String args[]) {
        List<String> list = new ArrayList<>();
        list.add("test1122");
        resourceGroup(list);
    }
}
