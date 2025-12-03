package pages.HomePage;

import config.Settings;
import pages.FormPage.Form;
import pages.FormPage.FormPagePaths;
import utilze.playwright;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Login extends playwright {
    Properties prop = new Properties();
    Form form = new Form();
    LoginPath loginPath = new LoginPath();
    FormPagePaths FormPagepaths = new FormPagePaths();

    public void validateUserIsOnHomePage() {
        try {
            assertPageHasURL(Settings.Url);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void EnterTheEmailAddress(String value) {
        String Email = switch (value.toLowerCase()) {
            case "valid" -> "AutomationTesting@carrer.com";
            case "without at the rate" -> "AutomationTest.com";
            case "without domain" -> "AutomationTesting@";
            case "blank email" -> "";
            default -> throw new RuntimeException("Please enter the valid option in the email address line");
        };

        fill("#email-address", Email);
    }

    public void EnterThePassword(String value) {
        String Password = switch (value.toLowerCase()) {
            case "valid" -> "AutomationTesting@1999";
            case "without at the rate" -> "AutomationTesting1999";
            case "without numbers" -> "AutomationTesting@";
            case "without alphabets" -> "123456!@#";
            case "without lower caps" -> "AUTOMATION@1999";
            case "without capital caps" -> "automation@1999";
            case "only low caps" -> "automation";
            case "only capital caps" -> "AUTOMATION";
            case "only numbers" -> "274652645237";
            case "blank password" -> "";
            default -> throw new RuntimeException("Please enter the valid option in the password field");
        };

        fill("#password", Password);
    }

    public void clickOnTheButton() {
        click("button:has-text('Sign In')");
    }

    public void userIsOnTheOverviewPage() {
        form.userIsOnOverviewPage();
        waitForTimeout(1000);
    }

    public void validateErrorMessage(String message) {
        if (message.equals("null")) {
            // No error message expected
            return;
        } else if (message.equals("Email must contain '@' symbol")) {
            // Using custom assertion method
            assertVisible(FormPagepaths.AT_THE_RATE_ERROR);
        } else {
            // Using custom assertion with text
            assertHasText(loginPath.exactText("p",message), message);
        }
    }

    public void userShouldNotLandOnOverviewPage() {
        // Using custom assertion method with negation
        assertIsNotVisible(loginPath.exactText("button","Submit Form"));
    }
}
