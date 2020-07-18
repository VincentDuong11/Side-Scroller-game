package utility;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRectangleBounds{
	
	private RectangleBounds mainR, testR;
	
	@BeforeEach
	public void setup(){
		mainR = new RectangleBounds( 100, 100, 200, 200);
	}
	
	@Test
	public void testContainsWithIn(){
		testR = new RectangleBounds( 110, 110, 180, 180);
		assertTrue( mainR.contains( testR));
		testR.translate( 5, 5);
		assertTrue( mainR.contains( testR));
		testR.translate( -10, -10);
		assertTrue( mainR.contains( testR));
		testR.translate( -5, -5);
		assertTrue( mainR.contains( testR));
		testR.translate( -1, -1);
		assertFalse( mainR.contains( testR));
	}
	
	@Test
	public void testContainsSameSize(){
		testR = new RectangleBounds( 100, 100, 200, 200);
		assertTrue( mainR.contains( testR));
	}
	
	@Test
	public void testContainsOverlap(){
		testR = new RectangleBounds( 99, 99, 100, 100);
		assertFalse( mainR.contains( testR));
		testR.translate( -1, -1);
		assertFalse( mainR.contains( testR));
		testR.translate( 202, 202);
		assertFalse( mainR.contains( testR));
	}
	
	@Test
	public void testContainsOutside(){
		testR = new RectangleBounds( 100, 10, 10, 10);
		assertFalse( mainR.contains( testR));
		testR.translate( 0, 300);
		assertFalse( mainR.contains( testR));
	}
	
	@Test
	public void testIntersectWithIn(){
		testR = new RectangleBounds( 110, 110, 180, 180);
		assertTrue( mainR.intersects( testR));
		testR.translate( 5, 5);
		assertTrue( mainR.intersects( testR));
		testR.translate( -10, -10);
		assertTrue( mainR.intersects( testR));
	}
	
	@Test
	public void testIntersectSameSize(){
		testR = new RectangleBounds( 100, 100, 200, 200);
		assertTrue( mainR.intersects( testR));
	}
	
	@Test
	public void testIntersectOverlap(){
		testR = new RectangleBounds( 99, 99, 100, 100);
		assertTrue( mainR.intersects( testR));
		testR = new RectangleBounds( 250, 250, 100, 100);
		assertTrue( mainR.intersects( testR));
		testR = new RectangleBounds( 250, 99, 100, 100);
		assertTrue( mainR.intersects( testR));
	}
	
	@Test
	public void testIntersectOutside(){
		testR = new RectangleBounds( 100, 10, 10, 10);
		assertFalse( mainR.intersects( testR));
		testR.translate( 0, 300);
		assertFalse( mainR.intersects( testR));
	}
}

