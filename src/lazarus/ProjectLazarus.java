/**
Copyright (c) 2012 Babin Philippe
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

    Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

package lazarus;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class ProjectLazarus extends StateBasedGame {
	
	/** Name of the game */
	private static String GAME_NAME = "Test Project Lazarus";
	
	/** Version of the game */
	private static String GAME_VERSION = "v0.01";
	
    public static final int PLAYSTATE = 1;
	
	public ProjectLazarus(){
		super("ProjectLazarus"); 
		
        this.addState( new PlayState(PLAYSTATE));
        this.enterState( PLAYSTATE);
	}
	
    public void initStatesList(GameContainer gameContainer) throws SlickException {
       //this.getState(PLAYSTATE).init(gameContainer, this);
    }
	 
	public static void main(String[] args) {
		try {
			CONST.APPLET = false;
			
			AppGameContainer app = new AppGameContainer( new ProjectLazarus());
			app.setDisplayMode( CONST.SCREEN_WIDTH, CONST.SCREEN_HEIGHT,false); 
			app.setVSync( true); 
			app.setShowFPS( true);
			
			app.setTitle( GAME_NAME + " " + GAME_VERSION);
			
			app.start();
		} catch (SlickException e) {
			e.printStackTrace(); 
		}
	}
	
}
