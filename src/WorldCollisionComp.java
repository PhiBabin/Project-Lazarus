import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


public class WorldCollisionComp extends Component {
	private TiledMap world;
	private Vector2f v;
	private Vector2f a;

	
	public WorldCollisionComp (String id , TiledMap world){
		this.id = id;
		this.world = world; 
	}
	
	public boolean checkCollision(){
		return true;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		Vector2f p = owner.getPosition();
		
		Input input = gc.getInput();
		
		 if(input.isKeyDown(Input.KEY_D)){
			 v.x=1;
		 }
		 else{
			 v.x=0;
		 }
		 
		owner.move(v.x * delta/10, v.y * delta/10);

		
	}

}
