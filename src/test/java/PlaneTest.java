import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlaneTest {

    private Plane plane1;
    private Plane plane2;

    @Before
    public void setUp(){
        plane1 = new Plane(PlaneModel.A320, "N475UA");
        plane2 = new Plane(PlaneModel.BOEING777, "G-YMML");
    }

    @Test
    public void getPlaneModel() {
        assertEquals(PlaneModel.A320, plane1.getPlaneModel());
    }

    @Test
    public void getRegistration() {
        assertEquals("G-YMML", plane2.getRegistration());
    }
}