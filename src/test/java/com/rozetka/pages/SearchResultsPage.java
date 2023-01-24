package com.rozetka.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {

    private final ElementsCollection productsInCatalog = $$("ul.catalog-grid > li");


    public ElementsCollection getProductsInCatalog() {
        return productsInCatalog;
    }

    public void checkResultsSizeInCatalogIsAtLeast(int expectedSize){
        productsInCatalog.shouldHave
                (CollectionCondition.sizeGreaterThanOrEqual(expectedSize));
    }

    public void checkSearchHeader(String searchInput){
        $(".search-header>h1").shouldHave(text(searchInput));
    }

    public boolean isProductAvailableInCatalog(int productIndex){
        return productsInCatalog.get(productIndex).$("div.goods-tile__availability--available")
                .isDisplayed();
    }

    public void checkProductPriceInCatalog(int productIndex){
        productsInCatalog.get(productIndex).$("span.goods-tile__price-value").
                shouldNotBe(Condition.empty);
    }

    public String getProductName(int productIndex){
        return productsInCatalog.get(productIndex).$("span.goods-tile__title").getText();
    }

    public void clickAddProductToCartButton(int productIndex){
        productsInCatalog.get(productIndex).$(".buy-button").click();
    }

    public void checkBuyButtonExists(int productIndex){
         productsInCatalog.get(productIndex).$(".buy-button_state_in-cart").shouldBe(exist);
    }

    public void checkAddProductNotificationAppears(){
        $("ul.notification__list").should(appear);
    }

    public CartModalWindowPage addProductToCart(int productIndex){
        clickAddProductToCartButton(productIndex);
        checkAddProductNotificationAppears();
        checkBuyButtonExists(productIndex);
        clickAddProductToCartButton(productIndex);
        return new CartModalWindowPage();
    }


}
