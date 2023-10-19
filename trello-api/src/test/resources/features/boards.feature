@Board
Feature: Board

    Background: Setup ApiRequestHandler
        Given I set apiRequestHandler with proper credential


@Board_001 @deleteBoard
Scenario: Create Board
    When I create a board with name "AT-08 test"
    Then I should see field "name" with value "AT-08 test"
        And I validate createBoard response schema

