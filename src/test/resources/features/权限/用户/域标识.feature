Feature: 用户域标识

  @users @smoke @all
  Scenario:
    Given open the "users.TokensPage" page for uri "/tokens/"
    Then I will see the "Token" text will be same as the Sql "SELECT token FROM TokenFinder WHERE domain_id = 1;"
