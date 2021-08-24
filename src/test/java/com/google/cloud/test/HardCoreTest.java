package com.google.cloud.test;

import com.google.cloud.model.ComputeEngine;
import com.google.cloud.page.CalculationResultPage;
import com.google.cloud.page.GoogleCloudHomePage;
import com.google.cloud.service.EstimationCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HardCoreTest extends CommonConditions {

    private static final String TERM_FOR_SEARCHING = "Google Cloud Platform Pricing Calculator";

    @Test
    public void resultValuesAreSimilarToTotalCostInMail() {

        ComputeEngine testEngine = EstimationCreator.withParametersFroMProperties();
        CalculationResultPage totalCalculationOfCost = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForTerms(TERM_FOR_SEARCHING)
                .chooseTheResultWeNeed()
                .activateComputeEngineChapter()
                .createTestEngineEstimate(testEngine);

        String totalCostFromPage = totalCalculationOfCost.getTotalCost();

        String totalCostFromMail = totalCalculationOfCost.pressEmailEstimateButton()
                .openNewTabAndSwitchToYopmail()
                .getNewRandomMailCopyAndTurnBackToCalculating()
                .fillEmailAndSendMail()
                .switchToPostBox()
                .openMail()
                .getTotalCostFromMail();

        Assert.assertEquals(totalCostFromMail, totalCostFromPage);
    }

}
