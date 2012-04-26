import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public class BasicMoveComp extends Component {
	
	private Vector2f v;
	private Vector2f a;

	public BasicMoveComp(String id) {
		this.id = id;
		v = new Vector2f( 0, 0);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
			
		Input input = gc.getInput();
		
		 if(input.isKeyDown(Input.KEY_D)){
			 v.x=1;
		 }
		 else{
			 v.x=0;
		 }
		 
		owner.move(v.x * delta/10, v.y * delta/10);
		//System.out.println("Position : " + owner.getPosition().x+ " " + owner.getPosition().y);
	}

}
