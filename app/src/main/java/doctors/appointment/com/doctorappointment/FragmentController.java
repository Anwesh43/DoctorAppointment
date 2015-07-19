package doctors.appointment.com.doctorappointment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by anweshmishra on 08/07/15.
 */
public class FragmentController {
    public static void changeFragment(MainActivity mainActivity,FragmentManager fragmentManager,Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,fragment);
        transaction.commitAllowingStateLoss();
    }
    public static void addFragment(MainActivity mainActivity,FragmentManager fragmentManager,Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content,fragment);
        transaction.commitAllowingStateLoss();

    }
}
