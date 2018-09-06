Feature: 已生成报表

  Background:
    Given open the "report.ListPage" page for uri "/reports/"

  @report
  Scenario Outline:
    Given I click the detail which name is "<name>"
    Then I download the file

  @all @smoke
    Examples:
      | name          |
      | sunxjautotest |

