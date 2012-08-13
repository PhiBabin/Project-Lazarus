/**
Copyright (c) 2012 Babin Philippe
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

    Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

package lazarus;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author Philippe Babin
 *
 *	This class works as the gameplay logic manager.
 *	The current drawable entity is render.
 */

public class PlayState extends BasicGameState {
	
	public static Level mainLevel;
	
	private RessourceManager resManag;
	
	private Player player;
	
	private ArrayList<Sprite> entityList = null; 
	
	private int lastBullet = 0;
	
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
    	 mainLevel = new Level();
    	 mainLevel.create( "Hamlet, Act 3, Scene 1", "To be, or not to be, that is the question: Whether tis Nobler in the mind to suffer The Slings and Arrows of outrageous Fortune, Or to take Arms against a Sea of troubles,");
    	 
    	 player = new Player( new Vector2f( 400, 100));
    	 
    	 player.itSdangerousToGoAloneTakeThis( new MachineGun( "Sasha"), 0);
    	 player.itSdangerousToGoAloneTakeThis( new Boomerang( "Bro-omerang"), 1);
    	 player.switchWeapon( 0);
    	 
    	 entityList = new ArrayList<Sprite>();
    	 
    	 Item.playstate = this;
    	 Sprite.playstate = this;
    }
 
    public void render( GameContainer gc, StateBasedGame sb, Graphics gr) throws SlickException {
    	gr.setBackground( Color.gray);
    	mainLevel.render( gc, sb, gr);
		
    	for( int i = 0; i < entityList.size();i++){
    		entityList.get( i).render( gc, sb, gr);
    		if( entityList.get( i).isDelete())
    			entityList.remove(i);
    	}
    	
		player.render( gc, sb, gr);
		
		gr.drawString( "Velocity Y - " + player.getV().y * 100, 5.f, 50.f);
		gr.drawString( "Triangle - ( " + (pCursor.x - player.getX() - CONST.PLAYER_WIDTH / 2) + "," + (pCursor.y - player.getY()) + ")", 5.f, 90.f);
		gr.drawString( "JUMP - " + player.isJumpLock(), 5.f, 105.f);
		gr.drawString( "Bullet - " + entityList.size(), 5.f, 120.f);
		//gr.drawString( "Angle new - " + pCursor.sub( player.getPosition()).getTheta(), 5.f, 135.f);
		//gr.drawString( "Angle - " + Math.atan( ( pCursor.y - player.getY()) / ( pCursor.x - player.getX() - CONST.PLAYER_WIDTH / 2)) * 180 / Math.PI, 5.f, 150.f);
		
		
    }
 
    public void update( GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
    	Input input = gc.getInput();

    	pCursor.x = input.getMouseX() + cam.x;
    	pCursor.y = input.getMouseY() + cam.y;
    	
    	player.update(gc, sb, delta);
    	
    	for( Sprite entity : entityList){
    		entity.update( gc, sb, delta);
    	}
    	
    	if( input.isKeyDown( Input.KEY_1))
    		player.switchWeapon( 0);
    	if( input.isKeyDown( Input.KEY_2))
    		player.switchWeapon( 1);
    	if( input.isKeyDown( Input.KEY_3))
    		player.switchWeapon( 2);
    	
    }
    
    public Player getPlayer(){
    	return player;
    }
    
    public Vector2f getCam(){
    	return cam;
    }
    
    public ArrayList<Sprite> getEntities(){
    	return entityList;
    }
    
    public void addEntity( Sprite entity){
    	entityList.add( entity);
    }
    
	@Override
	public int getID() {
		return stateID;
	}

}