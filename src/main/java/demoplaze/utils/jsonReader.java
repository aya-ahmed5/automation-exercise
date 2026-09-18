package demoplaze.utils;

import com.jayway.jsonpath.JsonPath;
import demoplaze.utils.actions.logger.logs;

import java.io.File;

import java.nio.file.Path;

import static java.nio.file.Files.readString;

public class jsonReader {
    static String  file;

    public jsonReader(String path)  {
        try{
            file= readString(Path.of(("src/test/resources/test-data")+File.separator+path+".json"));
        } catch (Exception e) {
            logs.errorMethod("error to load jsons",e.getMessage());
        }

    }


    public static String getjson(String key){
        try{
           return JsonPath.read(file,key);
        } catch (Exception e) {
            logs.errorMethod("error to get json key",e.getMessage());
            return "";

        }

    }
}
