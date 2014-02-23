package com.scl.android.gyrocad;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	private MyGLSurfaceView mGLView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mGLView = new MyGLSurfaceView(this);

		setContentView(R.layout.main);
	}
}
