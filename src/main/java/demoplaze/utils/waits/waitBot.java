package demoplaze.utils.waits;

import demoplaze.utils.propertyReader;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.ArrayList;

public class waitBot {
 private final WebDriver driver;
  public  waitBot(WebDriver driver){
      this.driver=driver;
  }

    public FluentWait<WebDriver> fluntWait(){
     return new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds((Long.parseLong(propertyReader.getproperty("DefaultWait")))))
                .pollingEvery(Duration.ofMillis(100))
                .ignoreAll(getExceptions());

    }

    private ArrayList<Class<?extends Exception>> getExceptions(){

        ArrayList<Class<?extends Exception>>  exception=new ArrayList<>();
        exception.add(ElementClickInterceptedException.class);
        exception.add(ElementNotInteractableException.class);
        exception.add(NullPointerException.class);
        exception.add(NoSuchElementException.class);
        return exception;

    }


}
