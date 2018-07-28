Feature: 新建知识

  Background:
    Given I click the "KnowledgePage" button
    And I will see the "knowledge.ListPage" page

  @knowledge
  Scenario Outline:新建知识
    Given I click the "CreateKnowledge" button
    When I set the parameter "EventCode" with value "<EventCodeValue>"
    And I set the parameter "KnowledgeName" with value "<NameValue>"
    And I choose the "<Type>" from the "GroupComboBox"
    And I choose the "<TagType>" from the "TagComboBox"
    And I set the parameter "Describe" with value "<Describe>"
    And I set the parameter "Solution" with value "<Solution>"
    And I click the "Confirm" button
    Then I will see the <Result>

  @smoke @all
    Examples: 保存成功
      | EventCodeValue | NameValue | Type       | TagType | Describe   | Solution      | Result                              |
      | sxjautotest    | sunxj1    | sxj        | 200     | sunxjTest  | do nothing    | "sunxj1" in the "ElementList"       |
      | sxjautotest2   |           | sxj        |         | 第二个自动化测试用例 |               | "sxjautotest2" in the "ElementList" |
      | sxjautotest3   |           | sxj,sunxc2 |         | 第三个自动化测试用例 | do everything | "sxjautotest3" in the "ElementList" |

  @all
    Examples: 保存失败
      | EventCodeValue | NameValue | Type            | TagType | Describe | Solution | Result                  |
      |                |           |                 |         |          |          | error message "请输入事件代码" |
      |                |           | 11              |         | 22       |          | error message "请输入事件代码" |
      |                |           | 11              |         |          |          | error message "请输入事件代码" |
      |                |           |                 |         | 22       |          | error message "请输入事件代码" |
      | newCode        |           |                 |         | test     |          | error message "请选择分组"   |
      | newCode        |           |                 |         |          |          | error message "请选择分组"   |
      | newCode        |           | sunxj_knowledge |         |          |          | error message "请输入描述"   |

