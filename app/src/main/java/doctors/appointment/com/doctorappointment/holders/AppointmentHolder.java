package doctors.appointment.com.doctorappointment.holders;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.*;
import org.json.JSONObject;
import java.util.Date;

import doctors.appointment.com.doctorappointment.CustomLinearLayoutManager;
import doctors.appointment.com.doctorappointment.DimensionSetter;
import doctors.appointment.com.doctorappointment.R;
import doctors.appointment.com.doctorappointment.ViewInitializer;
import doctors.appointment.com.doctorappointment.adapters.SectionAdapter;
import doctors.appointment.com.doctorappointment.decorators.DividerDecorator;
import doctors.appointment.com.doctorappointment.models.ComparableDate;
import doctors.appointment.com.doctorappointment.models.Customer;
import doctors.appointment.com.doctorappointment.models.CustomerDetailsView;
import doctors.appointment.com.doctorappointment.utils.FormatterUtil;

/**
 * Created by anweshmishra on 08/07/15.
 */
public class AppointmentHolder extends RecyclerView.ViewHolder{
    TextView date;
    RecyclerView sectionList;
    SortedMap<ComparableDate,List<CustomerDetailsView>> dayAppointmentMap;
    public AppointmentHolder(View view,SortedMap<ComparableDate,List<CustomerDetailsView>> dayAppointmentMap) {
        super(view);
        this.dayAppointmentMap = dayAppointmentMap;
        setUpViews(view);
    }
    private void setUpViews(final View view) {
        date = (TextView)view.findViewById(R.id.date);
        sectionList = (RecyclerView)view.findViewById(R.id.section);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            public void onGlobalLayout() {
                int width = view.getWidth();
                int height = view.getHeight();
            }
        });
    }
    public void setData(ComparableDate dateKey,Context context) {
        try {
            date.setTextSize(20);
            date.setText(dateKey.getDate());
            date.setBackgroundColor(Color.BLACK);
            final int numberOfChild = dayAppointmentMap.get(dateKey).size();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,numberOfChild*229);
            final LinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(context,200);
            sectionList.setLayoutParams(layoutParams);
            sectionList.setLayoutManager(linearLayoutManager);
            SectionAdapter sectionAdapter = new SectionAdapter(dayAppointmentMap.get(dateKey));
            sectionList.setAdapter(sectionAdapter);
            sectionList.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
            sectionList.addItemDecoration(new DividerDecorator(20, Color.parseColor("#ff6495ed")));
            sectionList.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    sectionList.scrollBy(0,100);
                    return true;
                }
            });
            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sectionList.smoothScrollToPosition(sectionList.getAdapter().getItemCount());
                    sectionList.smoothScrollToPosition(numberOfChild-1);

                }
            });

        }
        catch (Exception exception) {
            Log.d("ex",exception.toString());
        }
    }
}
