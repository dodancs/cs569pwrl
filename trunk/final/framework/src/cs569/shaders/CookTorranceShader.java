package cs569.shaders;

import javax.media.opengl.GL;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import cs569.apps.TronRuntime;
import cs569.misc.GLSLErrorException;

/**
 * Created on January 26, 2007
 * Course: CS569 (Interactive Computer Graphics) by Steve Marschner
 * Originally written for CS467/468 (Computer Graphics II and Practicum) by Kavita Bala
 * Copyright 2007 Computer Science Department, Cornell University
 * 
 * @author Adam Arbree -- arbree@cs.cornell.edu
 * @author Wenzel Jakob
 */
public class CookTorranceShader extends GLSLShader {
	/** The GLSL program parameter handles. */
	protected int lightPosition, eyePosition;
	protected int diffuseColor, specularColor;
	protected int mHandle, r0Handle;

	/**
	 * Default constructor
	 */
	protected CookTorranceShader() {
		super("cooktorrance");
	}

	/**
	 * Default constructor
	 */
	protected CookTorranceShader(String inName) {
		super(inName);
	}

	/**
	 * @see cs569.shaders.GLSLShader#retrieveGLSLParams()
	 */
	@Override
	protected void retrieveGLSLParams(GL gl) throws GLSLErrorException {
		lightPosition = getNamedParameter(gl, "lightPosition");
		diffuseColor = getNamedParameter(gl, "diffuseColor");
		specularColor = getNamedParameter(gl, "specularColor");
		eyePosition = getNamedParameter(gl, "eyePosition");
		mHandle = getNamedParameter(gl, "m");
		r0Handle = getNamedParameter(gl, "r0");
	}

	/**
	 * Expects eye, and diffuse color
	 * 
	 * @see cs569.shaders.GLSLShader#setGLSLParams(java.lang.Object[])
	 */
	@Override
	public void setGLSLParams(GL gl, Object... params) {
		if (params.length != 5) {
			throw new Error(this.getClass().getName()
					+ ": Invalid number of parameters.");
		}

		// Unpack the parameters
		Vector3f eye = (Vector3f) params[0];
		Color3f diffuseColorValue = (Color3f) params[1];
		Color3f specularColorValue = (Color3f) params[2];
		float M = ((Float) params[3]).floatValue();
		float N = ((Float) params[4]).floatValue();

		gl.glUniform4f(eyePosition, eye.x, eye.y, eye.z, 1.0f);
		Vector3f light = TronRuntime.getMainViewer().getLightPosition();
		gl.glUniform4f(lightPosition, light.x, light.y, light.z, 1.0f);

		// Set the material properties
		gl.glUniform4f(diffuseColor, diffuseColorValue.x, diffuseColorValue.y,
				diffuseColorValue.z, 1.0f);
		gl.glUniform4f(specularColor, specularColorValue.x, specularColorValue.y,
				specularColorValue.z, 1.0f);
		gl.glUniform1f(mHandle, M);

		double r0 = Math.pow(N-1.0f,2.0f)/Math.pow(N+1.0f,2.0f);
		gl.glUniform1f(r0Handle, (float) r0);
	}
}
