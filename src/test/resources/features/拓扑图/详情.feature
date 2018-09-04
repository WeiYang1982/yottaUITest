@topology
Feature: 拓扑图详情页

  Background:
    Given I insert into table "Topology" with "{'name':'sxjautotest','domain_id':'1','creator_id':'1','category':'0','group':'default_Topology'}"
    Then open the "topology.ListPage" page for uri "/topology/"
    Given I click the detail which name is "sxjautotest"
    Then I will see the "topology.DetailPage" page

  Scenario Outline: 禁用、启用编辑
    Then I will see the "<button>" is "<attribute>"
    Then I click the "SwitchButton" button
    And I will see the "<button>" is clickable

  @all @smoke
    Examples:
      | button | attribute |
#      | addButton,refreshButton | disabled  |

  Scenario Outline: 节点配置
    Given I click the "SwitchButton" button
    Then I set the parameter "NodeName" with value "<nodeName>"
    Then I set the parameter "NodeGroup" with value "<nodeGroup>"
    Then I click the "AddNodeButton" button
    Then I will see the alertErrorMessage "<message>"

  @all @smoke
    Examples:
      | nodeName | nodeGroup | message |
#      |          |           | 请输入节点名称 |
#      |          | test      | 请输入节点名称 |

  Scenario Outline: 第一种布局方式
    Given I click the "SwitchButton" button
    Then I set the parameter "NodeName" with value "<nodeName>"
    Then I set the parameter "NodeGroup" with value "<nodeGroup>"
    Then I click the "AddNodeButton" button
    Then I click the "SingleValueButton" button
    Then I set the parameter "TextArea" with value "<value>"
    Then I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    Then I will see the success message "搜索完成!"
    Then I choose the "<field>" from the "FiledInput"
    Then I set the parameter "Unit" with value "个"
    Then I click the "AddColourButton" button
    And I set the parameter "FromInput" with value "1"
    And I set the parameter "ToInput" with value "1000"
    Then I click the "SetColourButton" button
    And I set the parameter "SetColourInput" with value "#16DDE4"
    Then I click the "EnsureSetColourButton" button
    Then I click the "Apply" button
    Then I will see the success message "保存成功"
    Then take a screenshot

    Examples:
      | nodeName | nodeGroup | value | field |
#      | autotest  | testGroup | *\|stats count()                                                                                                                                                                                                                                                                                            | count()  |
#      | autotest1 | testGroup | tag:sample04061424 \| eval is_resplen_empty=empty(apache.resp_len) \| eval res_str=if(is_resplen_empty,"repslen_empty","resplen_non_null") \| table apache.resp_len, is_resplen_empty, res_str \| where is_resplen_empty==true                                                                              | res_str  |
#      | autotest2 | testGroup | tag:"sample04061424" \| eval res_mul=apache.resp_len*apache.status*0 - apache.resp_len*apache.status/apache.status%1000 \| eval r_add=apache.resp_len+apache.status*10-apache.resp_len-9*apache.status \| eval res_concat = appname + apache.clientip + apache.geo.city \| table res_mul, r_add, res_concat | r_add    |
#      | autotest3 | testGroup | tag:"sample04061424" AND apache.resp_len:>2000 \| eval resplen=apache.resp_len \| eval status=apache.status \| eval mid=apache.resp_len+apache.status \| eval res_mul=(apache.resp_len+apache.status)*apache.status \| table resplen, status, mid, res_mul                                                  | status   |
#      | autotest4 | testGroup | tag:"sample04061424" \| eval x_format = format("%s, %s => %s", logtype, tag, apache.clientip) \| table x_format                                                                                                                                                                                             | x_format |


  Scenario Outline: 第二种布局方式
    Given I click the "SwitchButton" button
    Then I set the parameter "NodeName" with value "<nodeName>"
    Then I set the parameter "NodeGroup" with value "<nodeGroup>"
    Then I click the "AddNodeButton" button
    Then I click the "LeftRightButton" button
    Then I click the "LeftValueButton" button
    Then I set the parameter "TextArea" with value "<leftValue>"
    Then I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    Then I will see the success message "搜索完成!"
    Then I choose the "<leftField>" from the "FiledInput"
    Then I set the parameter "Unit" with value "个"
    Then I click the "AddColourButton" button
    And I set the parameter "FromInput" with value "-960"
    And I set the parameter "ToInput" with value "140000"
    Then I click the "SetColourButton" button
    And I set the parameter "SetColourInput" with value "#16DDE4"
    Then I click the "EnsureSetColourButton" button
    Then I click the "Apply" button
    Then I click the "RightValueButton" button
    Then I set the parameter "TextArea" with value "<rightValue>"
    Then I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    Then I will see the success message "搜索完成!"
    Then I choose the "<rightField>" from the "FiledInput"
    Then I set the parameter "Unit" with value "个"
    Then I click the "AddColourButton" button
    And I set the parameter "FromInput" with value "-960"
    And I set the parameter "ToInput" with value "140000"
    Then I click the "SetColourButton" button
    And I set the parameter "SetColourInput" with value "#16DDE4"
    Then I click the "EnsureSetColourButton" button
    Then I click the "Apply" button
    Then I will see the success message "保存成功"
    Then take a screenshot

    Examples:
      | nodeName | nodeGroup | leftValue | leftField | rightValue | rightField |
