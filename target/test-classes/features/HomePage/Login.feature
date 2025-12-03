@admin @FormPage
#=================================================================
#     Login Test cases
#==================================================================
Feature: From Page - To Test Login
# -----------------------------------------------------------------
#           Back Ground
# -----------------------------------------------------------------
  Background: Logging and navigating to the Form Tab
    Given  user should be on the HomePage
# -----------------------------------------------------------------
#           Login with valid cred's
# -----------------------------------------------------------------
  @LoginValidCases
  Scenario Outline: To Test Login with valid cred's
    When user enter the "<Email Address>" Email in the Email Address field
    And user enter the "<Password>" password in the password field
    And click on the login button
    Then user should land on the overview page
    Examples:
      | Email Address | Password |
      | valid         | valid    |

  @LoginInvalidCases
  Scenario Outline: To Test Login with valid cred's
    When user enter the "<Email Address>" Email in the Email Address field
    And user enter the "<Password>" password in the password field
    And click on the login button
    Then user should see the error message "<Email Error Message>"
    Then user should see the error message "<Password Error Message>"
    And user should not land on the overview page
    Examples:
      | Email Address       | Password             | Email Error Message              | Password Error Message                                                         |
      | without at the rate | without at the rate  | Email must contain '@' symbol    | Password must contain at least one special character                           |
#      | without domain      | without numbers      | Email must contain a domain name | Password must contain at least one number                                      |
#      | blank email         | blank password       | Email is required                | Password is required                                                           |
#      | without domain      | only low caps        | Email must contain a domain name | Password must contain uppercase letter, number and special character           |
#      | valid               | only capital caps    | null                             | Password must contain lowercase letter, number and special character           |
#      | without domain      | only numbers         | Email must contain a domain name | Password must contain uppercase letter, lowercase letter and special character |
#      | blank email         | without alphabets    | Email is required                | Password must contain uppercase letter and lowercase letter                    |
#      | valid               | without lower caps   | null                             | Password must contain at least one lowercase letter                            |
#      | without at the rate | without capital caps | Email must contain '@' symbol    | Password must contain at least one uppercase letter                            |

