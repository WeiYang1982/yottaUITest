package com.yottabyte.utils;

import java.util.ArrayList;
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
        }else {
            GetLogger.getLogger().info("已经存在指定用户");
        }
    }

    /**
     * 创建一个用户分组 涉及到AccountGroup AccountGroup_Role表
     * @param userGroupName 用户分组名称
     * @param owners 用户分组所属者  -- 涉及到的表太多暂时不写该值
     * @param roles 用户分组角色 AccountGroup_Role表
     */
    public static void userGroup(String userGroupName, List<String> owners, List<String> roles) {
        String selectSql = "SELECT id FROM AccountGroup WHERE name = '" + userGroupName + "';";
        List list = JdbcUtils.query(selectSql);
        if (list.size()==0) {
            String insertUserGroup = "INSERT INTO `rizhiyi_system`.`AccountGroup` (`domain_id` , `name` , `memo`) VALUES (1, '" + userGroupName + "' , '');";
            JdbcUtils.insert(insertUserGroup);
            for (String s : roles) {
                String insertUserGroupRoleSql = "INSERT INTO `rizhiyi_system`.`AccountGroup_Role` (`account_group_id`, `role_id`) " +
                        "VALUES ( (SELECT id FROM `rizhiyi_system`.`AccountGroup` WHERE name in ('" + userGroupName + "')) , " +
                        "(SELECT id FROM `rizhiyi_system`.`Role` WHERE name in ('" + s + "') AND domain_id = 1) );";
                JdbcUtils.insert(insertUserGroupRoleSql);
            }
        }else {
            GetLogger.getLogger().info("已经存在指定用户分组");
        }

    }

    /**
     * 创建一个角色
     * @param roleName 角色名称
     * @param roleDes 角色描述
     * @param roleGroups 同时创建的资源分组  涉及到的表太多，暂时不使用
     */
    public static void role(String roleName, String roleDes, List<String> roleGroups) {
        String selectSql = "SELECT id FROM Role WHERE name = '" + roleGroups + "';";
        List list = JdbcUtils.query(selectSql);
        if (list.size() == 0) {
            String insertRole = "INSERT INTO `rizhiyi_system`.`Role` (`name`, `memo`, `domain_id`) " +
                    "VALUES ('" + roleName + "', '" + roleDes + "', 1);";
            JdbcUtils.insert(insertRole);
        }else {
            GetLogger.getLogger().info("已经存在指定角色");
        }
    }


    public static void main(String args[]) {
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list.add("admin");
        list1.add("admin");
        role("dsa", "", list1);
    }
}
