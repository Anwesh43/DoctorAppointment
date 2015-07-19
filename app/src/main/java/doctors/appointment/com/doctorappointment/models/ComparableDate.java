package doctors.appointment.com.doctorappointment.models;

import java.util.Date;

/**
 * Created by anweshmishra on 17/07/15.
 */
public class ComparableDate implements Comparable{
    String date;
    public ComparableDate(String date) {
        this.date = date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }
    public int compareTo(Object object) {
        int comparedVal = 0;
        if(object instanceof ComparableDate) {
            ComparableDate comparableDate1 = (ComparableDate)object;
            String[] days = this.date.split("-");
            String[] days1 = comparableDate1.date.split("-");
            comparedVal = compareDays(days,days1,days.length-1);
        }
        return comparedVal;
    }
    public int compareDays(String[] days,String[] days2,int index) {
        int value = Integer.parseInt(days[index])-Integer.parseInt(days2[index]);
        if(value == 0 && index > 0){
            compareDays(days,days2,index-1);
        }
        return value;
    }
    public boolean equals(Object object) {
        return ((ComparableDate)object).date.equals(date);
    }
}
