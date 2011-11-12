package sek.touch;

/**
 * Represents the various types of gestures supported
 * <p>
 * BESPOKE: add new gestures here
 * 
 * @author Steven Karas
 */
public enum GestureType {
	GestureEnded,		// indicates that the gesture has ended
	GestureContinue,	// indicates the gesture should continue
	Tap,
	LongTap,
	Drag,
	Pinch,
}
