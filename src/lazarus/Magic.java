package lazarus;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Magic extends Bullet {
	
	/** Origin of the Magic Ball */
	private Vector2f o = new Vector2f( 0, 0);
	
	/** deltaS of the Magic Ball */
	private Vector2f m = new Vector2f( 0, 0);
	
	private boolean sind;
	
	/**
	 * Constructor of the Player
	 * @param pSprite Animation
	 * @param nX Bullet X default position
	 * @param nY Bullet Y default position
	 */
	public Magic( Animation pSprite, Vector2f p, Vector2f v, boolean sind) {
		super( pSprite, p, v);
		this.v = v;
		this.o.x = p.x;
		this.o.y = p.y;
		this.sind = sind;
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
    	
    	if( sind){
        	if( v.x >= 0)
        		m.y = (float) ( Math.cos( time/100.f + Math.PI * 3/2) * CONST.MAGIC_MAX_AMPLITUDE) * force;
        	else
        		m.y = (float) ( Math.cos( time/100.f - Math.PI * 3/2) * CONST.MAGIC_MAX_AMPLITUDE) * force;
    	}
    	else{
        	if( v.x >= 0)
        		m.y = (float) ( Math.cos( time/100.f + Math.PI/2) * CONST.MAGIC_MAX_AMPLITUDE) * force;
        	else
        		m.y = (float) ( Math.cos( time/100.f - Math.PI/2) * CONST.MAGIC_MAX_AMPLITUDE) * force;
    	}
    	
    	p.x = (float) ( o.x + Math.cos( (m.getTheta() + v.getTheta()) * Math.PI/180) * m.length());
    	p.y = (float) ( o.y + Math.sin( (m.getTheta() + v.getTheta()) * Math.PI/180) * m.length());
    	
    	if( time > 3000)
    		delete = true;
    	
    	doCollision();
    }

    public void doCollision(){
    	for( Mob mob : playstate.getMobs()){
    		if( getCollisionRect().intersects( mob.getCollisionRect())){
    			if( mob.ping()){
    				mob.dmg( 10);
    			}
    		}
    	}
    }

}
