Feature: 创建一个资源分组

  Background:
    Given Create a "role" with "{'name':'AutoTestForResource','RoleDes':'','ResourceGroups':['日志来源']}"
    And Delete a "resourceGroup" with "{'name':['AutoTest','><script>alert(1)</script>']}"
    And Create a "resourceGroup" with "{'name':'AutoTest','type':['DashBoardGroup'],'owner':['admin']}"
    And open the "resourceGroups.ListPage" page for uri "/account/resourcegroups/"

  Scenario Outline: 为指定角色创建资源分组
    Given I need "<NeedRun>" create a "resourceGroup" with "{'name':'AutoTest','type':['DashBoardGroup'],'owner':['admin']}"
    And I click the "CreateResourceGroup" button
    Then I will see the "resourceGroups.CreatePage" page
    When I set the parameter "ResourceGroupName" with value "<Name>"
    And I choose the "<Type>" from the "ResourceGroupType"
    And I set the parameter "ResourceGroupDes" with value "<Des>"
    And I choose the "<Owner>" from the "ResourceGroupOwner"
    And I click the "CreateButton" button
    Then I will see the <Result>

    @smoke @resourceGroups @all
    Examples: 创建资源分组成功
    |NeedRun|Name|Type|Des|Owner|Result|
#    |N|AutoTest|仪表盘|Des1|admin,AutoTestForResource|success message "创建成功"|
#    |N|AutoTest|日志来源|   |admin|success message "创建成功"|
#    |N|AutoTest|日志来源|   |AutoTestForResource|success message "创建成功"|
#    |N|><script>alert(1)</script>|日志来源|   |admin|success message "创建成功"|

    @all @resourceGroups
    Examples: 创建资源分组失败，并对提示语做校验
    |NeedRun|Name|Type|Des|Owner|Result|
#    |N|         |仪表盘|Des1|admin|error message "填写资源分组名称"|
#    |N|AutoTest|    |Des1|admin|error message "请选择分组类型"|
#    |N|AutoTest|仪表盘|Des1|     |error message "请分配角色"|
    |Y|AutoTest|仪表盘|Des1|admin,AutoTestForResource|error message "保存失败: 资源组名称已存在\n错误码: FE_546"|

