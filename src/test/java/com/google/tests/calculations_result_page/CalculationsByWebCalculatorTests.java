package com.google.tests.calculations_result_page;

import com.google.pages.GoogleResultsPage;
import com.google.pages.GoogleWelcomePage;
import com.google.tests.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationsByWebCalculatorTests extends BaseTest {

    static GoogleWelcomePage welcomePage = new GoogleWelcomePage();
    static GoogleResultsPage resultsPage;

    @BeforeAll
    public static void beforeSuite(){
        resultsPage = welcomePage.enterSearchedValue(SEARCHED_VALUE);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10+5, 15",
            "10−5, 5",
            "10×5, 50",
            "10÷5, 2",
            "10+45÷5, 19",
            "10×0, 0",
            "10÷0, undefined"  // failed, result: "Infinity", in other tests "undefined"
    })
    public void arithmeticExamplesCalculationByWebCalculatorTest(String expression, String expectedResult){
        resultsPage.calculateArithmeticExpressionUsingWebCalculator(expression);
        assertEquals(expectedResult, resultsPage.getCalculationResult());
    }

    @ParameterizedTest(name = "{index} => calculates function: ({0}({1}) )")
    @CsvSource(delimiter = '|', textBlock = """
        π     | ''   | 3.14159265359
        e     | ''   | 2.71828182846
        sin   | −1   | -0.8414709848
        cos   | 120  | 0.81418097052
        tan   | 0    | 0
        ' ln '| e    | 1
        log   | 1    | 0
        '√ '  | 144  | 12       
    """)
    public void trigonometricFunctionsCalculationByWebCalculatorTest(String function, String number, String result){
        resultsPage.calculateTrigonometricFunctionUsingWebCalculator(function,number);
        assertEquals(result, resultsPage.getCalculationResult());
    }
}
