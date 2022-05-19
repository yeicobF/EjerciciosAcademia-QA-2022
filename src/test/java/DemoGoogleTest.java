import java.util.Map;
import static java.util.Map.entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoGoogleTest {
    public static String driversFolder = "drivers/";
    // Mapa inmutable.
    public static Map<String, String> driversMap = Map.ofEntries(
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

        if (browser == "edge") {
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

    public static void login(String username, String password, WebDriver driver) {
        // Navegamos con el driver hacia google.
        driver.navigate().to("https://demoqa.com/login");

        // Escribir usuario en el formulario.
        System.out.println("Escribiendo usuario");
        WebElement inputUser = driver.findElement(By.xpath("//input[@id='userName']"));
        inputUser.sendKeys(username);

        // Escribir contraseña en el formulario.
        System.out.println("Escribiendo contraseña");
        WebElement inputPassword = driver.findElement(By.xpath("//input[@id='password']"));
        inputPassword.sendKeys(password);

        // Hacer click en el botón de login.
        System.out.println("Haciendo click en el botón de login");
        WebElement loginButton = driver.findElement(
                By.xpath("//button[@id='login' and @type='button' and contains(text(), 'Login')]"));
        loginButton.click();
    }

    @Test
    public void loginInvalidCredentials() {
        WebDriver driver = getBrowserInstance("chrome");
        login("invalid", "invalid", driver);

        // Temporalmente, haremos uso de Thread.sleep para esperar a que el
        // navegador actúe antes de revisar los nuevos elementos.
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Validar mensaje de error del login.
        System.out.println("Validando mensaje de error");

        /*
         * // Encontrar elemento por selector CSS. WebElement
         * warningInvalidMessage =
         * driver.findElement(By.cssSelector("p[id='name']"));
         */
        // Encontrar elemento con Xpath.
        WebElement warningInvalidCredentials = driver.findElement(
                By.xpath("//p[contains(text(),'Invalid username or password!') and @id='name']"));

        // Vamos a hacer uso de Assert para validar ciertas cosas. En este caso,
        // vamos a validar que el valor del warning sea true, que esté
        // desplegado. assertTrue solo acepta valores booleanos.
        Assert.assertTrue(warningInvalidCredentials.isDisplayed());
    }

    @Test
    // Este Test no lo haremos, ya que tiene un Captcha.
    public void createNewUser() {
        WebDriver driver = getBrowserInstance("chrome");

        // Abrir página.
        System.out.println("Navegando a la página DemoQA.");
        driver.navigate().to("https://www.demoqa.com/login");

        System.out.println("Creando nuevo usuario.");
        WebElement inputUser = driver.findElement(By.xpath("//button[@id='newUser']"));
    }

    @Test
    public void loginValidCredentials() {
        // public void loginValidCredentials(String browser) {
        String user = "vectortesting";
        String password = "Vector1234!";
        WebDriver driver = getBrowserInstance("chrome");

        login(user, password, driver);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Buscar que se encuentre el username en el label.
        System.out.println("Buscando el username en el label");

        // WebElement userLabel =
        // driver.findElement(By.xpath("//label[contains(text(), user)]"));
        WebElement userLabel = driver.findElement(
                By.xpath("//label[@id='userName-value' and contains(text(), user)]"));

        Assert.assertTrue(userLabel.isDisplayed());

        // Cerramos la ventana actual, también terminando el driver si es la
        // última instancia del driver.
        /* driver.close(); */

        // Cerramos todas las instancias del driver.
        /* driver.quit(); */
    }
}
