package org.chi.example.test;

import org.chi.example.data.Goods;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.chi.example.page.Elmir;
import org.chi.example.page.SearchPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sun.security.util.PendingException;

import java.util.List;


public class TestProd extends BaseTest {

    List<Goods> expected;

    @BeforeTest
    public void setPrecodition() throws PendingException {
        RequestSpecification spec = RestAssured.given();
        String apiURL = "http://localhost:8080/api/goods";
        ValidatableResponse resp = spec.get(apiURL).then();
        expected = resp.extract().body().jsonPath().getList("goods", Goods.class);
        if (expected.isEmpty()) {
            throw new PendingException("No products were found from API call");
        }
    }

    @Test()
    public void verifyProductParametersAfterSearch(){
        Elmir elmir = PageFactory.initElements(driver, Elmir.class);
        SearchPage searchpage = PageFactory.initElements(driver, SearchPage.class);

        LOG.info("Opening Elmir home page");
        driver.get("https://elmir.ua/");
        elmir.closePopupWindow();

        SoftAssert softAssert = new SoftAssert();

        // check product from API
        expected.forEach(goods -> {
            elmir.searchProduct(goods.getName());
            try {
                wait.until(ExpectedConditions.visibilityOf(searchpage.getTotalResultElement()));
                Assert.assertEquals(searchpage.getTotalResultText(), "1 item was found");

                softAssert.assertEquals(searchpage.getName(), goods.getName(), "Product: " + goods.getName() + " name ");
                String actualPrice = searchpage.getPrice() != null && !searchpage.getPrice().isEmpty() ? searchpage.getPrice() : "0";
                softAssert.assertEquals(actualPrice, goods.getPrice(), "Product: " + goods.getName() + " price ");
                String actualAvailability = searchpage.getAvailability() != null && !searchpage.getAvailability().isEmpty() ? searchpage.getAvailability() : "0";
                softAssert.assertEquals(actualAvailability, goods.getAvailability(), "Product: " + goods.getName() + " availability");
                String actualCode = searchpage.getCode() != null && !searchpage.getCode().isEmpty() ? searchpage.getCode() : "0";
                softAssert.assertEquals(actualCode, goods.getCode(), "Product " + goods.getName() + " code");
            } catch (AssertionError e) {
                softAssert.fail("Product was not found " + goods.getName());
            }
        });

        softAssert.assertAll();

    }
}