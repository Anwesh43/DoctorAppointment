package doctors.appointment.com.doctorappointment.utils;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONArray;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import doctors.appointment.com.doctorappointment.AppConfig;
import doctors.appointment.com.doctorappointment.AppConstants;
import doctors.appointment.com.doctorappointment.CustomJsonArrayRequest;
import doctors.appointment.com.doctorappointment.FragmentController;
import doctors.appointment.com.doctorappointment.MainActivity;
import doctors.appointment.com.doctorappointment.fragments.AppointmentViewFragment;

/**
 * Created by anweshmishra on 16/07/15.
 */
public class RequestUtil {
    private static HashMap<String,Response.ErrorListener> errorListenerMap = new HashMap<String,Response.ErrorListener>();
    private static HashMap<String,Response.Listener> responseListenerMap = new HashMap<String,Response.Listener>();
    private static int methodCode;
    public static JsonArrayRequest makeCustomJsonArrayRequest(final int methodCode,String service,final MainActivity mainActivity,final String[] keys,final String[] values) {
        String url = AppConfig.getLocalUrl()+"/"+service;
        if(methodCode == Request.Method.GET) {
            String encodedUri =  EncodingUtil.encodeURI(keys,values);
            url = url+"?"+encodedUri;
        }
        mainActivity.dialogController.showProgressDialog();
        final CustomJsonArrayRequest jsonArrayRequest = new CustomJsonArrayRequest(service,url,getResponseListener(service,mainActivity),getErrorListener(service, mainActivity)){
            @Override
            public Map<String,String> getParams() {
                HashMap<String,String> paramsMap = new HashMap<String,String>();
                for(int i=0;i<keys.length;i++) {
                    paramsMap.put(keys[i],values[i]);
                }
                return paramsMap;
            }

            @Override
            public int getMethod() {
                return methodCode;
            }
        };

        return jsonArrayRequest;
    }
    private static Response.Listener getFetchResponseListener(final MainActivity mainActivity) {
        return new Response.Listener<JSONArray>(){
            public void onResponse(JSONArray jsonArray) {
                mainActivity.setAppointments(jsonArray);
                mainActivity.setMode("ONLINE");
                Log.d("appointments", jsonArray.toString());
                mainActivity.dialogController.dismissProgressDialog();
                mainActivity.setCurrentFragment(mainActivity.getAppointmentViewFragment());
            }
        };
    }
    private static Response.ErrorListener getFetchErrorListener(final MainActivity mainActivity) {
        return new Response.ErrorListener(){
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());
                mainActivity.dialogController.dismissProgressDialog();
                mainActivity.dialogController.showAlertDialog("Sorry Check Your Connection");
                mainActivity.setCurrentFragment(mainActivity.getAppointmentViewFragment());
            }
        };
    }
    private static Response.Listener getLoginResponseListener(final MainActivity mainActivity) {
        return new Response.Listener<JSONArray>(){
            public void onResponse(JSONArray jsonArray) {
                try {
                    mainActivity.dialogController.dismissProgressDialog();
                    String spId = jsonArray.getJSONObject(0).getString("serviceProviderID");
                    SharedPreferences.Editor editor = mainActivity.getSharedPreferences().edit();
                    editor.putBoolean(AppConstants.LOGIN_STATUS_KEY, true);
                    editor.putBoolean(AppConstants.IS_SPID_SET,true);
                    editor.putString("spId", spId);
                    editor.commit();
                    mainActivity.changeFragmentOnLogin();
                }
                catch(Exception exception) {

                }

            }
        };
    }
    private static Response.ErrorListener getLoginErrorListener(final MainActivity mainActivity) {
        return new Response.ErrorListener(){
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());
                mainActivity.dialogController.dismissProgressDialog();
                mainActivity.dialogController.showAlertDialog("Sorry Wrong User Credentials");
                mainActivity.signalLoginError();
            }
        };
    }
    private static Response.Listener getResponseListener(String service,final MainActivity mainActivity) {
        return responseListenerMap.get(service);
    }

    private static Response.ErrorListener getErrorListener(String service,final MainActivity mainActivity) {
        return errorListenerMap.get(service);
    }
    public static void createMap(MainActivity mainActivity) {
        errorListenerMap.put(AppConstants.FETCH_SERVICE,getFetchErrorListener(mainActivity));
        errorListenerMap.put(AppConstants.LOGIN_SERVICE,getLoginErrorListener(mainActivity));
        responseListenerMap.put(AppConstants.FETCH_SERVICE,getFetchResponseListener(mainActivity));
        responseListenerMap.put(AppConstants.LOGIN_SERVICE,getLoginResponseListener(mainActivity));
    }
}
