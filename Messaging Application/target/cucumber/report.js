$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/MobileTesting/MobileFeatures/mobileapp.feature");
formatter.feature({
  "name": "Mobile Application Feature",
  "description": "  Mobile application step feature file for testing using Appium Driver",
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
  "location": "MobileBrowser.i_am_an_agent_trying_to_log_in()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I obtain a key from the supervisor using a valid id \"001\" \"Ryan Falzon\"",
  "keyword": "When "
});
formatter.match({
  "location": "MobileBrowser.i_obtain_a_key_from_the_supervisor_using_a_valid_id(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the supervisor should give me a valid key",
  "keyword": "Then "
});
formatter.match({
  "location": "MobileBrowser.the_supervisor_should_give_me_a_valid_key()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I log in using that key",
  "keyword": "When "
});
formatter.match({
  "location": "MobileBrowser.i_log_in_using_that_key()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should be allowed to log in \"Messaging Page\"",
  "keyword": "Then "
});
formatter.match({
  "location": "MobileBrowser.i_should_be_allowed_to_log_in(String)"
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
  "location": "MobileBrowser.i_am_an_agent_trying_to_log_in()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I obtain a key from the supervisor using a valid id \"001\" \"Ryan Falzon\"",
  "keyword": "When "
});
formatter.match({
  "location": "MobileBrowser.i_obtain_a_key_from_the_supervisor_using_a_valid_id(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the supervisor should give me a valid key",
  "keyword": "Then "
});
formatter.match({
  "location": "MobileBrowser.the_supervisor_should_give_me_a_valid_key()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I wait for 2 seconds",
  "keyword": "When "
});
