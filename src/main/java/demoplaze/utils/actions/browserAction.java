package demoplaze.utils.actions;

import demoplaze.utils.logger.logs;
import demoplaze.utils.waits.waitBot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class browserAction {
private final WebDriver driver;
private waitBot waitBot;
    public browserAction(WebDriver driver){
    this.driver=driver;
    this.waitBot=new waitBot(driver);
}
    //maxmize
    public  void maxmize(){
    driver.manage().window().maximize();
    }
    //get current url
    public  String currenturl(){
    String url=driver.getCurrentUrl();
        logs.infoMethod("the url is "+url);
    return url;
    }
    //navigate to
    public void navigate(String url){
    driver.navigate().to(url);
        logs.infoMethod("navigation to  "+url);
    }
    //refresh
    public void refresh(String url){
        driver.navigate().refresh();
    }
    //open new tab
    public void newtab(){
        driver.switchTo().newWindow(WindowType.TAB);
    }
    //close tab
    public void close(String url){
        driver.close();
    }
    //get page title
    public String close(){
        return driver.getTitle();
    }

 //action to close extentions
    public  void closeExtentionPg(){
      String parent =  driver.getWindowHandle();
    waitBot.fluntWait().until(driver1 -> driver.getWindowHandles().size()>1);
      driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()).close();
      driver.switchTo().window(parent);
      logs.infoMethod("extension is closed ");
    }

}
