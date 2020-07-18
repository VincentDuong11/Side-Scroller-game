package sidescroller.scene;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import javafx.scene.canvas.Canvas;
import sidescroller.entity.GenericEntity;
import sidescroller.entity.property.Entity;
import sidescroller.entity.property.HitBox;
import sidescroller.entity.sprite.BackgroundSprite;
import sidescroller.entity.sprite.LandSprite;
import sidescroller.entity.sprite.PlatformSprite;
import sidescroller.entity.sprite.SpriteFactory;
import sidescroller.entity.sprite.TreeSprite;
import sidescroller.entity.sprite.tile.Tile;
import utility.Tuple;


public class MapBuilder implements MapBuilderInterface{
	private Tuple rowColCount;
	private Tuple dimension;
	private double scale;
	private Canvas canvas;
	private Entity background;
	private List<Entity> landMass;
	private List<Entity> other;
	
	
	protected MapBuilder() {
		landMass = new ArrayList<>();
		other = new ArrayList<>();
		
	}
	public static MapBuilder createBuilder() {return new MapBuilder();}
	
	public MapBuilder setCanvas(Canvas canvas) {
		this.canvas = canvas;
		return this;
	}
	
	public MapBuilder setGrid(Tuple rowColCount, Tuple dimension) {
		this.rowColCount = rowColCount;
		this.dimension = dimension;
		return this;
		}
	
	public MapBuilder buildLandMass(int rowPos, int colPos, int rowConut, int colCount) {
	
		LandSprite landSprite = SpriteFactory.get("Land");
		landSprite.init(scale, dimension, Tuple.pair(colPos, rowPos));
		landSprite.createSnapshot(canvas, rowConut, colCount);
		HitBox hitBox = HitBox.build(colPos * dimension.x() * scale, rowPos * dimension.y() * scale, scale * dimension.x() * colCount, scale * dimension.y() * rowConut);
		landMass.add(new GenericEntity(landSprite, hitBox));
		return this;
		}
	public MapBuilder setGridScale(double scale) {
		this.scale = scale;
		return this;}

	@Override
	public MapBuilderInterface buildBackground(BiFunction<Integer, Integer, Tile> callback) {
		// TODO Auto-generated method stub

		BackgroundSprite backgroundSprite = SpriteFactory.get("Background");
		backgroundSprite.init(scale, dimension, Tuple.pair(0, 0));
		backgroundSprite.createSnapshot(canvas, rowColCount, callback);
		HitBox hitBox = HitBox.build(0, 0, scale * dimension.x() * rowColCount.y(), scale * dimension.y() * rowColCount.x());
		background = new GenericEntity(backgroundSprite, hitBox);
	
		return this;
	}
	@Override
	public MapBuilderInterface buildTree(int rowPos, int colPos, Tile tile) {
		// TODO Auto-generated method stub

		TreeSprite treeSprite = SpriteFactory.get("Tree");
		treeSprite.init(scale, dimension, Tuple.pair( colPos, rowPos));
		treeSprite.createSnapshot(canvas, tile);
		other.add(new GenericEntity(treeSprite, null));
		
		return this;
	}
	@Override
	public MapBuilderInterface buildPlatform(int rowPos, int colPos, int length, Tile tile) {
		// TODO Auto-generated method stub
	
		PlatformSprite platformSprite = SpriteFactory.get("Platform");
		platformSprite.init(scale, dimension, Tuple.pair( colPos, rowPos));
		platformSprite.createSnapshot(canvas, tile, length);
		HitBox hitBox = HitBox.build((colPos + .5) * dimension.x() * scale, rowPos * dimension.y() * scale, scale * dimension.x() * (length - 1), scale * dimension.y() / 2);
		other.add(new GenericEntity(platformSprite, hitBox));
		return this;
	}
	@Override
	public Entity getBackground() {
		// TODO Auto-generated method stub
		return background;
	}
	@Override
	public List<Entity> getEntities(List<Entity> list) {
		// TODO Auto-generated method stub
		
		 list.addAll(landMass);
		 list.addAll(other);
		return list;
	}
}

