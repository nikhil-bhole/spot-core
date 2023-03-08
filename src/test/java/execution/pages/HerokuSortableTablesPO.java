package execution.pages;

import org.openqa.selenium.By;
import library.selenium.core.Element;
import library.selenium.core.Locator;
import library.selenium.exec.BasePage;

import java.util.List;

public class HerokuSortableTablesPO extends BasePage {

    By table = By.id("table1");
    String col = "//*[@id='table1']//tr/td[contains(text(),'%s')]/following-sibling::*[3]";

    public String getCash(String val) {
        String cash = $(Locator.Loc.XPATH, col, val).getText();
        return cash;
    }

    public int getNumberRows() {
        List<Element> rows = $(table).$(By.tagName("tbody")).$$(By.tagName("tr"));
        int count = rows.size();
        return count;
    }
}
