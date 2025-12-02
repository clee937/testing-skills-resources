Feature: FAQ Page

  Scenario: FAQ search term displayed on results page
    Given I am on the Makers FAQ page
    When I search for "hybrid"
    Then the results header should mention "hybrid"

  Scenario Outline: Performing an FAQ search for different character types
    Given I am on the Makers FAQ page
    When I search for "<search_term>"
    Then the results header should mention '<search_term>'
    Examples:
      | search_term |
      | 17%         |
      | cliché      |
      | 😄          |

  Scenario Outline: Message displayed when no search results found
    Given I am on the Makers FAQ page
    When I search for "<search_term>"
    Then the results body should say no results were found for "<search_term>"
    Examples:
      | search_term |
      | badger      |
      | xylophone   |
      | 123-456-789 |