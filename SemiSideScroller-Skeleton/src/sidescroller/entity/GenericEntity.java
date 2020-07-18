package sidescroller.entity;

import sidescroller.entity.property.Drawable;
import sidescroller.entity.property.Entity;
import sidescroller.entity.property.HitBox;
import sidescroller.entity.property.Sprite;

public class GenericEntity implements Entity{

	protected Sprite sprite;
	protected HitBox hitbox;

	public GenericEntity(){
	}

	public GenericEntity( Sprite sprite, HitBox hitbox){
		this.sprite = sprite;
		this.hitbox = hitbox;
	}

	@Override
	public void update(){
		// tree has no special update behavior
	}

	@Override
	public boolean hasHitbox(){
		return hitbox!=null;
	}

	@Override
	public boolean isDrawable(){
		return sprite!=null;
	}

	@Override
	public HitBox getHitBox(){
		return hitbox;
	}

	@Override
	public Drawable< ?> getDrawable(){
		return sprite;
	}

}
