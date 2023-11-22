Feature: Trello Home Page

  @trelloMo
  Scenario: User is able to create a card
    Given I am on trello Home Page
      And I can click on Add button
    When I click on add card option
      And I create a "testas" card in "todo" list in "test01" board
    Then I should see Home page
    When I open "test01" board
    Then I should see "testas" card
