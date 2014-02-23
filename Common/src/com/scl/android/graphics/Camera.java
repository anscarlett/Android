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

	private boolean mTracking;

	public Camera() {
		mPosition = new Vector3(10.0f, 10.0f, 10.0f);
		mLookAt = new Vector3(0.0f, 0.0f, 0.0f);
		mUp = new Vector3(0.0f, 1.0f, 0.0f);
		mClipNear = 0.01f;
		mClipFar = 1000.0f;
		mTransform = new Matrix4();
		mTracking = false;
	}

	public void lookAt(Vector3 _vector) {
		mLookAt = _vector;
	}

	public void zoom(float _distance) {

	}

	public void pan(float _distance) {

	}

	public void rotate(float _degrees) {

	}

	public void orbit(float _degrees) {

	}

	public void orbit(float _degrees, Vector3 _origin) {

	}

	public void elevate(float _degrees) {

	}

	public void elevate(float _degrees, Vector3 _origin) {

	}

	public void trackObject(Node _object) {
		if(_object != null) {
			mLookAt = _object.getOrigin();
			mTracking = true;
		} else {
			mTracking = false;
		}
	}

	public void trackObject(Vector3 _vector) {
		if(_vector != null ) {
			mLookAt = _vector;
			mTracking = true;
		} else {
			mTracking = false;
		}
	}

	public void followPath() {

	}
}
