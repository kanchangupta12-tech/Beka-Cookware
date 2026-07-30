package StepDefinitions;

import Pages.Cart;
import Pages.Product;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CartSD {

    Cart cart = new Cart();
    Product product = new Product();
    Double price;

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

    @When("user opens the cart page and update the quantity to zero")
    public void user_opens_the_cart_page_and_update_the_quantity_to_zero() {
        cart.updateCartCounter("0");

    }

    @Then("user validates the cart is empty")
    public void user_validates_the_cart_is_empty() {
        cart.checkEmptyCartMessage("en");
    }

    @When("user opens the cart page and update the quantity to ten")
    public void user_opens_the_cart_page_and_update_the_quantity_to_ten() {
        price = cart.fetchPrice();
        price = price * 10;
        cart.updateCartCounter("10");
    }

    @Then("user validates the price is updated for ten quantity")
    public void user_validates_the_price_is_updated_for_quantity() {
        Double upPrice = cart.fetchPrice();
        Assert.assertEquals(price, upPrice,"Price is not updated correctly - " + price + "," + upPrice);

    }
}
