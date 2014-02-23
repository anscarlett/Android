package com.scl.android.graphics;

import android.opengl.GLSurfaceView;

/**
 * Created by anscarlett on 11/02/14.
 *
 *
 */
public class SceneGraph {
	private static final String CLASS_TAG = SceneGraph.class.getCanonicalName();
	private GLSurfaceView.Renderer mRenderer;
	private Node mRoot;

	public SceneGraph(GLSurfaceView.Renderer _renderer) {
		this.mRenderer = _renderer;
	}

	public  void render() {

	}

	public void insertNode(Node _n) {
		/** todo: write implementation */
	}

	public void insertScene(Node _root, SceneGraph _sg) {
		/** todo: write implementation */
		}

	public void saveScene(Node _root) {
		/** todo: write implementation */
	}
}
