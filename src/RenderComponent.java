import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
 
 
public abstract class RenderComponent extends Component {
 
    public RenderComponent(String id){
    	this.id = id;
    }
 
    public abstract void render(GameContainer gc, StateBasedGame sb, Graphics gr);
}