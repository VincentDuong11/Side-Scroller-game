package sidescroller.entity.player;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import sidescroller.entity.property.HitBox;
import sidescroller.entity.property.Sprite;

public class PlayerHitBox extends HitBox{

	public enum Direction{
		TOP, BOTTOM, LEFT, RIGHT;
	}

	private Map< Direction, HitBox> hitboxes;
	private List< Direction> directions;

	public PlayerHitBox(){
		hitboxes = new EnumMap<>( Direction.class);
		directions = new LinkedList<>();
		sprite = new Sprite(){

			@Override
			public void draw( GraphicsContext gc){
				hitboxes.values().forEach( hb -> hb.getDrawable().draw( gc));
			}

			@Override
			public Sprite setStroke( Paint color){
				super.setStroke( color);
				if( directions.isEmpty())
					hitboxes.values().forEach( hb -> hb.getDrawable().setStroke( color));
				else
					directions.forEach( d -> hitboxes.get( d).getDrawable().setStroke( color));
				return this;
			}
		};
	}

	@Override
	public HitBox setBounds( double x, double y, double w, double h){
		super.setBounds( x, y, w, h);
		hitboxes.put( Direction.TOP, HitBox.build( x + 4, y, w - 8, 1));
		hitboxes.put( Direction.RIGHT, HitBox.build( x + w - 1, y + 4, 1, h - 8));
		hitboxes.put( Direction.BOTTOM, HitBox.build( x + 4, y + h - 1, w - 8, 1));
		hitboxes.put( Direction.LEFT, HitBox.build( x, y + 4, 1, h - 8));
		return this;
	}

	@Override
	public HitBox translate( double dx, double dy){
		super.translate( dx, dy);
		hitboxes.values().forEach( hb -> hb.translate( dx, dy));
		return this;
	}

	@Override
	public HitBox undoTranslate(){
		if( directions.isEmpty()){
			super.undoTranslate();
			hitboxes.values().forEach( HitBox::undoTranslate);
		}else{
			directions.forEach( this::undoTranslate);
		}
		return this;
	}

	public HitBox undoTranslate( Direction d){
		if( d == Direction.BOTTOM || d == Direction.TOP){
			bounds.moveY( prev.y());
			hitboxes.values().forEach( hb -> hb.getBounds().moveY( hb.getPrev().y()));
		}else if( d == Direction.LEFT || d == Direction.RIGHT){
			bounds.moveX( prev.x());
			hitboxes.values().forEach( hb -> hb.getBounds().moveX( hb.getPrev().x()));
		}
		return this;
	}

	@Override
	public boolean intersectBounds( HitBox hitbox){
		if( !bounds.intersects( hitbox.getBounds())){
			directions.clear();
			return false;
		}
		hitboxes.entrySet().forEach( en -> {
			if( en.getValue().intersectBounds( hitbox)){
				directions.add( en.getKey());
			}
		});
		return true;
	}

	public List< Direction> getIntersectDirection(){
		return directions;
	}

	public boolean isDirection( Direction d){
		return directions.contains( d);
	}
}
