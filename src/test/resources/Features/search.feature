Feature: Search Functionality
  Scenario: Search valid product and validate results
    Given user navigates to the application
    When user search a valid product
    Then user gets atleast one search results

  Scenario Outline: Search invalid product and validate “no results” message in EN & NL
    Given user navigates to the application
    When user changes the language "<language>"
    When user search a invalid product
    Then user gets no results message

    Examples:
    |language|
    |en      |
    |nl      |