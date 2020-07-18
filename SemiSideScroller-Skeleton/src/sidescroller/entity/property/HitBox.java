package sidescroller.entity.property;

import sidescroller.entity.GenericEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utility.RectangleBounds;
import utility.Tuple;

public class HitBox extends GenericEntity{

	protected Tuple prev;
	protected RectangleBounds bounds;

	protected HitBox(){
		sprite = new Sprite(){

			@Override
			public void draw( GraphicsContext gc){
				gc.setStroke( getStroke());
				gc.setLineWidth( getWidth());
				if( bounds != null)
					gc.strokeRect( bounds.x(), bounds.y(), bounds.w(), bounds.h());
			}
		};
		sprite.setStroke( Color.RED).setWidth( 3);
		prev = new Tuple();
	}

	public static HitBox build( double x, double y, double w, double h){
		return new HitBox().setBounds( x, y, w, h);
	}

	public RectangleBounds getBounds(){
		return bounds;
	}

	public HitBox setBounds( RectangleBounds bounds){
		this.bounds = bounds;
		prev.set( bounds.centerX(), bounds.centerY());
		return this;
	}

	public HitBox setBounds( double x, double y, double w, double h){
		return setBounds( new RectangleBounds( x, y, w, h));
	}

	public HitBox translate( double dx, double dy){
		prev.move( bounds.startPos());
		bounds.translate( dx, dy);
		return this;
	}

	public HitBox undoTranslate(){
		bounds.move( prev);
		return this;
	}

	public Tuple getPrev(){
		return prev;
	}

	public boolean containsBounds( HitBox box){
		return bounds.contains( box.getBounds());
	}

	public boolean intersectBounds( HitBox box){
		return bounds.intersects( box.getBounds());
	}

	@Override
	public boolean hasHitbox(){
		return true;
	}

	@Override
	public HitBox getHitBox(){
		return this;
	}

	@Override
	public String toString(){
		return bounds.toString();
	}
}
