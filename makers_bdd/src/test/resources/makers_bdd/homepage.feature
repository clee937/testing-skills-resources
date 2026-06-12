Feature: Homepage

  Scenario: Can access the FAQ page from the homepage
    Given I am on the Makers homepage
    When I click the FAQ link
    Then I should be on the FAQ page
