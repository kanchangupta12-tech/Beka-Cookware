package StepDefinitions;

import Pages.Search;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSD {

    Search search = new Search();
    @When("user search a valid product")
    public void user_search_a_valid_product() {
        search.searchProduct("validSearchProduct");
    }

    @Then("user gets atleast one search results")
    public void user_gets_atleast_one_search_results() {
        search.validateResults();
    }

    @When("user search a invalid product")
    public void user_search_a_invalid_product() {
        search.searchProduct("invalidSearchProduct");
    }

    @Then("user gets no results message")
    public void user_gets_no_results_message() {
        search.validateNoResults();
    }
}
