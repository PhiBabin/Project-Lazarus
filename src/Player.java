
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Player extends Sprite {
	private TiledMap world;

	private Vector2f pn = new Vector2f( 0, 0);
	private Vector2f v = new Vector2f( 0, 0);
	private Rectangle rect = new Rectangle( 400, 240, 100, 100);
	
	public Player(Animation pSprite, TiledMap world) {
		super(pSprite);
		this.world = world; 
	}
	
	public Player(Animation pSprite, TiledMap world, float nX, float nY) {
		super(pSprite, nX, nY);
		this.world = world; 
	}
	
	public Vector2f bfFun(Vector2f pr){
		if(rect.contains(pr.x + w, pr.y + h)){
			return bfFun(new Vector2f( pr.x-(pr.x-p.x)*0.01f, pr.y-(pr.y-p.y)*0.01f));
		}
		else return pr;
	}

	public void doWorldCollision2(){
		boolean lt=false;
		boolean lb=false;
		boolean rt=false;
		boolean rb=false;
		Vector2f optimal= new Vector2f( p.x, p.y);
		Vector2f pt= new Vector2f( 0, 0);
		
		for(int i=0; i<20; i++){
			for(int j=0; j<20; j++){
				pt.x=pn.x-(pn.x-p.x)*(float)Math.pow( 0.7, i);
				pt.y=pn.y-(pn.y-p.y)*(float)Math.pow( 0.7, j);
				lt=rect.contains( pt.x, pt.y);
				lb=rect.contains( pt.x, pt.y + h);
				rt=rect.contains( pt.x + w, pt.y);
				rb=rect.contains( pt.x + w, pt.y + h);
				if(!lt && !lb && !rt && !rb && 
						Math.sqrt((pt.x-p.x)*(pt.x-p.x)+(pt.y-p.y)*(pt.y-p.y))>
						Math.sqrt((optimal.x-p.x)*(optimal.x-p.x)+(optimal.y-p.y)*(optimal.y-p.y))){
					optimal.x=pt.x;
					optimal.y=pt.y;
				}
			}
		}
		if(optimal.y<=pn.y-1){
			v.y=0;
		}
		
	//	pp2.x=optimal.x;
	//	pp2.y=optimal.y;
		p.x=optimal.x;
		p.y=optimal.y;
	}
	public void doWorldCollision(){
		boolean lt=false, lb=false, rt=false, rb=false;
		
		pp2.x=pn.x;
		pp2.y=pn.y;
		int i=0;
		//! Top Left
		while(rect.contains( pp2.x, pp2.y) && !rect.contains( p.x, p.y) && i<20){
			pp2.x=pp2.x-(pp2.x-p.x)*0.01f;
			pp2.y=pp2.y-(pp2.y-p.y)*0.01f;
			i++;
			lt=true;
		}
		if(i>=20){
			pp2.x=p.x;
			pp2.y=p.y;
		}
		
		//! Top Right
		i=0;
		while(rect.contains( pp2.x + w, pp2.y) && !rect.contains( p.x + w, p.y) && i<20){
			pp2.x=pp2.x-(pp2.x-p.x)*0.01f;
			pp2.y=pp2.y-(pp2.y-p.y)*0.01f;
			i++;
			rt=true;
		}
		if(i>=20){
			pp2.x=p.x;
			pp2.y=p.y;
		}
		
		//! Bottom Left
		i=0;
		while(rect.contains( pp2.x, pp2.y + h) && !rect.contains( p.x, p.y + h) && i<20){
			pp2.x=pp2.x-(pp2.x-p.x)*0.01f;
			pp2.y=pp2.y-(pp2.y-p.y)*0.01f;
			i++;
			lb=true;
		}
		if(i>=20){
			pp2.x=p.x;
			pp2.y=p.y;
		}
		
		//! Bottom Right
		i=0;
		while(rect.contains( pp2.x + w, pp2.y + h) && !rect.contains( p.x + w, p.y + h) && i<20){
			pp2.x=pp2.x-(pp2.x-p.x)*0.01f;
			pp2.y=pp2.y-(pp2.y-p.y)*0.01f;
			i++;
			rb=true;
		}
		if(i>=20){
			pp2.x=p.x;
			pp2.y=p.y;
		}
		
		//p.x=pp2.x;
		//p.y=pp2.y;
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
    		v.x=0;
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
    //	p.x=p.x+v.x*delta;
    //	p.y=p.y+v.y*delta;
    	
    //	pn.x=p.x-10;
    //	pn.y=p.y-10;
    	
    	doWorldCollision2();
	//	System.out.println("p: " + p.x + " " + p.y + " pn: " + pn.x + " " + pn.y);
    }
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr){
    	gr.draw(rect);
    	
    	aniSprite.draw( p.x, p.y);
    	aniSprite.setCurrentFrame(1);
    	//aniSprite.draw( pn.x, pn.y);
    	aniSprite.setCurrentFrame(2);
    	//aniSprite.draw( pp1.x, pp1.y);
    	aniSprite.setCurrentFrame(3);
    	//aniSprite.draw( pp2.x, pp2.y);
    	aniSprite.setCurrentFrame(0);
    	
    }
}
