package com.google.tests.calculations_result_page;

import com.google.pages.GoogleResultsPage;
import com.google.pages.GoogleWelcomePage;
import com.google.tests.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationsByKeyboardTests extends BaseTest {

    static GoogleWelcomePage welcomePage = new GoogleWelcomePage();
    GoogleResultsPage resultsPage = new GoogleResultsPage();

    @BeforeAll
    public static void beforeSuite(){
        welcomePage.enterSearchedValue(SEARCHED_VALUE);
    }

    @ParameterizedTest(name = "{index} => {0}")
    @CsvSource(delimiter = '|', textBlock = """
        addition                       | 10+5                 | 15
        add positive and negative      | 0-100+50             | -50
        add negative numbers           | 0-100+(-20)          | -120
        add decimal numbers            | 1,2354+3.9999        | 5,2353
        subtraction                    | 10-5                 | 5
        subtract negative and positive | 0-100-6              | -106
        subtract negative numbers      | 0-100-(-500)         | 400
        subtract decimal numbers       | 783.4444-5463,33     | -4679,8856
        multiplication                 | 10*5                 | 50
        multiplication by zero         | 10*0                 | 0
        multiple negative numbers      | (-19191)*(-3333)     | 63963603
        multiple decimal numbers       | 23223232,343*333.5555| 7746236875.79
        division                       | 10/5                 | 2
        division by zero               | 10/0                 | undefined
        divide negative numbers        | (-8484848484)/(-4774)| 1777303.82991
        divide decimal numbers         | 64646464,44848/99.99 | 646529.297415
        arithmetic example             | 10+45:5              | 19   
        factorial                      | 5!                   | 120 
        X to the power of Y            | 1000000000000^5      | 1e+60
        sin()                          | s90                  | 0.8939966636
        cos()                          | c90                  | -0.44807361612
        tan()                          | t90                  | -1.99520041221
        ln()                           | le                   | 1
    """)
    public void calculateValuesFromKeyboardTest(String description, String arithmeticExpression, String result){
        resultsPage.calculateArithmeticExpressionUsingKeyboard(arithmeticExpression);
        assertEquals(result, resultsPage.getCalculationResult());
    }
    //10+45:5, 19",   // failed, because we cant enter ":" from keyboard, only "/"
    //"10/0, undefined"  // failed, result: "Infinity", in other tests "undefined"
}
