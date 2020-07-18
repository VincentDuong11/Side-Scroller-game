package utility;

import java.util.Objects;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * this class represent a tuple of x and y. it is back by {@link DoubleProperty}.
 * it also also capable of producing random tuples.
 * 
 * @author Shahriar (Shawn) Emami
 * @version March 9, 2020
 */
public class Tuple{

	/**
	 * {@link DoubleProperty} value of x and y
	 */
	private DoubleProperty x, y;

	/**
	 * create a new tuple at (0,0)
	 */
	public Tuple(){
		this( new SimpleDoubleProperty(), new SimpleDoubleProperty());
	}

	/**
	 * create a new tuple with {@link DoubleProperty} for x and y
	 * @param x - value of x
	 * @param y - value of y
	 */
	private Tuple( DoubleProperty x, DoubleProperty y){
		Objects.requireNonNull( x, "x property cannot be null");
		Objects.requireNonNull( y, "y property cannot be null");
		this.x = x;
		this.y = y;
	}

	/**
	 * create a new tuple with given x and y
	 * @param x - value of x
	 * @param y - value of y
	 */
	public Tuple( double x, double y){
		this( new SimpleDoubleProperty( x), new SimpleDoubleProperty( y));
	}

	public static Tuple pair( double a, double b){
		return new Tuple( a, b);
	}

	/**
	 * create a new tuple from given tuple
	 */
	public Tuple( Tuple p){
		this( p.x(), p.y());
	}

	public Tuple bind( Tuple p){
		x.bind( p.x);
		y.bind( p.y);
		return this;
	}

	public Tuple unbind(){
		x.unbind();
		y.unbind();
		return this;
	}

	public Tuple bindBidirectional( Tuple p){
		x.bindBidirectional( p.x);
		y.bindBidirectional( p.y);
		return this;
	}

	public Tuple unbindBidirectional( Tuple p){
		x.unbindBidirectional( p.x);
		y.unbindBidirectional( p.y);
		return this;
	}

	/**
	 * translate the tuple to a new location by the given distance
	 * @param dx - amount to move in x direction
	 * @param dy - amount to move in y direction
	 */
	public void translate( double dx, double dy){
		x( x() + dx);
		y( y() + dy);
	}

	/**
	 * move the current tuple to x and y
	 * @param x - new value of x
	 * @param y - new value of y
	 */
	public void move( double x, double y){
		set( x, y);
	}

	/**
	 * move the current tuple to a new tuple p
	 * @param p - new tuple
	 */
	public void move( Tuple p){
		set( p.x(), p.y());
	}

	/**
	 * set the value of x and y
	 * @param x - new value of x
	 * @param y - new value of y
	 */
	public void set( double x, double y){
		x( x);
		y( y);
	}

	/**
	 * get value of x
	 * @return value of x
	 */
	public double x(){
		return x.get();
	}

	/**
	 * get the {@link DoubleProperty} of x value
	 * @return {@link DoubleProperty} of x value
	 */
	public DoubleProperty xProperty(){
		return x;
	}

	/**
	 * get value of y
	 * @return value of y
	 */
	public double y(){
		return y.get();
	}

	/**
	 * get the {@link DoubleProperty} of y value
	 * @return {@link DoubleProperty} of y value
	 */
	public DoubleProperty yProperty(){
		return y;
	}

	/**
	 * set value of x
	 * @param x - new value of x
	 * @return return the current instance of this tuple
	 */
	public Tuple x( double x){
		this.x.set( x);
		return this;
	}

	/**
	 * set value of y
	 * @param y - new value of y
	 * @return return the current instance of this tuple
	 */
	public Tuple y( double y){
		this.y.set( y);
		return this;
	}

	/**
	 * get the angle between this tuple and given tuple
	 * @param p - a tuple which to find the angle
	 * @return angle between two tuples
	 */
	public double angle( Tuple p){
		return Math.atan2( y() - p.y(), x() - p.x());
	}

	/**
	 * convert tuple to an array;
	 * @return return an array of [x,y]
	 */
	public double[] toArray(){
		return new double[] { x(), y() };
	}

	/**
	 * create a random tuple around this tuple with given max radius.
	 * @param maxRadius - absolute value of max radius
	 * @return a new random tuple
	 */
	public Tuple randomTuple( double maxRadius){
		double angle = RandUtil.getDouble( 360);
		double randR = RandUtil.getDouble( maxRadius);
		double dx = x() + randR * Math.cos( Math.toRadians( angle));
		double dy = y() - randR * Math.sin( Math.toRadians( angle));
		return Tuple.pair( dx, dy);
	}

	/**
	 * create a random tuple around this tuple with given max distance on x and y.
	 * @param maxDx - absolute value of max distance in x direction
	 * @param maxDy - absolute value of max distance in y direction
	 * @return a new random tuple
	 */
	public Tuple randomTuple( double maxDx, double maxDy){
		double dx = x() + RandUtil.getDouble( maxDx) * RandUtil.getPosNeg();
		double dy = y() + RandUtil.getDouble( maxDy) * RandUtil.getPosNeg();
		return Tuple.pair( dx, dy);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + Double.hashCode( (x == null) ? 0 : x());
		result = prime * result + Double.hashCode( (y == null) ? 0 : y());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj){
		if( this == obj)
			return true;
		if( obj == null)
			return false;
		if( !(obj instanceof Tuple))
			return false;
		Tuple other = (Tuple) obj;
		if( x == null){
			if( other.x != null)
				return false;
		}else if( x.get() != other.x.get()){
			return false;
		}
		if( y == null){
			if( other.y != null)
				return false;
		}else if( y.get() != other.y.get()){
			return false;
		}
		return true;
	}

	@Override
	public String toString(){
		return "(" + x() + "," + y() + ")";
	}

	/**
	 * get a random tuple in the area of given height and with minus the given margin
	 * @param w - width of the rectangle which in tuple will spawn
	 * @param h - height of the rectangle which in tuple will spawn
	 * @param margin - the margin around the rectangle which tuple will not spawn
	 * @return a new tuple between the given height and width
	 */
	public static Tuple random( double w, double h, double margin){
		return random( w, h, margin, margin);
	}

	/**
	 * get a random tuple in the area of given height and with minus the given margin
	 * @param w - width of the rectangle which in tuple will spawn
	 * @param h - height of the rectangle which in tuple will spawn
	 * @param marginX - the margin on the width of rectangle which tuple will not spawn
	 * @param marginY - the margin on the height of rectangle which tuple will not spawn
	 * @return a new tuple between the given height and width
	 */
	public static Tuple random( double w, double h, double marginX, double marginY){
		return Tuple.pair( RandUtil.getDouble( marginX, w - marginX),
				RandUtil.getDouble( marginY, h - marginY));
	}
}
