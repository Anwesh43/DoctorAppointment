package doctors.appointment.com.doctorappointment;

import android.util.JsonToken;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;

/**
 * Created by anweshmishra on 08/07/15.
 */
public class CustomJsonArrayRequest extends JsonArrayRequest {
    String service;
    public CustomJsonArrayRequest(String service,String url,Response.Listener<JSONArray> listener,Response.ErrorListener errorListener){
        super(url,listener,errorListener);
        this.service = service;
    }
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            JSONObject jsonObject = new JSONObject();
            String je = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            if(service.equals(AppConstants.LOGIN_SERVICE)) {
                String newJe = je.replace("\\r","").replace("\\n","").replace("\\","").replace("\"{","{").replace("}\"","}");
                newJe = "["+newJe+"]";
                return Response.success(new JSONArray(newJe),HttpHeaderParser.parseCacheHeaders(response));
            }
            return Response.success(new JSONArray(je.substring(1,je.length()-1).replace("\\","")), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException var3) {
            return Response.error(new ParseError(var3));
        } catch (JSONException var4) {
            return Response.error(new ParseError(var4));
        }
    }
}
