package sidescroller.entity.sprite.tile;

import utility.Tuple;

public enum FloraTile implements Tile{

	BUSH(0,0,1,1),
	GRASS_EMPTY_SIDES(0,9,1,1), GRASS_FULL(0,10,1,1),
	FLOWER_RED(1,9,1,1), FLOWER_PINK(1,10,1,1), FLOWER_PURPLE(1,11,1,1), FLOWER_YELLOW(1,12,1,1),
	SUNFLOWER_LONG(1,1,1,2), SUNFLOWER_SHORT(1,2,1,2),
	TREE(0,13,6,7), TREE_DEAD(2,9,4,5);

	private Tuple coord;
	private Tuple count;
	private Tuple size;

	private FloraTile( int row, int col, int width, int height){
		coord = Tuple.pair( col, row);
		count = Tuple.pair( width, height);
		size = Tuple.pair( 16, 16);
	}

	@Override
	public Tuple coord(){
		return coord;
	}

	@Override
	public Tuple size(){
		return size;
	}

	@Override
	public Tuple count(){
		return count;
	}
}