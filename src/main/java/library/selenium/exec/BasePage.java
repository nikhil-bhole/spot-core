package library.selenium.exec;

import library.common.TestContext;
import library.selenium.core.Page;
import library.selenium.exec.driver.factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;



public class BasePage extends Page {

    public BasePage() {
        super(DriverFactory.getInstance().getDriver());
    }

    public WebDriver getDriver() {
        log.debug("obtaining the driver for current thread");
        return DriverFactory.getInstance().getDriver();
    }

    public WebDriverWait getWait() {
        log.debug("obtaining the wait for current thread");
        return DriverFactory.getInstance().getWait();
    }

    public void quitDriver() {
        log.debug("quitting the driver and removing from current thread of driver factory");
        DriverFactory.getInstance().quit();
    }

    public SoftAssert sa() {
        return TestContext.getInstance().sa();
    }

    public void performDriverOperation(String action, String value) {
        log.debug("performing selenium driver operation:" + action + ";" + value);
        switch (action) {
            case "launch":
                getDriver();
                break;
            case "goto":
                gotoURL(value);
                break;
            case "back":
                getDriver().navigate().back();
                break;
            case "forward":
                getDriver().navigate().forward();
                break;
            case "quit":
                quitDriver();
                break;
        }
    }


}