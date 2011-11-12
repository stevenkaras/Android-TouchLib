# TouchLib

TouchLib is a basic UX library that provides a modular/minimalist approach towards gesture recognition. The basic version of the library provides several built-in gestures:

 * Tap
 * Long tap
 * Drag
 * Pinch
 
More gestures can be added, as described in the section below (Adding new gestures).

## Development

If you're interested in helping development, just fork this project. I won't pull your changes, since I'd prefer for the code to remain solely mine.

## License

Copyright (c) 2011 Steven Karas

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## History

Touchlib came about because Android lacks a builtin library (or any open source library) for quick and easy gesture recognition. While Android does include a "Gesture" library, it does not work with a multitouch Pinch gesture, nor does it provide ongoing information about a gesture, such as the location, duration, or other such information. When the information is available, it is hidden behind cumbersome APIs that impose a significant performance hit.

As such, I sat down one weekend and built TouchLib to handle some basic gestures that I needed for a game. The first version was fugly, but it worked. Then I sat down a few days later, and came up with a sane design. A little bit of javadoc later, and I'm here, writing this document at 330am. Yay!

## Integrating with your application

To use this library, you'll need to forward all MotionEvents to the GestureManager in the onTouchEvent(MotionEvent) method of your View. GestureManager provides a default instance, but you can maintain your own, if you'd like. Maintaining multiple GestureManagers is not supported (it should be possible, but I just haven't tried it/thought it through). In addition, you'll want to add a listener for the gesture events. BaseGestureListener provides a default implementation of GestureListener, which does nothing. That way, you can override only the events you care about. In addition, it funnels all the gesture lifecycle events into a simple event (so onStartDrag, onMidDrag, and onEndDrag all call onDrag by default).

The example project provides a reference for how to utilize the library in a semi-sane manner.

Paid support is provided upon request. Donations via paypal are also welcome.

## Adding new gestures

If you'd like to add a new gesture, you'll need to first write a new processor that extends GestureProcessor. The handle* methods should be overridden to handle the various MotionEvent types. Then you'll need to add a new entry to the GestureType enum, and finally a new instance to the GestureManager. Once you've done all that, you'll need to tell the existing processors when to pass control over to your processor.