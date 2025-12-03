@forms @ui @regression
Feature: Forms Page End-to-End Validation

# -----------------------------------------------------------------
# Background
# -----------------------------------------------------------------
  Background: Logging and navigating to the Form Tab
    Given I navigate to the QA Playground homepage
    And I click on the "Forms" Module

# -----------------------------------------------------------------
# TEXT INPUT EDGE CASES
# -----------------------------------------------------------------

  @textInput @edgeCase @negative
  Scenario Outline: Text input - spaces only and trimming
    When I enter "<inputValue>" into the text input field
    And I submit the form
    Then the text input field should show required validation for empty or trimmed input

    Examples:
      | inputValue |
      | "   "      |
      | "  Test  " |

  @textInput @edgeCase @positive
  Scenario Outline: Text input - Unicode characters
    When I enter "<unicodeText>" into the text input field
    Then the text input field should contain "<unicodeText>"

    Examples:
      | unicodeText |
      | "测试"        |
      | "áéíóú"     |
      | "مرحبا"     |

  @textInput @edgeCase @positive
  Scenario Outline: Text input - very long input
    When I enter "<longText>" into the text input field
    Then the text input field should accept "<longText>"

    Examples:
      | longText                 |
      | "<1000_characters_here>" |

# -----------------------------------------------------------------
# EMAIL EDGE CASES
# -----------------------------------------------------------------

  @email @edgeCase @negative
  Scenario Outline: Email field - spaces, consecutive dots, and invalid formats
    When I enter "<emailInput>" into the email field
    And I submit the form
    Then an email validation error message should be displayed

    Examples:
      | emailInput           |
      | " test@gmail.com"    |
      | "test@gmail.com "    |
      | "te st@gmail.com"    |
      | "test..qa@gmail.com" |

  @email @edgeCase @positive
  Scenario Outline: Email field - valid unicode and + symbol
    When I enter "<validEmail>" into the email field
    Then no email validation error message should be displayed

    Examples:
      | validEmail               |
      | "tést@domain.com"        |
      | "user+tag@gmail.com"     |
      | "normal.email@domain.co" |

# -----------------------------------------------------------------
# PASSWORD EDGE CASES
# -----------------------------------------------------------------

  @password @edgeCase @negative
  Scenario Outline: Password - only letters or numbers
    When I enter "<passwordInput>" into the password field
    And I submit the form
    Then a password validation error should be displayed

    Examples:
      | passwordInput |
      | "abcdef"      |
      | "123456"      |

  @password @edgeCase @positive
  Scenario Outline: Password - special characters and emoji
    When I enter "<passwordInput>" into the password field
    Then the password field should accept "<passwordInput>"

    Examples:
      | passwordInput  |
      | "P@ssw0rd!"    |
      | "Rohith🔥2025" |
      | "Test123💯"    |

# -----------------------------------------------------------------
# NUMBER FIELD EDGE CASES
# -----------------------------------------------------------------

  @number @edgeCase @negative
  Scenario Outline: Number field - invalid numeric input
    When I enter "<inputValue>" into the number field
    Then the number field should reject "<inputValue>"

    Examples:
      | inputValue |
      | "abc"      |
      | "123abc"   |
      | "1e5"      |

  @number @edgeCase @positive
  Scenario Outline: Number field - negative, decimal, large numbers
    When I enter "<inputValue>" into the number field
    Then the number field should accept "<inputValue>"

    Examples:
      | inputValue |
      | -100       |
      | 25.75      |
      | 999999999  |

# -----------------------------------------------------------------
# TEXT AREA EDGE CASES
# -----------------------------------------------------------------

  @textArea @edgeCase @positive
  Scenario Outline: Text area - long input and emoji
    When I enter "<textValue>" into the text area
    Then the text area should accept "<textValue>"

    Examples:
      | textValue                 |
      | "<10000_characters_here>" |
      | "Line1\nLine2\nLine3"     |
      | "Testing 😃🚀❤️"          |

  @textArea @edgeCase @negative
  Scenario Outline: Text area - HTML / XSS
    When I enter "<textValue>" into the text area
    And I submit the form
    Then a validation error or safe sanitization should occur

    Examples:
      | textValue                   |
      | "<script>alert(1)</script>" |
      | "<div>Test</div>"           |

# -----------------------------------------------------------------
# FILE UPLOAD EDGE CASES
# -----------------------------------------------------------------

  @fileUpload @edgeCase @negative
  Scenario Outline: File upload - invalid names, large files, special characters
    When I upload "<fileName>"
    Then the file upload should reject invalid files or show proper validation

    Examples:
      | fileName                            |
      | "verylongfilename_1234567890...png" |
      | "test @ file.png"                   |
      | "corrupted_file.exe"                |

  @fileUpload @edgeCase @positive
  Scenario Outline: File upload - valid files
    When I upload "<fileName>"
    Then the uploaded file should display "<fileName>"

    Examples:
      | fileName       |
      | "sample.txt"   |
      | "image.png"    |
      | "document.pdf" |

# -----------------------------------------------------------------
# CHECKBOX EDGE CASES
# -----------------------------------------------------------------

  @checkbox @edgeCase
  Scenario: Checkbox double click / rapid clicks
    When I click the checkbox rapidly multiple times
    Then the checkbox state should remain consistent

# -----------------------------------------------------------------
# RADIO BUTTON EDGE CASES
# -----------------------------------------------------------------

  @radio @edgeCase
  Scenario: Radio button keyboard navigation
    When I navigate radio buttons using arrow keys
    Then selection should follow keyboard input correctly

# -----------------------------------------------------------------
# UI VISUAL EDGE CASES
# -----------------------------------------------------------------

  @ui @edgeCase @visual
  Scenario: Alignment, visibility, labels, placeholders, emoji handling
    Then all form fields should be aligned properly
    And all fields should be fully visible
    And each field should have a visible label
    And placeholders should appear correctly
    And emoji input should not break layout
