package com.rozetka.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private final SelenideElement searchInput = $("input[name=\"search\"]");

    public SearchResultsPage searchProduct(String input){
        searchInput.setValue(input).pressEnter();
        return new SearchResultsPage();
    }



}
