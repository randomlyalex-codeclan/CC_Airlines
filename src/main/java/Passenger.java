import java.util.ArrayList;

public class Passenger {

    private String name;
    private ArrayList<Bag> bags;
    private int seatNumber;
    private Flight bookedFlight;

    public Passenger(String name){
        this.name = name;
        this.bags = new ArrayList<Bag>();
        this.bookedFlight = null;
    }

    public String getName(){
        return this.name;
    }
    public int getBagCount(){
        return this.bags.size();
    }
    public int getTotalBagsWeight(){
        int totalBagWeight = 0;
        for (Bag bag : this.bags){
            totalBagWeight += bag.getWeight();
        }
        return totalBagWeight;
    }
    public String addRemoveBag(Bag bag){ //I don't like how i use a string return to inform, but i dont know any other way.
        if (this.bookedFlight != null)
        {
            if (this.bags.contains(bag))
            {
                this.bags.remove(bag);
                return "Bag Removed";
            }
            else
                {
                    if ((this.getTotalBagsWeight() + bag.getWeight())  < this.bookedFlight.getBasicPerPassengerWeight())
                    {
                        this.bags.add(bag);
                        return "Bag Added";
                    }
                    else return "Baggage Over Allowance";

                }
        }
        else
            if (this.bags.contains(bag))
            {
                this.bags.remove(bag);
                return "Bag Removed";
            }
            else
                {
                    this.bags.add(bag);
                    return "Bag Added Unknown Limit";
                }
    }


    public void addFlightDetails(Flight flight, int seatNumber) {
        this.bookedFlight = flight;
        this.seatNumber = seatNumber;
    }

    public Flight getFlightDetails(){
        return this.bookedFlight;
    }

    public Integer getSeatNumber(){
        return this.seatNumber;
    }
}
