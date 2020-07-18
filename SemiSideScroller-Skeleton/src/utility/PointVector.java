package utility;

public class PointVector{

	private double x, y;

	public PointVector( double x, double y){
		set( x, y);
	}

	public void set( double x, double y){
		this.x = x;
		this.y = y;
	}

	public static PointVector random(){
		double t = Math.random() * 2 * Math.PI;
		return new PointVector( Math.cos( t), Math.sin( t));
	}

	public static PointVector random( double minX, double maxX, double minY, double maxY){
		double x = Math.random() * (maxX - minX) + minX;
		double y = Math.random() * (maxY - minY) + minY;
		return new PointVector( x, y);
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public PointVector unit(){
		double m = magnitude();
		return new PointVector( x / m, y / m);
	}

	public PointVector normalize(){
		return unit();
	}

	public PointVector mult( double length){
		return new PointVector( x * length, y * length);
	}

	public PointVector add( PointVector vec){
		return new PointVector( x + vec.x, y + vec.y);
	}

	public PointVector add( double x, double y){
		return new PointVector( x + this.x, y + this.y);
	}

	public PointVector sub( PointVector vec){
		return new PointVector( x - vec.x, y - vec.y);
	}

	public double dot( PointVector vec){
		return x * vec.x + y * vec.y;
	}

	public double dot( double r, double t){
		return magnitude() * r * Math.cos( t);
	}

	public double cross( PointVector vec){
		return x * vec.y + y * vec.x;
	}

	public double cross( double r, double t){
		return magnitude() * r * Math.sin( t);
	}

	public double magnitude(){
		return Math.sqrt( x * x + y * y);
	}

	public double length(){
		return magnitude();
	}

	public double theta(){
		return Math.atan2( y, x);
	}
}
