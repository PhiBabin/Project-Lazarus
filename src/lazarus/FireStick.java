package lazarus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class FireStick extends Item {
	
	private int cooldown = 0;

	FireStick( String name){
		super( name);
		icon = RessourceManager.arms.getImage( 0);
		arms = RessourceManager.arms_f;
	}
	
	/**
	 * Item update function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param delta Time between frame
	 */
    public void update( GameContainer gc, StateBasedGame sb, int delta){
    	Input input = gc.getInput();
    	
    	cooldown += delta;
    	
		if( input.isMouseButtonDown( Input.MOUSE_LEFT_BUTTON) && cooldown >= 1500){
	    	Vector2f pPlayer = playstate.getPlayer().getPosition();
	    	Vector2f pCursor = new Vector2f( input.getMouseX() + playstate.getCam().x , input.getMouseY() +  playstate.getCam().y);
			
	    	Vector2f bG = new Vector2f(  pCursor.x - pPlayer.x - CONST.PLAYER_WIDTH / 2, pCursor.y - pPlayer.y);
			
			Vector2f bP = new Vector2f( 
					pPlayer.x + CONST.PLAYER_WIDTH / 2,
					pPlayer.y);
			Vector2f bV =  new Vector2f(
					(float) ( bG.x / bG.length()) * CONST.FIREBALL_VELOCITY,
					(float) ( bG.y / bG.length()) * CONST.FIREBALL_VELOCITY);
			Vector2f pStick = new Vector2f( 15, 0);
			
			Vector2f pBall = new Vector2f(
		    	(float) ( bP.x + Math.cos( (pStick.getTheta() + bG.getTheta()) * Math.PI/180) * pStick.length()) - RessourceManager.bullet.getWidth()/2,
		    	(float) ( bP.y + Math.sin( (pStick.getTheta() + bG.getTheta()) * Math.PI/180) * pStick.length()) - RessourceManager.bullet.getHeight()/2);
			
			playstate.addEntity( new FireBall( RessourceManager.fire, pBall, bV));
			
			cooldown = 0;
		}
    }
	
}
