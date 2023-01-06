package com.rozetka.tests;

import com.rozetka.pages.CartModalWindowPage;
import com.rozetka.pages.HomePage;
import com.rozetka.pages.SearchResultsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

public class SearchProductsTests extends BaseTest {

    private final String searchInput = "asus";
    SearchResultsPage resultsPage = new SearchResultsPage();
    CartModalWindowPage cartModal = new CartModalWindowPage();

    @BeforeEach
    public void beforeTest(){
        clearBrowserCookies();
        open(BASE_URL);
        new HomePage().searchProduct(searchInput);
    }

    @Test
    void searchProductFromHomePage(){
        resultsPage.checkSearchHeader(searchInput);
        resultsPage.checkResultsSizeInCatalogIsAtLeast(10);
    }

    @Test
    void verifySearchedProductPriceAndAvailability(){
        resultsPage.checkProductPriceInCatalog(1);
        resultsPage.checkProductIsAvailableInCatalog(1);
    }

    @Test
    void addProductToTheCartFromSearchCatalog(){
        String productNameInCatalog = resultsPage.getProductName(1);
        System.out.println("productNameInCatalog: " + productNameInCatalog);
        resultsPage.addProductToCart(1);

        String productNameInCart = cartModal.getAddedProductTitle();
        System.out.println("productNameInCart: " + productNameInCart);

        cartModal.checkProductsQuantityInCart(1);
        Assertions.assertEquals(productNameInCatalog, productNameInCart);
    }

}
