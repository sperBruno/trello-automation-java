@Board
Feature: Board

  Background: Setup ApiRequestHandler
    Given I set apiRequestHandler with proper credential


  @Board_001 @deleteBoard
  Scenario: Create Board
    When I create a board with name "AT-08 test"
    Then I should see field "name" with value "AT-08 test"
    And I validate createBoard response schema

  @Board_002 @deleteBoard
  Scenario: Get a board
    Given I create a board with name "AT-08 to get a board"
    And I should see field "name" with value "AT-08 to get a board"
    When I get a board with "boardId"
    Then I should see field "name" with value "AT-08 to get a board"

  @Board_003 @createBoard @deleteBoard
  Scenario: Update a board
    When I update board name with "AT-08 to update board name"
    Then I should see field "name" with value "AT-08 to update board name"

  @Board_004 @createBoard
  Scenario: Delete a board
    When I delete a board with "boardId"
    Then I validate that status code of response is 200
    Then I should see response body as
    """
    {"_value":null}
    """