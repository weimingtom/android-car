/*
  Multicolor Lamp (works with Amarino and the MultiColorLamp Android app)
  
  - based on the Amarino Multicolor Lamp tutorial
  - receives custom events from Amarino changing color accordingly
  
  author: Bonifaz Kaufmann - December 2009
*/
 
#include <MeetAndroid.h>

// declare MeetAndroid so that you can call functions with it
MeetAndroid meetAndroid;

// we need 3 PWM pins to control the leds
int left = 9;   
int right = 10;


void setup()  
{
  // use the baud rate your bluetooth module is configured to 
  // not all baud rates are working well, i.e. ATMEGA168 works best with 57600
  Serial.begin(57600); 
  
  // register callback functions, which will be called when an associated event occurs.
  meetAndroid.registerFunction(left, 'c');
  meetAndroid.registerFunction(right, 'd');  
   

  // set all color leds as output pins
  pinMode(left, OUTPUT);
  pinMode(right, OUTPUT);
  
 
}

void loop()
{
  meetAndroid.receive(); // you need to keep this in your loop() to receive events
}

/*
 * Whenever the multicolor lamp app changes the red value
 * this function will be called
 */
void left(byte flag, byte numOfValues)
{
  
  digitalWrite(left, LOW);
}

/*
 * Whenever the multicolor lamp app changes the green value
 * this function will be called
 */
void right(byte flag, byte numOfValues)
{
  digitalWrite(right, LOW);
}


