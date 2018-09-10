@dashboard @all @smoke
Feature:

  Background:
    Given I insert into table "DashBoardGroup" with "{'name':'sxjautotest','domain_id':'1','creator_id':'1','group':'default_DashBoardGroup'}"
    Then open the "dashboard.ListPage" page for uri "/dashboard/"

  Scenario Outline:
    Given the data name is "<name>" then i click the "分组" button
    Then I cancel selection "<group>" from the "GroupInput"
    And I click the "EnsureChangeGroupButton" button
    Then I will see the success message "请至少选择一个仪表盘分组"
    And I choose the "<group>" from the "GroupInput"
    Then I click the "EnsureChangeGroupButton" button
    Then I will see the success message "仪表盘更改分组成功"

    Examples:
      | name        | group                  |
      | sxjautotest | default_DashBoardGroup |