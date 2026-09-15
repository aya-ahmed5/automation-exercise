package demoplaze.validations;

import demoplaze.utils.actions.ElementAction;
import demoplaze.utils.logger.logs;
import demoplaze.utils.waits.waitBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public  abstract class baseAssertions {
    protected  WebDriver driver;
    protected  waitBot waitBot;
    protected ElementAction ElementAction;

    public baseAssertions(){

    }

    public baseAssertions(WebDriver driver) {
        this.driver = driver;
        this.waitBot = new waitBot(driver);
        this.ElementAction = new ElementAction(driver);
    }

    protected abstract void assertTrueMethod(boolean value, String message);

    protected abstract void assertFalseMethod(boolean value, String message);

    protected abstract void assertEqualMethod(String actual, String expected, String message);

    //equals
    public void equals(String actial, String expected, String message) {
        assertEqualMethod(actial, expected, message);
    }
    //is element visable

    public void assertisElementVisible(By locator) {

        assertTrueMethod(ElementAction.isElementVisible(locator), "element is not visible ");

    }
    //assert current url
    public   void asserturl(String expected){
        String actualurl=driver.getCurrentUrl();
        assertEqualMethod(expected,actualurl,"current url is not qual");

    }
    public   void assertTitle(String expected){
        String actualtitle=driver.getTitle();
        assertEqualMethod(expected,actualtitle,"current title is not qual");

    }
    public void assertFile(String name){
        assertTrueMethod(ElementAction.fileExcest(name), "file is not downloaded");
    }
    //assert get text

}
