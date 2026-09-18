package demoplaze.drivers;

import demoplaze.utils.actions.logger.logs;
import demoplaze.utils.propertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class chromeFactory  extends driverFactory{
    private final File extention=new File("src/main/resources/extentions/google.crx");

    public ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        String downloadPath = System.getProperty("user.dir")
                + "\\src\\test\\resources\\download";
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        options.setCapability(CapabilityType.ENABLE_DOWNLOADS, true);
        options.setExperimentalOption("prefs", prefs);
        if(propertyReader.getproperty("Extension").equalsIgnoreCase("enabled"))
        options.addExtensions(extention);
        switch (propertyReader.getproperty("Excuation_Type")) {
            case "LocalHeadless" -> options.addArguments("--headless=new");
            case "remote"-> {
                options.addArguments("--headless=new") ;
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-gpu");
            }
        }
      return options;
    }
    @Override
    public WebDriver getDriver() {
        if (propertyReader.getproperty("Excuation_Type").equalsIgnoreCase("Local")
                || propertyReader.getproperty("Excuation_Type").equalsIgnoreCase("LocalHeadless"))
        {
            return new ChromeDriver(getOptions());
        } else if (propertyReader.getproperty("Excuation_Type").equalsIgnoreCase("Remote"))
        {
            try{
                return new RemoteWebDriver(new URI("http://"+propertyReader.getproperty("remoteHost")+":"+propertyReader.getproperty("remotePort")+"/wd/hub").toURL(),getOptions());

            } catch (Exception e) {
                logs.errorMethod("uri has exception error ",e.getMessage());
                throw new IllegalArgumentException("Invalid uri  ");
            }
        }
        else{
            logs.errorMethod("no type of excuation local/remote/headless");
            throw new IllegalArgumentException("Invalid excuation type ");
        }


    }
}
