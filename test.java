package com.demo.android.test;
//我前几天公司里太忙了，今天编译了一下，修改了一点，还有4个错误，你看看，是什么原因
import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;
import at.abraxas.amarino.Amarino;//需要这个语句 

public class Test extends Activity {
         private static final String DEVICE_ADDRESS =  "00:11:33:16:00:02";

                        private GestureDetector detector_left;
                        private GestureDetector detector_right;
        
    
                  private Button left;
                  private Button right;
                
            /** Called when the activity is first created. */
            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.main);
                
                Amarino.connect(this, DEVICE_ADDRESS);
                

                left = (Button)findViewById(R.id.left);
                right = (Button)findViewById(R.id.right);
                        // this is how you tell Amarino to connect to a specific BT device from within your own code
        

                            detector_left = new GestureDetector(onGestureListener_LEFT);
                            detector_right = new GestureDetector(onGestureListener_RIGHT);

                            left.setOnTouchListener(onTouchListener);
                            right.setOnTouchListener(onTouchListener);
        }
        
        private OnTouchListener onTouchListener = new OnTouchListener() {
                
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                        // TODO Auto-generated method stub
                        switch (v.getId()) {

                        case R.id.left:
                                return detector_left.onTouchEvent(event);
                                break;
                        case R.id.right:
                                return detector_right.onTouchEvent(event);
                                break;
                        default:
                                break;
                        }
                        return false;
                }
        };
    
        private OnGestureListener onGestureListener_LEFT = new OnGestureListener() {////OnGestureListener  error  ？

                @Override
                public void onLongPress(MotionEvent e) {
                        // TODO Auto-generated method stub
                        Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'c', 3);  ////sendDataToArduino error  ？
                }
        };
        private OnGestureListener onGestureListener_RIGHT = new OnGestureListener() {  //OnGestureListener  error  ？

                @Override
                public void onLongPress(MotionEvent e) {
                        // TODO Auto-generated method stub
                	Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'd', 4);  //sendDataToArduino error  ？
                }
        };
        

            @Override
                protected void onStart() {
                super.onStart();
                  
                        // this is how you tell Amarino to connect to a specific BT device from within your own code
                        Amarino.connect(this, DEVICE_ADDRESS);
                }


                @Override
                protected void onStop() {
                        super.onStop();
                        
                        // if you connect in onStart() you must not forget to disconnect when your app is closed
                        Amarino.disconnect(this, DEVICE_ADDRESS);
                        

                }
}    
 