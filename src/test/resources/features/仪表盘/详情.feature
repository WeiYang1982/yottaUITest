Feature: 仪表盘详情页

  Background:
    Given I will see the "dashboard.ListPage" page

  Scenario Outline:
    Given I click the detail which name is "<name>"
    Then I will see the "dashboard.DetailPage" page
    Then I set the parameter "TagName" with value "<tagName>"
    Then I click the "EnsureCreateTagButton" button

    Examples:
      | name        | tagName |
      | sxjautotest | first   |