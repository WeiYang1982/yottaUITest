Feature: 知识分组

  Background:
    Given I insert into table "Knowledge" with "{'name':'sunxj1','code':'sunxj1','creator_id':'1','creator_name':'owner','description':'3','domain_id':'1','solution':'test','group':'default_Knowledge'}"
    And open the "knowledge.ListPage" page for uri "/knowledge/"

  @knowledge
  Scenario Outline:
    Given I click the "changegroup" button which name is "<name>"
    And I choose the "<Type>" from the "GroupComboBox"
    And I click the "Ensure" button
    Then I will see the <Result>
  @all @smoke
    Examples:
      | name   | Type           | Result                 |
      | sunxj1 | hunter_roles_m | success message "保存成功" |
