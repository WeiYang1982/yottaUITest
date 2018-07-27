Feature: 知识分组

  Background:
    Given I click the "KnowledgePage" button
    And I will see the "knowledge.ListPage" page

  @knowledge
  Scenario Outline:
    Given I click the "changegroup" button which name is "<name>"
    And I choose the "<Type>" from the "GroupComboBox"
    And I click the "Ensure" button
    Then I will see the <Result>
  @all @smoke
    Examples:
      | name   | Type       | Result                 |
      | sunxj1 | A系统,sunxc2 | success message "保存成功" |