#      | sxjautotest | autotestgroup | * \| stats count() | count()   | (* AND ip:192.168.1.134) \|stats pct('json.Load.load15', 25,50,75,95,99) | _pct.json.Load.load15.25 |

  Scenario Outline: 第三种布局方式
    Given I click the "SwitchButton" button
    Then I set the parameter "NodeName" with value "<nodeName>"
    Then I set the parameter "NodeGroup" with value "<nodeGroup>"
    Then I click the "AddNodeButton" button
    Then I click the "TopBottom" button
    Then I click the "TopValueButton" button
    Then I set the parameter "TextArea" with value "<leftValue>"
    Then I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    Then I will see the success message "搜索完成!"
    Then I choose the "<leftField>" from the "FiledInput"
    Then I set the parameter "Unit" with value "个"
    Then I click the "AddColourButton" button
    And I set the parameter "FromInput" with value "-960"
    And I set the parameter "ToInput" with value "140000"
    Then I click the "SetColourButton" button
    And I set the parameter "SetColourInput" with value "#16DDE4"
    Then I click the "EnsureSetColourButton" button
    Then I click the "Apply" button
    Then I click the "LowerValueButton" button
    Then I set the parameter "TextArea" with value "<rightValue>"
    Then I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    Then I will see the success message "搜索完成!"
    Then I choose the "<rightField>" from the "FiledInput"
    Then I set the parameter "Unit" with value "个"
    Then I click the "AddColourButton" button
    And I set the parameter "FromInput" with value "-960"
    And I set the parameter "ToInput" with value "140000"
    Then I click the "SetColourButton" button
    And I set the parameter "SetColourInput" with value "#16DDE4"
    Then I click the "EnsureSetColourButton" button
    Then I click the "Apply" button
    Then I will see the success message "保存成功"
    Then take a screenshot

    Examples:
      | nodeName    | nodeGroup     | leftValue          | leftField | rightValue                                                               | rightField               |
