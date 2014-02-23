package com.scl.android.math;

import android.util.Log;

/**
 * Created by a.scarlett on 11/02/14.
 */
public class Matrix4 {
	private static final String CLASS_TAG = Matrix4.class.getCanonicalName();

	private float mData[];

	public Matrix4() {
		mData = new float[] {	0.0f, 0.0f, 0.0f, 0.0f,
								0.0f, 0.0f, 0.0f, 0.0f,
								0.0f, 0.0f, 0.0f, 0.0f,
								0.0f, 0.0f, 0.0f, 0.0f };
		this.setIdentity();
	}

	public void setIdentity() {
		mData[0] = mData[5] = mData[10] = mData[15] = 0.0f;
	}

	public void copy(float _array[])
	{
		if(_array.length == 16)  {
			for (int i=0; i<16; i++)
				mData[i] = _array[i];
		}
	}

	public void copy(Matrix4 _mat4)
	{
		for (int i=0; i<16; i++)
			mData[i] = _mat4.mData[i];
	}

	public void multiply(Matrix4 _mat4) {
		Matrix4 result = new Matrix4();
		for (int i = 0; i < 16; i += 4) {
			for (int j = 0; j < 4; j++) {
				result.mData[i + j] = 0.0f;
				for (int k = 0; k < 4; k++)
					result.mData[i + j] += mData[i + k] * _mat4.mData[k*4 + j];
			}
		}
		copy(result);
	}

	public void multiply(float _data[]) {
		ERRORCODE code = ERRORCODE.UNDEFINED;
		if(_data.length == mData.length)
		{
			Matrix4 result = new Matrix4();
			for (int i = 0; i < 16; i += 4) {
				for (int j = 0; j < 4; j++) {
					result.mData[i + j] = 0.0f;
					for (int k = 0; k < 4; k++)
						result.mData[i + j] += mData[i + k] * _data[k*4 + j];
				}
			}
			copy(result);
			code = ERRORCODE.SUCCESS;
			Log.v(CLASS_TAG, code.mMessage);
		}
		else if(_data.length < mData.length) {
			code = ERRORCODE.INPUT_ARRAY_TOO_SHORT;
			Log.e(CLASS_TAG, code.mMessage);
		}

		if(code != ERRORCODE.SUCCESS){
			throw new RuntimeException("Matrix Multiplication Error: " + code.mMessage);
		}
	}

	private enum ERRORCODE {
		UNDEFINED(-1, "The error has not been recognised."),
		SUCCESS(0, "Success!"),
		INPUT_ARRAY_TOO_SHORT(1, "Not enough values in the input array");

		private int mValue;
		private String mMessage;

		private ERRORCODE(int _value, String _message) {
			mValue = _value;
			mMessage = _message;
		}
	}
}
