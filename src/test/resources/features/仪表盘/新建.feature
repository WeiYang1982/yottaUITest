Feature: 新建仪表盘

  Background:
    Given I will see the "dashboard.ListPage" page

  @dashboard
  Scenario Outline:
    Given I click the "CreateButton" button
    Then I set the parameter "DashBoardName" with value "<name>"
    Then I choose the "<group>" from the "DashBoardGroup"
    Then I click the "EnsureCreateButton" button
    Then I will see the <result>

  @all @smoke
    Examples: 新建成功
      | name        | group                  | result                    |
      | sxjautotest | default_DashBoardGroup | success message "仪表盘新建成功" |

  @all
    Examples: 新建失败
      | name        | group                  | result                                 |
      |             |                        | success message "仪表盘名称不能为空"            |
      | sxjtest     |                        | success message "请至少选择一个仪表盘分组"         |
      | sxjautotest | default_DashBoardGroup | error message "仪表盘分组名已存在\n错误码: FE_540" |

