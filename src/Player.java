
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Sprite {

	private Vector2f pn = new Vector2f( 0, 0);
	private Vector2f v = new Vector2f( 0, 0);
	private Vector2f pp1 = new Vector2f( 0, 0);
	private Vector2f pp2 = new Vector2f( 0, 0);
	private Rectangle rect = new Rectangle( 440, 240, 100, 100);
	
	public Player(Animation pSprite) {
		super(pSprite);
	}
	
	public Player(Animation pSprite, float nX, float nY) {
		super(pSprite, nX, nY);
		pn= new Vector2f(nX+20,nY+20);
	}
	
	public Vector2f bfFun(Vector2f pr){
		if(rect.contains(pr.x + w, pr.y + h)){
			return bfFun(new Vector2f( pr.x-(pr.x-p.x)*0.01f, pr.y-(pr.y-p.y)*0.01f));
		}
		else return pr;
	}
	
	public void doWorldCollision(){
		pp2.x=pn.x;
		pp2.y=pn.y;
		
		//! Top Left
		while(rect.contains( pp2.x, pp2.y) && !rect.contains( p.x, p.y)){
			pp2.x=pp2.x-(pp2.x-p.x)*0.01f;
			pp2.y=pp2.y-(pp2.y-p.y)*0.01f;
		}
		
		//! Top Right
		while(rect.contains( pp2.x + w, pp2.y) && !rect.contains( p.x + w, p.y)){
			pp2.x=pp2.x-(pp2.x-p.x)*0.01f;
			pp2.y=pp2.y-(pp2.y-p.y)*0.01f;
		}
		
		//! Bottom Left
		while(rect.contains( pp2.x, pp2.y + h) && !rect.contains( p.x, p.y + h)){
			pp2.x=pp2.x-(pp2.x-p.x)*0.01f;
			pp2.y=pp2.y-(pp2.y-p.y)*0.01f;
		}
		
		//! Bottom Right
		while(rect.contains( pp2.x + w, pp2.y + h) && !rect.contains( p.x + w, p.y + h)){
			pp2.x=pp2.x-(pp2.x-p.x)*0.01f;
			pp2.y=pp2.y-(pp2.y-p.y)*0.01f;
		}
	}

    public void update(GameContainer gc, StateBasedGame sb, int delta){
    	Input input = gc.getInput();
    	if(input.isKeyDown(Input.KEY_W)){
    		p.y-=0.1*delta;
    	}
    	 if(input.isKeyDown(Input.KEY_S)){
			 p.y+=0.1*delta;
		 }
    	if(input.isKeyDown(Input.KEY_A)){
    		p.x-=0.1*delta;
    	}
    	 if(input.isKeyDown(Input.KEY_D)){
			 p.x+=0.1*delta;
		 }
    	if(input.isKeyDown(Input.KEY_UP)){
    		pn.y-=0.1*delta;
    	}
    	 if(input.isKeyDown(Input.KEY_DOWN)){
			 pn.y+=0.1*delta;
		 }
    	if(input.isKeyDown(Input.KEY_LEFT)){
    		pn.x-=0.1*delta;
    	}
    	if(input.isKeyDown(Input.KEY_RIGHT)){
			 pn.x+=0.1*delta;
		}
    	
    	v.y-=0.01;
    	
    	doWorldCollision();
		 
    }
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr){
    	gr.draw(rect);
    	
    	aniSprite.draw( p.x, p.y);
    	aniSprite.setCurrentFrame(1);
    	aniSprite.draw( pn.x, pn.y);
    	aniSprite.setCurrentFrame(2);
    	aniSprite.draw( pp1.x, pp1.y);
    	aniSprite.setCurrentFrame(3);
    	aniSprite.draw( pp2.x, pp2.y);
    	aniSprite.setCurrentFrame(0);
    	
    }
}
