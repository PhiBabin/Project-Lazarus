/**
Copyright (c) 2012 Babin Philippe
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

    Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/

package lazarus;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Item {
	
	public static PlayState playstate;

	/**
	 * Name of the item ( seed)
	 */
	private String name;
	
	/**
	 * Image of the icon
	 */
	protected Image icon;
	
	/**
	 * Player arms draw when the item is equipped.
	 */
	protected Animation arms;
	

	/**
	 * Damage min deals to the mob
	 */
	private int damageMin;
	
	/**
	 * Damage max deals to the mob
	 */
	protected int damageMax;
	
	Item( String name){
		this.name = name;
	}

	/**
	 * Item render function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param gr Graphics
	 */
	public void render( GameContainer gc, StateBasedGame sb, Graphics gr){
		
	}
	
	/**
	 * Item update function
	 * @param gc GameContainer
	 * @param sb StateBasedGame
	 * @param delta Time between frame
	 */
    public void update( GameContainer gc, StateBasedGame sb, int delta){
    	
    }

	/**
	 * This function is call when the current weapon is switch
	 */
    public void switchWeapon(){
    
    }

	public String getName() {
		return name;
	}

	public int getDamageMin() {
		return damageMin;
	}

	public int getDamageMax() {
		return damageMax;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDamageMin(int damageMin) {
		this.damageMin = damageMin;
	}

	public void setDamageMax(int damageMax) {
		this.damageMax = damageMax;
	}
    
}
