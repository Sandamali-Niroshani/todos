package testScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ReadConfig;

public class BaseTest {

    ReadConfig readConfig = new ReadConfig();

    public String baseUrl = readConfig.getApplicationURL();
    public String browser = readConfig.getBrowser();
    public static WebDriver driver;

    @BeforeMethod
    public void setup(){

        if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(browser.equals("firefox")){

            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.get(baseUrl) ;
        driver.manage().window().maximize();
        System.out.println("Successfully redirected into Todos website");
    }

    @AfterMethod
    public void close(){
        driver.quit();
    }



}
