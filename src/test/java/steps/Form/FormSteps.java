package steps.Form;

import io.cucumber.java.en.*;
import pages.FormPage.Form;

public class FormSteps {

    Form Form = new Form();

    @Given("I navigate to the QA Playground homepage")
    public void iNavigateToTheQAPlaygroundHomepage() {
        Form.SignIn();
    }

    @And("I click on the {string} Module")
    public void iClickOnTheSection(String ModuleName) {
        Form.SelectingModule(ModuleName);
    }

    @And("Navigate inside the {string} from overview page")
    public void navigateInsideTheFromOverviewPage(String Tab) {
        System.out.println("User is landed on the "+ Tab + "Tab");
    }

    @Given("user should be on the login page")
    public void userShouldBeOnTheLoginPage() {
        Form.UserIsOnOverViewPage();
    }



}
