package sek.touch;

import java.util.HashSet;
import java.util.Set;

import sek.touch.processors.DragProcessor;
import sek.touch.processors.LongTapProcessor;
import sek.touch.processors.PinchProcessor;
import sek.touch.processors.TapProcessor;

import android.view.MotionEvent;

/**
 * Observer manager for gestures. In order to be useful, it must be explicitly passed motion events
 * in the onTouchEvent(MotionEvent) of a view.
 * <p>
 * Note that this is largely untested, and may behave funny if multiple managers are created, since
 * some state is shared by the Android system.
 * <p>
 * A default instance is provided as a convenience.
 * 
 * @author Steven Karas
 */
public class GestureManager {

	/** default instance of a manager */
	public static GestureManager manager = new GestureManager();
	
	/** collection of the listeners */
	private Set<GestureListener> listeners;
	
	/*
	 * the processors for the various gestures
	 * 
	 * BESPOKE: new gesture types must be added here
	 */
	private TapProcessor tap = new TapProcessor(this);
	private LongTapProcessor longTap = new LongTapProcessor(this);
	private DragProcessor drag = new DragProcessor(this);
	private PinchProcessor pinch = new PinchProcessor(this);

	/** the current gesture processor */
	private GestureProcessor processor = null;
	
	/** the delay between gestures */
	private static final long GESTURE_DELAY_INTERVAL = 150;
	
	/** the earliest time a new gesture will be allowed */
	private long nextGestureTime = 0;
	
	/**
	 * Process the given MotionEvent using the current gesture processor.
	 * <br>
	 * This should be called from the onTouchEvent(MotionEvent) of a view you want to have support gestures
	 * 
	 * @param event
	 */
	public void processTouchEvent(MotionEvent event) {
		//TODO: add palm detection
		
		// don't accept gestures until the gesture delay interval has passed
		if (event.getEventTime() < nextGestureTime) {
			return;
		}
		
		// bootstrap a new gesture, if necessary
		if (processor == null) {
			// default to the tap processor
			processor = tap;
			processor.startGesture(event);
		}
		
		// process the event
		GestureType currentType = processor.processEvent(event);
		switch (currentType) {
		case GestureEnded:
			processor = null;
			nextGestureTime = event.getEventTime() + GESTURE_DELAY_INTERVAL;
			return;
		case GestureContinue:
			// continue processing with the current gesture
			return;
		case Tap:
			processor = tap;
			break;
		case LongTap:
			processor = longTap;
			break;
		case Drag:
			processor = drag;
			break;
		case Pinch:
			processor = pinch;
			break;
		default:
			//BESPOKE: add new gesture types here
			throw new GestureException();
		}
		// inform the new processor that a gesture has started
		processor.startGesture(event);
	}
	
	/**
	 * Add a gesture listener
	 * 
	 * @param listener
	 */
	public void addGestureListener(GestureListener listener) {
		if (listeners == null) {
			listeners = new HashSet<GestureListener>();
		}
		listeners.add(listener);
	}

	/**
	 * Clear all the listeners from this manager
	 */
	public void removeAllListeners() {
		if (listeners == null) {
			return;
		}
		listeners.clear();
	}
	
	/*
	 * Event firing methods.
	 * BESPOKE: add new gesture events here
	 */
	protected void fireTap(float x, float y) {
		if (listeners == null) {
			return;
		}
		for (GestureListener listener : listeners) {
			listener.onTap(x, y);
		}
	}
	
	protected void fireStartLongTap(float x, float y, long duration) {
		if (listeners == null) {
			return;
		}
		for (GestureListener listener : listeners) {
			listener.onStartLongTap(x, y, duration);
		}
	}
	protected void fireMidLongTap(float x, float y, long duration) {
		if (listeners == null) {
			return;
		}
		for (GestureListener listener : listeners) {
			listener.onMidLongTap(x, y, duration);
		}
	}
	protected void fireEndLongTap(float x, float y, long duration) {
		if (listeners == null) {
			return;
		}
		for (GestureListener listener : listeners) {
			listener.onEndLongTap(x, y, duration);
		}
	}
	
	protected void fireStartDrag(float fromX, float fromY, float toX, float toY) {
		if (listeners == null) {
			return;
		}
		for (GestureListener listener : listeners) {
			listener.onStartDrag(fromX, fromY, toX, toY);
		}
	}
	protected void fireMidDrag(float fromX, float fromY, float toX, float toY) {
		if (listeners == null) {
			return;
		}
		for (GestureListener listener : listeners) {
			listener.onMidDrag(fromX, fromY, toX, toY);
		}
	}
	protected void fireEndDrag(float fromX, float fromY, float toX, float toY) {
		if (listeners == null) {
			return;
		}
		for (GestureListener listener : listeners) {
			listener.onEndDrag(fromX, fromY, toX, toY);
		}
	}
	
	protected void fireStartPinch(float scale, float centerX, float centerY) {
		if (listeners == null) {
			return;
		}
		for (GestureListener listener : listeners) {
			listener.onStartPinch(scale, centerX, centerY);
		}
	}
	protected void fireMidPinch(float scale, float centerX, float centerY) {
		if (listeners == null) {
			return;
		}
		for (GestureListener listener : listeners) {
			listener.onMidPinch(scale, centerX, centerY);
		}
	}
	protected void fireEndPinch(float scale, float centerX, float centerY) {
		if (listeners == null) {
			return;
		}
		for (GestureListener listener : listeners) {
			listener.onEndPinch(scale, centerX, centerY);
		}
	}

}
