package sek.touch.processors;

import sek.touch.GestureManager;
import sek.touch.GestureProcessor;
import sek.touch.GestureType;
import android.util.FloatMath;
import android.view.MotionEvent;

/**
 * Processor for pinch gestures
 * 
 * @author Steven Karas
 */
public class PinchProcessor extends GestureProcessor {

	public PinchProcessor(GestureManager manager) {
		super(manager);
	}

	/** starting coordinates of the pinch */
	private float x0, x1, y0, y1;
	/** coordinates of the center point of the pinch */
	private float centerX, centerY;
	/** the starting distance of the pinch */
	private float startDistance;
	
	@Override
	public void startGesture(MotionEvent event) {
		x0 = event.getX(0);
		x1 = event.getX(1);
		y0 = event.getY(0);
		y1 = event.getY(1);
		centerX = (x0 + x1) / 2;
		centerY = (y0 + y1) / 2;
		float dx = x1 - x0;
		float dy = y1 - y0;
		startDistance = FloatMath.sqrt(dx * dx + dy * dy);
		fireStartPinch(1, centerX, centerY);
	}

	@Override
	protected GestureType handlePointerDown(MotionEvent event) {
		fireEndPinch(1, centerX, centerY);
		return GestureType.GestureEnded;
	}

	@Override
	protected GestureType handlePointerUp(MotionEvent event) {
		float dx = event.getX(0) - event.getX(1);
		float dy = event.getY(0) - event.getY(1);
		float newDistance = FloatMath.sqrt(dx * dx + dy * dy);
		float scale = newDistance / startDistance;
		fireEndPinch(scale, centerX, centerY);
		return GestureType.GestureEnded;
	}

	@Override
	protected GestureType handleMove(MotionEvent event) {
		float dx = event.getX(0) - event.getX(1);
		float dy = event.getY(0) - event.getY(1);
		float newDistance = FloatMath.sqrt(dx * dx + dy * dy);
		float scale = newDistance / startDistance;
		fireMidPinch(scale, centerX, centerY);
		return GestureType.GestureContinue;
	}

}
