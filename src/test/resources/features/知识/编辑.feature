Feature: 编辑知识
  Background:
    Given I click the "KnowledgePage" button
    And I will see the "knowledge.ListPage" page

  Scenario Outline:
    Given I click the edit button which name is "<NameValue>"
    When I set the parameter "EventCode" with value "<EventCodeValue>"
    And I set the parameter "KnowledgeName" with value "<NameValue>"
    And I choose the "<TagType>" from the "TagComboBox"
    And I set the parameter "Describe" with value "<Describe>"
    And I set the parameter "Solution" with value "<Solution>"
    And I click the "Confirm" button
    Then I will see the success message "<SuccessMsg>"

  Examples: 保存成功
    |EventCodeValue|NameValue|TagType|Describe          |Solution|SuccessMsg|
    |sxjautotest1  |sunxj1   |200    |the first autotest|        |保存成功      |
