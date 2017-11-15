package com.king.batterytest.fbaselib.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

public class PictureGallery extends Gallery {

	private int length = 0;

	public PictureGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
		if (distanceX > 0) {
			return super.onScroll(e1, e2, distanceX, distanceY);
		} else {
			return super.onScroll(e1, e2, distanceX, distanceY);
		}
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {

		if (velocityX > 0) {
			super.onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
		} else {
			super.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
		}

		return false;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
