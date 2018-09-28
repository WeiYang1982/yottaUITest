Feature: 导入一个资源包

  Background:
    Given Create a "role" with "{'name':'AutoTestForSourceGroup','RoleDes':'','ResourceGroups':['日志来源']}"
    Given open the "resourceGroups.ImportPage" page for uri "/account/resourcegroups/import/"

  @somke @all @resourceGroups
  Scenario Outline: 导入资源包的正常流程测试
    When I upload a file with name "<InputFileName>"
    And I choose the "<Owner>" from the "AssignRole"
    And I click the "NextStepButton" button
    And I wait for "2000" millsecond
    And I click the "NextStepButton" button
    And I wait for "2000" millsecond
    And I click the "NextStepButton" button
    And I wait for "2000" millsecond
    Then I will see the success message "导入成功"

  Examples:
    |InputFileName|Owner|
    |/src/test/resources/testdata/resourceGroups/success.tar|admin|
    |/src/test/resources/testdata/resourceGroups/success.tar|admin,AutoTestForSourceGroup|

  @all @resourceGroups
  Scenario Outline: 导入资源包的异常流程测试-导入的文件格式/内容不正确
    When I upload a file with name "<InputFileName>"
    Then I will see the error message "<ErrorMessage>"

  Examples:
    |InputFileName|ErrorMessage|
    |/src/test/resources/testdata/resourceGroups/testcase.txt|仅支持 .tar 格式文件|
    |/src/test/resources/testdata/resourceGroups/testcase.tar.gz|仅支持 .tar 格式文件|
    |/src/test/resources/testdata/resourceGroups/testcase.tar|未知错误，请检查文件格式及内容是否正确，然后重新上传|

  @all @resourceGroups
  Scenario Outline: 导入资源包的异常流程测试-为空测试
    When I upload a file with name "<InputFileName>"
    And I choose the "<Owner>" from the "AssignRole"
    And I click the "NextStepButton" button
    Then I will see the error message "<ErrorMessage>"

  Examples:
    |InputFileName|Owner|ErrorMessage|
    |/src/test/resources/testdata/resourceGroups/testcase.tar|admin|数据格式错误，请重新上传|
    |/src/test/resources/testdata/resourceGroups/success.tar |     |请选择角色|
    |                                                        |     |请上传资源包|
    |                                                        |admin|请上传资源包|
