package com.scl.android.graphics;

import android.opengl.Matrix;
import com.scl.android.math.Matrix4;
import com.scl.android.math.Vector3;

/**
 * Created by anscarlett on 11/02/14.
 */
public class Camera extends Node{
	private static final String CLASS_TAG = Camera.class.getCanonicalName();

	private Vector3 mPosition;
	private Vector3 mLookAt;
	private Vector3 mUp;
	private Matrix4 mTransform;
	private float mClipNear;
	private float mClipFar;

	public Camera() {
		mPosition = new Vector3(10.0f, 10.0f, 10.0f);
		mLookAt = new Vector3(0.0f, 0.0f, 0.0f);
		mUp = new Vector3(0.0f, 1.0f, 0.0f);
		mClipNear = 0.01f;
		mClipFar = 1000.0f;
		mTransform = new Matrix4();
	}
}
