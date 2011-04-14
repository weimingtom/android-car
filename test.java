package com.demo.android.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import at.abraxas.amarino.Amarino;


public class test extends Activity {
		private static final String DEVICE_ADDRESS =  "00:11:33:16:00:02";
		
        
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Amarino.connect(this, DEVICE_ADDRESS);
        
        Button button1 = (Button)findViewById(R.id.left);
        Button button2 = (Button)findViewById(R.id.right);
		// this is how you tell Amarino to connect to a specific BT device from within your own code
		
    }
    private void senddate1(){    //这里想通过判断按钮是否被安下，来发送信息,但不知道具体的语法
    	if(button1)
    	{
    		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'a', 1);
    	}	
	}
    private void senddate2(){
    	if(button2)
    	{
    		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'b', 2);
    	}	
	}
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
   