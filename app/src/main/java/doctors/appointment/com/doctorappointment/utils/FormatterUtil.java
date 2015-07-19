package doctors.appointment.com.doctorappointment.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anweshmishra on 08/07/15.
 */
public class FormatterUtil {
    public static String getDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD-MM-YY");
        return simpleDateFormat.format(date);
    }
    public static String getTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(date);
    }
}
