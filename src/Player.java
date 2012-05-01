
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Player entity
 * This object has the ability to interacte with the map
 * It's controlled by keyboard input 
 * 
 * @author Philippe Babin
 *
 */
public class Player extends Sprite {
	/**
	 * TiledSet of the mainMap
	 */
	private TiledMap world;
	
	/**
	 * Velocity of the Player
	 */
	private Vector2f v = new Vector2f( 0, 0);
	
	/**
	 * New position of the Player
	 */
	private Vector2f pn = new Vector2f( 0, 0);
	
	/**
	 * Constructor of the Player
	 * @param pSprite Animation
	 * @param world	mainMap tileset
	 */
	public Player(Animation pSprite, TiledMap world) {
		super(pSprite);
		this.world = world; 
	}
	
	/**
	 * Constructor of the Player
	 * @param pSprite Animation
	 * @param world	mainMap tileset
	 * @param nX Player X default position
	 * @param nY Player Y default position
	 */
	public Player(Animation pSprite, TiledMap world, float nX, float nY) {
		super(pSprite, nX, nY);
		this.world = world; 
	}
	
	/**
	 * Return true if the global coordinate contain a solid tile block
 	 * @param x Coordinate X
	 * @param y Coordinate Y
	 * @return isSolid boolean 
	 */
	public boolean isSolidBlock(float x, float y){
		int tX = (int)(x)/world.getTileWidth();
		int tY = (int)(y)/world.getTileHeight();
		
		if(tX>0 && tX<world.getWidth() && tY>0 && tY<world.getHeight())
			return world.getTileProperty( world.getTileId( tX, tY, 0), "solid", "0")=="0";
		else 
			return false;
	}

	/**
	 * Found the optimal new position for the Player
	 */
	public void doWorldCollision(){
		boolean lt = isSolidBlock( pn.x, pn.y);
		boolean lb = isSolidBlock( pn.x, pn.y + h);
		boolean rt = isSolidBlock( pn.x + w, pn.y);
		boolean rb = isSolidBlock( pn.x + w, pn.y + h);
		boolean lc = isSolidBlock( pn.x, pn.y + h/2);
		boolean rc = isSolidBlock( pn.x + w, pn.y + h/2);
		
		double ptLength = 0, optimalLength = 0;
		 
		/** Contain the optimal new movable position for the Player */
		Vector2f optimal = new Vector2f( p.x, p.y);
		
		/** Contain all the tries for a new movable position */
		Vector2f pt = new Vector2f( 0, 0);
		
		/** We check for collision */
		if(!lt && !lb && !rt && !rb && !lc && !rc){
			p.x=pn.x;
			p.y=pn.y;
			return;
		}
		
		/** This loop tries all the possible position */
		for(int i=0; i<20; i++){
			for(int j=0; j<20; j++){
				
				pt.x = pn.x-(pn.x-p.x)*(float)Math.pow( 0.8, i);
				pt.y = pn.y-(pn.y-p.y)*(float)Math.pow( 0.8, j);
				
				/** We check for map collision */
				lt = isSolidBlock( pt.x, pt.y);
				lb = isSolidBlock( pt.x, pt.y + h);
				rt = isSolidBlock( pt.x + w, pt.y);
				rb = isSolidBlock( pt.x + w, pt.y + h);
				
				
				/** The new optimal position needs to have no collision and be longer than the last optimal position */
				if(!lt && !lb && !rt && !rb){
					
					/** We check the pt length compare to the current Player's position */
					ptLength = Math.sqrt((pt.x-p.x)*(pt.x-p.x)+(pt.y-p.y)*(pt.y-p.y));
					
					if(ptLength > optimalLength){
						lc = isSolidBlock( pt.x, pt.y + h/2);
						rc = isSolidBlock( pt.x + w, pt.y + h/2);
						
						if(!lc && !rc){
							optimal.x = pt.x;
							optimal.y = pt.y;
							optimalLength = ptLength;
						}
					}
				}
			}
		}
		
		/** If they was a vertical collision, set vertical velocity to zero */
		if(optimal.y<=pn.y-3){
			v.y=0;
		}
		
		/** The optimal new position becomes the current position */
		p.x=optimal.x;
		p.y=optimal.y;
	}


    public void update(GameContainer gc, StateBasedGame sb, int delta){
    	Input input = gc.getInput();
    	boolean u =input.isKeyDown(Input.KEY_W);
    	boolean d =input.isKeyDown(Input.KEY_S);
    	boolean l =input.isKeyDown(Input.KEY_A);
    	boolean r =input.isKeyDown(Input.KEY_D);
    	
    	if(u && !d){
    		v.y=-0.1f;
    	}
    	if(!u && d){
     		v.y=0.1f;
		 }
    	if((l && r) || ( !l && !r)){
    		v.x*=0.6;
    	}
    	if(l && !r){
    		v.x=-0.1f;
    	}
    	if(!l && r){
     		v.x=0.1f;
		}

    	if(input.isKeyDown(Input.KEY_SPACE)){
     		v = new Vector2f( 0, 0);
		}
    	
    	v.y+=0.001*delta;
    	
    	pn.x=p.x+v.x*delta;
    	pn.y=p.y+v.y*delta;
    	
    	doWorldCollision();
	//	System.out.println("p: " + p.x + " " + p.y + " pn: " + pn.x + " " + pn.y);
    }
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr){
    	gr.drawAnimation( aniSprite, p.x, p.y);
    	
    }
}
