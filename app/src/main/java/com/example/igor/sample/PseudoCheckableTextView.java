package com.example.igor.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Checkable;
import android.widget.TextView;

public class PseudoCheckableTextView extends TextView implements Checkable {

    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
    private static final String LOG_TAG = "PseudoCheckableTextView";

    private boolean mChecked = false;

    public PseudoCheckableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean b) {
        if (b != mChecked) {
            mChecked = b;
            refreshDrawableState();
        }
    }

    public void toggle() {
        Log.d(LOG_TAG, "toggle from " + mChecked + " to " + !mChecked);
        setChecked(!mChecked);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}
