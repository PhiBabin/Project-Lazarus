/**
Copyright (c) 2012 Babin Philippe
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

    Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

package lazarus;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/***
 * 30% more bro than a regular boomerang :D
 * 
 * @author Philippe Babin
 */

public class Brorang extends Sprite {
	
	/** Velocity of the Player */
	private Vector2f v = new Vector2f( 0, 0);
	
	/** Traveled distance */
	private int time = 0;
	
	/** Rotation of the boomerang */
	private float rotation = 0;
	
	/**  Is comeback */
	private boolean comeback = false;
	
	/**
	 * Constructor of the Player
	 * @param pSprite Animation
	 * @param nX Bullet X default position
	 * @param nY Bullet Y default position
	 */
	public Brorang( Animation pSprite, Vector2f p, Vector2f v) {
		super( pSprite, p);
		this.v = v;
	}
	
	/**
	 * Bullet render function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param gr Graphics
	 */
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr){
    	gr.rotate( p.x + 12 - playstate.getCam().x, p.y + 6 - playstate.getCam().y, rotation);
		aniSprite.draw( p.x - playstate.getCam().x, p.y - playstate.getCam().y);
    	gr.rotate( p.x + 12 - playstate.getCam().x, p.y + 6 - playstate.getCam().y, -rotation);
	}
	
	/**
	 * Bullet update function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param delta Time between frame
	 */
    public void update(GameContainer gc, StateBasedGame sb, int delta){
    	
    	if( !comeback){
        	rotation += CONST.BOOMERANG_ROTATION_VELOCITY_IN * delta;
	    	p.x += v.x * delta;
	    	p.y += v.y * delta;
	    	
	    	time += delta;
	    	
	    	Rectangle rec = new Rectangle( p.x + 4, p.y + 2, 16, 10);
	    	
	    	if( time > CONST.BOOMERANG_TIME_IN || playstate.mainLevel.isSolid( rec))
	    		comeback = true;
    	}
    	else{
        	rotation -= CONST.BOOMERANG_ROTATION_VELOCITY_OUT * delta;
        	Vector2f pPlayer = playstate.getPlayer().getPosition();
    		Vector2f bG = new Vector2f(  p.x - pPlayer.x - CONST.PLAYER_WIDTH / 2, p.y - pPlayer.y);
			
			Vector2f bP = new Vector2f( pPlayer.x + CONST.PLAYER_WIDTH / 2 , pPlayer.y);
			
	    	p.x -= ( bG.x / bG.length()) * CONST.BOOMERANG_VELOCITY_OUT * delta;
	    	p.y -= ( bG.y / bG.length()) * CONST.BOOMERANG_VELOCITY_OUT * delta;
    	}
    	
    	doCollision();
    }
    

    public void doCollision(){
    	for( Mob mob : playstate.getMobs()){
    		if( getCollisionRect().intersects( mob.getCollisionRect())){
    			if( mob.ping()){
        			comeback = true;
    				mob.dmg( 15);
    			}
    		}
    	}
    }

	public Vector2f getV() {
		return v;
	}

	public boolean isComeback() {
		return comeback;
	}

	public void setV(Vector2f v) {
		this.v = v;
	}

	public void setComeback(boolean comeback) {
		this.comeback = comeback;
	}
}
