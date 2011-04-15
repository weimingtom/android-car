package com.demo.android.test;

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

public class test extends Activity {
	 private static final String DEVICE_ADDRESS =  "00:11:33:16:00:02";
			private GestureDetector detector_up;
			private GestureDetector detector_down;
			private GestureDetector detector_left;
			private GestureDetector detector_right;
	
		  private Button button1;
		  private Button button2;
		  private Button button3;
		  private Button button4;
	        
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        Amarino.connect(this, DEVICE_ADDRESS);
	        
	        button1 = (Button)findViewById(R.id.up);
	        button2 = (Button)findViewById(R.id.down);
	        button3 = (Button)findViewById(R.id.left);
	        button4 = (Button)findViewById(R.id.right);
			// this is how you tell Amarino to connect to a specific BT device from within your own code
	
			    detector_up = new GestureDetector(onGestureListener_UP);
			    detector_down = new GestureDetector(onGestureListener_DOWN);
			    detector_left = new GestureDetector(onGestureListener_LEFT);
			    detector_right = new GestureDetector(onGestureListener_RIGHT);
				  button1.setOnTouchListener(onTouchListener);
			    button2.setOnTouchListener(onTouchListener);
			    button3.setOnTouchListener(onTouchListener);
			    button4.setOnTouchListener(onTouchListener);
	}
	
	private OnTouchListener onTouchListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.up:
				return detector_up.onTouchEvent(event);
				break;
			case R.id.down:
				return detector_down.onTouchEvent(event);
				break;
			case R.id.left:
				return detector_left.onTouchEvent(event);
				break;
			case R.id.down:
				return detector_right.onTouchEvent(event);
				break;
			default:
				break;
			}
			return false;
		}
	};
	private OnGestureListener onGestureListener_UP = new OnGestureListener() {

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'a', 1);
		}
	};
	private OnGestureListener onGestureListener_DOWN = new OnGestureListener() {

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'b', 2);
		}
	};
	private OnGestureListener onGestureListener_LEFT = new OnGestureListener() {

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'c', 3);
		}
	};
	private OnGestureListener onGestureListener_RIGHT = new OnGestureListener() {

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'd', 4);
		}
	};
	

	    @Override
		protected void onStart() {
	    	super.onStart();
			// in order to receive broadcasted intents we need to register our receiver
			registerReceiver(arduinoReceiver, new IntentFilter(AmarinoIntent.ACTION_RECEIVED));
			
			// this is how you tell Amarino to connect to a specific BT device from within your own code
			Amarino.connect(this, DEVICE_ADDRESS);
		}


		@Override
		protected void onStop() {
			super.onStop();
			
			// if you connect in onStart() you must not forget to disconnect when your app is closed
			Amarino.disconnect(this, DEVICE_ADDRESS);
			
			// do never forget to unregister a registered receiver
			unregisterReceiver(arduinoReceiver);
		}
}    
   