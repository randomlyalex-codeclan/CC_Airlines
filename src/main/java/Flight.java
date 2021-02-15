import java.util.ArrayList;

public class Flight {
    private ArrayList<Pilot> pilots;
    private ArrayList<AirCrew> airCrew;
    private ArrayList<Passenger> bookedPassengers;
    private Plane plane;
    private String flightNumber;
    private String destinationAirport;
    private String departureAirport;
    private String departureTime;
    private static int ASSUMED_PASSENGER_WEIGHT = 90;

    public Flight(String flightNumber, String destinationAirport, String departureAirport, String departureTime){
        this.flightNumber = flightNumber;
        this.destinationAirport = destinationAirport;
        this.departureAirport = departureAirport;
        this.departureTime = departureTime;
        this.airCrew = new ArrayList<AirCrew>();
        this.bookedPassengers = new ArrayList<Passenger>();
        this.pilots = new ArrayList<Pilot>();
    }

    //Pilots

    public int[] addRemovePilot(Pilot pilot){
        int[] response = new int[2];
        if (this.pilots.contains(pilot))
        {
            this.pilots.remove(pilot);
            response[1] = -1;
        }
        else {
            this.pilots.add(pilot);
            response[1] = 1;
        }
        response[0] = this.pilots.size();
        return response;
    }

    public ArrayList<Pilot> getPilots(){
        return this.pilots;
    }

    //Planes

    public void assignPlane(Plane plane){
        this.plane = plane;
    }

    public Plane getPlane(){
        return this.plane;
    }


    //Aircrew

    public ArrayList<AirCrew> getAirCrew(){
        return this.airCrew;
    }

    public int[] addRemoveAirCrew(AirCrew airCrew){
        int[] response = new int[2];
        if (this.airCrew.contains(airCrew))
        {
            this.airCrew.remove(airCrew);
            response[1] = -1;
        }
        else {
            this.airCrew.add(airCrew);
            response[1] = 1;
        }
        response[0] = this.airCrew.size();
        return response;
    }

    public boolean removeAirCrew(AirCrew airCrew){
        boolean removed;
        removed = this.airCrew.contains(airCrew);
        this.airCrew.remove(airCrew);
        return removed;
    }

    //Passengers

    public ArrayList<Passenger> getBookedPassengers(){
        return this.bookedPassengers;
    }

    public int getBasicPerPassengerWeight(){
        int planeMaxWeight = this.plane.getPlaneModel().getMaxWeight();
        int planeCapacity = this.plane.getPlaneModel().getPassengerCapacity();
        int availableWeightPerPassenger = (planeMaxWeight / 2) / planeCapacity;
        return availableWeightPerPassenger;
    }

    public int remainingSeats(){
        int planeCapacity = this.plane.getPlaneModel().getPassengerCapacity();
        return planeCapacity - this.bookedPassengers.size();
    }

    public int remainingWeightForAllPassengers(){
        int planeMaxWeight;
        int totalBookedPassengerBagsWeight = 0;
        int totalAssumedPassengerWeight = 0;
        planeMaxWeight = getPlane().getPlaneModel().getMaxWeight();
        for (Passenger passenger : this.bookedPassengers){
            totalAssumedPassengerWeight += ASSUMED_PASSENGER_WEIGHT;
            totalBookedPassengerBagsWeight += passenger.getTotalBagsWeight();
        }
        return (planeMaxWeight / 2) - totalAssumedPassengerWeight - totalBookedPassengerBagsWeight;
    }

    public int[] addRemovePassenger(Passenger passenger){
        int[] response = new int[4];
        response[1] = 0;
        if (this.bookedPassengers.contains(passenger))
        {
            this.bookedPassengers.remove(passenger);
            response[1] = -1;
        }
        else {
            if (
                    ( remainingWeightForAllPassengers() > (passenger.getTotalBagsWeight() + ASSUMED_PASSENGER_WEIGHT) )
                            &&
                            (remainingSeats() > 0)
                            &&
                            passenger.getTotalBagsWeight() < this.getBasicPerPassengerWeight()
            )
            {
            this.bookedPassengers.add(passenger);
            passenger.addFlightDetails(this, this.findFreeSeatNumber());
            response[1] = 1;
            }
        }
        int totalBooked = this.bookedPassengers.size();
        response[0] = totalBooked;

        int planeCapactiy = getPlane().getPlaneModel().getPassengerCapacity();
        response[2] = planeCapactiy;

        int planeMaxPassengerWeight = getPlane().getPlaneModel().getMaxWeight() / 2;
        response[3] = planeMaxPassengerWeight;
        return response;

    }

    //Get Flight details

    public String getDepartureAirport(){
        return this.departureAirport;
    }

    public String getDepartureTime() {
        return this.departureTime;
    }

    public String getDestinationAirport() {
        return this.destinationAirport;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public int findFreeSeatNumber(){
        int filledSeats = this.getBookedPassengers().size();
        int planeCapcity = this.plane.getPlaneModel().getPassengerCapacity();
        int freeSeatsRemaining = planeCapcity - filledSeats;
        if (freeSeatsRemaining > 0) {
            int randomFreeSeat;
            ArrayList<Integer> bookedSeatNumbers = new ArrayList<>();
            for (Passenger passenger : this.bookedPassengers){
                bookedSeatNumbers.add(passenger.getSeatNumber());
            }
            do {randomFreeSeat = (int) (Math.random() * planeCapcity) + 1;}
            while (bookedSeatNumbers.contains(randomFreeSeat));
            return randomFreeSeat;
        }
        else return -1;
    }


    public boolean readyForTakeOff(){
        boolean readyForTakeOff = false;
        boolean captainOnboard = false;
        boolean purserOnbaord = false;
        for (Pilot pilot : this.pilots){
            if (pilot.getRank() == Rank.CAPTAIN){
                captainOnboard = true;
                pilot.flyPlane(this);  //does this really work?
                break;
            }
        }
        for (AirCrew aircrew : this.airCrew){
            if (aircrew.getRank() == Rank.PURSER){
                purserOnbaord = true;
                break;
            }
        }
        readyForTakeOff = captainOnboard && purserOnbaord;
        return readyForTakeOff;
    }
}
