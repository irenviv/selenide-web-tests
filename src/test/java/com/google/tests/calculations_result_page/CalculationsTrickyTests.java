package com.google.tests.calculations_result_page;

import com.google.pages.GoogleResultsPage;
import com.google.pages.GoogleWelcomePage;
import com.google.tests.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationsTrickyTests extends BaseTest {

    static GoogleWelcomePage welcomePage = new GoogleWelcomePage();
    GoogleResultsPage resultsPage = new GoogleResultsPage();
    private String arithmeticExample;

    @BeforeAll
    public static void beforeSuite(){
        welcomePage.enterSearchedValue(SEARCHED_VALUE);
    }

    @Test
    public void verifyWrongEnteredDigitIsDeletedTest(){
        arithmeticExample = "562*6664";
        resultsPage.enterArithmeticExpressionUsingKeyboardButtons(arithmeticExample);
        resultsPage.clickButtonOnWebCalculator("CE");
        assertEquals("562 × 666", resultsPage.getCalculationResult());
    }

    @Test
    public void verifyCalculationHistoryIsSaved(){
        arithmeticExample = "12 + 23";
        resultsPage.calculateValuesUsingWebCalculator(arithmeticExample);
        resultsPage.clickCalculationHistoryButton();
        assertEquals(arithmeticExample + " = 35", resultsPage.getLastArithmeticExampleFromHistory());
    }

    @Test
    public void verifyPreviousActionsAreShownInMainWindowTest(){
        arithmeticExample = "2.5 × 2.5 =";
        String calculationResult = "Ans = 6.25";
        resultsPage.calculateValuesUsingWebCalculator(arithmeticExample);
        assertEquals(arithmeticExample, resultsPage.getPreviousCalculationOnMainWindow());

        resultsPage.clickButtonOnWebCalculator("CE");
        assertEquals(calculationResult, resultsPage.getPreviousCalculationOnMainWindow());
        assertEquals("0", resultsPage.getCalculationResult());
    }

    @Test
    public void navigatingThroughPreviousCalculationTest(){
        arithmeticExample = "23 + 23";
        resultsPage.calculateValuesUsingWebCalculator(arithmeticExample);
        resultsPage.clickCalculationHistoryButton();
        resultsPage.clickOnLastArithmeticExample();
        assertEquals(arithmeticExample, resultsPage.getCalculationResult());
    }
}
