import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class BasicRenderComp extends RenderComponent {
	
	private Image img;
	
	public BasicRenderComp(String id, Image img){
		super(id);
		this.img=img;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
		Vector2f p = owner.getPosition();
		
		img.draw(p.x , p.y, owner.getScale());
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		// TODO Auto-generated method stub

	}

}
