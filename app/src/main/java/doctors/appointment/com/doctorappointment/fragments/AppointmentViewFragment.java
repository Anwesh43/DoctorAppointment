package doctors.appointment.com.doctorappointment.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import java.util.*;
import org.json.JSONArray;
import android.content.*;
import doctors.appointment.com.doctorappointment.MainActivity;
import doctors.appointment.com.doctorappointment.R;
import doctors.appointment.com.doctorappointment.adapters.AppointmentAdapter;
import doctors.appointment.com.doctorappointment.decorators.DividerDecorator;
import doctors.appointment.com.doctorappointment.holders.AppointmentHolder;
import doctors.appointment.com.doctorappointment.models.ComparableDate;
import doctors.appointment.com.doctorappointment.models.Customer;
import doctors.appointment.com.doctorappointment.models.CustomerDetailsView;

/**
 * Created by anweshmishra on 08/07/15.
 */

public class AppointmentViewFragment extends Fragment {
    RecyclerView appointListView;
    Context context;
    List<Customer> customerList;
    SortedMap<ComparableDate,List<CustomerDetailsView>> dayAppointmentMap = new TreeMap<ComparableDate,List<CustomerDetailsView>>();
    public View onCreateView(LayoutInflater inflater,ViewGroup group,Bundle bundle) {
        View view = inflater.inflate(R.layout.appointment_view,group,false);

        appointListView = (RecyclerView)view.findViewById(R.id.appointment_list_view);
        appointListView.setLayoutManager(new LinearLayoutManager(context));
        AppointmentAdapter appointmentAdapter = new AppointmentAdapter(dayAppointmentMap,context);
        appointListView.setAdapter(appointmentAdapter);
        appointListView.addItemDecoration(new DividerDecorator(10, Color.WHITE));
        return view;
    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity.getApplicationContext();
        MainActivity mainActivity = (MainActivity)activity;
        customerList= ((MainActivity) activity).getCustomers();
        createDayAppointmentMap();
    }
    public void createDayAppointmentMap() {
        for(Customer customer:customerList) {
            String dateAndTime = customer.getAppointmentOn();
            String[] dateTimeArray = dateAndTime.split(" ");
            String date = dateTimeArray[0],time = dateTimeArray[1],bookingId=customer.getBookingID(),name = customer.getCustomerName(),mobileNO = customer.getCustomerID();
            CustomerDetailsView customerDetailsView = new CustomerDetailsView(time,date,name,bookingId,mobileNO);
            ComparableDate comparableDate = new ComparableDate(date);
            if(dayAppointmentMap.containsKey(comparableDate)) {
                List<CustomerDetailsView> containedList = dayAppointmentMap.get(comparableDate);
                containedList.add(customerDetailsView);
                dayAppointmentMap.put(comparableDate,containedList);
            }
            else {
                List<CustomerDetailsView> customerDetailsViewList = new ArrayList<CustomerDetailsView>();
                customerDetailsViewList.add(customerDetailsView);
                dayAppointmentMap.put(comparableDate,customerDetailsViewList);
            }

        }
    }
}
