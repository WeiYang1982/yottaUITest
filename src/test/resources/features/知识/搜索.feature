Feature: 切换资源分组

  Background:
    Given I insert into table "Knowledge" with "{'name':'sunxj1','code':'sunxj1','creator_id':'1','creator_name':'owner','description':'3','domain_id':'1','solution':'test','group':'default_Knowledge'}"
    And open the "knowledge.ListPage" page for uri "/knowledge/"

  @knowledge
  Scenario Outline:
    Then I choose the "<GroupList>" from the "GroupList"
    And I set the parameter "SearchInput" with value "<Search>"
    Then I will see the list of "<TableHeader>" contains "<GroupList>" or I see the "SearchList" contains "<Search>"

  @smoke @all
    Examples:
      | GroupList         | Search | TableHeader |
      | default_Knowledge |        | 分组          |
      |                   | sunxj1 |             |

