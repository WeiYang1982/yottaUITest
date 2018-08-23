Feature: 修改分组

  Background:
    Given open the "report.ListPage" page for uri "/reports/"

  @report
  Scenario Outline:
    When the data name is "<name>" then i click the "分组" button
    Then I choose the "<group>" from the "ChangeGroup"
    And I click the "Ensure" button
    And I will see the success message "保存成功"

  @all @smoke
    Examples:
      | name        | group |
      | sxjautotest | A系统   |