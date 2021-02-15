import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BagTest {

    private Bag bag;

    @Before
    public void setUp() {
        bag = new Bag(20);
    }

    @Test
    public void getWeight() {
        assertEquals(20, bag.getWeight());
    }
}