import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FlightTest {

    private Flight flightBAW1442;
    private Flight flightEZY482;
    private Plane plane;
    private Plane smallPlane;
    private Pilot captain;
    private Pilot captain2;
    private Pilot firstOfficer;
    private Pilot firstOfficer2;
    private AirCrew purser;
    private AirCrew purser2;
    private AirCrew flightAtt;
    private AirCrew flightAtt2;
    private Passenger passenger1;
    private Passenger passenger2;
    private Passenger passenger3;
    private Passenger passenger4;
    private Bag bag1;
    private Bag bag2;
    private Bag bag3;
    private Bag bag4;
    private Bag bag5;



    @Before
    public void setUp() {
        flightBAW1442 = new Flight("BAW1442", "EDI", "LHR", "09:46GMT");
        flightEZY482 = new Flight( "EZY482", "BFS", "EDI", "11:45GMT");  // Used to check passengers and baggage cals
        plane = new Plane (PlaneModel.A320, "A21N");
        smallPlane = new Plane(PlaneModel.TEST_MODEL, "TinyTest"); // Used to check passenger and baggage calcs
        captain = new Pilot ("Joe", Rank.CAPTAIN, "ABC123" );
        captain2 = new Pilot ("Max", Rank.CAPTAIN, "CAB321" );
        firstOfficer = new Pilot ("Mark", Rank.FIRST_OFFICER, "XYZ456" );
        firstOfficer2 = new Pilot ("Dan", Rank.FIRST_OFFICER, "ZYX654" );
        purser = new AirCrew("Jan", Rank.PURSER);
        purser2 = new AirCrew("Jill", Rank.PURSER);
        flightAtt = new AirCrew("Steve", Rank.FLIGHT_ATTENDANT);
        flightAtt2 = new AirCrew("David", Rank.FLIGHT_ATTENDANT);
        passenger1 = new Passenger("Ringo");
        passenger2 = new Passenger("Paul");
        passenger3 = new Passenger("John");
        passenger4 = new Passenger("George");
        bag1 = new Bag(5);
        bag2 = new Bag(10);
        bag3 = new Bag(20);
        bag4 = new Bag(35);
        bag5 = new Bag(200);
        flightEZY482.assignPlane(smallPlane);
        flightEZY482.addRemovePilot(captain);
        flightEZY482.addRemoveAirCrew(purser);
    }

    @Test
    public void testFlightCreatedWithNoPilot(){
        ArrayList<Pilot> pilots = flightBAW1442.getPilots();
        assertEquals(0, pilots.size());
    }

    @Test
    public void testFlightCreatedWithNoAirCrew(){
        ArrayList<AirCrew> airCrewList = flightBAW1442.getAirCrew();
        assertEquals(0, airCrewList.size());
    }

    @Test
    public void testFlightCreatedWithNoPassengers(){
        ArrayList<Passenger> passengers = flightBAW1442.getBookedPassengers();
        assertEquals(0, passengers.size());
    }

    //Pilot Checking

    @Test
    public void canAddPilot() {
        flightBAW1442.addRemovePilot(captain);
        assertEquals(1, flightBAW1442.getPilots().size());
    }

    @Test
    public void cantAddTheSamePilotTwice() {  //Same as removing test
        flightBAW1442.addRemovePilot(captain);
        flightBAW1442.addRemovePilot(firstOfficer);
        flightBAW1442.addRemovePilot(captain);
        assertEquals(1, flightBAW1442.getPilots().size());
    }

    @Test
    public void removeSecondPilotFromThree() { //Double check removing works explicitly
        flightBAW1442.addRemovePilot(captain);
        flightBAW1442.addRemovePilot(captain2);
        flightBAW1442.addRemovePilot(firstOfficer);
        flightBAW1442.addRemovePilot(captain2);
        assertTrue(flightBAW1442.getPilots().contains(captain) && flightBAW1442.getPilots().contains(firstOfficer));
    }



    @Test
    public void canAssignPlanetoFlight() {
        flightBAW1442.assignPlane(plane);
        assertEquals(plane, flightBAW1442.getPlane());
    }


    @Test
    public void canAddAirCrew() {
        flightBAW1442.addRemoveAirCrew(purser);
        assertEquals(1, flightBAW1442.getAirCrew().size());
    }

    @Test
    public void canAddRemoveAirCrew() {
        flightBAW1442.addRemoveAirCrew(purser);
        flightBAW1442.addRemoveAirCrew(flightAtt);
        flightBAW1442.addRemoveAirCrew(flightAtt2);
        flightBAW1442.addRemoveAirCrew(flightAtt);
        assertTrue( flightBAW1442.getAirCrew().contains(purser) && flightBAW1442.getAirCrew().contains(flightAtt2));
    }

    @Test
    public void cantFlyWithoutCaptainAndPurser() {
        flightBAW1442.addRemovePilot(firstOfficer);
        flightBAW1442.addRemoveAirCrew(flightAtt);
        assertEquals(false, flightBAW1442.readyForTakeOff());
    }

    @Test
    public void canFlyIfCaptainOnboardandPurse() {
        flightBAW1442.addRemovePilot(captain);
        flightBAW1442.addRemovePilot(firstOfficer);
        flightBAW1442.addRemoveAirCrew(purser);
        assertEquals(true, flightBAW1442.readyForTakeOff());
    }


    @Test
    public void addPassenger() {
        flightEZY482.addRemovePassenger(passenger1);
        assertEquals(1, flightEZY482.getBookedPassengers().size());
    }

    @Test
    public void addPassengerUpdatesFlightDetails() {
        flightEZY482.addRemovePassenger(passenger1);
        assertEquals(flightEZY482, passenger1.getFlightDetails());
    }

    @Test
    public void getBookedPassengerList() {
        flightEZY482.addRemovePassenger(passenger1);
        assertTrue(flightEZY482.getBookedPassengers().contains(passenger1));
    }

    @Test
    public void removePassenger() {
        flightEZY482.addRemovePassenger(passenger1);
        flightEZY482.addRemovePassenger(passenger2);
        flightEZY482.addRemovePassenger(passenger1);
        assertEquals(1, flightEZY482.getBookedPassengers().size());
    }

    @Test
    public void cantAddPassengerToFullCapacityPlane(){
        flightEZY482.addRemovePassenger(passenger1);
        flightEZY482.addRemovePassenger(passenger2);
        flightEZY482.addRemovePassenger(passenger3);
        flightEZY482.addRemovePassenger(passenger4);
        assertEquals(3, flightEZY482.getBookedPassengers().size());
    }

    @Test
    public void addPassengerToFullPlaneReturnsZeroAddItem(){
        int[] response;
        flightEZY482.addRemovePassenger(passenger1);
        flightEZY482.addRemovePassenger(passenger2);
        flightEZY482.addRemovePassenger(passenger3);
        response = flightEZY482.addRemovePassenger(passenger4);
        assertEquals(0,response[1]);
    }

    @Test
    public void cantAddPassengerIfPlaneWillBecomeOverweight(){
        int[] response;
        flightEZY482.addRemovePassenger(passenger2);
        flightEZY482.addRemovePassenger(passenger3);
        passenger2.addRemoveBag(bag4);
        passenger2.addRemoveBag(bag1);
        passenger3.addRemoveBag(bag2);
        response = flightEZY482.addRemovePassenger(passenger4);
        assertEquals(0,response[1]);
    }

    @Test
    public void cantAddOverweightedPassengerEvenIfThereIsRoom(){
        int[] response;
        passenger4.addRemoveBag(bag1);
        passenger4.addRemoveBag(bag2);
        passenger4.addRemoveBag(bag3);
        passenger4.addRemoveBag(bag4);
        passenger4.addRemoveBag(bag5);
        response = flightEZY482.addRemovePassenger(passenger4);
        assertEquals(0,response[1]);
    }

    @Test
    public void returnNegativeOnewhenNoFreeSeats(){
        ArrayList<Passenger> testAllPassengers = new ArrayList<Passenger>();
        for (int i=1 ; i < 200 ;i++){
            String name = String.valueOf(i);
            testAllPassengers.add(new Passenger(name));
        }
        flightBAW1442.assignPlane(plane);
        int i = 1;
        for (Passenger passenger : testAllPassengers){
            flightBAW1442.addRemovePassenger(passenger);
            if (i % 8 == 0){System.out.println(passenger.getSeatNumber() + ", ");}
            else{System.out.print(passenger.getSeatNumber() + ", ");}
            if (i % 4 == 0) System.out.print( " | ");
            i++;
        }
        assertEquals(-1,flightBAW1442.findFreeSeatNumber());
    }

    @Test
    public void returnAFreeNumberWhenSearsAreAvailable(){
        ArrayList<Passenger> testAllPassengers = new ArrayList<Passenger>();
        for (int i=1 ; i < 100 ;i++){
            String name = String.valueOf(i);
            testAllPassengers.add(new Passenger(name));
        }
        flightBAW1442.assignPlane(plane);
        int i = 1;
        for (Passenger passenger : testAllPassengers){
            flightBAW1442.addRemovePassenger(passenger);
            if (i % 8 == 0){System.out.println(passenger.getSeatNumber() + ", ");}
            else{System.out.print(passenger.getSeatNumber() + ", ");}
            if (i % 4 == 0) System.out.print( " | ");
            i++;
        }
        assertTrue(flightBAW1442.findFreeSeatNumber() != -1);
    }


}