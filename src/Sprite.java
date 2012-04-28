
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
	/** Animation of the sprite  */
	protected Animation aniSprite;
	
	/** Position of the sprite  */
	protected Vector2f p;
	
	/** Scale of the sprite  */
	protected float scale;

	/** Dimension of the sprite  */
	protected int h, w;
	
	/**
	 * Constructor of a simple sprite
	 * @param pSprite
	 */
	public Sprite(Animation pSprite){
		aniSprite=pSprite;
		h = aniSprite.getHeight();
		w = aniSprite.getWidth();
		
		p = new Vector2f(0,0);
	}
	
	/**
	 * Constructor of a simple Sprite
	 * @param pSprite , nX, nY
	 */
	public Sprite(Animation pSprite, float nX, float nY){
		aniSprite=pSprite;
		h = aniSprite.getHeight();
		w = aniSprite.getWidth();
		
		p= new Vector2f(nX,nY);
	}
	/**
	 * Simple Sprite render function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param gr Graphics
	 */
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr){
		aniSprite.draw( p.x, p.y);
	}
	
	/**
	 * Simple Sprite update function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param delta Time between frame
	 */
    public void update(GameContainer gc, StateBasedGame sb, int delta){
    	
    }

    /**
     * Move simple Sprite to new position
     * @param pX float
     * @param pY float
     */
	public void move(float pX, float pY){
		p = new Vector2f( p.x+pX,  p.y+pY);
	}
	
    /**
     * Move simple Sprite to new position
     * @param pM Vector2f 
     */
	public void move(Vector2f pM){
		p = new Vector2f( p.x+pM.x,  p.y+pM.y);
	}
	
	/**
	 * Return Sprite position
	 * @return position Vector2f
	 */
	public Vector2f getPosition() {
		return p;
	}

	/**
	 * Return Sprite scale
	 * @return scale float
	 */
    public float getScale(){
    	return scale;
    }

	/**
	 * Return Sprite x position
	 * @return X position  float
	 */
	public void setX(float x) {
		p.x = x;
	}

	/**
	 * Return Sprite y position
	 * @return Y position  float
	 */
	public void setY(float y) {
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
	 * Set Sprite scale
	 * @param nScale new Scale float
	 */
	public void setScale(float nScale){
    	scale = nScale;
    }
	
}
