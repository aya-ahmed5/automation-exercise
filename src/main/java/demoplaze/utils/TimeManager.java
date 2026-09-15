package demoplaze.utils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class TimeManager {
    //for screeshots or logs
    public static String currentTime(){
       return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
    }
    //for amy date
    public static String millisTime(){
        return Long.toString(System.currentTimeMillis());

    }

}
