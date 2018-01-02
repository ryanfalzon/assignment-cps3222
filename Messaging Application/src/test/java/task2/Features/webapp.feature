Feature: Web Application Feature

  Web application step feature file for testing using Selenium driver

  Scenario: Successful Login
    Given I am an agent trying to log in
    When I obtain a key from the supervisor using a valid id
    Then the supervisor should give me a valid key
    When I log in using that key
    Then I should be allowed to log in

  Scenario: Login timeout
    Given I am an agent trying to log in
    When I obtain a key from the supervisor using a valid id
    Then the supervisor should give me a valid key
    When I wait for 65 seconds
    And I log in using that key
    Then I should not be allowed to log in

    Scenario: Surpassing message limit
      Given I am a logged in agent
      When I attempt to send 25 messages
      Then the messages should be successfully sent
      When I try to send another message
      Then the system will inform me that I have exceeded my quota
      And  I will be logged out

    Scenario: Blocked words
      Given I am a logged in agent
      When I attempt to send the message <message> to another agent
      Then the other agent should receive the message <new-message>

    Scenario: Logging out
      Given I am a logged in agent
      When I click on "Log out"
      Then I should be logged out


