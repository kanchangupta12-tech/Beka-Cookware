Feature: Test the url is loaded
  Scenario: Verify that Beka homepage
    Given user navigates to the application
    When user checks for the header logo
    And user checks for the footer logo
    Then user is able to switch language