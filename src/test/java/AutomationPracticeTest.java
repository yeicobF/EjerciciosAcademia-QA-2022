import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AutomationPracticeTest {
    public static String BASE_URL = "http://automationpractice.com/index.php";

    public static Boolean isPageContactUs(WebDriver driver) {
        WebElement title = driver.findElement(By.xpath("//h1[contains(text(), 'Customer service - Contact us')]"));

        return title.isDisplayed();
    }

    @Test
    public void sendContactMessage() {
        WebDriver driver = Browser.getBrowserInstance("chrome");
        driver.navigate().to(BASE_URL);

        WebElement contactUsButton = driver.findElement(By.xpath("//a[@title='Contact Us']"));
        contactUsButton.click();

        if (!isPageContactUs(driver)) {
            throw new RuntimeException("No se encontró el elemento de contacto");
        }

        /*
         * Esperar a que un elemento esté disponible en la página para proceder.
         *
         * https://www.browserstack.com/guide/wait-commands-in-selenium-webdriver
         * https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/
         * ui/FluentWait.html
         */
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);
        ;

        /*
         * https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/
         * ui/FluentWait.html
         * 
         * WebElement title = wait.until(new Function<WebDriver, WebElement>() {
         * public WebElement apply(WebDriver driver) {
         * return driver.findElement(By.xpath("//h1[contains(text(), 'Contact us')]"));
         * }
         * });
         */
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(), 'Contact us')]")));

        
    }
}
