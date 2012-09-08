package lazarus;

import java.util.Calendar;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

public abstract class Mob extends Sprite {
	
	/** Healt points */
	protected int hp = 0; 
	
	/** Invulnerability time */
	protected long invul = 0; 
	
	/** Velocity of the mob */
	protected Vector2f v = new Vector2f( 0, 0);

	public Mob(Animation pSprite, Vector2f p) {
		super( pSprite, p);
	}

    public abstract boolean ping();

    public int getHp(){
    	return hp;
    }
    
    public void setHp( int hp){
    	this.hp = hp;
    }
    
    public void dmg( int ad){
    	if( Calendar.getInstance().getTime().getTime() - invul  >= 3000){
    		hp -= ad;
    		invul = Calendar.getInstance().getTime().getTime();
    	}
    }
    
    public void heal( int potion){
    	hp += potion;
    }
    
    public boolean isDead(){
    	return hp <= 0;
    }
}

