package com.google.cloud.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultPage extends AbstractPage {

    private String searchTerm;

    private final By resultOfSearchingLocator = By.xpath("//a[@class='gs-title']");

    public SearchResultPage(WebDriver driver, String searchTerm) {
        super(driver);
        this.searchTerm = searchTerm;
        PageFactory.initElements(this.driver, this);
    }

    public PlatformPricingCalculatorPage chooseTheResultWeNeed() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(resultOfSearchingLocator));
        List<WebElement> searchResultsList = driver.findElements(resultOfSearchingLocator);
        for (WebElement search : searchResultsList) {
            if (search.getText().equals(searchTerm)) {
                search.click();
                break;
            }
        }
        return new PlatformPricingCalculatorPage(driver);
    }

}
