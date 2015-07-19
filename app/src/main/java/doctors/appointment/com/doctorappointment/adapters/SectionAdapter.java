package doctors.appointment.com.doctorappointment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.SortedMap;

import doctors.appointment.com.doctorappointment.R;
import doctors.appointment.com.doctorappointment.holders.SectionHolder;
import doctors.appointment.com.doctorappointment.models.ComparableDate;
import doctors.appointment.com.doctorappointment.models.CustomerDetailsView;

/**
 * Created by anweshmishra on 17/07/15.
 */
public class SectionAdapter extends RecyclerView.Adapter<SectionHolder> {
    List<CustomerDetailsView> customerDetailsViews;
    public SectionAdapter(List<CustomerDetailsView> customerDetailsViews) {
        this.customerDetailsViews = customerDetailsViews;
    }
    public SectionHolder onCreateViewHolder(ViewGroup group,int res) {
        LayoutInflater layoutInflater = LayoutInflater.from(group.getContext());
        View view = layoutInflater.inflate(R.layout.section_view,group,false);
        return new SectionHolder(view);

    }
    public void onBindViewHolder(SectionHolder holder,int position) {
        holder.setData(customerDetailsViews.get(position));
    }
    public int getItemCount() {
        return customerDetailsViews.size();
    }
}
