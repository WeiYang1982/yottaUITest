@topology
Feature: 重命名拓扑图

  Background:
    Given I insert into table "Topology" with "{'name':'sxjautotest','domain_id':'1','creator_id':'1','category':'0','group':'default_Topology'}"
    Given I insert into table "Topology" with "{'name':'sunxjautotest','domain_id':'1','creator_id':'1','category':'0','group':'default_Topology'}"
    Then open the "topology.ListPage" page for uri "/topology/"

  @all
  Scenario Outline:
    Given the data name is "<name>" then i click the "重命名" button
    Then I set the parameter "NameInput" with value "<newName>"
    Then I click the "EnsureButton" button
    Then I will see the <message>

  @smoke
    Examples: 重命名成功
      | name        | newName  | message                |
      | sxjautotest | autotest | success message "保存成功" |

    Examples: 重命名失败
      | name        | newName       | message                                |
      | sxjautotest |               | error message "请输入拓扑图名称"               |
      | sxjautotest | sunxjautotest | error message "拓扑图名字已经存在\n错误码: FE_621" |

