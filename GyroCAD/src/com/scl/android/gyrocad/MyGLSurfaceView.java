package com.scl.android.gyrocad;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by anscarlett on 23/02/14.
 *
 *
 */
public class MyGLSurfaceView  extends GLSurfaceView {
	private MyGLRenderer mRenderer;

	// Offsets for touch events
	private float previousX;
	private float previousY;

	private float density;

	public MyGLSurfaceView(Context _context)
	{
		super(_context);
	}

	public MyGLSurfaceView(Context _context, AttributeSet _attrs)
	{
		super(_context, _attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent _event)
	{
		if (_event != null)
		{
			float x = _event.getX();
			float y = _event.getY();

			if (_event.getAction() == MotionEvent.ACTION_MOVE)
			{
				if (mRenderer != null)
				{
					float deltaX = (x - previousX) / density / 2f;
					float deltaY = (y - previousY) / density / 2f;

					mRenderer.deltaX += deltaX;
					mRenderer.deltaY += deltaY;
				}
			}
			previousX = x;
			previousY = y;

			return true;
		}
		else
		{
			return super.onTouchEvent(_event);
		}
	}

	public void setRenderer(MyGLRenderer _renderer, float _density)
	{
		this.mRenderer = _renderer;
		this.density = _density;
		super.setRenderer(mRenderer);
	}
}
