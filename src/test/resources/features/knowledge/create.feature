Feature: 
  Background: 
    Given I click the "KnowledgePage" button
    And I will see the "knowledge.ListPage" page
  Scenario Outline:新建知识
    Given I click the "CreateKnowledge" button
    When I set the parameter "EventCode" with value "<EventCodeValue>"
    And I set the parameter "KnowledgeName" with value "<NameValue>"
    And I choose the "<Type>" from the "GroupComboBox"
    And I choose the "<TagType>" from the "TagComboBox"
    And I set the parameter "Describe" with value "<Describe>"
    And I set the parameter "Solution" with value "<Solution>"
    And I set the bandwidth to "1"
    And I click the "Confirm" button

    Then I will see the success message "保存成功"

  Examples: 保存成功
    |EventCodeValue|NameValue|Type|TagType|Describe|Solution|
    |504           |sunxj1   |11  |测试标签|sunxjTest|do nothing|
    |事件代码        |sunxj2   |资源组-tyf|    |第二个自动化测试用例         |           |

  Examples: 保存失败，并对提示语进行校验
    |EventCodeValue|NameValue|Type|TagType|Describe|Solution|
    |              |         |    |       |        |        |
