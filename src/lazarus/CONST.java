/**
Copyright (c) 2012 Babin Philippe
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

    Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

package lazarus;

public class CONST {
	/** Applet Constant */
	public static boolean APPLET = true;
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	/** World parameter */
	public static final double G_FORCE = 0.000098; // Px/ms^2
	public static final double JUMP_FORCE = 0.8;
	public static final float MAX_VELOCITY = 0.8f;
	public static final float HORRIZONTAL_VELOCITY = 0.2f;
	
	/** Player parameter */
	public static final double PLAYER_MASS = 20;
	public static final int PLAYER_WIDTH = 18;
	public static final int PLAYER_HEIGHT = 30;

	public static final float RAVEN_VELOCITY = 0.3f;
	public static final int RAVEN_AD = 20;
	public static final float RAVEN_WATCH_VELOCITY = 0.1f;
	public static final float RAVEN_ANGULER_VELOCITY = 0.0005f;
	public static final int RAVEN_RANGE = 200;
	public static final int RAVEN_MIN_DISTANCE = 100;
	public static final int RAVEN_MAX_DISTANCE = 200;
	
	public static final float BULLET_VELOCITY = 0.8f;
	public static final float BOOMERANG_VELOCITY_IN = 0.4f;
	public static final float BOOMERANG_VELOCITY_OUT = 0.5f;
	public static final int BOOMERANG_TIME_IN = 800;
	public static final float BOOMERANG_ROTATION_VELOCITY_IN = 1.2f;
	public static final float BOOMERANG_ROTATION_VELOCITY_OUT = 1.61f;
	public static final float MAGIC_VELOCITY = 0.4f;
	public static final int MAGIC_MAX_AMPLITUDE = 80;
	public static final double FIREBALL_MASS = 7;
	public static final float FIREBALL_VELOCITY = 0.4f;
	
	
	/** Map parameter */
	public static final int TILE_WIDTH = 20;
	public static final int TILE_HEIGHT = 18;
}

