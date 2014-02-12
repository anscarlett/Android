package com.scl.android.math;

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

	public void multiply(Vector3 _vec3) {
			mData[0] = (mData[0] * _vec3.getmX()) + (mData[1] * _vec3.getmY()) + (mData[2] * _vec3.getmZ()) + mData[3];
			mData[1] = (mData[4] * _vec3.getmX()) + (mData[5] * _vec3.getmY()) + (mData[6] * _vec3.getmZ()) + mData[7];
			mData[2] = (mData[8] * _vec3.getmX()) + (mData[9] * _vec3.getmY()) + (mData[10] * _vec3.getmZ()) + mData[11];
			mData[3] = (mData[12] * _vec3.getmX()) + (mData[13] * _vec3.getmY()) + (mData[14] * _vec3.getmZ()) + mData[15];
	}
}
