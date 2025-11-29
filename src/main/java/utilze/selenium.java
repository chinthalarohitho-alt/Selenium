package utilze;

import Initialization.FrameWorkInitialization;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

public class selenium extends FrameWorkInitialization {

    private static final Random random = new Random();

    public static WebElement id(String Locator){
        return driver.findElement(By.id(Locator));
    }

    public static void wait (int number){
        try {
            Thread.sleep(number);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitForElementVisibility(String locator){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        }catch (TimeoutException e) {
            // only your custom message goes to the report
            throw new AssertionError("Element not visible: " + locator);
        }
    }

    public static WebElement IdLocator(String locator){
       return driver.findElement(By.id(locator));
    }

    public static WebElement ClassNameLocator(String locator){
        return driver.findElement(By.className(locator));
    }
    public static WebElement XpathLocator(String locator){
        return driver.findElement(By.xpath(locator));

    } public static WebElement LinkTextLocator(String locator){
        return driver.findElement(By.linkText(locator));
    }
    public static WebElement PartialLinkTextLocator(String locator){
        return driver.findElement(By.partialLinkText(locator));
    }
    public static WebElement TagNameLocator(String locator){
        return driver.findElement(By.tagName(locator));
    }
    public static WebElement NameLocator(String locator){
        return driver.findElement(By.name(locator));
    }
    public static WebElement CssSelectorLocator(String locator){
        return driver.findElement(By.cssSelector(locator));
    }

    public static WebElement button(String ButtonName){
        return driver.findElement(By.xpath("//button[contains(text(),'"+ButtonName+"')]"));
    }

    public static void click(String locator){
        driver.findElement(By.xpath(locator)).click();
    }

    public static String generateUniqueString(int minLength, int maxLength) {
        if (minLength < 1 || maxLength < minLength) {
            throw new IllegalArgumentException("minLength must be >=1 and <= maxLength");
        }

        int length = minLength + random.nextInt(maxLength - minLength + 1);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, length);
    }

    public static String getText(String locator) {
        return driver.findElement(By.xpath(locator)).getText();
    }

    public static boolean elementVisibility(String locator) {
        try {
            WebElement element = driver.findElement(By.xpath(locator));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
