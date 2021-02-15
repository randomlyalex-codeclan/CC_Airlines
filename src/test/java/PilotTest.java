import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PilotTest {

    private Pilot pilot;
    private Flight flight;

    @Before
    public void setUp() {
        pilot = new Pilot("Tom", Rank.CAPTAIN, "ABC123");
    }

    @Test
    public void getName() {
        assertEquals("Tom", pilot.getName());
    }

    @Test
    public void getRank() {
        assertEquals(Rank.CAPTAIN, pilot.getRank());
    }

    @Test
    public void getLicenseNumber() {
        assertEquals("ABC123", pilot.getLicenseNumber());
    }

    @Test
    public void flyPlane() {
        flight = new Flight("BAW1442", "EDI","LHR", "09:46GMT");
        assertEquals("This is your captain Tom speaking, we will be departing at 09:46GMT to EDI thank you for flying CC Airlines",
                pilot.flyPlane(flight));
    }
}