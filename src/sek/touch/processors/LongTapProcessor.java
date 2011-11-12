package sek.touch.processors;

import java.util.Timer;
import java.util.TimerTask;

import sek.touch.GestureManager;
import sek.touch.GestureProcessor;
import sek.touch.GestureType;

import android.view.MotionEvent;

/**
 * Processor for long taps. Long tap events are sent out every {@value #LONG_TAP_INTERVAL}ms.
 * 
 * @author Steven Karas
 */
public class LongTapProcessor extends GestureProcessor {

	public LongTapProcessor(GestureManager manager) {
		super(manager);
	}

	/** the delay before triggering a long tap */
	public static final long LONG_TAP_TRIGGER_DELAY = 500;
	/** the delay between firing long tap events */
	public static final long LONG_TAP_INTERVAL = 500;

	/** timer used to fire long tap events */
	private Timer timer = new Timer();

	/** the timer task used to fire long tap events */
	private TimerTask timerTask;

	/** the location of the long tap */
	private float x, y;

	/** the starting time of the long tap */
	private long startTime;

	/** the number of long tap events that have been fired */
	private int count;

	@Override
	public void startGesture(MotionEvent event) {
		x = event.getX();
		y = event.getY();
		startTime = event.getDownTime();
		count = 0;

		fireStartLongTap(x, y, event.getEventTime() - startTime);

		timerTask = new TimerTask() {
			@Override
			public void run() {
				count++;
				//TODO: fix the duration to actually be the duration
				fireMidLongTap(x, y, count * LONG_TAP_INTERVAL);
			}
		};
		timer.scheduleAtFixedRate(timerTask, LONG_TAP_INTERVAL, LONG_TAP_INTERVAL);
	}

	@Override
	protected GestureType handleUp(MotionEvent event) {
		endGesture(event.getEventTime() - startTime);
		return GestureType.GestureEnded;
	}

	@Override
	protected GestureType handleMove(MotionEvent event) {
		// do nothing
		return GestureType.GestureContinue;
	}

	/**
	 * End the current long tap
	 * 
	 * @param duration
	 */
	protected void endGesture(long duration) {
		fireEndLongTap(x, y, duration);
		timerTask.cancel();
	}
}
