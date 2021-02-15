import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassengerTest {

    private Passenger passenger;
    private Bag bag;
    private Bag bigBag;
    private Bag biggestBag;
    private ArrayList<Bag> bags;
    private Flight flightEZY482;
    private Plane plane;
    private LocalDateTime timeEZY482;


    @Before
    public void before(){
        timeEZY482 = LocalDateTime.of(2021, 02, 15, 11, 45, 00);
        flightEZY482 = new Flight( "EZY482", "BFS", "EDI", timeEZY482);
        plane = new Plane(PlaneModel.A320, "N475UA");
        flightEZY482.assignPlane(plane);
        bag = new Bag(20);
        bigBag = new Bag(45);
        biggestBag = new Bag(260);
        passenger = new Passenger("Joe");
        passenger.addFlightDetails(flightEZY482, 100);

    }

    @Test
    public void getName() {
        assertEquals("Joe", passenger.getName());
    }

    @Test
    public void getBagCountStartsZero() {
        assertEquals(0, passenger.getBagCount());
    }

    @Test
    public void canAddBag() {
        passenger.addRemoveBag(bag);
        assertEquals(1, passenger.getBagCount());
    }

    @Test
    public void canAddRemoveBag() {
        passenger.addRemoveBag(bag);
        passenger.addRemoveBag(bigBag);
        passenger.addRemoveBag(bag);
        assertEquals(1, passenger.getBagCount());
    }

    @Test
    public void cantAddMoreThanPerPassengerWeightInBags(){
        String response;
        passenger.addRemoveBag(bag);
        passenger.addRemoveBag(bigBag);
        response = passenger.addRemoveBag(biggestBag);
        assertEquals("Baggage Over Allowance",response);
    }

    @Test
    public void getTotalBagsWeightOneBag() {
        passenger.addRemoveBag(bag);
        assertEquals(20, passenger.getTotalBagsWeight());
    }

    @Test
    public void getTotalBagsWeightTwoBags() {
        passenger.addRemoveBag(bag);
        passenger.addRemoveBag(bigBag);
        assertEquals(65, passenger.getTotalBagsWeight());
    }

}