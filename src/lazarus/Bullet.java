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


/**
 * Bullet entity
 * This object is a shooting projectable.
 * 
 * @author Philippe Babin
 *
 */
public class Bullet extends Sprite {
	
	/** Velocity of the Bullet */
	private Vector2f v = new Vector2f( 0, 0);
	
	/** TimeLaspe **/
	private int time = 0;

	public Bullet( Animation pSprite, Vector2f v) {
		super( pSprite);
		this.v = v;
	}
	
	/**
	 * Constructor of the Player
	 * @param pSprite Animation
	 * @param nX Bullet X default position
	 * @param nY Bullet Y default position
	 */
	public Bullet( Animation pSprite, Vector2f p, Vector2f v) {
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
		aniSprite.draw( p.x - playstate.getCam().x, p.y - playstate.getCam().y);
	}
	
	/**
	 * Bullet update function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param delta Time between frame
	 */
    public void update(GameContainer gc, StateBasedGame sb, int delta){
    	time += delta;
    	
    	p.x += v.x * delta;
    	p.y += v.y * delta;
    	

    	Rectangle rec = new Rectangle( p.x, p.y, aniSprite.getWidth(), aniSprite.getHeight());
    	
    	if( playstate.mainLevel.isSolid( rec) || time > 3000)
    		delete = true;
    //	v.y += CONST.BULLET_MASS * CONST.G_FORCE * delta - 0.01 * Math.random();
    }
}
