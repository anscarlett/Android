package com.scl.android.math;

/**
 * Created by a.scarlett on 11/02/14.
 */
public class Vector3 {
	private static final String CLASS_TAG = Vector3.class.getCanonicalName();

	public float getmX() {
		return mX;
	}

	public float getmY() {
		return mY;
	}

	public float getmZ() {
		return mZ;
	}

	private float mX;
	private float mY;
	private float mZ;

	public Vector3() {
		this(0.0f, 0.0f, 0.0f);
	}

	public Vector3(float _x, float _y, float _z) {
		mX = _x;
		mY = _y;
		mZ = _z;
	}

	public void normalize() {
		float magnitude = (float) Math.sqrt((mX*mX) + (mY*mY) + (mZ*mZ));
		mY /= magnitude;
		mZ /= magnitude;
		mX /= magnitude;
	}

	private enum ERRORCODE {
		UNDEFINED(-1, "The error has not been recognised."),
		SUCCESS(0, "Success!");

		private int mValue;
		private String mMessage;

		private ERRORCODE(int _value, String _message) {
			mValue = _value;
			mMessage = _message;
		}
	}
}
