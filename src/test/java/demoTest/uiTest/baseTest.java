package demoTest.uiTest;

import demoplaze.drivers.intialDriver;
import demoplaze.drivers.webDriverProvider;
import demoplaze.utils.jsonReader;
import org.openqa.selenium.WebDriver;

public abstract class baseTest  implements webDriverProvider {
   protected intialDriver driver;
    protected jsonReader jsonResder;
    protected abstract String getjsonfile();
    @Override
    public WebDriver wrapDriver() {
      return driver.getThreadDriver();
    }



}
