import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.FluentWait;

public class BasePage {
  public WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  public static Wait<WebDriver> getNewWait(int timeoutSeconds, int pollingMillis, WebDriver driver) {
    /*
     * Esperar a que un elemento esté disponible en la página para proceder.
     *
     * https://www.browserstack.com/guide/wait-commands-in-selenium-webdriver
     * https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/
     * ui/FluentWait.html
     */
    return new FluentWait<WebDriver>(driver)
        .pollingEvery(Duration.ofMillis(pollingMillis))
        .withTimeout(Duration.ofSeconds(timeoutSeconds))
        .ignoring(NoSuchElementException.class);
  }
}
