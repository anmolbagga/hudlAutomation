Feature: Login feature of hudlApplication
		As a user
		I want to be shown an error message on entering wrong credentials
		So that I can enter correct credentials and login to the application
		Also I want to successfully login and navigate to homepage on providing right credentials

Scenario Outline: Unsuccessful login attempt of hudlApplication
    Given Open the Firefox and launch the hudl application
    When I enter Username as "<username>" and Password as "<password>"
    Then I am not able to login to the application
     And I close the browser
    Examples: 
    | username | password | 
    | wrongUserName | RightPassWord |
    | wrongUserName | WrongPassWord |
    | RightUserName | WrongPassWord |
    
Scenario: Successful login attempt of hudlApplication
Given Open the Firefox and launch the hudl application
    When I enter Username as "anmol_bagga10@yahoo.co.in" and Password as "anmolbagga10"
    Then I navigate to welcomePage
    And I close the browser
