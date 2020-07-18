package sidescroller.scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import sidescroller.animator.AnimatorInterface;
import sidescroller.entity.property.Entity;
import sidescroller.entity.property.HitBox;
import sidescroller.entity.sprite.tile.BackgroundTile;
import sidescroller.entity.sprite.tile.FloraTile;
import sidescroller.entity.sprite.tile.PlatformTile;
import utility.Tuple;

public class MapScene implements MapSceneInterface {
	private Tuple count;
	private Tuple size;
	private double scale;
	private AnimatorInterface animator;
	private List<Entity> players;
	private List<Entity> staticShapes;
	private BooleanProperty drawBounds;
	private BooleanProperty drawFPS;
	private BooleanProperty drawGrid;
	private Entity background;

	public MapScene() {
		drawBounds = new SimpleBooleanProperty(true);
		drawFPS = new SimpleBooleanProperty(true);
		drawGrid = new SimpleBooleanProperty(true);
		staticShapes = new ArrayList<>();
		players = new ArrayList<>();
	}

	public BooleanProperty drawFPSProperty() {
//		return new SimpleBooleanProperty(true);
		return drawFPS;
	}

	public boolean getDrawFPS() {
		return drawFPS.get();
	}

	public BooleanProperty drawBoundsProperty() {
//		return new SimpleBooleanProperty(true);
		return drawBounds;
	}

	public boolean getDrawBounds() {
		return drawBounds.get();
	}

	public BooleanProperty drawGridProperty() {
		return drawGrid;
	}

	public boolean getDrawGrid() {
		return drawGrid.get();
	}

	public MapScene setRowAndCol(Tuple count, Tuple size, double scale) {
		/**
		 * save date in the correct variables.
		 * 
		 * @param count - number of rows and columns in the grid.
		 * @param size  - width and height of each cell in grid.
		 * @param scale - a double multiplier for width and height of each grid cell.
		 * @return current instance of this class.
		 */
		this.count = count;
		this.size = size;
		this.scale = scale;

		return this;
	}

	public Tuple getGridCount() {
		return count;
	}

	public Tuple getGridSize() {
		return size;
	}

	public void start() {
		if (animator != null) {
			animator.start();
		}
	}

	public void stop() {
		if (animator != null) {
			animator.stop();
		}
	}

	public List<Entity> staticShapes() {
		return staticShapes;
	}

	public List<Entity> players() {
		return this.players	;
	}

	public MapScene setAnimator(AnimatorInterface newAnimator) {
		/**
		 * if current animator instance is not null stop it first. then assign the
		 * newAnimator to animator.
		 * 
		 * @param newAnimator - new animator to set.
		 * @return current instance of this class.
		 * @throws NullPointerException if argument is null.
		 */
		if (animator != null) {
			animator.stop();
		}
		animator = newAnimator;
		return this;
	}

	@Override
	public double getScale() {
		// TODO Auto-generated method stub
		return scale;
	}

	@Override
	public Entity getBackground() {
		// TODO Auto-generated method stub
		return background;
	}

	@Override
	public MapSceneInterface createScene(Canvas canvas) {
		// TODO Auto-generated method stub
		MapBuilder mb = MapBuilder.createBuilder();
		mb.setCanvas(canvas).setGrid(count, size).setGridScale(scale);
//		mb.buildBackground((Integer i1, Integer i2) -> BackgroundTile.EVENING_CLOUD);
//		this.background = mb.getBackground();
		mb.buildBackground((Integer row, Integer col) -> {
			if (row < 3) {
				ArrayList<BackgroundTile> list = new ArrayList<BackgroundTile>();
				list.add(BackgroundTile.EVENING_CLOUD);
				list.add(BackgroundTile.EVENING);
				Random rand = new Random();
				int index = rand.nextInt(list.size());
				BackgroundTile tile = list.get(index);
				return tile;
			}
			return BackgroundTile.EVENING;
		});

		this.background = mb.getBackground();

		mb.buildLandMass(5, 5, 9, 6);
		mb.buildTree(3, 7, FloraTile.SUNFLOWER_LONG);
		mb.buildTree(3, 7, FloraTile.GRASS_FULL);
		mb.buildTree(4, 7, FloraTile.GRASS_FULL);
		mb.buildTree(3, 17, FloraTile.TREE_DEAD);
		mb.buildLandMass(10, 11, 4, 5);
		mb.buildLandMass(8, 16, 6, 8);
		mb.buildLandMass(7, 24, 7, 6);
		mb.buildTree(0, 24, FloraTile.TREE);
		mb.buildPlatform(5, 14, 3, PlatformTile.STONE);
		
		mb.getEntities(staticShapes);

		return this;
	}

	@Override
	public boolean inMap(HitBox hitbox) {
		// TODO Auto-generated method stub

		return background.getHitBox().containsBounds(hitbox);
	}
}
