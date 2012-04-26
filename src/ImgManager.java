
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ImgManager {
	public Image player,tileset = null;
	
	public ImgManager(){
		loadImage();
	}
	
	public void loadImage(){
		try {
			player = new Image("img/player.png");
			tileset = new Image("img/tileset.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
}
