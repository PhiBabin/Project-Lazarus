package lazarus;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

public abstract class Mob extends Sprite {
	
	protected int hp; 
	
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
    	hp -= ad;
    	System.out.println("callisse!" + ad);
    }
    
    public void heal( int potion){
    	hp += potion;
    }
    
    public boolean isDead(){
    	return hp <= 0;
    }
}

