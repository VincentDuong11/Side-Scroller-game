package sidescroller.entity;

import javafx.scene.canvas.GraphicsContext;
import sidescroller.entity.property.Sprite;
import utility.Tuple;

public class Grid extends GenericEntity{
	
	public Grid( Tuple count, double w, double h){
		sprite = new Sprite(){
			
			@Override
			public void draw( GraphicsContext gc){
				gc.setStroke( getStroke());
				gc.setLineWidth( getWidth());
				for(int i=0; i<count.x();i++){
					gc.strokeLine( 0, i*getTileSize().y()*getScale(), w, i*getTileSize().y()*getScale());
				}
				for(int i=0; i<count.y();i++){
					gc.strokeLine( i*getTileSize().x()*getScale(), 0, i*getTileSize().x()*getScale(), h);
				}
			}
		};
	}
}
