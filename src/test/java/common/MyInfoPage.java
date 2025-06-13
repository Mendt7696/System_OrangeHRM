package common;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyInfoPage extends BaseTest {
    WebDriver driver;
    WebDriverWait wait;

    //Constructor
    public MyInfoPage(WebDriver driver) {
        super();
        this.driver = driver;

        //get explictwait
        long explicitWait = Long.parseLong(ConfigReader.getProperty("explicitTimeout=40"));
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(explicitWait));
    }

    //Locator

}
