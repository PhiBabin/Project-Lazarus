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
	
	private Vector2f goal;
	
	private int watchRange;
	
	private float angle;
	
	private int watchTime = 0;
	
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
    	case WATCH:
    		watch( delta);
    	case ATTACK:
    		attack( delta);
    	case ANNOY:
    		annoy( delta);
    		break;
    	}
    	
    }
    
    public void spawn( int delta){
		Rectangle r = playstate.mainLevel.closestTile( p);
		if( r != null)
			goal = new Vector2f( r.getCenterX() + (float)(3 * Math.random()) + 1, r.getY() + 2);
		else
			delete = true;
		currentS = State.LANDING;
    }
    
    public void landing( int delta){
    	Vector2f foo = new Vector2f( 
    			goal.x - p.x - aniSprite.getWidth()/2, 
    			goal.y - p.y - aniSprite.getHeight());
    	if( foo.length() > 4f){
	    	v = new Vector2f(
					(float) ( foo.x / foo.length()) * CONST.RAVEN_VELOCITY,
					(float) ( foo.y / foo.length()) * CONST.RAVEN_VELOCITY);
    	}
    	else{
    		currentS = State.LAND;
    		v = new Vector2f( 0, 0);
    		p = new Vector2f( goal.x - aniSprite.getWidth()/2, goal.y - aniSprite.getHeight());
    	}
    	
    	p.x += v.x * delta;
    	p.y += v.y * delta;
    }
    
    public void land( int delta){
    	Vector2f player = playstate.getPlayer().getPosition();
    	if( p.distance( player) < CONST.RAVEN_RANGE){
    		watchRange = (int) ( ( CONST.RAVEN_MAX_DISTANCE - CONST.RAVEN_MIN_DISTANCE) * Math.random() + CONST.RAVEN_MIN_DISTANCE);
    		angle = (float) ( Math.PI * ( 9 + (int)6 * Math.random())/8);
    		goal = new Vector2f( 
    				(float) ( player.x + Math.cos( angle) * watchRange), 
    				(float) ( player.y + Math.sin( angle) * watchRange));
    		Vector2f foo = new Vector2f( 
        			goal.x - p.x, 
        			goal.y - p.y);
    		v = new Vector2f(
					(float) ( foo.x / foo.length()) * CONST.RAVEN_VELOCITY,
					(float) ( foo.y / foo.length()) * CONST.RAVEN_VELOCITY);
    		
    		currentS = State.ANNOY;	
    	}
    }
    public void annoy( int delta){
    	if( goal.distance( p) > 4f){
	    	p.x += v.x * delta;
	    	p.y += v.y * delta;
    	}
    	else{
    		currentS = State.WATCH;
    	}
    	
    	
    }
    
    public void watch( int delta){
    	watchTime += delta;
    	
    	Vector2f player = playstate.getPlayer().getPosition();
    	if( watchTime < 3000)
    		angle -= CONST.RAVEN_ANGULER_VELOCITY * delta;
    	else if( watchTime < 6000)
    		angle += CONST.RAVEN_ANGULER_VELOCITY * delta;
    	else
    		watchTime = (int) ( 1000 * Math.random()) + 500;
    	goal.x = (float) ( player.x + Math.cos( angle) * watchRange - p.x);
    	goal.y = (float) ( player.y + Math.sin( angle) * watchRange - p.y);
		if( goal.length() > 2){
			v = new Vector2f(
				(float) ( goal.x / goal.length()) * CONST.RAVEN_WATCH_VELOCITY,
				(float) ( goal.y / goal.length()) * CONST.RAVEN_WATCH_VELOCITY);
	    	p.x += v.x * delta;
	    	p.y += v.y * delta;
		}
		else{
			p.x += goal.x;
			p.y += goal.y;
		}
    }
    
    public void attack( int delta){
    	
    }
}
