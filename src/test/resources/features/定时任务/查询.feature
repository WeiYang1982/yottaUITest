Feature:查询定时任务

  Background:
    Given I click the "TimedTaskPage" button
    Then I will see the "timedTask.ListPage" page

  Scenario Outline: 根据定时任务分组进行查询
    Given I choose the "<group>" from the "GroupList"
    Then I will see the list of "" contains "" or I see the "Names" contains "<name>"

    Examples:
      | group | name    |
      | sxj   | sxjtest |

  Scenario Outline: 根据搜索内容进行查询
    Given I set the parameter "SearchInput" with value "<inputName>"
    Then I will see the list of "" contains "" or I see the "Names" contains "<inputName>"

    Examples:
      | inputName |
      | sxj       |
