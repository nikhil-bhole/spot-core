package library.selenium.exec.driver.factory;

import library.selenium.exec.Constants;
import library.common.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Class to get the driver path , reate/quit manager and get the wat duration
 */
public abstract class DriverManager {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public WebDriver getDriver(){
		if (driver == null){
			createDriver();
		}
		return driver;
	}

	public WebDriver returnDriver(){
		return driver;
	}


	public void quitDriver(){
		if (driver != null){
			driver.quit();
			driver = null;
		}
	}
	
	public WebDriverWait getWait() {
		if (wait == null){
			wait = new WebDriverWait(driver, getWaitDuration());
		}
		return wait;
	}
	
	public String getDriverPath(String drivername){
    	String driver = drivername+(System.getProperty("os.name").split(" ")[0].equalsIgnoreCase("Windows")?".exe":"");
    	String path = Property.getVariable("cukes.driverPath");
    	return (path==null?"lib/drivers/":path)+System.getProperty("os.name").split(" ")[0].toLowerCase()+"/"+driver;
    }
	
	/** Returns duration for specified waits */
	public Duration getWaitDuration(){
		final int defaultWait = 10;	
		int duration;
		try {
			duration = Property.getProperties(Constants.SELENIUM_RUNTIME_PATH).getInt("defaultWait");
		} catch (Exception e) {
			duration = defaultWait;
		}
		return Duration.ofSeconds(duration);
	}

	protected abstract void createDriver();

	public abstract void updateResults(String result);

}