package doctors.appointment.com.doctorappointment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.*;
import org.json.JSONArray;
import doctors.appointment.com.doctorappointment.R;
import doctors.appointment.com.doctorappointment.holders.AppointmentHolder;
import doctors.appointment.com.doctorappointment.models.ComparableDate;
import doctors.appointment.com.doctorappointment.models.Customer;
import doctors.appointment.com.doctorappointment.models.CustomerDetailsView;

/**
 * Created by anweshmishra on 08/07/15.
 */
public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentHolder> {
    SortedMap<ComparableDate,List<CustomerDetailsView>> dayAppointmentMap;
    Context context;
    public AppointmentAdapter(SortedMap<ComparableDate,List<CustomerDetailsView>> dayAppointmentMap,Context context) {
        this.dayAppointmentMap = dayAppointmentMap;
        this.context = context;
    }
    public AppointmentHolder onCreateViewHolder(ViewGroup group,int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.appointment_list,group,false);
        AppointmentHolder appointmentHolder = new AppointmentHolder(view,dayAppointmentMap);
        return appointmentHolder;
    }
    public void onBindViewHolder(AppointmentHolder holder,int position) {
        try {
            int index = 0;
            ComparableDate comparableDateKey = null;
            for(ComparableDate comparableDate:dayAppointmentMap.keySet()) {
                if(index == position) {
                    comparableDateKey = comparableDate;
                    break;
                }
                index++;
            }
            holder.setData(comparableDateKey,context);
        }
        catch (Exception ex) {

        }
    }
    public int getItemCount() {
        return dayAppointmentMap.keySet().size();
    }
}
