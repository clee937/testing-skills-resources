Feature: FAQ Page

  Scenario: FAQ search term displayed on results page
    Given I am on the Makers FAQ page
    When I search for "hybrid"
    Then the results page should display results for this term

  Scenario: FAQ search term for two words displayed on results page
    Given I am on the Makers FAQ page
    When I search for "remote pairing"
    Then the results page should display results for this term

  Scenario Outline: Message displayed when no search results found
    Given I am on the Makers FAQ page
    When I search for "<search_term>"
    Then the results body should say no results were found for "<search_term>"
    Then there should be <results_count> results
    And the term "<search_term>" should appear in the URL

    Examples:
      | search_term | results_count |
      | badger      | 0             |
      | xylophone   | 0             |
      | 123-456-789 | 0             |

  Scenario Outline: Performing an FAQ search for different character types
    Given I am on the Makers FAQ page
    When I search for "<search_term>"
    Then the results body should say no results were found for "<search_term>"
    Examples:
      | search_term |
      | 67%         |
      | cliché      |
      | 😄          |