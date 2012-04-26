
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Sprite {
	/** Animation of the sprite  */
	protected Animation aniSprite;
	
	/** Position of the sprite  */
	protected Vector2f p;
	
	protected int h, w;
	
	/**
	 * 
	 * @param pImg
	 */
	public Sprite(Animation pSprite){
		aniSprite=pSprite;
		h = aniSprite.getHeight();
		w = aniSprite.getWidth();
		
		p = new Vector2f(0,0);
	}
	/**
	 * 
	 * @param pSprite , nX, nY
	 */
	public Sprite(Animation pSprite, float nX, float nY){
		aniSprite=pSprite;
		h = aniSprite.getHeight();
		w = aniSprite.getWidth();
		
		p= new Vector2f(nX,nY);
	}
	
	public void render(){
		aniSprite.draw( p.x, p.y);
	}

	public void move(float pX, float pY){
		p= new Vector2f( p.x+pX,  p.y+pY);
	}
	
	public Vector2f getPosition() {
		return p;
	}
	
	public void setX(float x) {
		p.x = x;
	}
	
	public void setY(float y) {
		p.y = y;
	}
	
	public Animation getAnimation() {
		return aniSprite;
	}
    public void update(GameContainer gc, StateBasedGame sb, int delta){}
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr){}
}
