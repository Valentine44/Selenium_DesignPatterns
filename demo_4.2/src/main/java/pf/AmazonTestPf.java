package pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pf.pages.HomePagePf;
import pf.pages.ItemPagePf;

import java.util.concurrent.TimeUnit;

public class AmazonTestPf {
    private WebDriver driver;

    @BeforeClass(description = "Start browser")
    private void initBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(description = "Amazon search test")
    public void amazonSearchTest() {
        ItemPagePf iphoneSePage = new HomePagePf(driver).open().fillSearchInput("Iphone SE").startSearch().openFirstSearchResult();
        Double actualPrice = iphoneSePage.readItemPrice();
        Assert.assertTrue(actualPrice < 350, "Iphone SE cost more than $350. Go and work!");
    }

    @AfterClass(description = "close browser")
    public void kill(){
        driver.close();
    }
}
