package doctors.appointment.com.doctorappointment.models;

import com.orm.SugarApp;
import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by anweshmishra on 16/07/15.
 */
public class Customer extends SugarRecord<Customer>{
    public String alertType;
    public String requestDateAndTime;
    public String customerID;
    public String customerName;
    public String serviceProviderId;
    public String serviceProviderName;
    public String appointmentOn;
    public String tokenNo;
    public String customerMsg;
    public String bookingID;
    public Customer() {

    }
    public Customer(ArrayList<String> params) {
        this.alertType = params.get(0);
        this.requestDateAndTime = params.get(1);
        this.customerID = params.get(2);
        this.customerName = params.get(3);
        this.serviceProviderId = params.get(4);
        this.serviceProviderName = params.get(5);
        this.appointmentOn = params.get(6);
        this.tokenNo = params.get(7);
        this.customerMsg = params.get(8);
        this.bookingID = params.get(9);
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getRequestDateAndTime() {
        return requestDateAndTime;
    }

    public void setRequestDateAndTime(String requestDateAndTime) {
        this.requestDateAndTime = requestDateAndTime;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public String getServiceProviderName() {
        return serviceProviderName;
    }

    public void setServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }

    public String getAppointmentOn() {
        return appointmentOn;
    }

    public void setAppointmentOn(String appointmentOn) {
        this.appointmentOn = appointmentOn;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getCustomerMsg() {
        return customerMsg;
    }

    public void setCustomerMsg(String customerMsg) {
        this.customerMsg = customerMsg;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }
}
