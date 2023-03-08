package library.selenium.exec;


import library.common.TestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.asserts.SoftAssert;
import library.selenium.exec.driver.factory.DriverFactory;

import java.lang.reflect.Method;

/**
 * Class to hold start up (set test context) and tear down (shut down browser) for selenium test. This class should be
 * extended by each test class in the test project
 */
public class BaseTest {

    protected Logger log = LogManager.getLogger(this.getClass().getName());
    protected BasePage po;

    protected TestContext context = TestContext.getInstance();
    
    /**
     * return web driver for the current thread - can be used when running using TestNG
     */
    public WebDriver getDriver() {
        log.debug("obtaining the driver for current thread");
        return DriverFactory.getInstance().getDriver();
    }

    /**
     * return web driver wait for the current thread - can be used when running using TestNG
     */
    public WebDriverWait getWait() {
        log.debug("obtaining the wait for current thread");
        return DriverFactory.getInstance().getWait();
    }

    /**
     * return BasePO instance - can be used when running using TestNG
     */
    public BasePage getPO() {
        log.debug("obtaining an instance of the base page object");
        if (this.po == null) {
            this.po = new BasePage();
        }
        return po;
    }

    public void setPO() {
        log.debug("obtaining an instance of the base page object");
        this.po = new BasePage();
    }

    /**
     * SoftAssert instance from TestContext to be used - can be used when running using TestNG
     */
    protected SoftAssert sa() {
        return TestContext.getInstance().sa();
    }

    /**
     * TODO Read the 'tech stack' for a given test run and enable parallel execution from json file
     */
    //@DataProvider(name = "techStackJSON", parallel = true)
    public Object[][] techStackJSON() throws Exception {
        return new Object[0][];
    }

    /**
     * TODO Read the 'tech stack' for a given test run and enable parallel execution from excel file
     */
    //@DataProvider(name = "techStackExcel", parallel = true)
    public Object[][] techStackExcel() throws Exception {
        return new Object[0][];
    }

    /**
     * TODO set the test context information
     */
    //@BeforeMethod
    public void startUp(Method method, Object[] args) {

    }

    /**
     * if cucumber test the update the status and removes the current thread's value for this thread-local
     */
    //@AfterMethod(groups = {"quitDriver"})
    public void closeDown(ITestResult result) {

    }
    
    //@BeforeTest
    public void startReport() {

    }

    @AfterTest
    public void tearDown() {
        getDriver().quit();
    }
   
}
