Feature:

  Background:
    Given open the "report.ListPage" page for uri "/reports/"

  @report
  Scenario Outline:
    When the data name is "<name>" then i click the "删除" button

  @all @smoke
    Examples:
      | name  |
      | 自动化测试 |