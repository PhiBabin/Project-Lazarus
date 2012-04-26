import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class ProjectLazarus extends StateBasedGame {
	/** Screen size */
	private int screenWidth=800, 	screenHeight=600;
	
	/** Name of the game */
	private String GAME_NAME = "Test Project Lazarus";
	
	/** Version of the game */
	private String GAME_VERSION = "0.01";

	/** Name of the game */
	private String GAMENAME= "";
	
    public static final int PLAYSTATE          = 1;
	
	/** position of quad */
	private float x = 400, 		y = 300;
	
	/** dimension of quad */
	private float width = 200, 		height = 200;
	
	/** angle of quad rotation */
	private float rotation = 0;
	
	/** time at last frame */
	private long lastFrame;
	
	/** frames per second */
	private int fps;
	
	/** last fps time */
	private long lastFPS;
	
	/** the running flag */
	private boolean gameRunning = true;
	
	
	public ProjectLazarus(){
		super("ProjectLazarus"); 
		
        this.addState(new PlayState(PLAYSTATE));
        this.enterState(PLAYSTATE);
	}
	
    public void initStatesList(GameContainer gameContainer) throws SlickException {
    	
        this.getState(PLAYSTATE).init(gameContainer, this);
    }
	 
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new ProjectLazarus());
			app.setDisplayMode(800,600,false); 
			app.setVSync(true); 
			app.setShowFPS(true);
			
			app.start();
		} catch (SlickException e) {
			e.printStackTrace(); 
		}
	}
	
}
