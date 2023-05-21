package org.chi.example.page;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.chi.example.test.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Elmir {
    WebDriverWait wait;
    WebDriver driver;

    @FindBy(xpath = "//*[@id='q']")
    WebElement searchPage;

    @FindBy(xpath = "//*[@id='subscribe-deny']")
    WebElement popupWindow;

    public void searchProduct (String text) {
        BaseTest.LOG.debug("Searching items were started");
        searchPage.clear();
        BaseTest.LOG.debug("Cleared searchbox");
        searchPage.click();
        searchPage.sendKeys(text);
        BaseTest.LOG.debug("Filled text field "+ text);
        searchPage.sendKeys(Keys.ENTER);
        BaseTest.LOG.info("Searching for items, click ENTER");
    }
    public Elmir(WebDriver driver){
        this.driver=driver;
        wait =new WebDriverWait(driver,Duration.ofSeconds(10),Duration.ofMillis(500));
        BaseTest.LOG.debug("Init Elmir");
    }

    public void closePopupWindow (){
        BaseTest.LOG.debug("Method closePopupWindow started");
        wait.until(ExpectedConditions.visibilityOf(popupWindow));
       popupWindow.click();
       BaseTest.LOG.debug("PopupWindow was closed");


    }
}
