package StepDefinitions;

import Pages.Category;
import io.cucumber.java.en.Then;

public class CategorySD {

    Category cat = new Category();
    @Then("user clicks on cookware category")
    public void user_clicks_on_cookware_category() {
        // Write code here that turns the phrase above into concrete actions
        cat.chooseMainCat("en");
    }
    @Then("choose any product set category and validates the product displayed count")
    public void choose_any_product_set_and_validates_the_product_displayed_count() {
        // Write code here that turns the phrase above into concrete actions
        cat.chooseCategory("en");
    }
    @Then("user choose any subcategories subCategory and validates the product displayed count")
    public void user_choose_any_subcategories_and_validates_the_product_displayed_count() {
        // Write code here that turns the phrase above into concrete actions
        cat.chooseProduct("en");
    }



}
