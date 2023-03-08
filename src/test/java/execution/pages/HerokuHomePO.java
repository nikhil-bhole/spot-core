package execution.pages;

import org.openqa.selenium.By;
import library.selenium.core.Locator;
import library.selenium.exec.BasePage;

public class HerokuHomePO extends BasePage {

    By heading = By.tagName("h3");
    String menu = "//li/a[contains(text(),'%s')]";
    
    public void pickMenu(String val) {
        $(Locator.Loc.XPATH, menu, val).click();
    }

    public String getHeading(){
        String val = $(heading).getText();
        return val;
    }
}
