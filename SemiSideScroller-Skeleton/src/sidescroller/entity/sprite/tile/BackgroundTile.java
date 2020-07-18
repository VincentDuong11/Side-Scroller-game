package sidescroller.entity.sprite.tile;

import utility.Tuple;

public enum BackgroundTile implements Tile{

	MORNING_TOP(0,3), EVENING_TOP(0,4), NIGHT_TOP(0,5),
	MORNING(1,3), EVENING(1,4), NIGHT(1,5),
	MORNING_CLOUD(2,3), EVENING_CLOUD(2,4), NIGHT_CLOUD(2,5),
	WATER_SURFACE(0,2), WATER(1,5);

	private Tuple coord;
	private Tuple count;
	private Tuple size;

	private BackgroundTile( int row, int col){
		coord = Tuple.pair( col, row);
		count = Tuple.pair( 1, 1);
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