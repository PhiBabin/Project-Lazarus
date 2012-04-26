
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
	
	public void collisionPossibility(){
		boolean col=false;
		if(rect.contains(pn.x + w, pn.y + h)){
			//! Horizontal collision
			if(rect.contains(p.x + w, pn.y + h)){
				col=true;
				pp1.y=rect.getY()-h;
				pp1.x = pn.x + (p.x-pn.x)/2 ;
			}
			else{
				pp1.y=pn.y;
			}
			//! Vertical collision
			if(rect.contains(pn.x + w, p.y + h)){
				col=true;
				//pp1.x=p.x;
				pp1.x = rect.getX() - w;
				pp1.y = pn.y + (p.y-pn.y)/2 ;
			}
			else{
				if(!col)pp1.x=pn.x;
			}
		}
		else{
			pp1.x=pn.x;
			pp1.y=pn.y;
		}
		
		
		if(!col) pp1 = new Vector2f( 0, 0);
		

		if(rect.contains(pp1.x + w, pp1.y + h))System.out.println("collision");
		else System.out.println("non-collision");
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
    	
    	collisionPossibility();
		 
    }
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr){
    	gr.draw(rect);
    	
    	aniSprite.draw( p.x, p.y);
    	aniSprite.setCurrentFrame(1);
    	aniSprite.draw( pn.x, pn.y);
    	aniSprite.setCurrentFrame(2);
    	aniSprite.draw( pp1.x, pp1.y);
    	aniSprite.setCurrentFrame(0);
    	
    }
}
