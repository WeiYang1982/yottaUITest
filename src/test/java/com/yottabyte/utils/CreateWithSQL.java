package com.yottabyte.utils;

import com.yottabyte.config.ConfigManager;

import java.util.ArrayList;
import java.util.List;

public class CreateWithSQL {
    static ConfigManager config = new ConfigManager();
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
                    "VALUES ('" + userName + "' , '" + fullName + "' , '" + GetMD5.getMD5(password) + "' , 1 , '" + telephone + "', '" + email + "' , 'user');";
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
        String selectSql = "SELECT id FROM Role WHERE name = '" + roleName + "';";
        List list = JdbcUtils.query(selectSql);
        if (list.size() == 0) {
            String insertRole = "INSERT INTO `rizhiyi_system`.`Role` (`name`, `memo`, `domain_id`) " +
                    "VALUES ('" + roleName + "', '" + roleDes + "', 1);";
            JdbcUtils.insert(insertRole);
        }else {
            GetLogger.getLogger().info("已经存在指定角色");
        }
    }

    /**
     * 创建一个监控 默认使用事件数 低级告警级别 运行用户为admin 日志来源为所有日志
     * @param alertName 监控名称
     * @param alertGroups 监控分组名称
     */
    public static void alert(String alertName, List<String> alertGroups) {
        String selectSql = "SELECT id FROM Role WHERE name = '" + alertName + "';";
        List list = JdbcUtils.query(selectSql);
        if (list.size() == 0) {
            String insertAlert = "INSERT INTO `rizhiyi_system`.`Alert` " +
                    "(`name`, `domain_id`, `owner_id`, `check_interval`, `interval_unit`, `check_condition`, `enabled`, `alert_meta`, `level`, `source_groups`, `query`)" +
                    " VALUES ('" + alertName + "', 1, 1, 300, 1, '1|min|count|>|low:1', 1, '\"[]\"', -1, 'all', '*');";
            JdbcUtils.insert(insertAlert);
            for (String s : alertGroups) {
                String insertSql = "INSERT INTO `rizhiyi_system`.`ResourceGroup_Resource` (`resource_group_id`, `resource_id`) " +
                        " VALUES ((SELECT id FROM ResourceGroup WHERE name in ('" + s + "') and domain_id = 1) , " +
                        " (SELECT id FROM Alert WHERE name in ('" + alertName + "') and domain_id = 1));";
                JdbcUtils.insert(insertSql);
            }
        }else {
            GetLogger.getLogger().info("已经存在指定监控");
        }
    }

    /**
     * 创建一个资源分组
     * @param resourceGroupName 资源分组名称
     * @param types 数据库中的类型名称 可参考下面的数组resourceGroups
     * @param owners 角色名称 暂时没用到
     */
    public static void resourceGroup(String resourceGroupName, List<String> types, List<String> owners) {
        String[] resourceGroups = {"Alert", "DashBoardGroup", "SavedSchedule",
                "SavedSearch", "SourceGroup", "Trend", "Dictionary",
                "ParserRule", "Report", "AgentStatus", "Knowledge", "Topology", "Macro"};
        String selectSql = "SELECT id FROM ResourceGroup WHERE name = '" + resourceGroupName + "';";
        List list = JdbcUtils.query(selectSql);
        if (list.size() == 0) {
            for (String owner : owners) {
                for (String type : types) {
                    String insertSql = "";
                    if ("all".equalsIgnoreCase(type)) {
                        for (String t:resourceGroups) {
                            insertSql = "insert into ResourceGroup(domain_id, name, memo, creator_id, category) " +
                                    "select d.id, '" + resourceGroupName +
                                    "', 'Sql Create', a.id, '" + t + "' from Account a " +
                                    "left join Domain d on a.domain_id=d.id where d.name='ops' and a.name='" + config.get("username") + "';";
                            JdbcUtils.insert(insertSql);
                        }
                    }else {
                        insertSql = "insert into ResourceGroup(domain_id, name, memo, creator_id, category) " +
                                "select d.id, '" + resourceGroupName +
                                "', 'Sql Create', a.id, '" + type + "' from Account a " +
                                "left join Domain d on a.domain_id=d.id where d.name='ops' and a.name='" + config.get("username") + "';";
                        JdbcUtils.insert(insertSql);
                    }
                }
            }
        }else {
            GetLogger.getLogger().info("已经存在指定资源分组");
        }

    }

    public static void main(String args[]) {
//        String json = "{'name':'AutoTestForAlert','fullname':'','email':'AutoTestForAlert@yottabyte.cn','telephone':'','password':'qqqqq11111','userGroup':['admin']}";
        String json = "{'name':'AutoTestResourceGroup','type':['Alert'],'owner':['admin']}";


        JsonStringPaser paser = new JsonStringPaser();
        Object pars[] = paser.jsonParser(json);
        GetElementFromPage.getMethod("com.yottabyte.utils.CreateWithSQL", "resourceGroup", pars);

    }
}
