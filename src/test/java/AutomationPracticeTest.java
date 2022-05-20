import java.time.Duration;
import java.util.List;
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

    @Test
    public void sendContactMessage() {
        WebDriver driver = Browser.getBrowserInstance("chrome");
        driver.navigate().to(BASE_URL);

        WebElement contactUsButton = driver.findElement(By.xpath("//a[@title='Contact Us']"));
        contactUsButton.click();

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
        System.out.println("Nos encontramos en Contact Us.");

        WebElement subjectButton = driver.findElement(By.xpath("//select[@id='id_contact']"));
        subjectButton.click();

        // Obtenemos todas las opciones.
        List<WebElement> subjectOptions = driver
                .findElements(By.cssSelector(
                        "select[id='id_contact'] > option"));
        System.out.println("Hay " + subjectOptions.size() + " opciones.");
        subjectOptions.forEach(option -> System.out.println(option.getText()));

        subjectOptions.get(1).click();
    }
}
