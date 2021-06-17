package space.nullpointerexception.libraary.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import space.nullpointerexception.libraary.DataUtils.Utility;

public class FixedScrollView extends ScrollView {


    public FixedScrollView(Context context) {
        super(context);
    }

    public FixedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FixedScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) Utility.pxFromDp(getContext(),600), MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
