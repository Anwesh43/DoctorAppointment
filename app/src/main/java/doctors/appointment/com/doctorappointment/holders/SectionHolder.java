package doctors.appointment.com.doctorappointment.holders;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.TextView;

import doctors.appointment.com.doctorappointment.R;
import doctors.appointment.com.doctorappointment.ViewInitializer;
import doctors.appointment.com.doctorappointment.models.CustomerDetailsView;

/**
 * Created by anweshmishra on 17/07/15.
 */
public class SectionHolder extends RecyclerView.ViewHolder {
    private TextView patientName,mobileNO,bookingId,timeView;
    public SectionHolder(View view) {
        super(view);
        setUpViews(view);
    }
    public void setUpViews(View view) {
        view.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

            }
        });
        ViewInitializer<TextView> viewInitializer = new ViewInitializer<TextView>();

        patientName = viewInitializer.initializeView(R.id.patient_name,view);
        mobileNO = viewInitializer.initializeView(R.id.mobileNO,view);
        bookingId = viewInitializer.initializeView(R.id.bookingID,view);
        timeView = viewInitializer.initializeView(R.id.patient_name,view);
    }
    public void setData(CustomerDetailsView customerDetailsView) {
        TextView textViews[] = {patientName,bookingId,mobileNO,timeView};
        String values[] = {customerDetailsView.getName()!=null?customerDetailsView.getName():"No name",customerDetailsView.getBookingId(),customerDetailsView.getMobileNO(),customerDetailsView.getTime()};
        setTextFieldValues(textViews,values);
    }
    private void setTextFieldValues(TextView[] textViews,String[] values) {
        int index = 0;
        for(TextView textView:textViews) {
            textView.setTextColor(20);
            textView.setText(values[index]);
            textView.setTextColor(Color.BLACK);
            index++;
        }
    }
}
