package doctors.appointment.com.doctorappointment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.*;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.android.volley.*;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import android.content.pm.*;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONArray;

import doctors.appointment.com.doctorappointment.dao.CustomerDao;
import doctors.appointment.com.doctorappointment.fragments.AppointmentViewFragment;
import doctors.appointment.com.doctorappointment.fragments.LoginFragment;
import doctors.appointment.com.doctorappointment.models.Customer;
import doctors.appointment.com.doctorappointment.utils.RequestUtil;
import java.util.*;

public class MainActivity extends Activity implements FragmentEventHandler{
    LoginFragment loginFragment;
    RequestQueue requestQueue;
    int requestSendTime = 0;
    JSONArray appointments;
    CustomJsonArrayRequest jsonArrayRequest;
    FragmentManager fragmentManager;
    SharedPreferences doctorSharedPreferences;
    AppointmentViewFragment appointmentViewFragment;
    String mode = "OFFLINE";
    ArrayList<Fragment> addedFragments = new ArrayList<Fragment>();
    public DialogController dialogController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        dialogController = new DialogController(this);
        doctorSharedPreferences = getSharedPreferences(AppConstants.DOCTOR_USER_COOKIES, Context.MODE_PRIVATE);
        fragmentManager = getFragmentManager();
        loginFragment = new LoginFragment();
        appointmentViewFragment = new AppointmentViewFragment();
        requestQueue = Volley.newRequestQueue(this);
        RequestUtil.createMap(this);
        if(!FragmentDecider.isLoggedIn(doctorSharedPreferences)) {
            setCurrentFragment(loginFragment);
        }
        else {
            changeFragmentOnLogin();
        }
    }
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);

    }
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);

    }
    public SharedPreferences getSharedPreferences() {
        return doctorSharedPreferences;
    }
    public void changeFragmentOnLogin() {
        String keys[] = {"spId"};
        String values[] = {getSharedPreferences().getString("spId","nothing")};
        requestQueue.add(RequestUtil.makeCustomJsonArrayRequest(Request.Method.POST,AppConstants.FETCH_SERVICE, this,keys,values));

    }
    public FragmentManager getCurrentFragmentManager() {
        return fragmentManager;
    }
    public void setAppointments(JSONArray jsonArray) {
        this.appointments = jsonArray;
        try {
            OfflineSynchronizer.syncCustomerDb(this,jsonArray);
        }
        catch (Exception exception) {
            dialogController.showAlertDialog(exception.toString());
        }
    }
    public void setCurrentFragment(Fragment fragment) {
        if(addedFragments.size() == 0) {
            FragmentController.addFragment(this,fragmentManager, fragment);
            addedFragments.add(fragment);
        }
        else {
            FragmentController.changeFragment(this,fragmentManager, fragment);
            addedFragments.add(fragment);
        }
    }
    public JSONArray fetchAppointment() {
        return appointments;
    }
    public AppointmentViewFragment getAppointmentViewFragment() {
        return appointmentViewFragment;
    }
    public List<Customer> getCustomers() {
        return CustomerDao.getAllCustomers();
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public void createLoginRequest(String userName,String password) {
        String keys[] = {"serviceproviderLoginVO.username","serviceproviderLoginVO.pswd"};
        String values[] = {userName,password};
        JsonArrayRequest request = RequestUtil.makeCustomJsonArrayRequest(Request.Method.GET,AppConstants.LOGIN_SERVICE,this,keys,values);
        requestQueue.add(request);
    }
    public void signalLoginError() {
        loginFragment.setTextBoxesError();
    }
}
