public class Plane {
    private PlaneModel planeModel;
    private String registration;

    public Plane(PlaneModel planeModel, String registration){
        this.planeModel = planeModel;
        this.registration = registration;
    }

    public PlaneModel getPlaneModel() {
        return this.planeModel;
    }

    public String getRegistration() {
        return this.registration;
    }
}
