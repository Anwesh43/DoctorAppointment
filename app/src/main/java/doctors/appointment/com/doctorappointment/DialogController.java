package doctors.appointment.com.doctorappointment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

/**
 * Created by anweshmishra on 16/07/15.
 */
public class DialogController {

    private ProgressDialog progressDialog;
    private AlertDialog.Builder alertDialog;
    private Activity activity;
    public DialogController(Activity activity){
        this.activity = activity;
    }
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }
    public void showAlertDialog(String message) {
        alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
}
