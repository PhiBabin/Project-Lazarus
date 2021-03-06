/**
Copyright (c) 2012 Babin Philippe
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

    Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

package lazarus;

import java.util.ArrayList;
import java.util.Calendar;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
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
	
	private ArrayList<Raven> mobsList = null; 
	
	private int lastBullet = 0;
	
	private Vector2f pCursor = new Vector2f( 0, 0);
	
	private Vector2f cam = new Vector2f( 0, 0);
	
	private Rectangle camRect;
	
	private Rectangle moveableRect;
	
	int stateID = -1;
	
	/**
	 * Construct our PlayState
	 * @param stateID
	 */
	public PlayState( int stateID){
		this.stateID = stateID;
		camRect = new Rectangle( 0, 0, CONST.SCREEN_WIDTH, CONST.SCREEN_HEIGHT);
		moveableRect = new Rectangle( 0, 0, camRect.getWidth() /4,camRect.getHeight() /4);
	}
	

    public void init( GameContainer gc, StateBasedGame sbg) throws SlickException {
    	System.out.println( "Applet: " + CONST.APPLET );
    	
    	resManag = new RessourceManager();
    	mainLevel = new Level();
    	mainLevel.create( "Hamlet, Act 3, Scene 1", "To be, or not to be, that is the question: Whether tis Nobler in the mind to suffer The Slings and Arrows of outrageous Fortune, Or to take Arms against a Sea of troubles, And by opposing end them: to die, to sleep No more; and by a sleep, to say we end The heart-ache, and the thousand Natural shocks That Flesh is heir to? 'Tis a consummation Devoutly to be wished. To die to sleep, To sleep, perchance to Dream; Ay, there's the rub, For in that sleep of death, what dreams may come, When we have shuffled off this mortal coil, Must give us pause. There's the respect That makes Calamity of so long life: For who would bear the Whips and Scorns of time, The Oppressor's wrong, the proud man's Contumely, The pangs of despised Love, the Law's delay, The insolence of Office, and the Spurns That patient merit of the unworthy takes, When he himself might his Quietus make With a bare Bodkin? Who would Fardels bear, To grunt and sweat under a weary life, But that the dread of something after death, The undiscovered Country, from whose bourn No Traveller returns, Puzzles the will, And makes us rather bear those ills we have, Than fly to others that we know not of. Thus Conscience does make Cowards of us all,");
    	 
    	player = new Player( new Vector2f( 390, 200));
    	 
    	player.itSdangerousToGoAloneTakeThis( new MachineGun( "Sasha"), 0);
    	player.itSdangerousToGoAloneTakeThis( new Boomerang( "Bro-omerang"), 1);
    	player.itSdangerousToGoAloneTakeThis( new Stick( "ExplainThat!"), 2);
    	player.itSdangerousToGoAloneTakeThis( new FireStick( "IJustWantToKillStuffWithFire"), 3);
    	player.switchWeapon( 2);
    	 
    	entityList = new ArrayList<Sprite>();
    	mobsList = new ArrayList<Raven>();
    	 
    	Item.playstate = this;
    	Level.playstate = this;
    	Sprite.playstate = this;
    	
    }
 
    public void render( GameContainer gc, StateBasedGame sb, Graphics gr) throws SlickException {
    	gr.setBackground( Color.gray);
    	
    	
    	mainLevel.render( gc, sb, gr);
    	
		player.render( gc, sb, gr);
		
    	for( Sprite entity : entityList){
    		entity.render( gc, sb, gr);
    	}
		
    	for( Raven mobs : mobsList){
    		mobs.render( gc, sb, gr);
    	}
		
		gr.setLineWidth( 3.f);
		gr.setColor( Color.orange);
		//gr.draw( camRect);
		gr.setColor( Color.green);
		//gr.draw( moveableRect);
		gr.setColor( Color.white);

		gr.drawString( "Player "+ player.getHp() + "/100 ( " + player.getX() + ", " + player.getY() + ")", 5.f, 35.f);
		gr.drawString( "Velocity Y - " + Math.round( player.getV().y * 100), 5.f, 50.f);
		gr.drawString( "JUMP - " + player.isJumpLock(), 5.f, 105.f);
		gr.drawString( "Solid - " + mainLevel.isSolid( pCursor), 5.f, 120.f);
		gr.drawString( "Bullet - " + entityList.size(), 5.f, 135.f);
		gr.drawString( "Mobs - " + mobsList.size(), 5.f, 150.f);
	
		//gr.drawString( "Angle new - " + pCursor.sub( player.getPosition()).getTheta(), 5.f, 135.f);
		//gr.drawString( "Angle - " + Math.atan( ( pCursor.y - player.getY()) / ( pCursor.x - player.getX() - CONST.PLAYER_WIDTH / 2)) * 180 / Math.PI, 5.f, 150.f);
		
		
    }
 
    public void update( GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
    	
    	
    	
    	Input input = gc.getInput();

    	pCursor.x = input.getMouseX() + cam.x;
    	pCursor.y = input.getMouseY() + cam.y;
    	
//    	moveableRect.setCenterX( player.getX());
//    	moveableRect.setCenterY( player.getY());
    	if( player.getX() <= moveableRect.getX())
    		moveableRect.setX(player.getX());
    	if( player.getX() + CONST.PLAYER_WIDTH > moveableRect.getMaxX())
    		moveableRect.setX( player.getX() + CONST.PLAYER_WIDTH - moveableRect.getWidth());
    	if( player.getY() <= moveableRect.getY())
    		moveableRect.setY(player.getY());
    	if( player.getY() + CONST.PLAYER_HEIGHT > moveableRect.getMaxY())
    		moveableRect.setY( player.getY() + CONST.PLAYER_HEIGHT - moveableRect.getHeight());
    	
    	camRect.setCenterX( moveableRect.getCenterX());
    	camRect.setCenterY( moveableRect.getCenterY());
    	cam.x = Math.round( camRect.getX());
    	cam.y = Math.round( camRect.getY());
    	
    	
    	player.update(gc, sb, delta);
    	
    	for( int i = 0; i < entityList.size();i++){
    		entityList.get( i).update( gc, sb, delta);
    		if( entityList.get( i).isDelete())
    			entityList.remove(i);
    	}
    	
    	for( int i = 0; i < mobsList.size();i++){
    		mobsList.get( i).update( gc, sb, delta);
    		if( mobsList.get( i).isDelete())
    			mobsList.remove(i);
    	}
    	
    	if( input.isKeyPressed( Input.KEY_Q))
    		player.switchToLastEquipped();
    	
    	if( input.isKeyPressed( Input.KEY_1))
    		player.switchWeapon( 0);
    	if( input.isKeyPressed( Input.KEY_2))
    		player.switchWeapon( 1);
    	if( input.isKeyPressed( Input.KEY_3))
    		player.switchWeapon( 2);
    	if( input.isKeyPressed( Input.KEY_4))
    		player.switchWeapon( 3);
    	

    	if( input.isKeyPressed( Input.KEY_K)){
    		mobsList.clear();
    		entityList.clear();
    	}

    	if( input.isKeyPressed( Input.KEY_R))
        	mobsList.add( new Raven( RessourceManager.raven, new Vector2f( (float)(900 * Math.random()), (float)(100 + 100 * Math.random()))));
    	if( input.isKeyPressed( Input.KEY_E))
        	for(int i =0; i<50; i++)mobsList.add( new Raven( RessourceManager.raven, new Vector2f( (float)(2400 * Math.random()), (float)(100 + 100 * Math.random()))));
    }
    
    public Player getPlayer(){
    	return player;
    }
    
    public Vector2f getCam(){
    	return cam;
    }
    
    public Rectangle getCamRect(){
    	return camRect;
    }
    
    public ArrayList<Sprite> getEntities(){
    	return entityList;
    }
    
    public void addEntity( Sprite entity){
    	entityList.add(entity);
    }
    
    public ArrayList<Raven> getMobs(){
    	return mobsList;
    }
    
    public void addMob( Raven mob){
    	mobsList.add(mob);
    }
    
    public void addText( String text, Vector2f s, Color color){

    }
    
	@Override
	public int getID() {
		return stateID;
	}

}