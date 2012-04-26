
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ImgManager {
	public Animation player,tileset = null;
	public SpriteSheet imgPlayer, imgTileset = null;
	
	public ImgManager(){
		loadImage();
	}
	
	public void loadImage(){
		try {
			imgPlayer = new SpriteSheet( new Image("img/player.png"), 9, 21);
			imgTileset = new SpriteSheet( new Image("img/tileset.png"), 20, 9);
			player = new Animation( imgPlayer, 100000);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
}
