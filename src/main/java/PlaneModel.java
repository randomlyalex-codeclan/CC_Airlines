import java.util.ArrayList;

public enum PlaneModel {
    BOEING737(162, 70535),
    BOEING747(420, 362875),
    BOEING777(365, 263080),
    A320(179, 73500),
    A380(555, 560000),
    TEST_MODEL(3, 620); //How else would I test this, this seems wrong.

    private final int passengerCapacity;
    private final int maxTakeOffWeight;

    PlaneModel(int passengerCapacity, int maxTakeOffWeight){
        this.passengerCapacity = passengerCapacity;
        this.maxTakeOffWeight = maxTakeOffWeight;
    }

    public int getMaxWeight(){
        return this.maxTakeOffWeight;
    }

    public int getPassengerCapacity(){
        return this.passengerCapacity;
    }


}
