package doctors.appointment.com.doctorappointment.dao;

import doctors.appointment.com.doctorappointment.models.Customer;
import java.util.*;
/**
 * Created by anweshmishra on 16/07/15.
 */
public class CustomerDao {
    public static void saveCustomer(Customer customer) {
        customer.save();
    }
    public static List<Customer> getAllCustomers() {
        return Customer.listAll(Customer.class);
    }
}
