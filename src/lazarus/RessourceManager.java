/**
Copyright (c) 2012 Babin Philippe
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

    Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

package lazarus;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class RessourceManager {
	public static Animation player = null, raven = null, arms = null, arms_b = null, arms_m = null, arms_f = null,
	bullet = null, boomerang = null, magic = null, fire = null;
	public static SpriteSheet tilesetImg = null;
	
	public RessourceManager(){
		loadImage();
	}
	
	public void loadImage(){
		try {
			tilesetImg = getSprite( "img/tileset.png", CONST.TILE_WIDTH, CONST.TILE_HEIGHT, false);
			
			player = new Animation( getSprite( "img/player.png", CONST.PLAYER_WIDTH, CONST.PLAYER_HEIGHT, false), 1);
			player.stop();
			
			raven = new Animation( getSprite( "img/raven.png", 28, 20, false), 1);
			raven.stop();
			
			arms = new Animation( getSprite( "img/arm_MG.png", 34, 30, false), 1);
			arms.stop();
			bullet = new Animation( getSprite( "img/bullet.png", 10, 10, false), 1);
			bullet.stop();
			arms_b = new Animation( getSprite( "img/arm_test.png", 18, 30, false), 1);
			arms_b.stop();
			arms_m = new Animation( getSprite( "img/arm_magic.png", 25, 30, false), 1);
			arms_m.stop();
			arms_f = new Animation( getSprite( "img/arm_fire.png", 25, 30, false), 1);
			arms_f.stop();
			boomerang = new Animation( getSprite( "img/boomerang.png", 28, 14, false), 1);
			magic = new Animation( getSprite( "img/magic.png", 15, 15, false), 1);
			fire = new Animation( getSprite( "img/fire.png", 16, 16, false), 1);
			
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public SpriteSheet getSprite( String path, int w, int h, boolean reverse) throws SlickException{
		System.out.println( path +"("+ w +","+ h +") has been load.");
		if( CONST.APPLET)
			return new SpriteSheet( new Image( Thread.currentThread().getContextClassLoader().getResourceAsStream(path), path, false), w,  h);
		else 
			return new SpriteSheet( new Image( path, reverse), w, h);
	}
	
}
