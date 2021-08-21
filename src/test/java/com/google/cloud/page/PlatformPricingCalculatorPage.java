package com.google.cloud.page;

import com.google.cloud.model.ComputeEngine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PlatformPricingCalculatorPage {

    WebDriver driver;

    @FindBy(xpath = "//md-tab-item/div[@class='tab-holder compute'][@title='Compute Engine']")
    private WebElement computeEngineButton;

    public PlatformPricingCalculatorPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    private PlatformPricingCalculatorPage switchToIframe() {

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@name, 'goog_')]")));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("myFrame")));
        return this;
    }

    public PlatformPricingCalculatorPage activateComputeEngineChapter() {

        switchToIframe();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//md-tab-item/div[@class='tab-holder compute'][@title='Compute Engine']")));
        computeEngineButton.click();
        return this;
    }

    public CalculationResultPage createTestEngineEstimate(ComputeEngine computeEngine){
        inputNumberOfInstance(computeEngine.getNumberOfInstance());
        selectOperationSystem(computeEngine.getOperationSystem());
        selectVMClass(computeEngine.getVmClass());
        selectSeriesAndMachineType(computeEngine.getSeries(), computeEngine.getType());
        addGPUAndSetParameters(computeEngine.getNumberOfGPUs(), computeEngine.getGPUsType());
        setLocalSSD(computeEngine.getValueSSD());
        setDataCenterLocation(computeEngine.getLocation());
        setCommitmentPeriod(computeEngine.getCommitmentTerm());
        pressButtonAddToEstimate();
        return new CalculationResultPage(driver);
    }

    public PlatformPricingCalculatorPage inputNumberOfInstance(String term) {

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("input_67"))).sendKeys(term);

        return this;
    }

    private PlatformPricingCalculatorPage getDropDownListAndSelectValue(String term) {
        List<WebElement> listOfOperationSystems = new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@id, 'select_container_')][@aria-hidden = 'false']/md-select-menu/md-content/md-option/div[contains(@class, 'md-text')]")));
        for (WebElement item : listOfOperationSystems) {
            if (item.getText().equals(term)) {
                item.click();
                break;
            }
        }
        return this;
    }

    public PlatformPricingCalculatorPage selectOperationSystem(String term) {

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id("select_80"))).click();
        getDropDownListAndSelectValue(term);

        return this;
    }

    public PlatformPricingCalculatorPage selectVMClass(String term) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id("select_84"))).click();
        getDropDownListAndSelectValue(term);
        return this;
    }

    public PlatformPricingCalculatorPage selectSeriesAndMachineType(String series, String type) {

        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(By.id("select_92"))).click();
        List<WebElement> listOfSeries = new WebDriverWait(driver, 220)
                .until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@id, 'select_container_')][@aria-hidden = 'false']/md-select-menu/md-content/md-option/div[contains(@class, 'md-text')]")));
        for (WebElement item : listOfSeries) {
            if (item.getText().contains(series)) {
                item.click();
                break;
            }
        }
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(By.id("select_94"))).click();
        List<WebElement> listOfTypes = new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@id, 'select_container_')][@aria-hidden = 'false']/md-select-menu/md-content/md-optgroup/md-option/div[contains(@class, 'md-text')]")));
        for (WebElement item : listOfTypes) {
            if (item.getText().equals(type)) {
                item.click();
                break;
            }
        }

        return this;
    }

    public PlatformPricingCalculatorPage addGPUAndSetParameters(String numberOfGPUs, String gPUsType){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@name = 'ComputeEngineForm']//md-checkbox[@aria-label = 'Add GPUs']")))
                .click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id("select_value_label_429")))
                .click();
        getDropDownListAndSelectValue(numberOfGPUs);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id("select_value_label_430")))
                .click();
        getDropDownListAndSelectValue(gPUsType);

        return this;
    }

    public PlatformPricingCalculatorPage setLocalSSD(String valueSSD){

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.id("select_value_label_391")))
                .click();
        getDropDownListAndSelectValue(valueSSD);

        return this;
    }

    public PlatformPricingCalculatorPage setDataCenterLocation(String location){

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id = 'select_value_label_65']/span")))
                .click();
        getDropDownListAndSelectValue(location);

        return this;

    }

    public PlatformPricingCalculatorPage setCommitmentPeriod(String commitmentTerm){

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id = 'select_value_label_66']")))
                .click();
        getDropDownListAndSelectValue(commitmentTerm);

        return this;
    }

    public CalculationResultPage pressButtonAddToEstimate(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label = 'Add to Estimate']")))
                .click();
        return new CalculationResultPage(driver);
    }


}
