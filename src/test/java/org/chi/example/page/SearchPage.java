package org.chi.example.page;
import org.chi.example.test.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[@class='total']")
    WebElement totalResultText;

    @FindBy(xpath = "//li[@ga-position='0']//a[@title]")
    WebElement nameFirst;

    @FindBy(xpath = "//li[@ga-position='0']//div[@class='pprice']")
    WebElement priceFirst;

    @FindBy(xpath = "//li[@ga-position='0']//span[@class='ti-t']")
    WebElement availabilityFirst;

    @FindBy(xpath = "//li[@ga-position='0']//div[@class='ti-id stop-select']")
    WebElement codeFirst;

    public SearchPage (WebDriver driver){
        this.driver=driver;
        wait =new WebDriverWait(driver, Duration.ofSeconds(10),Duration.ofMillis(500));
        BaseTest.LOG.debug("Init searchbox");
    }

    public String getTotalResultText(){
        String text = totalResultText.getText();
        BaseTest.LOG.debug("Get totalResultText: "+ text);
        return text;
    }
    public WebElement getTotalResultElement(){ return totalResultText;}

    public String getName(){
        wait.until(ExpectedConditions.visibilityOf(nameFirst));
        String text = nameFirst.getText();
        BaseTest.LOG.debug("Get Name");
        return text;
    }

    public String getPrice(){
        wait.until(ExpectedConditions.visibilityOf(priceFirst));
        String text = priceFirst.getText();
        BaseTest.LOG.debug("First price "+ text);
        return text;
    }

    public String getAvailability(){
        wait.until(ExpectedConditions.visibilityOf(availabilityFirst));
        String text = availabilityFirst.getText();
        BaseTest.LOG.debug("First availability "+ text);
        return text;
    }

    public String getCode(){
        wait.until(ExpectedConditions.visibilityOf(codeFirst));
        String text = codeFirst.getText();
        BaseTest.LOG.debug("First code"+text);
        return text;
    }

}
