package demoplaze.media;

import demoplaze.utils.TimeManager;
import demoplaze.utils.allureUtils;
import demoplaze.utils.logger.logs;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class screenshots {
    //screenshot for page
    public  static void screenshot(String screenName,WebDriver driver){
       File file= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       File target=new File(("test-output/screenshots")+File.separator+screenName+ TimeManager.currentTime()+".png");
       try {
           FileUtils.copyFile(file, target);
           allureUtils.allureScreenShots(screenName,target.getAbsolutePath());
       } catch (Exception e) {
           logs.errorMethod("cant take screen shots",e.getMessage());

       }
    }
    //screenshot for element
    public  static void screenshotElement(By locator,WebDriver driver){
        String accessibleName=driver.findElement(locator).getAccessibleName();
        File file= driver.findElement(locator).getScreenshotAs(OutputType.FILE);
        File target=new File(("test-output/screenshots")+File.separator+ accessibleName+TimeManager.currentTime()+".png");
        try {
            FileUtils.copyFile(file, target);
        } catch (Exception e) {
            logs.errorMethod("cant take screen shots for element",e.getMessage());

        }
    }

}
