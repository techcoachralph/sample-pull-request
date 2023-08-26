package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
    WebDriver driver;

    public Home (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='cookie-banner']//button[.='Got it']")
    private WebElement acceptCookies;

    @FindBy(id = "location-typeahead-home-input")
    private WebElement deliveryAddressInputField;

    @FindBy(xpath = "//button[.='Find Food']")
    private WebElement findFood;

    // getters
    public WebElement getAcceptCookies() {
        return acceptCookies;
    }

    public WebElement getDeliveryAddressInputField() {
        return deliveryAddressInputField;
    }

    public WebElement getFindFood() {
        return findFood;
    }

    // actions
    public void clickAcceptCookies() {
        System.out.println("Click Accept cookies button");
        getAcceptCookies().click();
    }

    public void enterDeliveryAddress(String deliveryAddress) {
        System.out.println("Enter address: " + deliveryAddress);
        getDeliveryAddressInputField().sendKeys(deliveryAddress);
    }

    public void clickFindFoodButton() {
        System.out.println("Click Find Food button");
        getFindFood().click();
    }

    public void searchForRestaurant(String deliveryAddress) throws InterruptedException {
        enterDeliveryAddress(deliveryAddress);
        Thread.sleep(3000);
        clickFindFoodButton();
    }
}
