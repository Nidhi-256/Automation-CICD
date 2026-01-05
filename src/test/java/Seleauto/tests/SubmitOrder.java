package Seleauto.tests;

import Seleauto.TestComponents.BaseTest;
import Seleauto.pageobject.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;


public class SubmitOrder extends BaseTest
{
    String productName = "ZARA COAT 3";
    @Test (dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
    {
        // TODO Auto-generated method stub

        ProductCatalogue productCatalogue= landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage= productCatalogue.goToCartPage();
        Boolean match= cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckOutPage checkOutPage= cartPage.goToCheckout();
        checkOutPage.selectCountry("india");
        ConfirmationPage confirmationPage= checkOutPage.submitOrder();
        String confirmMessage= confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


    }

    @Test (dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest()
    {
        ProductCatalogue productCatalogue= landingPage.loginApplication("aditisinha01@gmail.com","Aditi@0202");
        OrderPage orderPage= productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }


    /*@DataProvider   // Taking data in Object format
	  public Object[][] getData()
	  {
	    return new Object[][]  {{"sikhasharma04@gmail.com","Isikha@8010","ZARA COAT 3"}, {"aditisinha01@gmail.com","Aditi@0202","ADIDAS ORIGINAL" } };

	  }

     */

    /*@DataProvider  // Taking data in the form of hash Map
	  public Object[][] getData()
    {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("email", "sikhasharma04@gmail.com");
        map.put("password", "Isikha@8010");
        map.put("product", "ZARA COAT 3");
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("email", "aditisinha01@gmail.com");
        map1.put("password", "Aditi@0202");
        map1.put("product", "ADIDAS ORIGINAL");
        return new Object[][] {{map},{map1}};
    }

     */
    // Taking data from json file. Reading the data from Json file and then converting it into Hash map in the BaseTest class.
    @DataProvider
    public Object[][] getData() throws IOException
    {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Seleauto//data//PurchaseOrder.json");
        return new Object[][]  {{data.get(0)}, {data.get(1) } };

    }

}
