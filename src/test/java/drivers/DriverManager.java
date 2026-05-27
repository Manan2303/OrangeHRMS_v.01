package drivers;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Get Driver
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Local Driver Initialization
    public static void initDriver(String br, String os ,String URL) {

        switch (br.toLowerCase()) {

        case "chrome":

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            if (os.toLowerCase().contains("linux")) {

                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }

            driver.set(new ChromeDriver(options));
            break;

        case "edge":

            WebDriverManager.edgedriver().setup();
            driver.set(new EdgeDriver());
            break;

        case "firefox":

            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
            break;

        default:
            throw new IllegalArgumentException("Invalid browser name");
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().get(URL);
    }

    // Remote Driver Initialization
    public static void remoteDriver(String os, String br) throws Exception {

        DesiredCapabilities cap = new DesiredCapabilities();

        // OS
        if (os.equalsIgnoreCase("Windows")) {
            cap.setPlatform(Platform.WIN10);
        }

        else if (os.equalsIgnoreCase("Linux")) {
            cap.setPlatform(Platform.LINUX);
        }

        else if (os.equalsIgnoreCase("Mac")) {
            cap.setPlatform(Platform.MAC);
        }

        else {
            System.out.println("Invalid Operating System");
        }

        // Browser
        switch (br.toLowerCase()) {

        case "chrome":
            cap.setBrowserName("chrome");
            break;

        case "edge":
            cap.setBrowserName("MicrosoftEdge");
            break;

        case "firefox":
            cap.setBrowserName("firefox");
            break;

        default:
            System.out.println("Invalid Browser");
            return;
        }

        driver.set(
                new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"),
                        cap));

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
    }

    // Quit Driver
    public static void quitDriver() {

        if (getDriver() != null) {

            getDriver().quit();
            driver.remove();
        }
    }
}