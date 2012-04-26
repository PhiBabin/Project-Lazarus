import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
 
 
public class Entity {
 
	private String id;
 
    private Vector2f p;
    private float scale;
    private float rotation;
 
    private RenderComponent renderComponent = null;
 
    private ArrayList<Component> components = null;
 
    public Entity(String id){
        this.id = id;
 
        components = new ArrayList<Component>();
 
        p = new Vector2f(0,0);
        scale = 1;
        rotation = 0;
    }
 
    public void addComponent(Component component){
        if(RenderComponent.class.isInstance(component))
            renderComponent = (RenderComponent)component;
 
        component.setOwnerEntity(this);
        components.add(component);
    }
 
    public Component getComponent(String id){
        for(Component comp : components){
		    if ( comp.getId().equalsIgnoreCase(id) )
		        return comp;
		}
 
        return null;
    }
 
    public Vector2f getPosition(){
    	return p;
    }
 
    public float getScale(){
    	return scale;
    }
 
    public float getRotation(){
    	return rotation;
    }
 
    public String getId(){
    	return id;
    }
    
    public void move(float pX, float pY){
		p= new Vector2f( p.x+pX,  p.y+pY);
	}
 
    public void setPosition(Vector2f position) {
    	this.p = position;
    }
 
    public void setRotation(float rotate) {
        rotation = rotate;
    }
 
    public void setScale(float scale) {
    	this.scale = scale;
    }
 
    public void update(GameContainer gc, StateBasedGame sb, int delta){
        for(Component component : components){
            component.update(gc, sb, delta);
        }
    }
 
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr){
        if(renderComponent != null)
            renderComponent.render(gc, sb, gr);
    }
}
