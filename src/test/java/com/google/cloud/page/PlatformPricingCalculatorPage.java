package com.google.cloud.page;

import com.google.cloud.model.ComputeEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PlatformPricingCalculatorPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//md-tab-item/div[@class='tab-holder compute'][@title='Compute Engine']")
    private WebElement computeEngineButton;

    @FindBy(xpath = "//iframe[contains(@name, 'goog_')]")
    private WebElement firstLevelIframe;

    @FindBy(id = "myFrame")
    private WebElement secondLevelIframe;

    @FindBy(id = "input_67")
    private WebElement numberOfInstanceField;

    @FindBy(id = "select_80")
    private WebElement operationSystemField;

    @FindBy(id = "select_84")
    private WebElement vmClassField;

    @FindBy(id = "select_92")
    private WebElement seriesField;

    @FindBy(id = "select_94")
    private WebElement machineTypeField;

    @FindBy(xpath = "//form[@name = 'ComputeEngineForm']//md-checkbox[@aria-label = 'Add GPUs']")
    private WebElement addGPUCheckbox;

    @FindBy(id = "select_value_label_429")
    private WebElement numberOfGPUField;

    @FindBy(id = "select_value_label_430")
    private WebElement gpuTypeField;

    @FindBy(id = "select_value_label_391")
    private WebElement valueSSDField;

    @FindBy(xpath = "//*[@id = 'select_value_label_65']/span")
    private WebElement dataCenterLocationField;

    @FindBy(id = "select_value_label_66")
    private WebElement commitmentPeriodField;

    @FindBy(xpath = "//button[@aria-label = 'Add to Estimate']")
    private WebElement addToEstimateButton;

    private final By COMPUTE_ENGINE_MODULE_LOCATOR = By.xpath("//md-tab-item/div[@class='tab-holder compute'][@title='Compute Engine']");
    private final By MACHINE_TYPE_FROM_DROP_DOWN_LIST = By.xpath("//div[contains(@id, 'select_container_')][@aria-hidden = 'false']/md-select-menu/md-content/md-optgroup/md-option/div[contains(@class, 'md-text')]");
    private final By ITEM_FROM_DROP_DOWN_LIST = By.xpath("//div[contains(@id, 'select_container_')][@aria-hidden = 'false']/md-select-menu/md-content/md-option/div[contains(@class, 'md-text')]");

    public PlatformPricingCalculatorPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    private void switchToIframe() {

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(firstLevelIframe));
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(secondLevelIframe));

    }

    public PlatformPricingCalculatorPage activateComputeEngineChapter() {

        switchToIframe();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .presenceOfElementLocated(COMPUTE_ENGINE_MODULE_LOCATOR));
        computeEngineButton.click();

        return this;
    }

    public CalculationResultPage createTestEngineEstimate(ComputeEngine computeEngine) {
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

    public void inputNumberOfInstance(String term) {

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(numberOfInstanceField)).sendKeys(term);

    }

    private void getDropDownListAndSelectValue(String term) {
        List<WebElement> listOfItems = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(ITEM_FROM_DROP_DOWN_LIST));
        for (WebElement item : listOfItems) {
            if (item.getText().equals(term)) {
                item.click();
                break;
            }
        }
    }

    public void selectOperationSystem(String term) {

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(operationSystemField)).click();
        getDropDownListAndSelectValue(term);

    }

    public void selectVMClass(String term) {

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(vmClassField)).click();
        getDropDownListAndSelectValue(term);

    }

    public void selectSeriesAndMachineType(String series, String type) {

        logger.info(type);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(seriesField)).click();
        getDropDownListAndSelectValue(series);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(machineTypeField)).click();

        List<WebElement> listOfTypes = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(MACHINE_TYPE_FROM_DROP_DOWN_LIST));
        for (WebElement item : listOfTypes) {
            if (item.getText().equals(type)) {
                item.click();
                break;
            }
        }
    }

    public void addGPUAndSetParameters(String numberOfGPUs, String gPUsType) {

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(addGPUCheckbox))
                .click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(numberOfGPUField))
                .click();
        getDropDownListAndSelectValue(numberOfGPUs);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(gpuTypeField))
                .click();
        getDropDownListAndSelectValue(gPUsType);

    }

    public void setLocalSSD(String valueSSD) {

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(valueSSDField))
                .click();
        getDropDownListAndSelectValue(valueSSD);

    }

    public void setDataCenterLocation(String location) {

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(dataCenterLocationField))
                .click();
        getDropDownListAndSelectValue(location);

    }

    public void setCommitmentPeriod(String commitmentTerm) {

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(commitmentPeriodField))
                .click();
        getDropDownListAndSelectValue(commitmentTerm);

    }

    public void pressButtonAddToEstimate() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(addToEstimateButton))
                .click();
        new CalculationResultPage(driver);
    }

}
