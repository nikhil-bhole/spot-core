package library.selenium.core;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import library.common.Property;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * Base class providing set of common selenium methods
 */

public class Screenshot {

    /**
     * capture displayed area or scrolling screenshot and return a file object.
     * to capture scrolling screenshot property scrollingScreenshot = true has to be set in runtime.properties file
     */
    public static File grabScreenshot(WebDriver driver) {
    	
        String screenshotType = null;
        System.out.println(Constants.SELENIUM_RUNTIME_PATH);
        screenshotType = Property.getProperties(Constants.SELENIUM_RUNTIME_PATH).getString("scrollingScreenshot");
        System.out.println(screenshotType);
        if (screenshotType != null) {
            return (screenshotType == "true" ? grabScrollingScreenshot(driver) : grabDisplayedAreaScreenShot(driver));
        } else {
            return grabDisplayedAreaScreenShot(driver);
        }
    }

    /**
     * capture screenshot for the displayed area and return a file object
     */
    public static File grabDisplayedAreaScreenShot(WebDriver driver) {
        try {
            Thread.sleep(Property.getProperties(Constants.SELENIUM_RUNTIME_PATH).getInt("screenshotDelay", 0));
        } catch (InterruptedException | NumberFormatException e) {
            e.printStackTrace();
        }
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return screenshot;

    }

    /**
     * capture scrolling screenshot and return a file object
     */
    public static File grabScrollingScreenshot(WebDriver driver) {
        try {
            Thread.sleep(Property.getProperties(Constants.SELENIUM_RUNTIME_PATH).getInt("screenshotDelay", 0));
        } catch (InterruptedException | NumberFormatException e) {
            e.printStackTrace();
        }

        ru.yandex.qatools.ashot.Screenshot screenshot;

        if (System.getProperties().get("os.name").toString().contains("Mac")) {
            screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportRetina(100, 0, 0, 2)).takeScreenshot(driver);
        } else {
            screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        }

        File file = new File("image.png");

        try {
            ImageIO.write(screenshot.getImage(), "PNG", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * grab screenshot snippet
     */
    public static File snipScreenshot(File screenshot, By by, Dimension dim, Point point) {

        try {
            BufferedImage buffer = ImageIO.read(screenshot);
            // Crop the entire page screenshot to get only element screenshot
            BufferedImage snippet = buffer.getSubimage(0, point.getY(), point.getX() + dim.width, dim.height);
            ImageIO.write(snippet, "png", screenshot);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenshot;
    }

    /**
     * capture screenshot and save to specified location
     */
    public static File saveScreenshot(File screenshot, String filePath) {
        UUID uuid = UUID.randomUUID();
        File file = new File(filePath + uuid + ".png");
        try {
            FileUtils.moveFile(screenshot, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * capture screenshot and save to specified location
     */
    public static File saveScreenshot(File screenshot, String filePath, String fileName) {
        File file = new File(filePath + fileName + ".png");
        try {
            FileUtils.moveFile(screenshot, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * Compare the screenshot
     */
    public static Boolean compareScreenshot(File fileExpected, File fileActual) throws IOException {

        BufferedImage bufileActual = ImageIO.read(fileActual);
        BufferedImage bufileExpected = ImageIO.read(fileExpected);

        DataBuffer dafileActual = bufileActual.getData().getDataBuffer();
        DataBuffer dafileExpected = bufileExpected.getData().getDataBuffer();

        int sizefileActual = dafileActual.getSize();

        Boolean matchFlag = true;

        for (int j = 0; j < sizefileActual; j++) {
            if (dafileActual.getElem(j) != dafileExpected.getElem(j)) {
                matchFlag = false;
                break;
            }
        }

        return matchFlag;
    }



}
