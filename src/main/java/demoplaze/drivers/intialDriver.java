package demoplaze.drivers;

import demoplaze.utils.actions.ElementAction;
import demoplaze.utils.actions.alertActions;
import demoplaze.utils.actions.browserAction;
import demoplaze.utils.actions.frameActions;
import demoplaze.utils.logger.logs;
import demoplaze.utils.propertyReader;
import demoplaze.validations.hardAssertions;
import demoplaze.validations.softAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class intialDriver {
     ThreadLocal<WebDriver> threadLocal=new ThreadLocal<>();
     public  String browsertype= System.getProperty("browser_Type");
    public intialDriver(){
            Createdriver Value= Createdriver.valueOf(browsertype.toLowerCase());
            logs.infoMethod("driver starting on "+Value);
            System.out.println("browsertype = " + browsertype);
            System.out.println("System property = " + System.getProperty("browser_Type"));
            WebDriver actualDriver= ThreadGuard.protect(Value.driverValue().getDriver());
            threadLocal.set(actualDriver);
        }

     public alertActions alerts(){
       return new alertActions(getThreadDriver());
     }
     public browserAction browserAction(){
        return new browserAction(getThreadDriver());
     }
    public ElementAction ElementAction(){
        return new ElementAction(getThreadDriver());
    }
    public frameActions frameActions(){
        return new frameActions(getThreadDriver());
    }
    public hardAssertions hardAssertions(){
        return new hardAssertions(getThreadDriver());
    }
    public softAssertions softAssertions(){
        return new softAssertions(getThreadDriver());
    }


    public WebDriver getThreadDriver(){

    return threadLocal.get();
    }
   public void quitDriver(){

     threadLocal.get().quit();
}

}
