package lazarus;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class FireBall extends Bullet {

	public FireBall( Animation pSprite, Vector2f v) {
		super( pSprite, v);
	}
	
	/**
	 * Constructor of the Player
	 * @param pSprite Animation
	 * @param nX Bullet X default position
	 * @param nY Bullet Y default position
	 */
	public FireBall( Animation pSprite, Vector2f p, Vector2f v) {
		super( pSprite, p, v);
	}
	
	/**
	 * Bullet update function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param delta Time between frame
	 */
    public void update(GameContainer gc, StateBasedGame sb, int delta){
    	time += delta;

        v.y += CONST.FIREBALL_MASS * CONST.G_FORCE * delta;
        
    	

    	Rectangle rec = new Rectangle( p.x + v.x * delta, p.y + v.y * delta, aniSprite.getWidth(), aniSprite.getHeight());
    	
    	if( playstate.mainLevel.isSolid( rec)){
    		rec = new Rectangle( p.x + v.x * delta, p.y, aniSprite.getWidth(), aniSprite.getHeight());
    		Rectangle recHor = new Rectangle( p.x, p.y + v.y * delta, aniSprite.getWidth(), aniSprite.getHeight());
    		if( !playstate.mainLevel.isSolid( rec))
    			v.y *= -1;
    		else if( !playstate.mainLevel.isSolid( recHor))
    			v.x *= -1;
    	}
    	
    	p.x += v.x * delta;
    	p.y += v.y * delta;
    	
    	
    	if( time > 6000)
    		delete = true;
    }

}
