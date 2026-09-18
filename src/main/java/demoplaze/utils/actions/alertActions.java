package demoplaze.utils.actions;

import demoplaze.utils.actions.logger.logs;
import demoplaze.utils.waits.waitBot;
import org.openqa.selenium.WebDriver;

public class alertActions {
   private final WebDriver driver;
   private final waitBot waitBot;
    public alertActions(WebDriver driver){
        this.driver=driver;
        this.waitBot=new waitBot(driver);
    }
    //accept text
    public void acceptAlert(){
        waitBot.fluntWait().until(d->{
           try{
               driver.switchTo().alert().accept();
               return true;
           } catch (Exception e) {
               logs.errorMethod("fail to accept alert",e.getMessage());
               return false;
           }


        });
    }
    //dismiss text
    public void dissmissAlert(){
        waitBot.fluntWait().until(d->{
            try{
                driver.switchTo().alert().dismiss();
                return true;
            } catch (Exception e) {
                logs.errorMethod("fail to dismissalert",e.getMessage());
                return false;
            }


        });
    }
    // get alert text
    public String getAlertText(){
      return  waitBot.fluntWait().until(d->{
            try{
             String mes=   driver.switchTo().alert().getText();
            return !mes.isEmpty()?mes:null;

            } catch (Exception e) {
                logs.errorMethod("fail to get alert text",e.getMessage());
                return null;
            }


        });
    }
    //send alert text
    public void sendAlertText(String text){
         waitBot.fluntWait().until(d->{
            try{
                driver.switchTo().alert().sendKeys(text);
                return true;

            } catch (Exception e) {
                logs.errorMethod("fail to send alert text",e.getMessage());
                return false;
            }


        });
    }



}
