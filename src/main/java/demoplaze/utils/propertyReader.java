package demoplaze.utils;

import demoplaze.utils.logger.logs;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.Properties;


public class propertyReader {

    public static void getReader() {
        Properties properties = new Properties();
        try {
            Collection<File> files = FileUtils.listFiles(new File("src/main/resources"), new String[]{".properties"}, true);
            files.forEach(file -> {
                try {
                    properties.load(FileUtils.openInputStream(file));
                } catch (Exception e) {
                    logs.errorMethod("cant get files ",e.getMessage());
                }
            });
            properties.putAll(System.getProperties());
            System.getProperties().putAll(properties);

        } catch (Exception e) {
            logs.errorMethod("cant load properties ");
        }
    }
    public  static String getproperty(String key) {
        try {
            return System.getProperty(key);
        } catch (Exception e) {
            logs.errorMethod("cant load properties ",e.getMessage());
            return "";

        }

    }
}
