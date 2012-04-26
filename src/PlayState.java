/**
 * 
 * @author Philippe Babin
 *
 *	This class works as the gameplay logic manager.
 *	The main gameplay spreads in subState that will influence the experience. 
 *	The current drawable entity is render.
 */
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class PlayState extends BasicGameState {
	
	private TiledMap mainMap;
	
	private ImgManager imageMan;
	
	private Entity player;
	
	private enum STATES {
        START_GAME_STATE, NEW_PIECE_STATE, MOVING_PIECE_STATE, LINE_DESTRUCTION_STATE,
        PAUSE_GAME_STATE, HIGHSCORE_STATE, GAME_OVER_STATE
    }
	int stateID=-1;
	
	/**
	 * Construct our PlayState
	 * @param stateID
	 */
	public PlayState(int stateID){
		this.stateID=stateID;
	}
	

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	imageMan = new ImgManager();
    	 mainMap = new TiledMap("map/level.tmx");
    	 
    	 player= new Entity("Player");
    	 //player.addComponent(new BasicMoveComp( "PlayerMovement"));
    	 player.addComponent(new WorldCollisionComp( "PlayerCollision"));
    	 player.addComponent(new BasicRenderComp( "PlayerRendering" , imageMan.player));
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gc1) throws SlickException {
		mainMap.render(0,0);
		System.out.println(mainMap.getTileProperty(mainMap.getTileId( 2, 5, 0), "kill", "0"));
		gc1.drawString("Hello, Slick world!", 0, 200); 
		
		player.render(gc, sbg, gc1);
 
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	player.update(gc, sbg, delta);
    }
    
	@Override
	public int getID() {
		return stateID;
	}

}