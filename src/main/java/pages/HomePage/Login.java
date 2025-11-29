package pages.HomePage;

import org.junit.Assert;
import pages.FormPage.Form;
import pages.FormPage.FormPagePaths;
import utilze.selenium;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Login{
    Properties prop = new Properties();
    Form form = new Form();
    LoginPath loginPath = new LoginPath();
    FormPagePaths FormPagepaths = new FormPagePaths();

    public void validateUserIsOnHomePage() throws IOException {
        try {
            FileInputStream PropUrl = new FileInputStream("src/main/resources/alpha.properties");
            prop.load(PropUrl);
            String ActualUrl = selenium.driver.getCurrentUrl();
            String ExpectedUrl = prop.getProperty("Url");
            Assert.assertEquals(ExpectedUrl, ActualUrl);
        }catch (IOException e){
            throw new IOException(e.getMessage());
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
        selenium.IdLocator("email-address").sendKeys(Email);
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
            default -> throw new RuntimeException("Please enter the valid option in the email address line");
        };
        selenium.IdLocator("password").sendKeys(Password);
    }

    public void clickOnTheButton() {
        selenium.button("Sign In").click();
    }


    public void userIsOnTheOverviewPage() {
        form.UserIsOnOverViewPage();
        selenium.wait(1);
    }

    public void validateErrorMessage(String message) {
        if(message.equals("null")){
            Assert.assertTrue(true);
        }else if(message.equals("Email must contain '@' symbol")){
              Assert.assertTrue(selenium.elementVisibility(FormPagepaths.AtTheRateInvalidMessage));
        }else {
            Assert.assertTrue(selenium.elementVisibility(FormPagepaths.containsText("p",message)));
        }
    }

    public void userShouldNotLandOnOverviewPage() {
        Assert.assertFalse(selenium.elementVisibility(FormPagepaths.containsText("button","Submit Form")));
    }
}
