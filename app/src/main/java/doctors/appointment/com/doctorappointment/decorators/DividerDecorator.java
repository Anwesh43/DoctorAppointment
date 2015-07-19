package doctors.appointment.com.doctorappointment.decorators;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by anweshmishra on 08/07/15.
 */
public class DividerDecorator extends RecyclerView.ItemDecoration {
    int space;
    int color;
    public DividerDecorator(int space,int color) {
        this.space = space;
        this.color = color;
    }
    public void getItemOffsets(Rect outRect,View view,RecyclerView recyclerView,RecyclerView.State state) {
        outRect.bottom = space;

    }
    public void onDrawOver(Canvas canvas,RecyclerView recyclerView,RecyclerView.State state) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        for(int i=0;i<recyclerView.getChildCount();i++) {
            View child = recyclerView.getChildAt(i);
            Rect rect = new Rect();
            rect.left = 0;
            rect.right = recyclerView.getWidth();
            rect.top = child.getBottom();
            rect.bottom = child.getBottom()+space;
            canvas.drawRect(rect,paint);
        }

    }
}
