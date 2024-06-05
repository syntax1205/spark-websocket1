public class Car {
    private String licensePlate;
    private String make;
    private boolean isAvailable;
    private int speed;
    public void rentOut() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }

    public Car(String licensePlate){
        this.licensePlate = licensePlate;
    }
    public String licensePlate() {
        return licensePlate;
    }
    public int speed(){
        return speed;
    }
    public void setSpeed(int mSpeed){
        speed = mSpeed;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
}
