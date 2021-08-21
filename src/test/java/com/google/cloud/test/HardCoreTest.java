package com.google.cloud.test;

import com.google.cloud.model.ComputeEngine;
import com.google.cloud.page.CalculationResultPage;
import com.google.cloud.page.GoogleCloudHomePage;
import com.google.cloud.service.EstimationCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HardCoreTest extends CommonConditions{

    public String termForSearching ="Google Cloud Platform Pricing Calculator";

    @Test
    public void resultValuesAreSimilarToTotalCostInMail() {

        ComputeEngine testEngine = EstimationCreator.withParametersFroMProperties();
        CalculationResultPage calculationOfCost = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForTerms(termForSearching)
                .chooseTheResultWeNeed()
                .activateComputeEngineChapter()
                .createTestEngineEstimate(testEngine);

        String totalCostFromPage = calculationOfCost.getTotalCost();

        String totalCostFromMail = calculationOfCost.pressEmailEstimateButton()
                .openNewTabAndSwitchToYopmail()
                .getNewRandomMailCopyAndTurnBackToCalculating()
                .fillEmailAndSendMail()
                .switchToPostBox()
                .openMail()
                .getTotalCostFromMail();

        Assert.assertEquals(totalCostFromMail, totalCostFromPage);
    }

}
