
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Sprite {

	public Player(Animation pSprite) {
		super(pSprite);
	}
	
	public Player(Animation pSprite, float nX, float nY) {
		super(pSprite, nX, nY);
	}

    public void update(GameContainer gc, StateBasedGame sb, int delta){}
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr){}
}
