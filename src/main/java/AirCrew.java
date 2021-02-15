public class AirCrew {
    private String name;
    private Rank rank;

    public AirCrew(String name, Rank rank){
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return this.name;
    }

    public Rank getRank() {
        return this.rank;
    }

    public String relayMessage(String message){
        System.out.println(message);
        return message;
    }

}
