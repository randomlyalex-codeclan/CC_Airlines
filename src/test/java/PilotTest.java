import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PilotTest {

    private Pilot pilot;
    private Flight flight;
    private LocalDateTime timeBAW1442;

    @Before
    public void setUp() {pilot = new Pilot("Tom", Rank.CAPTAIN, "ABC123");}

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
        timeBAW1442 = LocalDateTime.of(2021, 02, 15, 9, 05, 00);
        flight = new Flight("BAW1442", "EDI","LHR", timeBAW1442);
        assertEquals("This is your captain Tom speaking, we will be departing at 09:05 to EDI thank you for flying CC Airlines",
                pilot.flyPlane(flight));
    }
}