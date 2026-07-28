package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomepageSD {
//testing
    Pages.Homepage hp = new Pages.Homepage();
    @Given("user navigates to the application")
    public void user_navigates_to_the_application() {
        hp.openApplication();
    }
    @When("user checks for the header logo")
    public void user_checks_for_the_header_logo() {
        hp.checkHeaderLogo();
    }
    @When("user checks for the footer logo")
    public void user_checks_for_the_footer_logo() {
       hp.checkFooterLogo();
    }
    @Then("user is able to switch language")
    public void user_is_able_to_switch_language() {
        // Write code here that turns the phrase above into concrete actions
        hp.switchLanguage();
    }

}
