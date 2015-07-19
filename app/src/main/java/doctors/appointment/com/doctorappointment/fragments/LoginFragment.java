package doctors.appointment.com.doctorappointment.fragments;

/**
 * Created by anweshmishra on 08/07/15.
 */
import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import doctors.appointment.com.doctorappointment.AppConstants;
import doctors.appointment.com.doctorappointment.DimensionSetter;
import doctors.appointment.com.doctorappointment.MainActivity;
import doctors.appointment.com.doctorappointment.R;
import doctors.appointment.com.doctorappointment.ViewInitializer;
import doctors.appointment.com.doctorappointment.ViewUtils;

public class LoginFragment extends Fragment{
    MainActivity mainActivity;
    private EditText doctorUser,doctorPass;
    public View onCreateView(LayoutInflater inflater,ViewGroup group,Bundle bundle) {
        final View view = inflater.inflate(R.layout.login_layout,group,false);
        ViewInitializer<EditText> editTextInitializer = new ViewInitializer<EditText>();
        ViewInitializer<TextView> labelTextInitializer = new ViewInitializer<TextView>();
        ViewInitializer<Button> buttonViewInitializer = new ViewInitializer<Button>();
        final TextView userLabel = labelTextInitializer.initializeView(R.id.user_label,view);
        final TextView passLabel = labelTextInitializer.initializeView(R.id.pass_label,view);
        doctorUser = editTextInitializer.initializeView(R.id.doctor_user,view);
        doctorPass = editTextInitializer.initializeView(R.id.doctor_pass,view);
        final Button button = buttonViewInitializer.initializeView(R.id.login,view);
        setUpLoignAction(button,doctorUser,doctorPass);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = view.getWidth(), height = view.getHeight();
                DimensionSetter.setLabelTextDimensions(userLabel,0,0,width/3,height/20);
                DimensionSetter.setLabelTextDimensions(passLabel,0,3*height/20,width/3,height/20);
                DimensionSetter.setEditTextDimensions(doctorUser, width / 3, 0, width, height / 20);
                DimensionSetter.setEditTextDimensions(doctorPass,width/3,3*height/20,width,height/20);
                DimensionSetter.setButtonDimensions(button,0,5*height/20,width,height/20);
            }
        });
        return view;
    }
    public void setUpLoignAction(Button button,final EditText user,final EditText pass) {
        button.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                ArrayList<EditText> emptyFields = ViewUtils.getEmptyTextFields(user,pass);
                if(emptyFields.size()!=0) {
                    for(EditText emptyField:emptyFields) {
                        emptyField.setError("Please enter value");
                    }
                }
                else {
                    if(ViewUtils.isEditTextValueEqual(user,pass)) {
                        mainActivity.createLoginRequest(user.getText().toString(),pass.getText().toString());
                    }
                    else {
                        setTextBoxesError();
                    }

                }
            }
        });
    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity)activity;
    }
    public void setTextBoxesError() {
        doctorUser.setError("please enter proper values");
        doctorPass.setError("please enter proper values");
    }
}
