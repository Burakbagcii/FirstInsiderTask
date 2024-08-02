package utils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.helpers.PropertyManager;

public class BrowserManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private static WebDriverWait wait = null;
    private static BrowserManager browserManager = new BrowserManager();

    public static BrowserManager getInstance() {
        if(browserManager == null) {
            browserManager = new BrowserManager();
        }
        return browserManager;
    }

    public WebDriver getDriver(){
        return driver.get();
    }

    public WebDriverWait getWait(){
        return wait;
    }

    public void initialize(String browserName) {
        switch (browserName) {
            case "Chrome": {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--test-type");
                options.addArguments("--disable-extensions");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--allow-running-insecure-content");
                options.addArguments("--disable-translate");
                options.addArguments("--always-authorize-plugins");
                options.addArguments("--disable-infobars");
                options.addArguments("--enable-automation");
                options.setExperimentalOption("useAutomationExtension", false);
                options.addArguments("--remote-allow-origins=*");
                System.setProperty("webdriver.chrome.driver", getClass().getClassLoader()
                        .getResource(PropertyManager.getInstance().getProperty("webDrivers")).getFile());
                ChromeDriver chromeDriver = new ChromeDriver();
                chromeDriver.get(PropertyManager.getInstance().getProperty("url"));
                driver.set(chromeDriver);
                break;
            }
            case "Firefox": {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--test-type");
                options.addArguments("--disable-extensions");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--allow-running-insecure-content");
                options.addArguments("--disable-translate");
                options.addArguments("--always-authorize-plugins");
                options.addArguments("--disable-infobars");
                options.addArguments("--enable-automation");
                System.setProperty("webdriver.chrome.driver", getClass().getClassLoader()
                        .getResource(PropertyManager.getInstance().getProperty("webDrivers")).getFile());
                FirefoxDriver firefoxDriver = new FirefoxDriver();
                firefoxDriver.get(PropertyManager.getInstance().getProperty("url"));
                driver.set(firefoxDriver);
                break;
            }
            default: {

            }
        }
        manage();
        setDefaultPageTimeout();
        wait = getWebDriverWait();
    }

    public void manage(){
        if (driver.get() != null) {
            driver.get().manage().window().maximize();
            driver.get().manage().deleteAllCookies();
        }
    }

    public void setDefaultPageTimeout(){
        if (driver.get() != null) {
            driver.get().manage().timeouts().pageLoadTimeout(
                    Integer.parseInt(PropertyManager.getInstance().getProperty("pageLoadTimeout")),
                    TimeUnit.SECONDS);
        }
    }

    public WebDriverWait getWebDriverWait(){
        if (driver.get() != null) {
            return new WebDriverWait(driver.get(), Duration.ofSeconds(
                    Integer.parseInt(PropertyManager.getInstance().getProperty("pageLoadTimeout"))));
        }
        return null;
    }

    public void quitWebdriver() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }
}
