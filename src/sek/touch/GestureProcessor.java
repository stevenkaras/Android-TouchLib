package sek.touch;

import android.view.MotionEvent;

/**
 * Base class for Gesture processors. A gesture processor maintains the current state of the
 * gesture.
 * 
 * @author Steven Karas
 */
public abstract class GestureProcessor {

	public static boolean DEBUG_MODE = false;
	
	/** stored reference to the gesture manager  */
	private final GestureManager manager;

	/**
	 * Create a new gesture processor, that triggers events on the provided manager
	 * 
	 * @param manager
	 */
	public GestureProcessor(GestureManager manager) {
		this.manager = manager;
	}

	/**
	 * Start a new gesture
	 * 
	 * @param event
	 */
	public abstract void startGesture(MotionEvent event);

	/**
	 * Process the next event in this gesture. If a new type of gesture has been started, the
	 * GestureType returned corresponds to the processor type. If the gesture has simply ended, then
	 * the return value should be {@link GestureType#GestureEnded}. If the gesture has not yet
	 * ended, then the return value should be {@link GestureType#GestureContinue}
	 * 
	 * @param event
	 * @return
	 */
	public GestureType processEvent(MotionEvent event) {
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			return handleDown(event);
		case MotionEvent.ACTION_MOVE:
			return handleMove(event);
		case MotionEvent.ACTION_POINTER_DOWN:
			return handlePointerDown(event);
		case MotionEvent.ACTION_POINTER_UP:
			return handlePointerUp(event);
		case MotionEvent.ACTION_UP:
			return handleUp(event);
		default:
			throw new GestureException();
		}
	}

	/**
	 * Called to handle ACTION_UP events
	 * 
	 * @param event
	 * @return
	 */
	protected GestureType handleUp(MotionEvent event) {
		if (DEBUG_MODE) {
			throw new GestureException();
		} else {
			return GestureType.GestureEnded;
		}
	}

	/**
	 * Called to handle ACTION_POINTER_UP events
	 * 
	 * @param event
	 * @return
	 */
	protected GestureType handlePointerUp(MotionEvent event) {
		if (DEBUG_MODE) {
			throw new GestureException();
		} else {
			return GestureType.GestureEnded;
		}
	}

	/**
	 * Called to handle ACTION_POINTER_DOWN events
	 * 
	 * @param event
	 * @return
	 */
	protected GestureType handlePointerDown(MotionEvent event) {
		if (DEBUG_MODE) {
			throw new GestureException();
		} else {
			return GestureType.GestureEnded;
		}
	}
	
	/**
	 * Called to handle ACTION_MOVE events
	 * 
	 * @param event
	 * @return
	 */
	protected GestureType handleMove(MotionEvent event) {
		if (DEBUG_MODE) {
			throw new GestureException();
		} else {
			return GestureType.GestureEnded;
		}
	}

	/**
	 * Called to handle ACTION_DOWN events
	 * 
	 * @param event
	 * @return
	 */
	protected GestureType handleDown(MotionEvent event) {
		if (DEBUG_MODE) {
			throw new GestureException();
		} else {
			return GestureType.GestureEnded;
		}
	}
	
	/*
	 * Convenience methods for firing events on the manager
	 * 
	 * BESPOKE: add new gesture events here
	 */

	protected void fireTap(float x, float y) {
		manager.fireTap(x, y);
	}

	protected void fireStartLongTap(float x, float y, long duration) {
		manager.fireStartLongTap(x, y, duration);
	}

	protected void fireMidLongTap(float x, float y, long duration) {
		manager.fireMidLongTap(x, y, duration);
	}

	protected void fireEndLongTap(float x, float y, long duration) {
		manager.fireEndLongTap(x, y, duration);
	}

	protected void fireStartDrag(float fromX, float fromY, float toX, float toY) {
		manager.fireStartDrag(fromX, fromY, toX, toY);
	}

	protected void fireMidDrag(float fromX, float fromY, float toX, float toY) {
		manager.fireMidDrag(fromX, fromY, toX, toY);
	}

	protected void fireEndDrag(float fromX, float fromY, float toX, float toY) {
		manager.fireEndDrag(fromX, fromY, toX, toY);
	}

	protected void fireStartPinch(float scale, float centerX, float centerY) {
		manager.fireStartPinch(scale, centerX, centerY);
	}

	protected void fireMidPinch(float scale, float centerX, float centerY) {
		manager.fireMidPinch(scale, centerX, centerY);
	}

	protected void fireEndPinch(float scale, float centerX, float centerY) {
		manager.fireEndPinch(scale, centerX, centerY);
	}

}
