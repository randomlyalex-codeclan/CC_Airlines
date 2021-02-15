public class Pilot extends AirCrew{
    private String licenseNumber;

    public Pilot(String name, Rank rank, String licenseNumber){
        super(name, rank);
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String flyPlane(Flight flight){
        String takeOff;
        if (this.getRank() == Rank.CAPTAIN){
        takeOff = "This is your captain "
                + getName()
                + " speaking, we will be departing at "
                + flight.getDepartureTime()
                + " to "
                + flight.getDestinationAirport()
                + " thank you for flying CC Airlines";
        }
        else takeOff = "Waiting for the Captain";

        System.out.println(takeOff);
        return takeOff;
    }

    //Test that it is the Captain before flying the plane?

    //Test that Pilots crew be Pursers or Attendants??
}
