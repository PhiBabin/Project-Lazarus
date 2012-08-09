import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public class MachineGun extends Item {
	private int lastBullet = 0;
	
	
	MachineGun(){
		icon = RessourceManager.arms.getImage( 0);
		arms = RessourceManager.arms;
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
	    	gr.drawAnimation( arms, pPlayer.x + 3, pPlayer.y - 3);
	    	gr.rotate( pPlayer.x + CONST.PLAYER_WIDTH / 2, pPlayer.y, -armAngle);
	    }
    	else{
    		arms.setCurrentFrame(1);
	    	gr.rotate( pPlayer.x + CONST.PLAYER_WIDTH / 2, pPlayer.y, armAngle);
	    	gr.drawAnimation( arms, pPlayer.x - 6, pPlayer.y - 3);
	    	gr.rotate( pPlayer.x + CONST.PLAYER_WIDTH / 2, pPlayer.y, -armAngle);
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
    	
    	lastBullet += delta;
		if( input.isMouseButtonDown( Input.MOUSE_LEFT_BUTTON) && lastBullet >= 200){
			Vector2f bG = new Vector2f(  pCursor.x - pPlayer.x - CONST.PLAYER_WIDTH / 2, pCursor.y - pPlayer.y);
			double h = Math.sqrt( bG.x * bG.x + bG.y * bG.y);
			
			Vector2f bP = new Vector2f( pPlayer.x + CONST.PLAYER_WIDTH / 2, pPlayer.y);
			Vector2f bV = null;
	
			bV = new Vector2f(
					(float) ( bG.x / h) * CONST.BULLET_VELOCITY,
					(float) ( bG.y / h) * CONST.BULLET_VELOCITY);
			
			playstate.addEntity( new Bullet( RessourceManager.bullet, bP.x, bP.y, bV));
			
			lastBullet = 0;
		}
    }
}
