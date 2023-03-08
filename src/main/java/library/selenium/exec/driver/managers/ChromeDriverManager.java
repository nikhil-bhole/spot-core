package library.selenium.exec.driver.managers;

import library.selenium.exec.driver.factory.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;


public class ChromeDriverManager extends DriverManager {

    protected Logger log = LogManager.getLogger(this.getClass().getName());


    @Override
    public void createDriver() {

        driver = new ChromeDriver();
     
    }

    @Override
    public void updateResults(String result) {
        //do nothing
    }
} 