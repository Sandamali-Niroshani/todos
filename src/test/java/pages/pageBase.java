package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class pageBase {

    /**
     * This method is used to capture the screenshots.
     * It captures the screenshot and saves it in the Screenshots folder.
     *
     * @param driver WebDriver
     * @param name   name of the screenshot
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void captureScreenshots(WebDriver driver, String name) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + name + ".png");
        try {
            FileUtils.copyFile(source, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Screenshot taken");
    }

    /**
     * This method is used to wait for the element to be visible.
     *
     * @param driver  WebDriver
     * @param element WebElement
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void waitForElementVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This method is used to wait for the element to be invisible.
     *
     * @param driver  WebDriver
     * @param element WebElement
     * @return boolean
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public boolean waitForElementInvisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
        return true;
    }

    /**
     * This method is used to wait for the element to be invisible.
     *
     * @param driver WebDriver
     * @param xpath  xpath of the element
     * @return boolean
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public boolean waitForElementInvisible(WebDriver driver, String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
        return true;
    }

    /**
     * This method is used to move to element
     * @param driver WebDriver
     * @param element WebElement
     * @return void
     * @autor Niroshani
     * @completed 2025-02-06
     */
    public void moveToElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

}
