package com.scl.android.graphics;

import com.scl.android.math.Matrix4;
import com.scl.android.math.Vector3;

/**
 * Created by anscarlett on 11/02/14.
 */
public class Mesh extends Node{
	private static final String CLASS_TAG = Mesh.class.getCanonicalName();

	private Vector3[] mVertices;
	private short[] mIndices;
	private Vector3[] mNormals;
	private Matrix4 mTransform;

	private Vector3 mCenter;

	public Mesh() {
		super();
	}

	public void saveMesh() {

	}
}
