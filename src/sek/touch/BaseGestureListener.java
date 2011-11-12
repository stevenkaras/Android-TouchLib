package sek.touch;

/**
 * Convenience implementation of {@link GestureListener}. Includes basic events that combine gesture
 * lifecycle (start, mid, end) for extra-simple processing
 * 
 * @author Steven Karas
 */
public class BaseGestureListener implements GestureListener {

	@Override
	public void onTap(float x, float y) {
	}

	@Override
	public void onStartLongTap(float x, float y, long duration) {
		onLongTap(x, y, duration);
	}

	@Override
	public void onMidLongTap(float x, float y, long duration) {
		onLongTap(x, y, duration);
	}

	@Override
	public void onEndLongTap(float x, float y, long duration) {
		onLongTap(x, y, duration);
	}

	/**
	 * Called on any long tap event (start, mid, or end)
	 * 
	 * @param x
	 * @param y
	 * @param duration
	 */
	public void onLongTap(float x, float y, long duration) {
		// do nothing
	}

	@Override
	public void onStartDrag(float fromX, float fromY, float toX, float toY) {
		onDrag(fromX, fromY, toX, toY);
	}

	@Override
	public void onMidDrag(float fromX, float fromY, float toX, float toY) {
		onDrag(fromX, fromY, toX, toY);
	}

	@Override
	public void onEndDrag(float fromX, float fromY, float toX, float toY) {
		onDrag(fromX, fromY, toX, toY);
	}

	/**
	 * Called on any drag event (start, mid, or end)
	 * 
	 * @param fromX
	 * @param fromY
	 * @param toX
	 * @param toY
	 */
	public void onDrag(float fromX, float fromY, float toX, float toY) {
		// do nothing
	}

	@Override
	public void onStartPinch(float scale, float centerX, float centerY) {
		onPinch(scale, centerX, centerY);
	}

	@Override
	public void onMidPinch(float scale, float centerX, float centerY) {
		onPinch(scale, centerX, centerY);
	}

	@Override
	public void onEndPinch(float scale, float centerX, float centerY) {
		onPinch(scale, centerX, centerY);
	}

	/**
	 * Called on any pinch event (start, mid, or end)
	 * 
	 * @param scale
	 * @param centerX
	 * @param centerY
	 */
	public void onPinch(float scale, float centerX, float centerY) {
		// do nothing
	}

}
