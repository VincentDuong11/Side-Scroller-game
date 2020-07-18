package sidescroller.entity.property;

public interface Entity{

	void update();

	boolean hasHitbox();

	boolean isDrawable();

	HitBox getHitBox();

	Drawable< ?> getDrawable();
}
