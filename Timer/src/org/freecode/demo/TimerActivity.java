package org.freecode.demo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class TimerActivity extends Activity {

	public final static String CLOCK_TYPE_STOPWATCH = "STOP_WATCH";
	public final static String CLOCK_TYPE_TIMER = "TIMER";
	private int theHour = 0;
	private int theMinute = 0;
	private int theSecond = 0;
	private int theMillisecond = 0;
	private String theClockType = CLOCK_TYPE_STOPWATCH;
	
	private Timer theTimer = new Timer();
	private Handler mHandler = new Handler();
	
	// to save the performance, not use image and sound
	// private MediaPlayer thePlayer = null;
	// private ImageView imgvwMain = null;

	private TextView txvTime;
	private RadioButton rbtnStopWatch;
	private RadioButton rbtnTimer;

	private final static String TAG = "Clock Log ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initializeGUI();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initializeGUI() {
		txvTime = (TextView) findViewById(R.id.txvTime);
		txvTime.setText(convertTotalMillisecondsToHHMISSsss());
		
		// imgvwMain = (ImageView) findViewById(R.id.imgvwMain);
	}

	private int formatStringToInt(String aVal) {
		return Integer.parseInt(aVal);
	}

	private String formatIntegerToString(int aVal) {
		return String.format("%02d", aVal);
	}

	private String formatIntegerToString(int aVal, int numLeading) {
		return String.format("%0" + numLeading + "d", aVal);
	}

	private int convertHHMISSToTotalMilliseconds() {
		return theHour * 60 * 60 * 1000 + theMinute * 60 * 1000 + theSecond
				* 1000 + theMillisecond;
	}

	private String convertTotalMillisecondsToHHMISSsss() {
		return formatIntegerToString(theHour) + ":"
				+ formatIntegerToString(theMinute) + ":"
				+ formatIntegerToString(theSecond) + "."
				+ formatIntegerToString(theMillisecond, 3);
	}

	private void setHHMISSByMilliseconds(int millisecondVal) {
		int reminder = 0;

		theHour = millisecondVal / (60 * 60 * 1000);
		reminder = millisecondVal % (60 * 60 * 1000);
		theMinute = reminder / (60 * 1000);
		reminder = reminder % (60 * 1000);
		theSecond = reminder / 1000;
		theMillisecond = reminder % 1000;
	}

	private void refreshHHMISSsss() {
		txvTime.setText(convertTotalMillisecondsToHHMISSsss());
	}
	
//	private void playDrumSound() {
//		thePlayer = MediaPlayer.create(this, R.raw.drums);
//		thePlayer.start();
//	}
	
//	private void showImage(boolean flag )
//	{
//		if ( flag ) {
//			imgvwMain.setVisibility( View.VISIBLE );
//		}
//		else {
//			imgvwMain.setVisibility( View.INVISIBLE );
//		}
//	}

	public void changeClockType(View v) {
		boolean isChecked = ((RadioButton) v).isChecked();
		switch (v.getId()) {
		case R.id.rbtnStopWatch:
			if (isChecked) {
				theClockType = CLOCK_TYPE_STOPWATCH;
			}
			break;
		case R.id.rbtnTimer:
			if (isChecked) {
				theClockType = CLOCK_TYPE_TIMER;
			}
			break;
		default:
			Toast.makeText(this, "Please choose a Clock Type",
					Toast.LENGTH_SHORT).show();
			break;
		}

	}

	public void addHour(View v) {
		if (theHour < 23) {
			theHour += 1;
		} else {
			theHour = 0;
		}
		refreshHHMISSsss();
	}

	public void addMinute(View v) {
		if (theMinute < 59) {
			theMinute += 1;
		} else {
			theMinute = 0;
		}
		refreshHHMISSsss();
	}

	public void addSecond(View v) {
		if (theSecond < 59) {
			theSecond += 1;
		} else {
			theSecond = 0;
		}
		refreshHHMISSsss();
	}

	public void subtractHour(View v) {
		if (theHour > 0) {
			theHour -= 1;
		} else {
			theHour = 23;
		}
		refreshHHMISSsss();
	}

	public void subtractMinute(View v) {
		if (theMinute > 0) {
			theMinute -= 1;
		} else {
			theMinute = 59;
		}
		refreshHHMISSsss();
	}

	public void subtractSecond(View v) {
		if (theSecond > 0) {
			theSecond -= 1;
		} else {
			theSecond = 59;
		}
		refreshHHMISSsss();
	}

	/**
	 * start the clock, counting up for Stop Watch and down for Timer
	 * Separate threads to change time and refresh GUI
	 * @param v
	 */
	public void startClock(View v) {
		
		if ( CLOCK_TYPE_TIMER.equalsIgnoreCase( theClockType ) &&
				convertHHMISSToTotalMilliseconds() < 1 ) {
			Toast.makeText(this, "Please add some hours, minutes or seconds before starting the Timer", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// showImage(true);
		
		if ( theTimer == null ) {
			theTimer = new Timer();
		}

		theTimer.schedule(new TimerTask() {
			public void run() {
				int totalMilliseconds = convertHHMISSToTotalMilliseconds();
				
				if (CLOCK_TYPE_TIMER.equalsIgnoreCase( theClockType ) &&
						totalMilliseconds < 1) {
					releaseTimer();
					
					// playDrumSound();
					return;
				}

				if (CLOCK_TYPE_STOPWATCH.equalsIgnoreCase(theClockType)) {
					totalMilliseconds++;
				} else if (CLOCK_TYPE_TIMER.equalsIgnoreCase(theClockType)) {
					totalMilliseconds--;
				}
				setHHMISSByMilliseconds(totalMilliseconds);

				// another thread to refresh the text view
				mHandler.post(new Runnable() {
					public void run() {
						refreshHHMISSsss();
					}
				});
			}

		}, 0, 1);

	}

	public void stopClock(View v) {
		// showImage(false);
		releaseTimer();
	}
	
	public void releaseTimer() {
		if (theTimer != null) {
			theTimer.cancel();
			theTimer.purge();
			theTimer = null;
		}
	}

	/**
	 * zero out all the values
	 * @param v
	 */
	public void resetClock(View v) {
		
		releaseTimer();
		
		theHour = 0;
		theMinute = 0;
		theSecond = 0;
		theMillisecond = 0;

		refreshHHMISSsss();
	}

	public void closeActivity(View v) {
//		if ( thePlayer != null ) {
//			thePlayer.release();
//		}
		finish();
		
	}

}
