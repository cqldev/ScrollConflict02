
package com.cql.scrollconflict;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements OnTouchListener {
    
    public ListView upListView;
    
    public ListView downListView;
    
    public int upViewState = 0;
    
    public int downViewState = 0;
    
    private int upScrollY;
    
    private int downScrollY;
    
    private int lastY;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        initViews();
    }
    
    private void initViews(){
        upListView = (ListView) findViewById(R.id.uplistview);
        downListView = (ListView) findViewById(R.id.downlistview);
        
        List<String> datas = initDatas();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
        upListView.setAdapter(adapter);
        downListView.setAdapter(adapter);
        upListView.setOnTouchListener(this);
        downListView.setOnTouchListener(this);
    }
    
    private List<String> initDatas(){
        List<String> datas = new ArrayList<String>();
        
        for(int i=0;i<20;i++){
            datas.add("data"+i);
        }
        return datas;
    }
    

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        ListView listView = (ListView) v;
        
        int visibleHeight = listView.getHeight();
        int childCount = listView.getAdapter().getCount();
        int childHeight = listView.getChildAt(0).getHeight();
        int listViewHeight = childCount * childHeight;

        switch(v.getId()){
            
            case R.id.uplistview:
                switch(action){
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().getParent().requestDisallowInterceptTouchEvent(true);
                        lastY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int offsetY = (int) (event.getY() - lastY);
//                        View view = (View) listView.getAdapter().getItem(0);
//                        if(offsetY > 0 && view.getTop() == 0){
//                            v.getParent().getParent().requestDisallowInterceptTouchEvent(false);
//                            break;
//                        }
                        upScrollY -= offsetY;
                        if(upScrollY + visibleHeight >= listViewHeight){
                            v.getParent().getParent().requestDisallowInterceptTouchEvent(false);
                            upScrollY = 0;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        v.performClick();
                        break;
                }
                break;
            case R.id.downlistview:
                switch(action){
                    
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().getParent().requestDisallowInterceptTouchEvent(true);
                        lastY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int offsetY = (int) (event.getY() - lastY);
                        downScrollY -= offsetY;
                        if(downScrollY + visibleHeight >= listViewHeight){
                            v.getParent().getParent().requestDisallowInterceptTouchEvent(false);
                            downScrollY = 0;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        v.performClick();
                        break;
                }
                break;
                
        }
        
        return false;
    }

    
}
