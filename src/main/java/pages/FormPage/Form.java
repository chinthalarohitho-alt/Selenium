package pages.FormPage;

import pages.FormPage.FormPagePaths;
import utilze.selenium;

public class Form extends selenium {
    FormPagePaths FormPagepaths = new FormPagePaths();

    public void SignIn() {
        selenium.IdLocator("email-address").sendKeys("AutomationTesting@carrer.com");
        selenium.IdLocator("password").sendKeys("AutomationTesting@1999");
        selenium.button("Sign In").click();
        selenium.wait(6000);
    }

    public void SelectingModule(String moduleName) {
        selenium.button(moduleName).click();
    }

    public void UserIsOnOverViewPage() {
        selenium.wait(3);
        selenium.waitForElementVisibility(FormPagepaths.containsText("button","Submit Form"));
    }
}
