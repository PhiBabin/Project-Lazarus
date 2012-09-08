/**
Copyright (c) 2012 Babin Philippe
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

    Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

package lazarus;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Level {
	class Tile{
		public Rectangle r;
		public short tileId;
		Tile( Vector2f p, short tileId){
			this.r = new Rectangle( p.x, p.y, CONST.TILE_WIDTH, CONST.TILE_HEIGHT);
			this.tileId = tileId;
		}
	}
	
	public static PlayState playstate;
	
	private String txt, title;
	
	private SpriteSheet tilesetImg;
	
	private ArrayList< Image> tiles = new ArrayList< Image>();
	
	private ArrayList< Tile> level;
	
	private Random rand;
	
	private final String alpha = "abcdefghijklmnopqrstuvwxyz,':;-.!?";
	private final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ,':;-.!?";
	
	public Level() throws SlickException{
		tilesetImg = RessourceManager.tilesetImg;
		
		loadTileset();
	}
	
	public void create( String title, String txt){
		this.title = title;
		this.txt = txt;
		level = new ArrayList<Tile>();
		//rand = new Random( txt.hashCode());
		rand = new Random();
		
		int yMod = 26;
		for( int i = 0; i < txt.length(); i++){
			if( txt.charAt( i) == ' ')
				yMod += rand.nextInt( 5) - 2;
			else
				level.add( new Tile( new Vector2f( i * CONST.TILE_WIDTH, yMod * CONST.TILE_HEIGHT), getCharTie( txt.charAt( i))));
		}
	}

	public short getCharTie( char c){
		if( alpha.indexOf( c) != -1)
			return (short)(alpha.indexOf( c) + 3);
		if( ALPHA.indexOf( c) != -1)
			return (short)(ALPHA.indexOf( c) + 3);
		else 
			return 2;
	}
	
	public boolean isSolid( float x, float y){
		return isSolid( new Vector2f( x, y));
	}
	
	public boolean isSolid( Rectangle rec){
		for( Tile drawed : level){
			if( drawed.r.intersects( rec))
				return true;
		}
		return false;
	}
	public Rectangle closestTile( Vector2f b){
		Rectangle r = null;
		Vector2f u;
		float opDistance = -1;
		for( Tile tile : level){
			u = new Vector2f( tile.r.getCenterX()-b.x, tile.r.getCenterY()-b.y);
			if( opDistance > u.length() || opDistance == -1){
				r = tile.r;
				opDistance = u.length();
			}
				
		}
		return r;
	}
	
	public boolean isSolid( Vector2f b){
		for( Tile drawed : level){
			//if( drawed.r.contains( b.x, b.y))
			if( b.x >= drawed.r.getX() &&
				b.y >= drawed.r.getY() &&
				b.x < drawed.r.getX() + drawed.r.getWidth() &&
				b.y < drawed.r.getY() + drawed.r.getHeight())
					return true;
		}
		return false;
	}
    
    public void loadTileset() throws SlickException{
	    for(int y = 0; y < tilesetImg.getVerticalCount(); y++){
	    	for(int x = 0; x < tilesetImg.getHorizontalCount(); x++){
	    		tiles.add( tilesetImg.getSubImage( x, y));
	    		tiles.get( tiles.size()-1).setFilter( Image.FILTER_NEAREST );
	    	}
		}
    }

	/**
	 * Simple Sprite render function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param gr Graphics
	 */
	public void render( GameContainer gc, StateBasedGame sb, Graphics gr){
		for( Tile drawed : level){
    		if( drawed.r.getX() >= 0 && drawed.r.getY() >= 0 ){
    			//&& drawed.p.y < getHeightInTiles() && drawed.p.x < getWidthInTiles()){
        		gr.drawImage( tiles.get( drawed.tileId - 1), drawed.r.getX() - playstate.getCam().x, drawed.r.getY() - playstate.getCam().y);
    		}
		}
	
	}
	
	/**
	 * Simple Sprite update function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param delta Time between frame
	 */
    public void update( GameContainer gc, StateBasedGame sb, int delta){
    	
    }

}
