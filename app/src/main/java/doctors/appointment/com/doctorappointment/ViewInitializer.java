package doctors.appointment.com.doctorappointment;

import android.view.View;

/**
 * Created by anweshmishra on 08/07/15.
 */
public class ViewInitializer<T> {
    public T initializeView(int id,View view) {
        return (T)view.findViewById(id);
    }
}
