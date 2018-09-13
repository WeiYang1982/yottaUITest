@offlineTask @all @smoke
Feature: 搜索下载列表

  Background:
    Given open the "splSearch.OfflineTaskPage" page for uri "/download/"

  Scenario Outline:
    Given search "<searchName>" and I will see the column number "1" contains "<name>"

    Examples:
      | searchName           | name     |
      | {'input':'autotest'} | autotest |