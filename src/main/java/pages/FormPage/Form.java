package pages.FormPage;

import config.Settings;
import config.frameWorkConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilze.playwright;

import java.io.IOException;
import java.util.Properties;

public class Form extends playwright {

    private static final Logger logger = LoggerFactory.getLogger(Form.class);
    private final FormPagePaths paths;

    public Form() {
        this.paths = new FormPagePaths();
    }

    /**
     * Sign in with default credentials
     */
    public void signIn() throws IOException {
        logger.info("Signing in with default credentials");

        fill(paths.byId("email-address"), Settings.Username);
        fill(paths.byId("password"),Settings.Password );
        click(paths.exactText("button","Sign In"));

        waitForNetworkIdle();
        logger.info("Sign in completed successfully");
    }

    /**
     * Select a module by clicking on its button
     */
    public void selectModule(String moduleName) {
        logger.info("Selecting module: {}", moduleName);

        // Using dynamic locator from paths
        String moduleButton = paths.hasText("button", moduleName);
        click(moduleButton);

        waitForLoadState();
    }

    /**
     * Verify user is on the Overview page
     */
    public void userIsOnOverviewPage() {
        logger.info("Verifying user is on Overview page");
        waitForElementVisibility(paths.exactText("button","Submit Form"));
    }

    /**
     * Verify @ symbol error message is displayed
     */
    public void verifyAtSymbolError() {
        assertVisible(paths.AT_THE_RATE_ERROR);
    }

    /**
     * Verify error message with specific text
     */
    public void verifyErrorMessage(String errorText) {
        String errorLocator = paths.containsText("p", errorText);
        assertVisible(errorLocator);
    }

    /**
     * Click on any button with specific text
     */
    public void clickButtonWithText(String buttonText) {
        String buttonLocator = paths.containsText("button", buttonText);
        click(buttonLocator);
    }

    /**
     * Get text from element using dynamic locator
     */
    public String getTextFromElement(String tagName, String containingText) {
        String locator = paths.containsText(tagName, containingText);
        return getText(locator);
    }

}
