package pf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPagePf extends AbstractPagePf {

    @FindBy(css = "#result_0 a.s-access-detail-page")
    private WebElement firstResultLink;

    public SearchResultsPagePf(WebDriver driver) {
        super(driver);
    }

    public ItemPagePf openFirstSearchResult(){
        waitForElementVisible(firstResultLink);
        firstResultLink.click();
        return new ItemPagePf(driver);
    }
}
