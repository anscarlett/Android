package com.scl.android.graphics;

import com.scl.android.math.Vector3;

/**
 * Created by anscarlett on 12/02/14.
 */
public class Node {
	private static final String CLASS_TAG = Node.class.getCanonicalName();
	private String mName;

	private Vector3 mOrigin;

	protected Node() {
		this(new Vector3(0.0f, 0.0f, 0.0f));
	}

	protected Node(Vector3 _origin) {

	}

	public Vector3 getOrigin() {
		return mOrigin;
	}
	public void setOrigin(Vector3 _origin) {
		this.mOrigin = _origin;
	}
}
