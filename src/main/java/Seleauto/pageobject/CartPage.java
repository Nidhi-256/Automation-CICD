package Seleauto.pageobject;

import Seleauto.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents
{
    WebDriver driver;

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProducts;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public Boolean VerifyProductDisplay(String productName) {
        Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;

    }

    public CheckOutPage goToCheckout() {
        checkoutEle.click();
        return new CheckOutPage(driver);

    }


}
