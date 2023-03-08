package library.selenium.exec.driver.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import library.selenium.exec.driver.managers.*;

/**
 * Threadlocal instance of WebDriver using Factory pattern
 * Supports remote execution via Saucelabs / BrowserStack / Selenium Grid or local execution.
 * <p>
 * The use of either saucelabs/grid/local is set in the Context instance for
 * the execution thread (via the TestNG DataProvider). For saucelabs or grid then the address
 * of the target host and any credentials are specified in the project properties file.
 * <p>
 * For local execution the broswers currently supported are Firefox, Chrome, IE, Edge,
 * Safari, HTML Unit and Phantom JS but this can be extended as needed by adding further
 * DriverManager classes.
 * <p>
 * The locations of the required drivers/binaries are specified in the project properties file.
 */

public class DriverFactory {

    public enum ServerType {
        local, grid, saucelabs, browserstack, appium;
    }

    public enum BrowserType {
        chrome, firefox, ie, edge, safari, phantomjs, htmlunit;
    }

    protected DriverFactory() {
    }

    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {
        return instance;
    }

    ThreadLocal<DriverManager> driverManager = new ThreadLocal<DriverManager>() {
        protected DriverManager initialValue() {
            return setDM();
        }
    };

    public DriverManager driverManager() {
        return driverManager.get();
    }

    public WebDriver getDriver() {
        return driverManager.get().getDriver();
    }

    public WebDriver returnDriver() {
        return driverManager.get().returnDriver();
    }

    public WebDriverWait getWait() {
        return driverManager.get().getWait();
    }

    public void quit() {
        driverManager.get().quitDriver();
        driverManager.remove();
    }

    public DriverManager setDM() {
        /**
         * TODO : Set server type to run tests on sauce labs/ browser stack
         */
        //ServerType serverType = ServerType.valueOf(DriverContext.getInstance().getTechStack().get("seleniumServer"));
        String browserType = "chrome";
        driverManager.set(new ChromeDriverManager());
        return driverManager.get();
    }
} 