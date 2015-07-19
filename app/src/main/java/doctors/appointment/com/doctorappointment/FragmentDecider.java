package doctors.appointment.com.doctorappointment;

import android.content.SharedPreferences;

/**
 * Created by anweshmishra on 16/07/15.
 */
public class FragmentDecider {
    public static boolean isLoggedIn(SharedPreferences sharedPreferences) {
        //To be Changed Post New Logged In implementation
        return sharedPreferences.getBoolean(AppConstants.LOGIN_STATUS_KEY,false) && sharedPreferences.getBoolean(AppConstants.IS_SPID_SET,false);
    }
}
