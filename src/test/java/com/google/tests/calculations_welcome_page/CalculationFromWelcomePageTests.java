package com.google.tests.calculations_welcome_page;

import com.google.pages.GoogleResultsPage;
import com.google.pages.GoogleWelcomePage;
import com.google.tests.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationFromWelcomePageTests extends BaseTest {

    GoogleWelcomePage welcomePage = new GoogleWelcomePage();
    GoogleResultsPage resultsPage;

    @ParameterizedTest
    @CsvSource(value = {
            "10+5, 15",
            "10-5, 5",
            "10*5, 50",
            "10/5, 2",
            "π, 3.14159265359",
            "10+45:5, 19",
            "5!, 120",
            "log(10), 1",
            "√(20736), 144",
            "1000000000000^5, 1e+60",
            "10*0, 0",
            "10/0, undefined"
    })
    public void calculateValuesOnWelcomePageTest(String arithmeticExpression, String result){
        resultsPage = welcomePage.enterSearchedValue(arithmeticExpression);
        String actualResult;
        if (arithmeticExpression.contains("/0")){
            actualResult = resultsPage.getCalculationResultZeroDivision();
        } else {
            actualResult = resultsPage.getCalculationResult();
        }
        assertEquals(result, actualResult);
    }
}
