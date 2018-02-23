Feature: 创建一个资源分组

  Background:
    Given I click the "ResourceGroupsPage" button
    Then I will see the "resourceGroups.ListPage" page

  Scenario Outline: 为指定角色创建资源分组
    Given need run condition "<NeedRun>" There is a resourceGroup with name "AutoTest1" , type "仪表盘" , owner "admin"
    And I click the "CreateResourceGroup" button
    Then I will see the "resourceGroups.CreatePage" page
    When I set the parameter "ResourceGroupName" with value "<Name>"
    And I choose the "<Type>" from the "ResourceGroupType"
    And I set the parameter "ResourceGroupDes" with value "<Des>"
    And I choose the "<Owner>" from the "ResourceGroupOwner"
    And I click the "CreateButton" button
    Then I will see the <Result>

    @smoke
    Examples: 创建资源分组成功
    |NeedRun|Name|Type|Des|Owner|Result|
    |N|AutoTest1|仪表盘|Des1|admin,hunter_roles_iis|success message "创建成功"|
    |N|AutoTest2|日志来源|   |admin|success message "创建成功"|
    |N|AutoTest2|日志来源|   |zhaixiaoyuRole|success message "创建成功"|
    |N|"><script>alert(1)</script>|日志来源|   |admin|success message "创建成功"|

    @all
    Examples: 创建资源分组失败，并对提示语做校验
    |NeedRun|Name|Type|Des|Owner|Result|
    |N|         |仪表盘|Des1|admin|error message "填写资源分组名称"|
    |N|AutoTest3|    |Des1|admin|error message "请选择分组类型"|
    |N|AutoTest4|仪表盘|Des1|     |error message "请分配角色"|
    |Y|AutoTest1|仪表盘|Des1|admin,hunter_roles_iis|error message "保存失败: 资源组名称已存在\n错误码: FE_546"|

