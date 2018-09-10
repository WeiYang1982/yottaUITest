@payments @all
Feature: 删除受益人

  Background:
    Given I insert into table "Beneficiary" with "{'name':'autotest','domain_id':'1'}"
    Then open the "payments.ListPage" page for uri "/payments/"

  Scenario Outline:
    Given the data name is "<name>" then i click the "删除" button
    Then I click the "EnsureButton" button
    Then I will see the success message "<message>"

    Examples:
      | name     | message |
      | autotest | 删除成功    |