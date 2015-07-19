package doctors.appointment.com.doctorappointment;

/**
 * Created by anweshmishra on 08/07/15.
 */
public class AppConfig {
    private static String localUrl="http://192.168.1.6:8080/appointmentSys";
    private static String prodUrl="";
    public static String getLocalUrl() {
        return localUrl;
    }
    public static String getProdUrl() {
        return prodUrl;
    }

}
