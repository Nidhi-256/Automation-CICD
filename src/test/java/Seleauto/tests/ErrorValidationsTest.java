package Seleauto.tests;

import Seleauto.TestComponents.BaseTest;
import Seleauto.TestComponents.Retry;
import Seleauto.pageobject.CartPage;
import Seleauto.pageobject.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest
{
    @Test (groups = {"ErrorHandling"},retryAnalyzer= Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {


        landingPage.loginApplication("sikhasharma04@gmail.com", "Ipikha@8010");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

    }


    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException, ExceptionInInitializerError
    {

        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("aditisinha01@gmail.com", "Aditi@0202");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }



}
