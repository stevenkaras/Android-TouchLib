package sek.touch.processors;

import sek.touch.GestureManager;
import sek.touch.GestureProcessor;
import sek.touch.GestureType;
import android.util.FloatMath;
import android.view.MotionEvent;

/**
 * Default processor for gestures. Taps are the start of every other gesture. As such, this processor
 * must determine when a tap has ended, and a different type of gesture has begun.
 * 
 * @author Steven Karas
 *
 */
public class TapProcessor extends GestureProcessor {
	
	/** the starting coordinates of the tap */
	private float x, y;
	/** the time at which the current tap started */
	private long startTime;
	
	public TapProcessor(GestureManager manager) {
		super(manager);
	}

	@Override
	public void startGesture(MotionEvent event) {
		x = event.getX();
		y = event.getY();
		startTime = event.getEventTime();
	}
	
	@Override
	protected GestureType handlePointerDown(MotionEvent event) {
		// currently, we only support pinch multitouch gestures
		return GestureType.Pinch;
	}
	
	@Override
	protected GestureType handleMove(MotionEvent event) {
		float dx = x - event.getX();
		float dy = y - event.getY();
		float distance = FloatMath.sqrt(dx * dx + dy * dy);
		
		// check if it's turned into a drag gesture
		if (distance > DragProcessor.DRAG_TRIGGER_DISTANCE) {
			return GestureType.Drag;
		}
		
		long duration = event.getEventTime() - startTime;

		// check if it's turned into a long tap gesture
		if (duration > LongTapProcessor.LONG_TAP_TRIGGER_DELAY) {
			return GestureType.LongTap;
		}
		
		// do nothing
		return GestureType.GestureContinue;
	}

	@Override
	protected GestureType handleUp(MotionEvent event) {
		fireTap(x, y);
		return GestureType.GestureEnded;
	}

	@Override
	protected GestureType handleDown(MotionEvent event) {
		// the tap has just started
		return GestureType.GestureContinue;
	}
}
