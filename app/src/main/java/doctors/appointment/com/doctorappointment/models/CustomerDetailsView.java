package doctors.appointment.com.doctorappointment.models;

/**
 * Created by anweshmishra on 17/07/15.
 */
public class CustomerDetailsView {
    String time;
    String date;
    String name;
    String bookingId;
    String mobileNO;
    public CustomerDetailsView(String time,String date,String name,String bookingId,String mobileNO) {
        this.time = time;
        this.bookingId = bookingId;
        this.date = date;
        this.name = name;
        this.mobileNO = mobileNO;

    }
    public String getTime() {
        return this.time;
    }
    public String getDate() {
        return this.date;
    }
    public String getName() {
        return this.name;
    }
    public String getMobileNO() {
        return this.mobileNO;
    }
    public String getBookingId() {
        return this.bookingId;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    public void setMobileNO(String mobileNO) {
        this.mobileNO = mobileNO;
    }


}
