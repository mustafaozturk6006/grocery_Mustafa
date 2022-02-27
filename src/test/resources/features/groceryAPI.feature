@wip
Feature: grocery API Testing

  Scenario: Verify that an authorized user is able to get all grocery products data
    Given set GET allgrocery service api endpoint
    When  set request header
    And   set authorization
    And   send a GET HTTP request
    Then  Verify status code 200
    And   body contains data

  Scenario: Verify that an authorized user is able to post grocery products data
    Given set POST employee service api endpoint
    When  set request header
    And   set authorization
    And   send a POST HTTP request
    Then  Verify status code 201

  Scenario: Verify that an authorized user is able to get specific grocery products data
    Given set GET allgrocery service api endpoint with param "apple"
    When  set request header
    And   set authorization
    And   send a GET HTTP request
    Then  Verify status code 200
    And   body contains data related param

  Scenario: Verify that user gets 404 status code when sent wrong endpoint
    Given set GET wrong service api endpoint with param "apple"
    When  set request header
    And   set authorization
    And   send a GET HTTP request
    Then  Verify status code 404
    And   body contains -error- message

