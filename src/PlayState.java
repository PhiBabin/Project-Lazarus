/**
Copyright (c) 2012 Babin Philippe
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

    Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.effects.FireEmitter;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author Philippe Babin
 *
 *	This class works as the gameplay logic manager.
 *	The current drawable entity is render.
 */

public class PlayState extends BasicGameState {
	
	private TiledMap mainMap;
	
	private RessourceManager resManag;
	
	private Player player;
	
	private Vector2f pCursor = new Vector2f( 0, 0);
	private Vector2f cam = new Vector2f( 0, 0);
	
	int stateID=-1;
	
	/**
	 * Construct our PlayState
	 * @param stateID
	 */
	public PlayState( int stateID){
		this.stateID = stateID;
	}
	

    public void init( GameContainer gc, StateBasedGame sbg) throws SlickException {
    	resManag = new RessourceManager();
    	 mainMap = new TiledMap("map/level.tmx");
    	 
    	 player = new Player( resManag.player, resManag.arms, mainMap, 400, 100);
    }
 
    public void render( GameContainer gc, StateBasedGame sbg, Graphics gr) throws SlickException {
		mainMap.render( 0, 0);
		player.render( gc, sbg, gr);
		
		gr.drawString( player.getV().y + "", 5.f, 50.f);
		gr.drawString( "Angle - " + Math.atan( ( pCursor.y - player.getY()) / ( pCursor.x - player.getX())), 5.f, 65.f);
		gr.drawString( "JUMP - " + player.isJumpLock(), 5.f, 90.f);
 
    }
 
    public void update( GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	
    	player.update(gc, sbg, delta);

    	pCursor.x = input.getMouseX() + cam.x;
    	pCursor.y = input.getMouseY() + cam.y;
    }
    
	@Override
	public int getID() {
		return stateID;
	}

}