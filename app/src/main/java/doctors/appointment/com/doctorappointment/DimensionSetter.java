package doctors.appointment.com.doctorappointment;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by anweshmishra on 08/07/15.
 */
public class DimensionSetter {
    public static void setEditTextDimensions(EditText editText,int width,int height,int maxWidth,int maxHeight) {
        editText.setX(width);
        editText.setY(height);
        editText.setWidth(maxWidth - width);
        editText.setHeight(maxHeight);
    }
    public static void setLabelTextDimensions(TextView labelText,int width,int height,int maxWidth,int maxHeight) {
        labelText.setX(width);
        labelText.setY(height);
        labelText.setWidth(maxWidth-width);
        labelText.setHeight(maxHeight);
    }
    public static void setButtonDimensions(Button button,int width,int height,int maxWidth,int maxHeight) {
        button.setX(width);
        button.setY(height);
        button.setWidth(maxWidth-height);
        button.setHeight(maxHeight);
    }
 }
