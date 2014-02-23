package com.scl.android.gyrocad;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import com.scl.android.graphics.RawResourceReader;
import com.scl.android.graphics.SceneGraph;
import com.scl.android.graphics.shader.ShaderHelper;

/**
 * Created by anscarlett on 23/02/14.
 *
 *
 */
public class MyGLRenderer implements GLSurfaceView.Renderer{
	private static final String CLASS_TAG = MyGLRenderer.class.getCanonicalName();

	/** References to other main objects. */
	private final MainActivity mActivity;
//	public final ErrorHandler errorHandler;
//	public final AndroidGL20 glEs20;

	private final float[] modelMatrix = new float[16];
	private final float[] viewMatrix = new float[16];
	private final float[] projectionMatrix = new float[16];
	private final float[] mvpMatrix = new float[16];
	private final float[] accumulatedRotation = new float[16];
	private final float[] currentRotation = new float[16];
	private final float[] lightModelMatrix = new float[16];
	private final float[] temporaryMatrix = new float[16];

	private int mvpMatrixUniform;
	private int mvMatrixUniform;
	private int lightPosUniform;

	public int positionAttribute;
	public int normalAttribute;
	public int colorAttribute;

	private static final String MVP_MATRIX_UNIFORM = "u_MVPMatrix";
	private static final String MV_MATRIX_UNIFORM = "u_MVMatrix";
	private static final String LIGHT_POSITION_UNIFORM = "u_LightPos";

	private static final String POSITION_ATTRIBUTE = "a_Position";
	private static final String NORMAL_ATTRIBUTE = "a_Normal";
	private static final String COLOR_ATTRIBUTE = "a_Color";

	public static final int POSITION_DATA_SIZE_IN_ELEMENTS = 3;
	public static final int NORMAL_DATA_SIZE_IN_ELEMENTS = 3;
	public static final int COLOR_DATA_SIZE_IN_ELEMENTS = 4;

	public static final int BYTES_PER_FLOAT = 4;
	public static final int BYTES_PER_SHORT = 2;

	public static final int STRIDE = (POSITION_DATA_SIZE_IN_ELEMENTS + NORMAL_DATA_SIZE_IN_ELEMENTS + COLOR_DATA_SIZE_IN_ELEMENTS)
			* BYTES_PER_FLOAT;

	private final float[] lightPosInModelSpace = new float[] { 0.0f, 0.0f, 0.0f, 1.0f };
	private final float[] lightPosInWorldSpace = new float[4];
	private final float[] lightPosInEyeSpace = new float[4];
	private int program;
	public volatile float deltaX;
	public volatile float deltaY;
	private SceneGraph mSceneGraph;

	public MyGLRenderer(final MainActivity _activity) {
		this.mActivity = _activity;
	}

	@Override
	public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
		mSceneGraph = new SceneGraph(this);

		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		GLES20.glEnable(GLES20.GL_DEPTH_TEST);

		final float eyeX = 0.0f;
		final float eyeY = 0.0f;
		final float eyeZ = -0.5f;

		// We are looking toward the distance
		final float lookX = 0.0f;
		final float lookY = 0.0f;
		final float lookZ = -5.0f;

		final float upX = 0.0f;
		final float upY = 1.0f;
		final float upZ = 0.0f;

		Matrix.setLookAtM(viewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);

		final String vertexShader = RawResourceReader.readTextFileFromRawResource(mActivity,
				R.raw.per_pixel_vertex_shader_no_tex);
		final String fragmentShader = RawResourceReader.readTextFileFromRawResource(mActivity,
				R.raw.per_pixel_fragment_shader_no_tex);

		final int vertexShaderHandle = ShaderHelper.compileShader(GLES20.GL_VERTEX_SHADER, vertexShader);
		final int fragmentShaderHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader);

		program = ShaderHelper.createAndLinkProgram(vertexShaderHandle, fragmentShaderHandle, new String[] {
				POSITION_ATTRIBUTE, NORMAL_ATTRIBUTE, COLOR_ATTRIBUTE });

		Matrix.setIdentityM(accumulatedRotation, 0);
	}

	@Override
	public void onSurfaceChanged(GL10 glUnused, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
		final float ratio = (float) width / height;
		final float left = -ratio;
		final float right = ratio;
		final float bottom = -1.0f;
		final float top = 1.0f;
		final float near = 1.0f;
		final float far = 1000.0f;
		Matrix.frustumM(projectionMatrix, 0, left, right, bottom, top, near, far);
	}

	@Override
	public void onDrawFrame(GL10 glUnused) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		GLES20.glUseProgram(program);
		mvpMatrixUniform = GLES20.glGetUniformLocation(program, MVP_MATRIX_UNIFORM);
		mvMatrixUniform = GLES20.glGetUniformLocation(program, MV_MATRIX_UNIFORM);
		lightPosUniform = GLES20.glGetUniformLocation(program, LIGHT_POSITION_UNIFORM);
		positionAttribute = GLES20.glGetAttribLocation(program, POSITION_ATTRIBUTE);
		normalAttribute = GLES20.glGetAttribLocation(program, NORMAL_ATTRIBUTE);
		colorAttribute = GLES20.glGetAttribLocation(program, COLOR_ATTRIBUTE);
		Matrix.setIdentityM(lightModelMatrix, 0);
		Matrix.translateM(lightModelMatrix, 0, 0.0f, 7.5f, -8.0f);
		Matrix.multiplyMV(lightPosInWorldSpace, 0, lightModelMatrix, 0, lightPosInModelSpace, 0);
		Matrix.multiplyMV(lightPosInEyeSpace, 0, viewMatrix, 0, lightPosInWorldSpace, 0);
		Matrix.setIdentityM(modelMatrix, 0);
		Matrix.translateM(modelMatrix, 0, 0.0f, 0.0f, -12f);
		Matrix.setIdentityM(currentRotation, 0);
		Matrix.rotateM(currentRotation, 0, deltaX, 0.0f, 1.0f, 0.0f);
		Matrix.rotateM(currentRotation, 0, deltaY, 1.0f, 0.0f, 0.0f);
		deltaX = 0.0f;
		deltaY = 0.0f;
		Matrix.multiplyMM(temporaryMatrix, 0, currentRotation, 0, accumulatedRotation, 0);
		System.arraycopy(temporaryMatrix, 0, accumulatedRotation, 0, 16);
		Matrix.multiplyMM(temporaryMatrix, 0, modelMatrix, 0, accumulatedRotation, 0);
		System.arraycopy(temporaryMatrix, 0, modelMatrix, 0, 16);
		Matrix.multiplyMM(mvpMatrix, 0, viewMatrix, 0, modelMatrix, 0);
		GLES20.glUniformMatrix4fv(mvMatrixUniform, 1, false, mvpMatrix, 0);
		Matrix.multiplyMM(temporaryMatrix, 0, projectionMatrix, 0, mvpMatrix, 0);
		System.arraycopy(temporaryMatrix, 0, mvpMatrix, 0, 16);
		GLES20.glUniformMatrix4fv(mvpMatrixUniform, 1, false, mvpMatrix, 0);
		GLES20.glUniform3f(lightPosUniform, lightPosInEyeSpace[0], lightPosInEyeSpace[1], lightPosInEyeSpace[2]);
		mSceneGraph.render();
	}
}
