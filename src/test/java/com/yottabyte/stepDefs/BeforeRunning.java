package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetLogger;
import com.yottabyte.utils.JdbcUtils;
import com.yottabyte.utils.JsonStringPaser;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sunxj
 */
public class BeforeRunning {

    /**
     * 删除数据
     *
     * @param tableName  表名
     * @param columnName 待删除的列名
     * @param fieldList  待删除的字段名
     */
    @And("^I delete from \"([^\"]*)\" where \"([^\"]*)\" in \"([^\"]*)\"$")
    public void delete(String tableName, String columnName, List<String> fieldList) {
        if (fieldList.size() > 0) {
            StringBuffer deleteSql = new StringBuffer("delete from " + tableName + " where " + columnName + " in (");
            for (String field : fieldList) {
                deleteSql.append("'" + field + "',");
            }
            String sql = deleteSql.toString().substring(0, deleteSql.length() - 1) + ")";
            JdbcUtils.delete(sql);
        } else {
            GetLogger.getLogger().info("filedList为空，不需要删除.");
        }
    }

    /**
     * 新增数据，若第一列的字段值存在，则不添加
     *
     * @param tableName  表名
     * @param columnList 列名
     * @param fieldList  字段值
     */
    @Then("^I insert into table \"([^\"]*)\" which columnName in \"([^\"]*)\" and values in \"([^\"]*)\"$")
    public void insert(String tableName, List<String> columnList, List<String> fieldList) {
        // 查找该条数据是否存在
        String querySql = "select id from " + tableName + " where " + columnList.get(0) + " ='" + fieldList.get(0) + "'";
        List<String> resultList = JdbcUtils.query(querySql);

        // 若不存在该条数据则添加
        if (resultList.size() == 0) {
            StringBuffer insertSql = new StringBuffer("insert into " + tableName + " (");
            // 遍历列名
            for (int i = 0; i < columnList.size(); i++) {
                if (i != columnList.size() - 1) {
                    insertSql.append(columnList.get(i) + ",");
                } else {
                    insertSql.append(columnList.get(i) + ") values (");
                }
            }
            // 遍历字段值
            for (int i = 0; i < fieldList.size(); i++) {
                if (i != fieldList.size() - 1) {
                    insertSql.append("'" + fieldList.get(i) + "',");
                } else {
                    insertSql.append("'" + fieldList.get(i) + "')");
                }
            }
            System.out.println(insertSql.toString());
            JdbcUtils.insert(insertSql.toString());
        }
    }

    /**
     * 插入数据（若传入json中包含group，则添加分组信息）
     *
     * @param tableName
     * @param values
     */
    @Given("^I insert into table \"([^\"]*)\" with \"([^\"]*)\"$")
    public void insertWithGroup(String tableName, String values) {
        Map<String, Object> map = JsonStringPaser.json2Stirng(values);

        // 查询是否存在该条数据
        String querySql = "select id from " + tableName + " where ";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            querySql += entry.getKey() + " = '" + entry.getValue() + "'";
            break;
        }

        // 不存在该条数据时才添加
        if (JdbcUtils.query(querySql).size() == 0) {
            StringBuffer insertSql = new StringBuffer("insert into " + tableName + " (");
            if (map.containsKey("group")) {
                // 获取分组名称
                String groupName = map.get("group").toString();
                map.remove("group");
                String finalSql = this.assembleSql(map, insertSql);
                int id = JdbcUtils.insert(finalSql);
                this.insertIntoGroup(groupName, id);
            } else {
                String finalSql = this.assembleSql(map, insertSql);
                JdbcUtils.insert(finalSql);
            }
        }
    }

    /**
     * 插入数据以及分组信息
     *
     * @param groupName
     * @param moduleId
     */
    private void insertIntoGroup(String groupName, int moduleId) {
        String querySql = "select id from ResourceGroup where domain_id=1 and name='" + groupName + "'";
        int groupId = Integer.parseInt(JdbcUtils.query(querySql).get(0));
        String insertSql = "insert into ResourceGroup_Resource (resource_id,resource_group_id) values(" + moduleId + "," + groupId + ")";
        JdbcUtils.insert(insertSql);
    }

    /**
     * 删除数据以及分组信息
     *
     * @param tableName
     * @param values
     */
    @Given("^I delete from \"([^\"]*)\" where \"([^\"]*)\"$")
    public void deleteWithGroup(String tableName, String values) {
        Map<String, Object> map = JsonStringPaser.json2Stirng(values);
        // 资源信息
        List<String> valueList = this.mapValue2List(map, "name");
        StringBuffer queryResourceIdList = new StringBuffer("select id from " + tableName + " where name in (");
        List<String> idList = JdbcUtils.query(this.assembleSql(valueList, queryResourceIdList.toString()));
        // 不存在该资源则返回
        if (idList.size() == 0)
            return;
        String deleteResourceSql = "delete from " + tableName + " where id in (";
        // 删除资源
        JdbcUtils.delete(assembleSql(idList, deleteResourceSql));
    }

    /**
     * 组装sql
     *
     * @param map
     * @param sql
     * @return
     */
    private String assembleSql(Map<String, Object> map, StringBuffer sql) {
        StringBuffer subSql = new StringBuffer(" values (");
        int i = 0;
        for (String key : map.keySet()) {
            if (i == map.size() - 1) {
                sql.append(key + ")");
                subSql.append("'" + map.get(key) + "')");
            } else {
                sql.append(key + ",");
                subSql.append("'" + map.get(key) + "',");
            }
            i++;
        }
        return sql.toString() + subSql.toString();
    }

    private String assembleSql(List list, String sql) {
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                sql += "'" + list.get(i) + "',";
            } else {
                sql += "'" + list.get(i) + "')";
            }
        }
        return sql;
    }

    /**
     * map的值转化为list
     *
     * @param map
     * @param key
     * @return
     */
    private List<String> mapValue2List(Map map, String key) {
        List<String> list = new ArrayList<>();
        if (map.get(key).toString().contains(",")) {
            list = (List<String>) map.get(key);
        } else {
            list.add(map.get(key).toString());
        }
        return list;
    }


}
