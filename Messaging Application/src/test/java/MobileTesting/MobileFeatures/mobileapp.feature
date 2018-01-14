Feature: Mobile Application Feature

  Mobile application step feature file for testing using Appium Driver

  Scenario: Successful Login
    Given I am an agent trying to log in
    When I obtain a key from the supervisor using a valid id "001" "Ryan Falzon"
    Then the supervisor should give me a valid key
    When I log in using that key
    Then I should be allowed to log in "Messaging Page"

  Scenario: Login timeout
    Given I am an agent trying to log in
    When I obtain a key from the supervisor using a valid id "001" "Ryan Falzon"
    Then the supervisor should give me a valid key
    When I wait for 2 seconds
    And I log in using that key
    Then I should not be allowed to log in "Login Unsuccessful - Expired Login Key"

  Scenario: Surpassing message limit
    Given I am a logged in agent
    When I attempt to send 25 messages
    Then the messages should be successfully sent
    When I try to send another message
    Then the system will inform me that I have exceeded my quota "Automatic Logout"
    And I will be logged out "Home Page"

  Scenario Outline: Blocked words
    Given I am a logged in agent
    When I attempt to send the message <message> to another agent
    Then the other agent should receive the message <new-message>

    Examples:
      | message | new-message |
      |  Hello there   |  Hello there  |
      |  Send recipe now   |  Send now  |
      |  Nuclear recipe is ready   |  is ready  |
      |  GinGer nuclear RECipE.  |  .  |

  Scenario: Logging out
    Given I am a logged in agent
    When I click on log out
    Then I should be logged out "Home Page"
