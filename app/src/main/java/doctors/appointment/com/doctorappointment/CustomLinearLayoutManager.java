package doctors.appointment.com.doctorappointment;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by anweshmishra on 18/07/15.
 */
public class CustomLinearLayoutManager extends LinearLayoutManager {
    int space;
    public CustomLinearLayoutManager(Context context,int space) {
        super(context);
        this.space = space;
    }
    public void smoothScrollToPosition(RecyclerView recyclerView,RecyclerView.State state,final int targetPosition) {
        final int position = getPosition(getChildAt(0));

        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                int direction = 1;
                if(position>targetPosition) {
                    direction*=-1;
                }
                return new PointF(0,direction);
            }
            protected void onTargetFound(View targetView, RecyclerView.State state, Action action) {
                final int dx = calculateDxToMakeVisible(targetView, getHorizontalSnapPreference());
                final int dy = calculateDyToMakeVisible(targetView, getVerticalSnapPreference());
                final int distance = (int) Math.sqrt(dx * dx + dy * dy);
                final int time = calculateTimeForDeceleration(distance);
                if (time > 0) {
                    action.update(-dx, -dy, time, mDecelerateInterpolator);
                }
            }
        };
        smoothScroller.setTargetPosition(targetPosition);

        startSmoothScroll(smoothScroller);
    }
}
