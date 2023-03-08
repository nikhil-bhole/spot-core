package execution.tests;

import library.selenium.exec.BaseTest;
import execution.pages.HerokuHomePO;
import execution.pages.HerokuSortableTablesPO;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class HerokuTests extends BaseTest {

    String app = "https://the-internet.herokuapp.com/";

    @Test
    public void runTest1()  {
        getDriver().get(app);
        Assert.assertEquals(getDriver().getTitle(), "The Internet");
        HerokuHomePO po = new HerokuHomePO();
        po.pickMenu("Sortable Data Tables");
        Assert.assertEquals(po.getHeading(), "Data Tables");
    }

    @Test
    public void runTest2()  {
        getDriver().get(app);
        HerokuHomePO menu = new HerokuHomePO();
        String pick = "Sortable Data Tables";
        menu.pickMenu(pick);

        Map<String, String> values = new HashMap<String, String>();
        values.put("Conway", "$50.00");
        values.put("Smith", "$50.00");
        values.put("Doe", "$100.00");
        values.put("Bach", "$51.00");

        HerokuSortableTablesPO tab = new HerokuSortableTablesPO();
        sa().assertEquals(tab.getNumberRows(), values.size());

        for (Map.Entry<String, String> entry : values.entrySet()) {
            sa().assertEquals(tab.getCash(entry.getKey()), entry.getValue(), "Checking row:" + entry.getKey());
        }
        sa().assertAll();
    }



}
