package pf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePagePf extends AbstractPagePf {

    @FindBy(css = "#twotabsearchtextbox")
    WebElement searchInput;

    @FindBy(xpath = "//input[@value='Go']")
    WebElement goButton;

    public HomePagePf(WebDriver driver) {
        super(driver);
    }

    public HomePagePf open() {
        driver.get("https://amazon.com");
        return this;
    }

    public HomePagePf fillSearchInput(String query) {
        searchInput.sendKeys(query);
        return this;
    }

    public SearchResultsPagePf startSearch() {
        goButton.click();
        return new SearchResultsPagePf(driver);
    }
}
