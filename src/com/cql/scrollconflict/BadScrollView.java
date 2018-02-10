package com.cql.scrollconflict;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;


public class BadScrollView extends ScrollView {
    
    private MainActivity mActivity;
    
    private int lastY;
    
    public BadScrollView(Context context) {
        this(context, null);
    }

    public BadScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BadScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BadScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mActivity = (MainActivity) context;
    }
    
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
    
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.d("cql","l = "+l);
        Log.d("cql","t = "+t);
        Log.d("cql","oldl = "+oldl);
        Log.d("cql","oldt = "+oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction() & MotionEvent.ACTION_MASK;
        
        if(action == MotionEvent.ACTION_DOWN){
            Log.d("cql","down,y = "+ev.getY());
        }else if(action == MotionEvent.ACTION_UP){
            Log.d("cql","up,y = "+ev.getY());
        }
        
//        int a = mActivity.upListView.getHeight();
//        int b = mActivity.upListView.getMeasuredHeight();
//        int d = mActivity.upListView.getChildCount();
//        int c = mActivity.upListView.getChildAt(0).getHeight();
        return super.dispatchTouchEvent(ev);
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int action = ev.getAction() & MotionEvent.ACTION_MASK;
        
        switch(action){
            case MotionEvent.ACTION_DOWN:
//                lastY = (int) ev.getY();
                super.onInterceptTouchEvent(ev);
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
//                int x = (int) ev.getRawX();
//                int y = (int) ev.getRawY();
//                int offsetY = (int) (ev.getY() - lastY);
//                if(offsetY < 0){
//                    if((isTouchPointInView(mActivity.upListView, x, y) && mActivity.upViewState != 1)){
//                        intercept = true;
//                    }else if(isTouchPointInView(mActivity.downListView, x, y) && mActivity.downViewState != 1){
//                        intercept = true;
//                    }else{
//                        intercept = false;
//                    }
//                }else if(offsetY > 0){
//                    if(isTouchPointInView(mActivity.upListView, x, y) && mActivity.upViewState != 0){
//                        intercept = true;
//                    }else if(isTouchPointInView(mActivity.downListView, x, y) && mActivity.downViewState != 0){
//                        intercept = true;
//                    }else{
//                        intercept = false;
//                    }
//                }
                intercept = true;
                break;
            case MotionEvent.ACTION_UP:
                intercept = true;
                break;
            default:
                break;
        }
        return intercept;
    }
    
}
