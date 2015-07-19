package doctors.appointment.com.doctorappointment.utils;

/**
 * Created by anweshmishra on 20/07/15.
 */
public class EncodingUtil {
    public static String encodeURI(String keys[],String values[]) {
        String encodedParams="";
        if(keys.length != 0) {
            encodedParams = keys[0]+"="+values[0];
        }
        for(int i=1;i<keys.length;i++) {
            encodedParams = encodedParams+"&"+keys[i]+"="+values[i];
        }
        return encodedParams;
    }
}
