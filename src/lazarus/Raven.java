package lazarus;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Raven extends Sprite {
	private enum State  { SPAWN, LANDING, LAND, ANNOY, WATCH, ATTACK};
	
	private State currentS;
	
	private Vector2f landingZone;
	
	/** Velocity of the Bullet */
	protected Vector2f v = new Vector2f( 0, 0);

	public Raven(Animation pSprite, Vector2f p) {
		super( pSprite, p);
		currentS = State.SPAWN;
	}

	/**
	 * Simple Sprite render function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param gr Graphics
	 */
	public void render( GameContainer gc, StateBasedGame sb, Graphics gr){
		aniSprite.draw( p.x - playstate.getCam().x, p.y - playstate.getCam().y);
	}
	
	/**
	 * Simple Sprite update function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param delta Time between frame
	 */
    public void update( GameContainer gc, StateBasedGame sb, int delta){
    	switch(currentS){
    	case SPAWN:
    		spawn( delta);
    		break;
    	case LANDING:
    		landing( delta);
    		break;
    	case LAND:
    		land( delta);
    		break;
    	case ANNOY:
    		annoy( delta);
    		break;
    	}
    	
    }
    
    public void spawn( int delta){
		Rectangle r = playstate.mainLevel.closestTile( p);
		if( r != null)
			landingZone = new Vector2f( r.getCenterX() + (float)(3 * Math.random()) + 1, r.getY() + 2);
		else
			delete = true;
		currentS = State.LANDING;
    }
    
    public void landing( int delta){
    	Vector2f foo = new Vector2f( 
    			landingZone.x - p.x - aniSprite.getWidth()/2, 
    			landingZone.y - p.y - aniSprite.getHeight());
    	if( foo.length() > 4f){
	    	v = new Vector2f(
					(float) ( foo.x / foo.length()) * CONST.RAVEN_VELOCITY,
					(float) ( foo.y / foo.length()) * CONST.RAVEN_VELOCITY);
    	}
    	else{
    		currentS = State.LAND;
    		v = new Vector2f( 0, 0);
    		p = new Vector2f( landingZone.x - aniSprite.getWidth()/2, landingZone.y - aniSprite.getHeight());
    	}
    	
    	p.x += v.x * delta;
    	p.y += v.y * delta;
    }
    
    public void land( int delta){
    	if( p.distance( playstate.getPlayer().getPosition()) < CONST.RAVEN_RANGE)
    		currentS = State.ANNOY;
    }
    public void annoy( int delta){
    	Vector2f foo = new Vector2f( 
    			playstate.getPlayer().getX() - p.x, 
    			playstate.getPlayer().getY() - p.y);
    	p.x += foo.x / foo.length() * CONST.RAVEN_VELOCITY * delta;
    	p.y += foo.y / foo.length() * CONST.RAVEN_VELOCITY * delta;
    	
    }
}
