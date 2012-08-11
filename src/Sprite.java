/**
 * 
 * @author Philippe Babin
 *
 *	This class works as the gameplay logic manager.
 *	The current drawable entity is render.
 */

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Simplest drawable entity
 * 
 * @author Philippe Babin
 *
 */
public class Sprite {
	
	/** THE PlayState (I know, I'm not suppose to do that. Sorry :_(  */
	public static PlayState playstate;
	
	/** Animation of the sprite  */
	protected Animation aniSprite;
	
	/** Position of the sprite  */
	protected Vector2f p;
	
	/** Scale of the sprite  */
	protected float scale;

	/** Dimension of the sprite  */
	protected int h, w;
	
	/** Position of the sprite  */
	protected boolean delete;
	
	/**
	 * Constructor of a simple sprite
	 * @param pSprite
	 */
	public Sprite(Animation pSprite){
		aniSprite = pSprite;
		h = aniSprite.getHeight();
		w = aniSprite.getWidth();
		
		p = new Vector2f( 0, 0);
		
		delete = false;
	}
	
	/**
	 * Constructor of a simple Sprite
	 * @param pSprite , nX, nY
	 */
	public Sprite(Animation pSprite, Vector2f p){
		aniSprite = pSprite;
		h = aniSprite.getHeight();
		w = aniSprite.getWidth();
		
		this.p = p;
		
		delete = false;
	}
	
	/**
	 * Simple Sprite render function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param gr Graphics
	 */
	public void render( GameContainer gc, StateBasedGame sb, Graphics gr){
		aniSprite.draw( p.x, p.y);
	}
	
	/**
	 * Simple Sprite update function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param delta Time between frame
	 */
    public void update( GameContainer gc, StateBasedGame sb, int delta){
    	
    }
    
    /**
     * Move the Sprite to a new position
     * @param pX 
     * @param pY 
     */
	public void move( int pX, int pY){
		p = new Vector2f( p.x + pX,  p.y + pY);
	}
	
    /**
     * Move simple Sprite to new position
     * @param pM Translation vector
     */
	public void move( Vector2f pM){
		p = new Vector2f( p.x + pM.x,  p.y + pM.y);
	}
	
	
	/**
	 * Return Sprite position
	 * @return position Vector2f
	 */
	public Vector2f getPosition() {
		return p;
	}
	
	/**
	 * Return Sprite X position
	 * @return position float
	 */
	public float getX() {
		return p.x;
	}
	
	/**
	 * Return Sprite Y position
	 * @return position float
	 */
	public float getY() {
		return p.y;
	}


	/**
	 * Return Sprite scale
	 * @return scale float
	 */
    public float getScale(){
    	return scale;
    }

	/**
	 * Set Sprite x position
	 * @return X position  float
	 */
	public void setX(float x) {
		p.x = x;
	}

	/**
	 * Set Sprite y position
	 * @return Y position  float
	 */
	public void setY(float y) {
		p.y = y;
	}
	
	/**
	 * Set Sprite position
	 * @return Y position  float
	 */
	public void setPosition( Vector2f p) {
		this.p = p;
	}	
	
	/**
	 * Set Sprite position
	 * @return Y position  float
	 */
	public void setPosition( float x, float y) {
		p.x = x;
		p.y = y;
	}
	
	/**
	 * Return Sprite's animation
	 * @return Sprite's Animation
	 */
	public Animation getAnimation() {
		return aniSprite;
	}

	/**
	 * Is need to be delete?
	 * @return delete
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * Set Sprite scale
	 * @param nScale new Scale float
	 */
	public void setScale(float nScale){
    	scale = nScale;
    }
	
}
