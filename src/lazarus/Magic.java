package lazarus;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Magic extends Bullet {
	
	/** Velocity of the Magic Ball */
	private Vector2f v = new Vector2f( 0, 0);
	
	/** Origin of the Magic Ball */
	private Vector2f o = new Vector2f( 0, 0);
	
	/** deltaS of the Magic Ball */
	private Vector2f m = new Vector2f( 0, 0);
	
	/** TimeLaspe **/
	private int time = 0;

	public Magic( Animation pSprite, Vector2f v) {
		super( pSprite, v);
		this.v = v;
	}
	
	/**
	 * Constructor of the Player
	 * @param pSprite Animation
	 * @param nX Bullet X default position
	 * @param nY Bullet Y default position
	 */
	public Magic( Animation pSprite, Vector2f p, Vector2f v) {
		super( pSprite, p, v);
		this.v = v;
		this.o.x = p.x;
		this.o.y = p.y;
	}

	/**
	 * Bullet update function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param delta Time between frame
	 */
    public void update(GameContainer gc, StateBasedGame sb, int delta){
    	time += delta;
    	float force;
    	if( time > 500)
    		force = 1;
    	else
    		force = (float)time/500.f;
    		
    	m.x += v.length() * delta;
    	if( v.x >= 0)
    		m.y = (float) ( Math.cos( time/100.f + Math.PI/2) * CONST.MAGIC_MAX_AMPLITUDE) * force;
    	else
    		m.y = (float) ( Math.cos( time/100.f - Math.PI/2) * CONST.MAGIC_MAX_AMPLITUDE) * force;
    	
    	p.x = (float) ( o.x + Math.cos( (m.getTheta() + v.getTheta()) * Math.PI/180) * m.length());
    	p.y = (float) ( o.y + Math.sin( (m.getTheta() + v.getTheta()) * Math.PI/180) * m.length());
    	

    	Rectangle rec = new Rectangle( p.x, p.y, aniSprite.getWidth(), aniSprite.getHeight());
    	
    	if( playstate.mainLevel.isSolid( rec) || time > 3000)
    		delete = true;
    }

}
