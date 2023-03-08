package execution.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import library.selenium.core.Locator.Loc;
import library.selenium.core.Page;

public class HerokuHomePage extends Page {

    By heading = By.tagName("h3");
    String menu = "//li/a[contains(text(),'%s')]";

    public HerokuHomePage(WebDriver driver) {
        super(driver);
    }

    public void pickMenu(String val) {
        $(Loc.XPATH, menu, val).click();
    }

    public String getHeading(){
        String val = $(heading).getText();
        return val;
    }
}
