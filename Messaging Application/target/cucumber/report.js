$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/AutomatedWebTesting/Features/webapp.feature");
formatter.feature({
  "name": "Web Application Feature",
  "description": "  Web application step feature file for testing using Selenium driver",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Successful Login",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I am an agent trying to log in",
  "keyword": "Given "
});
formatter.match({
  "location": "WebAppStepDefs.i_am_an_agent_trying_to_log_in()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I obtain a key from the supervisor using a valid id \"001\" \"Ryan Falzon\"",
  "keyword": "When "
});
formatter.match({
  "location": "WebAppStepDefs.i_obtain_a_key_from_the_supervisor_using_a_valid_id(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the supervisor should give me a valid key",
  "keyword": "Then "
});
formatter.match({
  "location": "WebAppStepDefs.the_supervisor_should_give_me_a_valid_key()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I log in using that key",
  "keyword": "When "
});
formatter.match({
  "location": "WebAppStepDefs.i_log_in_using_that_key()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should be allowed to log in \"Messaging Page\"",
  "keyword": "Then "
});
formatter.match({
  "location": "WebAppStepDefs.i_should_be_allowed_to_log_in(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Login timeout",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I am an agent trying to log in",
  "keyword": "Given "
});
formatter.match({
  "location": "WebAppStepDefs.i_am_an_agent_trying_to_log_in()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I obtain a key from the supervisor using a valid id \"001\" \"Ryan Falzon\"",
  "keyword": "When "
});
formatter.match({
  "location": "WebAppStepDefs.i_obtain_a_key_from_the_supervisor_using_a_valid_id(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the supervisor should give me a valid key",
  "keyword": "Then "
});
formatter.match({
  "location": "WebAppStepDefs.the_supervisor_should_give_me_a_valid_key()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I wait for 65 seconds",
  "keyword": "When "
});
formatter.match({
  "location": "WebAppStepDefs.i_wait_for_seconds(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I log in using that key",
  "keyword": "And "
});
formatter.match({
  "location": "WebAppStepDefs.i_log_in_using_that_key()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should not be allowed to log in \"Login Unsuccessful - Expired Login Key\"",
  "keyword": "Then "
});
formatter.match({
  "location": "WebAppStepDefs.i_should_not_be_allowed_to_log_in(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Surpassing message limit",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I am a logged in agent",
  "keyword": "Given "
});
formatter.match({
  "location": "WebAppStepDefs.i_am_a_logged_in_agent()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I attempt to send 25 messages",
  "keyword": "When "
});
formatter.match({
  "location": "WebAppStepDefs.i_attempt_to_send_messages(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the messages should be successfully sent",
  "keyword": "Then "
});
formatter.match({
  "location": "WebAppStepDefs.the_messages_should_be_successfully_sent()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I try to send another message",
  "keyword": "When "
});
formatter.match({
  "location": "WebAppStepDefs.i_try_to_send_another_message()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the system will inform me that I have exceeded my quota \"Automatic Logout\"",
  "keyword": "Then "
});
formatter.match({
  "location": "WebAppStepDefs.the_system_will_inform_me_that_I_have_exceeded_my_quota(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I will be logged out \"Home Page\"",
  "keyword": "And "
});
formatter.match({
  "location": "WebAppStepDefs.i_will_be_logged_out(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenarioOutline({
  "name": "Blocked words",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "I am a logged in agent",
  "keyword": "Given "
});
formatter.step({
  "name": "I attempt to send the message \u003cmessage\u003e to another agent",
  "keyword": "When "
});
formatter.step({
  "name": "the other agent should receive the message \u003cnew-message\u003e",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "message",
        "new-message"
      ]
    },
    {
      "cells": [
        "Hello there",
        "Hello there"
      ]
    },
    {
      "cells": [
        "Send recipe now",
        "Send now"
      ]
    },
    {
      "cells": [
        "Nuclear recipe is ready",
        "is ready"
      ]
    },
    {
      "cells": [
        "GinGer nuclear RECipE.",
        "."
      ]
    }
  ]
});
formatter.scenario({
  "name": "Blocked words",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I am a logged in agent",
  "keyword": "Given "
});
formatter.match({
  "location": "WebAppStepDefs.i_am_a_logged_in_agent()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I attempt to send the message Hello there to another agent",
  "keyword": "When "
});
formatter.match({
  "location": "WebAppStepDefs.i_attempt_to_send_the_message_to_another_agent(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the other agent should receive the message Hello there",
  "keyword": "Then "
});
formatter.match({
  "location": "WebAppStepDefs.the_other_agent_should_receive_the_message(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Blocked words",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I am a logged in agent",
  "keyword": "Given "
});
formatter.match({
  "location": "WebAppStepDefs.i_am_a_logged_in_agent()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I attempt to send the message Send recipe now to another agent",
  "keyword": "When "
});
formatter.match({
  "location": "WebAppStepDefs.i_attempt_to_send_the_message_to_another_agent(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the other agent should receive the message Send now",
  "keyword": "Then "
});
formatter.match({
  "location": "WebAppStepDefs.the_other_agent_should_receive_the_message(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Blocked words",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I am a logged in agent",
  "keyword": "Given "
});
formatter.match({
  "location": "WebAppStepDefs.i_am_a_logged_in_agent()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I attempt to send the message Nuclear recipe is ready to another agent",
  "keyword": "When "
});
formatter.match({
  "location": "WebAppStepDefs.i_attempt_to_send_the_message_to_another_agent(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the other agent should receive the message is ready",
  "keyword": "Then "
});
formatter.match({
  "location": "WebAppStepDefs.the_other_agent_should_receive_the_message(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Blocked words",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I am a logged in agent",
  "keyword": "Given "
});
formatter.match({
  "location": "WebAppStepDefs.i_am_a_logged_in_agent()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I attempt to send the message GinGer nuclear RECipE. to another agent",
  "keyword": "When "
});
formatter.match({
  "location": "WebAppStepDefs.i_attempt_to_send_the_message_to_another_agent(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the other agent should receive the message .",
  "keyword": "Then "
});
formatter.match({
  "location": "WebAppStepDefs.the_other_agent_should_receive_the_message(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Logging out",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I am a logged in agent",
  "keyword": "Given "
});
formatter.match({
  "location": "WebAppStepDefs.i_am_a_logged_in_agent()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click on log out",
  "keyword": "When "
});
formatter.match({
  "location": "WebAppStepDefs.i_click_on_log_out()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should be logged out \"Home Page\"",
  "keyword": "Then "
});
formatter.match({
  "location": "WebAppStepDefs.i_should_be_logged_out(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});