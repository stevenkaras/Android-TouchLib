package sek.touch.processors;

import sek.touch.GestureManager;
import sek.touch.GestureProcessor;
import sek.touch.GestureType;
import android.view.MotionEvent;

/**
 * Processor for a drag gesture
 * 
 * @author Steven Karas
 */
public class DragProcessor extends GestureProcessor {

	/** the distance required to start a drag gesture */
	public static final float DRAG_TRIGGER_DISTANCE = 40;
	
	/** the starting coordinates of the current drag gesture */
	private float x, y;
	
	public DragProcessor(GestureManager manager) {
		super(manager);
	}

	@Override
	public void startGesture(MotionEvent event) {
		x = event.getX();
		y = event.getY();
		fireStartDrag(x, y, x, y);
	}

	@Override
	protected GestureType handleUp(MotionEvent event) {
		fireEndDrag(x, y, event.getX(), event.getY());
		return GestureType.GestureEnded;
	}

	@Override
	protected GestureType handlePointerDown(MotionEvent event) {
		// would indicate that a multitouch gesture has started
		fireEndDrag(x, y, x, y);
		return GestureType.Pinch;
	}
	
	@Override
	protected GestureType handleMove(MotionEvent event) {
		fireMidDrag(x, y, event.getX(), event.getY());
		return GestureType.GestureContinue;
	}

}
