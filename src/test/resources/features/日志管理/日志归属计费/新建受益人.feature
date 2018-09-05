@payments
Feature:

  Background:
    Then open the "payments.ListPage" page for uri "/payments/"

  Scenario Outline:
    Given I click the "CreateButton" button
