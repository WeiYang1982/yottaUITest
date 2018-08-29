package com.yottabyte.utils;

import java.util.List;

public class CreateWithSQL {
    /**
     *  创建一个用户 并在分组表中增加相应信息
     * @param userName 用户名
     * @param fullName 全名
     * @param email 邮箱
     * @param telephone 电话
     * @param password 密码（自动按照md5加密）
     * @param userGroup 用户分组 可能会多个
     */
    public static void user(String userName, String fullName, String email, String telephone, String password, List<String> userGroup) {
        String selectSql = "SELECT id FROM Account WHERE name = '" + userName + "';";
        List list = JdbcUtils.query(selectSql);
        if (list.size() == 0) {
            String insertUserSql = "INSERT INTO `rizhiyi_system`.`Account` (`name`,`full_name`,`passwd`, `domain_id`,`phone`,`email` , `access_type` ) " +
                    "VALUES ('" + userName + "' , '" + fullName + "' , '" + getMD5.getMD5(password) + "' , 1 , '" + telephone + "', '" + email + "' , 'user');";
            JdbcUtils.insert(insertUserSql);
            for (String s:userGroup) {
                String insertUserGroupSql = "INSERT INTO `rizhiyi_system`.`AccountGroup_Account` (`account_group_id`, `account_id`) VALUES " +
                        "((SELECT id FROM AccountGroup as g WHERE g.domain_id = 1 AND g.name = '" + s + "'), " +
                        "(SELECT id FROM Account as a WHERE a.name = '" + userName + "'));";
                JdbcUtils.insert(insertUserGroupSql);
            }
        }
    }
}
