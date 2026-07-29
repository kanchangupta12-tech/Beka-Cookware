package StepDefinitions;

import Pages.Homepage;
import Pages.Product;
import Utility.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ProductSD {

    Product product = new Product();
    Homepage hp = new Homepage();
    String price;
    @When("user selects a product")
    public void user_selects_a_product() {
        product.moveToProduct();
    }

    @Then("user validates product image is displayed")
    public void user_validates_product_image_is_displayed() {
        Assert.assertTrue(product.productImage(), "Product Image displayed correctly");
    }

    @Then("validates product name, price and description is displayed")
    public void validates_product_name_price_and_description_is_displayed() {
        String productTitle = "";
        String productDescription = "";

        if ( Homepage.langConfirmation.equalsIgnoreCase("eng")) {
            productTitle = ConfigReader.getValue("en.properties","productName");
            productDescription = ConfigReader.getValue("en.properties","productDescription");
        } else if (Homepage.langConfirmation.equalsIgnoreCase("nl")){
            productTitle = ConfigReader.getValue("nl.properties","productName");
            productDescription = ConfigReader.getValue("nl.properties","productDescription");
        } else {
            Assert.fail("Language not yet supported");
        }
        product.checkProductTitle(productTitle);
        product.checkDescription(productDescription);
        price = product.checkPrice();
        if (!price.isEmpty()){
            Assert.assertTrue(true, "Product price displayed");
        } else {
            Assert.fail("Product price is not displayed");
        }
    }

    @When("user switches language and selects a product")
    public void user_switches_language_and_selects_a_product() {
       hp.switchLanguage();
    }

    @Then("user validates the product price")
    public void user_validates_the_product_price() {
        String priceNL = product.checkPriceInNLlang();
        if (price == priceNL) {
            Assert.assertTrue(true, "Product price is same for eng and nl lang - "+ priceNL +","+price);
        } else {
            Assert.fail("Product price is same for eng and nl lang - "+ priceNL+","+price);
        }
    }

    @Then("validates the test image gallery")
    public void validates_the_test_image_gallery() {
        product.switchGalleryImage();
    }
}
