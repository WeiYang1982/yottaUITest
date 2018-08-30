Feature: 编辑知识

  Background:
    Given I insert into table "Knowledge" with "{'name':'sunxj1','code':'sunxj1','creator_id':'1','creator_name':'owner','description':'3','domain_id':'1','solution':'test','group':'default_Knowledge'}"
    Then I click the "KnowledgePage" button
    And I will see the "knowledge.ListPage" page

  @knowledge
  Scenario Outline:
    Given the data name is "<NameValue>" then i click the "编辑" button
    When I set the parameter "EventCode" with value "<EventCodeValue>"
    And I set the parameter "KnowledgeName" with value "<NameValue>"
    And I choose the "<TagType>" from the "TagComboBox"
    And I set the parameter "Describe" with value "<Describe>"
    And I set the parameter "Solution" with value "<Solution>"
    And I click the "Confirm" button
    Then I will see the <Result>

  @all @smoke
    Examples: 保存成功
      | EventCodeValue | NameValue | TagType | Describe           | Solution | Result                 |
      | sxjautotest1   | sunxj1    | 200     | the first autotest | test     | success message "保存成功" |

  @all
    Examples: 保存失败
      | EventCodeValue | NameValue | TagType | Describe | Solution | Result                  |
      |                | sunxj1    |         |          |          | error message "请输入事件代码" |
      | sxjautotest1   | sunxj1    |         |          |          | error message "请输入描述"   |
