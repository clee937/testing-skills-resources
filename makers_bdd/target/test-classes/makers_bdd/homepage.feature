Feature: Homepage

  Scenario Outline: Can access links from the homepage
    Given I am on the Makers homepage
    When I click the "<link_text>" link
    Then I should be on the "<expected_path>" page
    Examples:
      | link_text | expected_path |
      | FAQ | /en/knowledge |
      | Academy | /bootcamp-courses |
      | Apprenticeships | /apprenticeships |

