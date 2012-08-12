/**
Copyright (c) 2012 Babin Philippe
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

    Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public class Boomerang extends Item {
	
	private boolean inHand;
	private Brorang theBrorang;
	
	Boomerang( String name){
		super( name);
		icon = RessourceManager.arms.getImage( 0);
		arms = RessourceManager.arms_b;
		inHand = true;
		theBrorang = new Brorang( RessourceManager.boomerang, new Vector2f( 0, 0), new Vector2f( 0, 0));
	}
	
	/**
	 * Item render function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param gr Graphics
	 */
	public void render( GameContainer gc, StateBasedGame sb, Graphics gr){
    	Input input = gc.getInput();
    	Vector2f pCursor = new Vector2f( input.getMouseX(), input.getMouseY());
    	
    	Vector2f pPlayer = playstate.getPlayer().getPosition();
    	
    	float armAngle = (float)( Math.atan( ( pCursor.y - pPlayer.y) / ( pCursor.x - pPlayer.x - CONST.PLAYER_WIDTH / 2 - 0.00001)) * 180 / Math.PI);
    	
    	if( pCursor.x > pPlayer.x + CONST.PLAYER_WIDTH / 2){
    		arms.setCurrentFrame(0);
	    	gr.rotate( pPlayer.x + CONST.PLAYER_WIDTH / 2, pPlayer.y, armAngle);
	    	
	    	if( inHand)
	    		gr.drawAnimation( theBrorang.getAnimation(), pPlayer.x + CONST.PLAYER_WIDTH / 2 - 1, pPlayer.y + 2);
	    	gr.drawAnimation( arms, pPlayer.x + CONST.PLAYER_WIDTH / 2 - 4, pPlayer.y - 7);
	    	
	    	gr.rotate( pPlayer.x + CONST.PLAYER_WIDTH / 2, pPlayer.y, -armAngle);
	    }
    	else{
    		arms.setCurrentFrame(1);
	    	gr.rotate( pPlayer.x + CONST.PLAYER_WIDTH / 2, pPlayer.y, armAngle);
	    	
	    	if( inHand)
	    		gr.drawAnimation( theBrorang.getAnimation(), pPlayer.x + CONST.PLAYER_WIDTH / 2 - 13, pPlayer.y + 2);
	    	gr.drawAnimation( arms, pPlayer.x + CONST.PLAYER_WIDTH / 2 - arms.getWidth() + 4, pPlayer.y - 7);
	    	
	    	gr.rotate( pPlayer.x + CONST.PLAYER_WIDTH / 2, pPlayer.y, -armAngle);
    	}
    	
    	if( !inHand){
    		theBrorang.render( gc, sb, gr);
    	}
	}
	
	/**
	 * Item update function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param delta Time between frame
	 */
    public void update( GameContainer gc, StateBasedGame sb, int delta){
    	Input input = gc.getInput();
    	Vector2f pCursor = new Vector2f( input.getMouseX(), input.getMouseY());
    	
    	Vector2f pPlayer = playstate.getPlayer().getPosition();
    	
		if( input.isMouseButtonDown( Input.MOUSE_LEFT_BUTTON) && inHand){
			Vector2f bG = new Vector2f(  pCursor.x - pPlayer.x - CONST.PLAYER_WIDTH / 2, pCursor.y - pPlayer.y);
			double h = Math.sqrt( bG.x * bG.x + bG.y * bG.y);
			
			Vector2f bP = new Vector2f( 
					pPlayer.x + CONST.PLAYER_WIDTH / 2 - RessourceManager.boomerang.getWidth()/2,
					pPlayer.y - RessourceManager.boomerang.getHeight()/2);
			Vector2f bV =  new Vector2f(
					(float) ( bG.x / h) * CONST.BOOMERANG_VELOCITY_IN,
					(float) ( bG.y / h) * CONST.BOOMERANG_VELOCITY_IN);
			
			theBrorang = new Brorang( RessourceManager.boomerang, bP, bV);
			//playstate.addEntity( new Brorang( RessourceManager.boomerang, bP, bV));
			
			inHand = false;
		}
		
		
    	if( !inHand){
    		theBrorang.update( gc, sb, delta);
    		Vector2f bG = new Vector2f( theBrorang.getX() - pPlayer.x - CONST.PLAYER_WIDTH / 2, theBrorang.getY() - pPlayer.y);
	    	if( bG.length() < 5.f && theBrorang.isComeback()){
	    		inHand = true;
	    	}
    	}
    }
    
    public void switchWeapon(){
    	inHand = true;
    }
}
