import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarRentalSystem {
    private List<Car> cars;
    private List<Rental> rentals;

    public CarRentalSystem(){
        cars = Collections.synchronizedList(new ArrayList<>());
        rentals = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public Rental rentCar(String licensePlate, Customer customer, LocalDate rentalDate){
        Car car = findAvailableCar(licensePlate);
        if(car != null){
            Rental rental = new Rental(car, customer, rentalDate);
            rentals.add(rental);
            return rental;
        }
        return null;
    }
    private Car findAvailableCar(String licensePlate){
        return cars.stream()
                .filter(c -> c.licensePlate().equals(licensePlate) && c.isAvailable())
                .findFirst().orElse(null);
    }
}
