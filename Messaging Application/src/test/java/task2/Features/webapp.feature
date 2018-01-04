Feature: Web Application Feature

  Web application step feature file for testing using Selenium driver

  Scenario: Successful Login
    Given I am an agent trying to log in
    When I obtain a key from the supervisor using a valid id "001" "Ryan Falzon"
    Then the supervisor should give me a valid key
    When I log in using that key
    Then I should be allowed to log in "Messaging Page"

