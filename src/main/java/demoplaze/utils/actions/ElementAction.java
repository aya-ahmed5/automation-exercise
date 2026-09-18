package demoplaze.utils.actions;

import demoplaze.utils.actions.logger.logs;
import demoplaze.utils.waits.waitBot;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class ElementAction {

    private final WebDriver driver;
    private final waitBot waitBot;
    public ElementAction(WebDriver driver){
      this.waitBot=  new waitBot(driver);
      this.driver=driver;
    }
    //click
    public ElementAction click(By locator){
        waitBot.fluntWait().until(d -> {
            try {
                WebElement element = driver.findElement(locator);
                scrolling(locator);
                element.click();
                logs.infoMethod("clicking sussess");
                return true;   // <-- ناقصة في كودك الأصلي

            } catch (ElementClickInterceptedException e) {
                try {
                    WebElement element = driver.findElement(locator);
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", element);
                    logs.infoMethod("clicked via JS fallback due to intercepted element " + locator);
                    return true;
                } catch (Exception jsEx) {
                    logs.errorMethod("JS click fallback also failed for element ", jsEx.getMessage());
                    return false;
                }

            } catch (Exception e) {
                logs.errorMethod("error to click element from element ", e.getMessage());
                return false;
            }
        });

        return this;
    }

    //gettext
    public String getText(By locator){
     return   waitBot.fluntWait().until(d->{
            try{
                scrolling(locator);
                String mes=  driver.findElement(locator).getText();
                return !mes.isEmpty()?mes: null;

            }

            catch (Exception e) {
                logs.errorMethod("error to get text element from element "+locator);

                return null;
            }
        });

    }
    //sendtext
    public ElementAction sendText(By locator, String text){
          waitBot.fluntWait().until(d->{
            try{
                scrolling(locator);
                driver.findElement(locator).sendKeys(text);
                logs.infoMethod("susses to send text element "+text +"from element"+locator);
               return true;

            } catch (Exception e) {
                return false;
            }
        });
return this;
    }
    //uploadfile
    public ElementAction uploadfile(By locator, String filename){
        String file=  System.getProperty(("user.dir")+File.separator+filename);
        waitBot.fluntWait().until(d->{
            try{
                scrolling(locator);
                driver.findElement(locator).sendKeys(file);
                logs.infoMethod("susses to upload file  "+filename+"from elemnt "+locator);
                return true;

            } catch (Exception e) {
                return false;
            }
        });
   return this;
    }
    public boolean isElementVisible(By locator) {
        return waitBot.fluntWait().until(d -> {
            try {

                driver.findElement(locator).isDisplayed();
                return true;
            } catch (Exception e) {
                logs.errorMethod("erorr to displyed element", e.getMessage());
                return false;

            }
        });

    }
    public ElementAction hover(By locator){
       waitBot.fluntWait().until(d->{
           try{
               scrolling(locator);
               new Actions(driver).moveToElement(driver.findElement(locator)).perform();
               logs.infoMethod("susses to hover element");
               return true;
           } catch (Exception e) {
               logs.errorMethod("erorr to hover element", e.getMessage());
               return false;

           }
       });
return this;
    }
    public boolean fileExcest(String name){
       return waitBot.fluntWait().until(d->{
            try{
                File file=new File("src/test/resources/download/"+name);
                if(file.exists()){
                    logs.infoMethod("file is excest");
                    return true;
                }else{
                    logs.errorMethod("file is not excest");
                    return false;
                }
            } catch (Exception e) {
                logs.errorMethod("erorr to check file excest", e.getMessage());
                return false;

            }
        });

    }
    public boolean dropdown(By locator,String value) {
        return waitBot.fluntWait().until(d -> {
            try {
                scrolling(locator);
                Select select=new Select(driver.findElement(locator));
                select.selectByVisibleText(value);
                return true;
            } catch (Exception e) {
                logs.errorMethod("erorr to select element", e.getMessage());
                return false;

            }
        });
    }

    public WebElement  findElement(By locator){
       return driver.findElement(locator);
    }

    public void scrolling(By locator){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});",findElement(locator));


    }
}
