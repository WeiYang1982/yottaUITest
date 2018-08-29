package com.yottabyte.stepDefs;

import com.yottabyte.utils.GetLogger;
import com.yottabyte.utils.JdbcUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.util.List;

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
        }else {
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
}
