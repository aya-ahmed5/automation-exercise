package demoplaze.utils;

import demoplaze.utils.actions.logger.logs;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.nio.file.Path;

import static java.nio.file.Files.newInputStream;
import static java.nio.file.Files.readString;

public class allureUtils {

    public static void allureScreenShots(String name, String filename){
        File file = new File(filename);
        if (file.exists()) {
            try {
                Allure.addAttachment(name, newInputStream(Path.of(filename)));
            } catch (Exception e) {
                logs.errorMethod("error to add attachment to allure report " + e.getMessage());

            }
        }
    }
    public  static void attchLogsAllure(String name, String filename){
        File file = new File(filename);
        LogManager.shutdown();
        if (file.exists()) {
            try {
                Allure.addAttachment(name,readString(Path.of(filename)));
            } catch (Exception e) {
                logs.errorMethod("error to add logs to allure report " + e.getMessage());

            }
        }
    }

}



