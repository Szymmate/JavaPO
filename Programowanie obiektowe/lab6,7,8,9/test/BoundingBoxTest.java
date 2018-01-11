import org.junit.Test;

import static org.junit.Assert.*;

public class BoundingBoxTest {
    @Test
    public void contains() throws Exception {
        BoundingBox a=new BoundingBox(12,12,0,0);
        BoundingBox b=new BoundingBox(11,11,1,1);
        assertTrue("Nie zawierają się",a.contains(b));
    }

    @Test
    public void contains1() throws Exception {
        BoundingBox b=new BoundingBox(11,11,1,1);
        assertTrue("Nie zawierają się",b.contains(5,5));
    }

    @Test
    public void intersects() throws Exception {
        BoundingBox a=new BoundingBox(12,12,0,0);
        BoundingBox b=new BoundingBox(13,13,1,1);
        BoundingBox c=new BoundingBox(13,13,-1,-1);
        BoundingBox d=new BoundingBox(11,11,1,1);
        assertTrue("Nie przecinają się",a.intersects(b));
        assertTrue("Nie przecinają się",!a.intersects(c));
        assertTrue("Nie przecinają się",!a.intersects(d));
    }

    @Test
    public void add() throws Exception {
    }

    @Test
    public void getCenterX() throws Exception {
    }

    @Test
    public void getCenterY() throws Exception {
    }

}