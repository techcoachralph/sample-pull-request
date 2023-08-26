package testcases;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Home;


import java.time.Duration;

@Listeners({ReportPortalTestNGListener.class})
public class UberEats {
    WebDriver driver;

    @BeforeClass
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterMethod
    public void tearDown() {
//        driver.quit();
    }

    @Test
    public void orderFood() throws InterruptedException {
        // set up driver
        driver = new ChromeDriver();

        // instantiate page factory from page object model
        Home home = new Home(driver);

        //# go to uber eats website
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("http://www.ubereats.com");
        home.clickAcceptCookies();
    home.searchForRestaurant("8R37+95 Coconut Creek, Florida");
    Thread.sleep(10000);
    //# results page is displayed
     Assert.assertEquals(driver.findElement(By.cssSelector("div.bc h1")).getText(), "All stores");

    //
    //# click on second restaurant under national brands
    driver.findElement(By.xpath("(//li[@data-testid='carousel-slide']//a[@data-testid='store-card']//h3)[2]")).click();
    //
    //# validate menu title is displayed
    Assert.assertTrue(driver.findElement(By.cssSelector("h1[data-testid='store-title-summary']")).isDisplayed());

    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,250)", "");

    Thread.sleep(3000);

    driver.findElement(By.xpath("//div[contains(@class,'lazyload-wrapper')][./picture]/following-sibling::div")).click();

    Thread.sleep(3000);

    // we need to add code that will choose required attributes on menu items

    // customization many
    // count the amount of customization pick many
    int amountOfCustomizationPickMany = driver.findElements(By.xpath("//div[@data-testid='customization-pick-many']")).size();
        for (int i = 0; i < amountOfCustomizationPickMany; i++) {
            // click a checkbox from each
            System.out.println("loop " + i);
            int count = i+ 1;
            driver.findElement(By.xpath("((//div[@data-testid='customization-pick-many'])["+ count + "]//div/label)|" +
                    "(//div[@data-testid='customization-pick-many'])["+ count + "]//div/button[@data-testid='quantity-increment-selection-button']")).click();
            driver.findElement(By.xpath("((//div[@data-testid='customization-pick-many'])["+ count + "]//div/label)|" +
                    "((//div[@data-testid='customization-pick-many'])["+ count + "]//div/button[@data-testid='quantity-increment-selection-button'])[2]")).click();
            driver.findElement(By.xpath("((//div[@data-testid='customization-pick-many'])["+ count + "]//div/label)|" +
                    "((//div[@data-testid='customization-pick-many'])["+ count + "]//div/button[@data-testid='quantity-increment-selection-button'])[3]")).click();
            Thread.sleep(1000);
        }


    //customization pick one
    //count amount of customizeation pick one
    int amountOfRadioButtonsForPickOneCustomization = driver.findElements(By.xpath("//div[@data-testid='customization-pick-one']")).size();
        for (int i = 0; i < amountOfRadioButtonsForPickOneCustomization; i++) {
            System.out.println("loop " + i);
            //click a radio button from each
            int count = i + 1;
            driver.findElement(By.xpath("(//div[@data-testid='customization-pick-one'])[" + count + "]//div/label")).click();
        }

     //add to cart
     driver.findElement(By.cssSelector("div[data-test='add-to-cart-cta'] button")).click();

    Thread.sleep(5000);

    Assert.assertEquals(driver.findElement(By.cssSelector("button[data-test='cart-btn']")).getText(), "Cart • 1");
    }


}
