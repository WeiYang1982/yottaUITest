Feature: 新建拓扑图

  Background:
    Given I delete from "Topology" where "{'name':['sxjautotest','测试中文名称'],'group':'default_Topology'}"
    And I insert into table "Topology" with "{'name':'sunxjautotest','domain_id':'1','creator_id':'1','category':'0','group':'default_Topology'}"
    Then open the "topology.ListPage" page for uri "/topology/"

  @all
  Scenario Outline:
    Given I click the "CreateButton" button
    Then I set the parameter "NameInput" with value "<name>"
    Then I choose the "<group>" from the "GroupInput"
    Then I click the "EnsureButton" button
    Then I will see the <message>

  @smoke
    Examples: 成功新建拓扑图
      | name        | group            | message                |
      | sxjautotest | default_Topology | success message "创建成功" |
      | 测试中文名称      | default_Topology | success message "创建成功" |

    Examples: 新建拓扑图失败
      | name             | group            | message                                |
      |                  |                  | error message "请输入拓扑图名称"               |
      | test             |                  | error message "请选择拓扑图分组"               |
      | 我是超出长度的名称哦我有十六个字 | default_Topology | error message "名称太长, 请输入小于32个字符的名称"    |
      | sunxjautotest    | default_Topology | error message "拓扑图名字已经存在\n错误码: FE_621" |
