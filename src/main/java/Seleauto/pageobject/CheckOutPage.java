package Seleauto.pageobject;

import Seleauto.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponents
{
    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = ".btnn.action__submit")
    private WebElement submit;

    @FindBy(css = "[placeholder='Select Country']")
    private WebElement country;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    private WebElement selectCountry;

    private By results = By.cssSelector(".ta-results");

    public void selectCountry(String countryName) {
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        //waitForElementToAppear(By.cssSelector(".ta-results"));
        waitForElementToAppear (results);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder()
    {
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy (0,500)");
        submit.click();
        return new ConfirmationPage(driver);


    }


}
