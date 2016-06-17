package testing;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap control
 * Created by Alexander on 2016/6/17.
 */
public class MapControl {
    public static void main(String[] args) {
        Map<String, String> mMap = new HashMap<String, String>();
        mMap.put("admin", "admin");
        System.out.println("Is contains admin? " + mMap.get("admin"));
        System.out.println("Is contains admon? " + mMap.get("admon"));
    }
}
