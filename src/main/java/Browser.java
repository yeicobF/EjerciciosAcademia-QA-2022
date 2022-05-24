import java.util.Map;
import java.util.Objects;

import static java.util.Map.entry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Browser {
  public static final String driversFolder = "drivers/";
  // Mapa inmutable.
  public static final Map<String, String> driversMap = Map
      .ofEntries(
          entry("chrome", "chromedriver.exe"),
          entry("edge", "msedgedriver.exe"));

  public static WebDriver getBrowserInstance(String browser) {
    System.out.println("Inicializando Driver.");

    // Si no existe el driver en el Map, se lanza una excepción. Esto
    // permite que no tengamos que hacer esta verificación más adelante.
    if (!driversMap.containsKey(browser)) {
      throw new IllegalArgumentException("Invalid browser: " + browser);
    }

    String driver = "webdriver." + browser + ".driver";
    String executable = driversFolder + driversMap.get(browser);

    // Referenciar el driver que estaremos utilizando.
    System.setProperty(driver, executable);

    if (Objects.equals(browser, "edge")) {
      return new EdgeDriver();
    }

    // Al menos por el momento, si el navegador no es Edge, es Chrome. Me
    // gustaría instanciar a partir del Map para no tener que poner
    // condicionales, pero no sé si sea posible hacerlo.
    //
    // El WebDriver deberá apuntar a Chrome, ya que desde aquí, las
    // instrucciones se ejecutarán sobre el chrome que esté abierto.
    return new ChromeDriver();

  }
}
