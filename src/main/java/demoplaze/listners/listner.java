package demoplaze.listners;
import demoplaze.drivers.UiTest;
import demoplaze.drivers.webDriverProvider;
import demoplaze.media.screenshots;
import demoplaze.media.videorecorder;
import demoplaze.utils.allureUtils;
import demoplaze.utils.actions.logger.logs;
import demoplaze.utils.propertyReader;
import demoplaze.validations.softAssertions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;

public class listner implements IExecutionListener, IInvokedMethodListener,ITestListener {
    public void onExecutionStart() {
        logs.infoMethod("starting test");
        cleanDirectory(new File("test-output/recordings"));
        cleanDirectory(new File("test-output/screenshots"));
        cleanDirectory(new File("test-output/Logs"));
        cleanDirectory(new File("src/test/resources/download"));
        logs.infoMethod("all directory deleted");
        createDirectory(new File("test-output/recordings"));
        createDirectory(new File("test-output/screenshots"));
        createDirectory(new File("src/test/resources/download"));
        logs.infoMethod("all directory created");

        propertyReader.getReader();
    }
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if(method.isTestMethod()) {
            if (testResult.getInstance() instanceof UiTest) {

                videorecorder.startRecording();
            }
        }
    }
    public void onTestSuccess(ITestResult result) {
        logs.infoMethod("test scuseee",result.getName());
    }

    public void onTestFailure(ITestResult result) {
        logs.infoMethod("test fail", result.getName());
    }
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = null;
        if (method.isTestMethod())
        {
            if(testResult.getInstance().getClass().isAnnotationPresent(UiTest.class))
            {
                videorecorder.stopRecording(testResult.getName());
                if (testResult.getInstance() instanceof webDriverProvider provider){
            driver = provider.wrapDriver();
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS -> screenshots.screenshot(testResult.getName() + "pass", driver);
                case ITestResult.FAILURE -> screenshots.screenshot(testResult.getName() + "fail", driver);
                case ITestResult.SKIP -> screenshots.screenshot(testResult.getName() + "skip", driver);
            }
        }
         }

            softAssertions.assertAll();
            allureUtils.attchLogsAllure("logs", System.getProperty("user.dir") + "/test-output/Logs/log.log");



        }
    }

    public void cleanDirectory(File file){
        try{
            FileUtils.forceDelete(file);

        } catch (Exception e) {
            logs.errorMethod("cants delete any directory",e.getMessage());
        }
    }
    public void createDirectory(File file){
        try {
            FileUtils.forceMkdir(file);
        } catch (Exception e) {
            logs.errorMethod("cants craete any directory any directory",e.getMessage());
        }
    }
}
