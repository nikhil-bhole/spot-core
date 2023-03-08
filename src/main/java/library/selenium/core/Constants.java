package library.selenium.core;

/**
 * POJO used to define constants of selenium
 */
public class Constants {

    public static final String BASE_PATH = System.getProperty("user.dir") + "/src/test/resources/";
    public static final String SELENIUM_RUNTIME_PATH = BASE_PATH + "config/selenium/" + "runtime.properties";
    public static final String SCREENSHOT_PATH = System.getProperty("user.dir") +"\\test-output/screenshots/";
}