#      | sxjautotest | autotestgroup | * \| stats count() | count()   | (* AND ip:192.168.1.134) \|stats pct('json.Load.load15', 25,50,75,95,99) | _pct.json.Load.load15.25 |

  Scenario Outline: 第四种布局方式
    Given I click the "SwitchButton" button
    Then I set the parameter "NodeName" with value "<nodeName>"
    Then I set the parameter "NodeGroup" with value "<nodeGroup>"
    Then I click the "AddNodeButton" button
    Then I click the "TopLeftRight" button
    Then I click the "TopValueButton" button
    Then I set the parameter "TextArea" with value "<leftValue>"
    Then I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    Then I will see the success message "搜索完成!"
    Then I choose the "<leftField>" from the "FiledInput"
    Then I set the parameter "Unit" with value "个"
    Then I click the "AddColourButton" button
    And I set the parameter "FromInput" with value "-960"
    And I set the parameter "ToInput" with value "140000"
    Then I click the "SetColourButton" button
    And I set the parameter "SetColourInput" with value "#16DDE4"
    Then I click the "EnsureSetColourButton" button
    Then I click the "Apply" button

    Then I click the "LowerLeft" button
    Then I set the parameter "TextArea" with value "<rightValue>"
    Then I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    Then I will see the success message "搜索完成!"
    Then I choose the "<rightField>" from the "FiledInput"
    Then I set the parameter "Unit" with value "个"
    Then I click the "AddColourButton" button
    And I set the parameter "FromInput" with value "-960"
    And I set the parameter "ToInput" with value "140000"
    Then I click the "SetColourButton" button
    And I set the parameter "SetColourInput" with value "#16DDE4"
    Then I click the "EnsureSetColourButton" button
    Then I click the "Apply" button

    Then I click the "LowerRight" button
    Then I set the parameter "TextArea" with value "<rightValue>"
    Then I click the "DateEditor" button
    And I click the "Yesterday" button
    And I click the "SearchButton" button
    Then I will see the success message "搜索完成!"
    Then I choose the "<rightField>" from the "FiledInput"
    Then I set the parameter "Unit" with value "个"
    Then I click the "AddColourButton" button
    And I set the parameter "FromInput" with value "-960"
    And I set the parameter "ToInput" with value "140000"
    Then I click the "SetColourButton" button
    And I set the parameter "SetColourInput" with value "#16DDE4"
    Then I click the "EnsureSetColourButton" button
    Then I click the "Apply" button
    Then I will see the success message "保存成功"
    Then take a screenshot

    Examples:
      | nodeName    | nodeGroup     | leftValue          | leftField | rightValue                                                               | rightField               |
      | sxjautotest | autotestgroup | * \| stats count() | count()   | (* AND ip:192.168.1.134) \|stats pct('json.Load.load15', 25,50,75,95,99) | _pct.json.Load.load15.25 |

  Scenario Outline: 失败提示
    Given I click the "SwitchButton" button
    Then I set the parameter "NodeName" with value "test"
    Then I set the parameter "NodeGroup" with value "group"
    Then I click the "AddNodeButton" button
    Then I click the "LeftRightButton" button
    Then I click the "LeftValueButton" button
    Then I set the parameter "TextArea" with value "<leftValue>"
    Then I click the "<dateEditor>" button
    And I click the "<date>" button
    And I click the "SearchButton" button
    Then I will see the alertErrorMessage "<message>"

    Examples:
      | leftValue | dateEditor | date | message |
#      |           |            |       | 请输入搜索内容    |
#      | *         |            |       | 请输入搜索时间范围  |
#      | *         | DateEditor | Today | 请输入统计类搜索内容 |

  Scenario Outline: 点击应用失败
    Given I click the "SwitchButton" button
    Then I set the parameter "NodeName" with value "test"
    Then I set the parameter "NodeGroup" with value "group"
    Then I click the "AddNodeButton" button
    Then I click the "LeftRightButton" button
    Then I click the "LeftValueButton" button
    Then I set the parameter "TextArea" with value "<leftValue>"
    Then I click the "DateEditor" button
    And I click the "Today" button
    And I click the "SearchButton" button
    Then I will see the success message "搜索完成!"
    Then I choose the "<field>" from the "FiledInput"
    Then I set the parameter "Unit" with value "<unit>"
    Then I click the "AddColourButton" button
    And I set the parameter "FromInput" with value "<lowerLimit>"
    And I set the parameter "ToInput" with value "<topLimit>"
    Then I click the "SetColourButton" button
    And I set the parameter "SetColourInput" with value "#16DDE4"
    Then I click the "EnsureSetColourButton" button
    Then I click the "Apply" button
    Then I will see the alertErrorMessage "<message>"

    Examples:
      | leftValue | field | unit | lowerLimit | topLimit | message |
#      | *\|stats count() |         |      |            |          | 请选择展示字段         |
#      | *\|stats count() | count() |      |            |          | 请输入合理的分段颜色数值区间！ |
#      | *\|stats count() | count() |      | 1          |          | 请输入合理的分段颜色数值区间！ |
#      | *\|stats count() | count() |      | 1          | 1        | 请输入合理的分段颜色数值区间！ |
#      | *\|stats count() | count() |      | 1          | -1       | 请输入合理的分段颜色数值区间！ |

