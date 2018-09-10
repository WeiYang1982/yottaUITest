@dashboard
Feature: 新建仪表盘

  Background:
    Given I insert into table "DashBoardGroup" with "{'name':'sxjautotest','domain_id':'1','creator_id':'1','group':'default_DashBoardGroup'}"
    Then I delete from "DashBoardGroup" where "{'name':'autotest'}"
    Then open the "dashboard.ListPage" page for uri "/dashboard/"

  Scenario Outline:
    Given I click the "CreateButton" button
    Then I set the parameter "DashBoardName" with value "<name>"
    Then I choose the "<group>" from the "DashBoardGroup"
    Then I click the "EnsureCreateButton" button
    Then I will see the <result>

  @all @smoke
    Examples: 新建成功
      | name     | group                  | result                    |
      | autotest | default_DashBoardGroup | success message "仪表盘新建成功" |

  @all
    Examples: 新建失败
      | name               | group                  | result                                               |
      |                    |                        | success message "仪表盘名称不能为空"                          |
      | sxjtest            |                        | success message "请至少选择一个仪表盘分组"                       |
      | #@$@               |                        | success message "名称格式有误, 仅支持汉字，数字，字母，中划线及下划线"        |
      | 我是超长的名称我有十八个字哦不信你查 |                        | success message "名称字数超过限制, 字符最多不能超过32位 (单个汉字为2字符占比)" |
      | sxjautotest        | default_DashBoardGroup | error message "仪表盘分组名已存在\n错误码: FE_540"               |

