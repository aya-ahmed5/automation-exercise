package demoplaze.utils.actions.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class logs {
    private static Logger logger(){
       return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }
    public static void infoMethod(String...text){
        logger().info(String.join("",text));
    }
    public static void fetalMethod(String...text){
        logger().fatal(String.join("",text));
    }
    public static void debugMethod(String...text){
        logger().debug(String.join("",text));
    }
    public static void traceMethod(String...text){
        logger().trace(String.join("",text));
    }
    public static void errorMethod(String...text){
        logger().error(String.join("",text));
    }
    public static void warnMethod(String...text){
        logger().warn(String.join("",text));
    }

}
