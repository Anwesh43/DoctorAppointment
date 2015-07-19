package doctors.appointment.com.doctorappointment;

import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by anweshmishra on 08/07/15.
 */
public class ViewUtils {
    public static boolean checkEditTextEmpty(EditText editText) {
        return editText.getText().toString().trim().equals("");
    }
    public static ArrayList<EditText> getEmptyTextFields(EditText...editTexts) {
        ArrayList<EditText> editTextArrayList = new ArrayList<EditText>();
        for(int i=0;i<editTexts.length;i++) {
            if(checkEditTextEmpty(editTexts[i])) {
                editTextArrayList.add(editTexts[i]);
            }
        }
        return editTextArrayList;
    }
    public static boolean checkTextFieldEqualsValue(EditText editText,String value) {
        return editText.getText().toString().equals(value);
    }
    public static boolean isEditTextValueEqual(EditText...editTexts) {
        boolean isEqual = true;
        String firstString = editTexts[0].getText().toString();
        for(int i=1;i<editTexts.length;i++) {
            String currentString = editTexts[i].getText().toString();
            if(!firstString.equals(currentString)) {
                isEqual = false;
                break;
            }
        }
        return isEqual;
    }
}
