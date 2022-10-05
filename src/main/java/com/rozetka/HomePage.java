package com.rozetka;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private final SelenideElement searchInput = $("input[name=\"search\"]");

    public SearchResultsPage searchProduct(String input){
        searchInput.setValue(input).pressEnter();
        return new SearchResultsPage();
    }



}
