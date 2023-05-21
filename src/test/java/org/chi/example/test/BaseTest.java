package org.chi.example.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    //protected ChromeOptions options;
    protected WebDriverWait wait;
    protected Actions action;
    public static final Logger LOG = LogManager.getLogger(BaseTest.class.getName());

    @BeforeTest
    public void setDriver(){
        WebDriverManager.chromedriver().setup();
        //driver = WebDriverManager.chromedriver().create();
//        WebDriverManager.chromedriver()
//                .linux()
//                .browserVersion("95.0.4638.69")
//                .setup();
//        options = new ChromeOptions();
//        options.addArguments("start-maximized");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500));
        action = new Actions(driver);
        BaseTest.LOG.debug("Init HomePage");
    }

    @AfterTest(alwaysRun = true)
    public void closeUp() {
        if (driver != null)
            driver.quit();
    }



}
