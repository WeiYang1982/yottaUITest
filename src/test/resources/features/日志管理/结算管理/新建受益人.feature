@payments
Feature: 添加受益人

  Background:
    Given I insert into table "Beneficiary" with "{'name':'autotest','domain_id':'1'}"
    Then I delete from "Beneficiary" where "{'name':['sxjautotest','测试中文名','测试乱码&%¥*','测试超出长度的名称能否保存成功']}"
    Then open the "payments.ListPage" page for uri "/payments/"

  @all
  Scenario Outline:
    Given I click the "CreateButton" button
    Then I will see the "payments.CreatePage" page
    Then I set the parameter "PaymentsName" with value "<name>"
    Then I set the parameter "Describe" with value "<describe>"
    Then I choose the "<appName>" from the "AppName"
    Then I click the "CreateButton" button
    Then I will see the success message "<message>"

  @smoke
    Examples: 保存成功
      | name          | describe | appName | message                         |
      | sxjautotest   | autotest | apache  | 保存成功                            |
      | 测试中文名         |          | json    | 保存成功                            |
      | 测试乱码&%¥*      |          |         | 受益人保存成功，但Appname分配失败\n错误码: FE_3 |
      | 测试超出长度的名称能否保存成功 |          | apache  | 保存成功                            |

    Examples: 保存失败
      | name     | describe | appName | message                     |
      |          |          |         | 请填写受益人                      |
      | autotest |          |         | 保存失败: 受益人名称已存在\n错误码: FE_610 |



