package socket;

import java.util.Calendar;

/**
 * Created by Alexander on 2016/6/15.
 */
public class Utils {

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime().toString();
    }

    public static void println(String str) {
        System.out.println(getTime() + ": " + str);
    }
}
