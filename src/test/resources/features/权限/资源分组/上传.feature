Feature: 导入一个资源包

  Background:
    Given open the "resourceGroups.ListPage" page for uri "/account/resourcegroups/"

  @somke @all @resourceGroups
  Scenario Outline: 导入资源包的正常流程测试
    Given I click the "UploadButton" button
    When I upload a file with name "<InputFileName>"
    And I click the "<ButtonType>" button
    And I choose the "<Owner>" from the "ResourceGroupOwner"
    And I click the "OKButton" button
    Then I will see the success message "导入成功"

  Examples:
    |InputFileName|ButtonType|Owner|
    |/src/test/resources/testdata/resourceGroups/success.tar|BothSaveButton|admin|
    |/src/test/resources/testdata/resourceGroups/success.tar|NotCoverButton|admin|
    |/src/test/resources/testdata/resourceGroups/success.tar|CoverButton|admin,autotest|

  @all @resourceGroups
  Scenario Outline: 导入资源包的异常流程测试-导入的文件格式/内容不正确
    Given I click the "UploadButton" button
    When I upload a file with name "<InputFileName>"
    Then I will see the error message "<ErrorMessage>"

  Examples:
    |InputFileName|ErrorMessage|
    |/src/test/resources/testdata/resourceGroups/testcase.txt|仅支持 .tar 格式文件|
    |/src/test/resources/testdata/resourceGroups/testcase.tar.gz|仅支持 .tar 格式文件|
    |/src/test/resources/testdata/resourceGroups/testcase.tar|未知错误，请检查文件格式及内容是否正确，然后重新上传|

  @all @resourceGroups
  Scenario Outline: 导入资源包的异常流程测试-为空测试
    Given I click the "UploadButton" button
    When I upload a file with name "<InputFileName>"
    And I choose the "<Owner>" from the "ResourceGroupOwner"
    And I click the "OKButton" button
    Then I will see the error message "<ErrorMessage>"

  Examples:
    |InputFileName|Owner|ErrorMessage|
    |/src/test/resources/testdata/resourceGroups/testcase.tar|admin|未知错误，请检查文件格式及内容是否正确，然后重新上传\n错误码: FE_3|
    |/src/test/resources/testdata/resourceGroups/success.tar |     |请选择角色|
    |                                                        |     |请选择角色|
    |                                                        |admin|请上传资源包|
    |/src/test/resources/testdata/resourceGroups/success.tar|admin|请选择重名处理方式|
