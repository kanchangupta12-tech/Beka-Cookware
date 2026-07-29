package StepDefinitions;

import Pages.Cart;
import Pages.Product;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartSD {

    Cart cart = new Cart();
    Product product = new Product();

    @When("user click on cart menu")
    public void user_click_on_cart_menu() {
        cart.clickCartOption();
    }

    @Then("user validates empty cart message")
    public void user_validates_empty_cart_message() {
        cart.checkCartMessage();
    }

    @Then("clicks on continue shopping and homepage is displayed")
    public void clicks_on_continue_shopping_and_homepage_is_displayed() {
        cart.continueShopping();
        cart.validateHomePageIsDisplayed();
    }

    @When("user adds product to the cart")
    public void user_adds_product_to_the_cart() {
        product.moveToProduct();
        cart.addToCart();

    }

    @Then("user validates the cart counter")
    public void user_validates_the_cart_counter() {
        cart.validateCartCounter("1");
    }

    @Then("user validates the valid pricing format")
    public void user_validates_the_valid_pricing_format() {
        cart.validatePriceFormat();
    }

    @When("user opens the cart page and update the quantity")
    public void user_opens_the_cart_page_and_update_the_quantity() {
        cart.incrementCart();
    }

    @Then("user validates the cart counter and the recalculated prices")
    public void user_validates_the_cart_counter_and_the_recalculated_prices() {
        cart.validateCartCounter("2");
        cart.validateRecalculatePrice();
    }


}
