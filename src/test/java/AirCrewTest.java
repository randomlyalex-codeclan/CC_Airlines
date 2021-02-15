import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AirCrewTest {

    private AirCrew purser;
    private AirCrew flightAttendant;

    @Before
    public void before() {
        purser = new AirCrew("Bob", Rank.PURSER);
        flightAttendant = new AirCrew("Mark", Rank.FLIGHT_ATTENDANT);
    }

    @Test
    public void getName() {
        assertEquals("Bob", purser.getName());
    }

    @Test
    public void getRank() {
        assertEquals(Rank.FLIGHT_ATTENDANT, flightAttendant.getRank());
    }

    @Test
    public void relayMessage() {
        assertEquals(
                "Now we request your full attention as the flight attendants demonstrate the safety features of this aircraft",
                flightAttendant.relayMessage("Now we request your full attention as the flight attendants demonstrate the safety features of this aircraft")
        );
    }

    //Test that air crew can't be Pilots or First Officers?
}