package doctors.appointment.com.doctorappointment;

import android.content.Context;

import com.google.gson.Gson;
import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import doctors.appointment.com.doctorappointment.dao.CustomerDao;
import doctors.appointment.com.doctorappointment.models.Customer;

/**
 * Created by anweshmishra on 16/07/15.
 */
public class OfflineSynchronizer {

    public static void syncCustomerDb(Context context,JSONArray jsonArray) throws Exception{
        int sizeOfCustomerDb = 0;
        try {
            sizeOfCustomerDb  = CustomerDao.getAllCustomers().size();
        }
        catch (Exception ex) {

        }
        if(sizeOfCustomerDb < jsonArray.length()) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ArrayList<String> values = new ArrayList<String>();
                Iterator<String> iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                    String newKey = iterator.next();
                    values.add(jsonObject.getString(newKey));
                }
                Gson gson = new Gson();
                String customerJson = jsonObject.toString();
                Customer customer = gson.fromJson(customerJson,Customer.class);
                CustomerDao.saveCustomer(customer);
            }
        }
    }
}
