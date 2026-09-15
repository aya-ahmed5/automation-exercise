package demoplaze.utils.actions;

import demoplaze.utils.logger.logs;
import demoplaze.utils.waits.waitBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class frameActions {
    private final WebDriver driver;
    private final waitBot waitBot;
    public frameActions(WebDriver driver){
        this.driver=driver;
        this.waitBot=new waitBot(driver);
    }
    //frams by index
    public void frameindex(String index){

        waitBot.fluntWait().until(d->{
          try {
              driver.switchTo().frame(index);
              logs.infoMethod("switch to frame"+index);
              return true;

          } catch (Exception e) {
              return false;
          }

        });
    }
    //frams by name
    public void framename(String index){

        waitBot.fluntWait().until(d->{
            try {
                driver.switchTo().frame(index);
                logs.infoMethod("switch to frame"+index);
                return true;

            } catch (Exception e) {
                return false;
            }

        });
    }
    //frams by element
    public void frameElement(By by){

        waitBot.fluntWait().until(d->{
            try {
                driver.switchTo().frame(driver.findElement(by));
                logs.infoMethod("switch to frame"+by);
                return true;

            } catch (Exception e) {
                return false;
            }

        });
    }
    //default frame
    public void defaultframe(By by){

        waitBot.fluntWait().until(d->{
            try {
                driver.switchTo().defaultContent();
                logs.infoMethod("switch to defaultframe"+by);
                return true;

            } catch (Exception e) {
                return false;
            }

        });
    }



}
