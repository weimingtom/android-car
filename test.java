
package com.demo.android.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import at.abraxas.amarino.Amarino;
public class Test extends Activity {
         private static final String DEVICE_ADDRESS =  "00:11:33:16:00:02";

                        private GestureDetector detector_left;
                        private GestureDetector detector_right;
        
    
                  private Button left;
                  private Button right;
                  
                  private Context mContext;
                
            /** Called when the activity is first created. */
            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.main);
                mContext = getApplicationContext();
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
 
                        case R.id.right:
                                return detector_right.onTouchEvent(event);
  
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
                        Amarino.sendDataToArduino(mContext, DEVICE_ADDRESS, 'c', 3);  ////sendDataToArduino error  ？
                }

				@Override
				public boolean onDown(MotionEvent arg0) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean onFling(MotionEvent e1, MotionEvent e2,
						float velocityX, float velocityY) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean onScroll(MotionEvent e1, MotionEvent e2,
						float distanceX, float distanceY) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void onShowPress(MotionEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public boolean onSingleTapUp(MotionEvent e) {
					// TODO Auto-generated method stub
					return false;
				}
        };
        private OnGestureListener onGestureListener_RIGHT = new OnGestureListener() {  //OnGestureListener  error  ？

                @Override
                public void onLongPress(MotionEvent e) {
                        // TODO Auto-generated method stub
                        Amarino.sendDataToArduino(mContext, DEVICE_ADDRESS, 'd', 4);  //sendDataToArduino error  ？
                }

				@Override
				public boolean onDown(MotionEvent e) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean onFling(MotionEvent e1, MotionEvent e2,
						float velocityX, float velocityY) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean onScroll(MotionEvent e1, MotionEvent e2,
						float distanceX, float distanceY) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void onShowPress(MotionEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public boolean onSingleTapUp(MotionEvent e) {
					// TODO Auto-generated method stub
					return false;
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