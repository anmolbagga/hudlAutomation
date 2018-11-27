Feature: Login attempt of hudlApplication

  Scenario: Unsuccessful login attempt of hudlApplication
    Given Open the Firefox and launch the hudl application
    When I Enter the wrong credentials
    Then I am not able to login to the application
   
