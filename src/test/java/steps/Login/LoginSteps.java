package steps.Login;

import io.cucumber.java.en.*;
import pages.HomePage.Login;

import java.io.IOException;

public class LoginSteps {

    Login login = new Login();

    @Given("user should be on the HomePage")
    public void userShouldBeOnTheHomePage() throws IOException {
        login.validateUserIsOnHomePage();
    }

    @When("user enter the {string} Email in the Email Address field")
    public void userEnterTheEmailInTheEmailAddressField(String Value) {
        login.EnterTheEmailAddress(Value);
    }

    @And("user enter the {string} password in the password field")
    public void userEnterThePasswordInThePasswordField(String Value) {
        login.EnterThePassword(Value);
    }


    @And("click on the login button")
    public void clickOnTheLoginButton() {
        login.clickOnTheButton();
    }

    @Then("user should land on the overview page")
    public void userShouldLandOnTheOverviewPage() {
        login.userIsOnTheOverviewPage();
    }

    @Then("user should see the error message {string}")
    public void userShouldSeeTheErrorMessage(String Message) {
        login.validateErrorMessage(Message);
    }

    @And("user should not land on the overview page")
    public void userShouldNotLandOnTheOverviewPage() {
        login.userShouldNotLandOnOverviewPage();
    }
}
