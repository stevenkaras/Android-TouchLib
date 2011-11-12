package sek.touch;

import sek.touch.processors.LongTapProcessor;

/**
 * Listener for Gesture events.
 * <p>
 * BESPOKE: if you want to add a new type of gesture, you should add the relevant events here, in
 * the {@link BaseGestureListener}. You will also need to add the processor to the
 * {@link GestureType} enum, and in the {@link GestureManager} class
 * 
 * @author Steven Karas
 */
//TODO: remove useless parameters from events
public interface GestureListener {

	/**
	 * Called when a tap has been registered.
	 * 
	 * @param x
	 * @param y
	 */
	public void onTap(float x, float y);

	/**
	 * Called when a long tap has been started
	 * 
	 * @param x
	 * @param y
	 * @param duration
	 */
	public void onStartLongTap(float x, float y, long duration);

	// TODO: fix the javadoc so it references the actual value
	/**
	 * Called every 500ms.
	 * 
	 * @param x
	 * @param y
	 * @param duration
	 * @see LongTapProcessor#LONG_TAP_INTERVAL
	 */
	public void onMidLongTap(float x, float y, long duration);

	/**
	 * Called when a long tap has ended
	 * 
	 * @param x
	 * @param y
	 * @param duration
	 */
	public void onEndLongTap(float x, float y, long duration);

	/**
	 * Called when a drag gesture has started
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 */
	public void onStartDrag(float fromX, float fromY, float toX, float toY);

	/**
	 * Called when a drag gesture has been updated
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 */
	public void onMidDrag(float fromX, float fromY, float toX, float toY);

	/**
	 * Called when a drag gesture has ended
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 */
	public void onEndDrag(float fromX, float fromY, float toX, float toY);

	/**
	 * Called when a pinch gesture has started
	 * 
	 * @param scale
	 * @param centerX
	 * @param centerY
	 */
	public void onStartPinch(float scale, float centerX, float centerY);

	/**
	 * Called when a pinch gesture has been updated
	 * 
	 * @param scale
	 * @param centerX
	 * @param centerY
	 */
	public void onMidPinch(float scale, float centerX, float centerY);

	/**
	 * Called when a pinch gesture has ended
	 * 
	 * @param scale
	 * @param centerX
	 * @param centerY
	 */
	public void onEndPinch(float scale, float centerX, float centerY);

}
