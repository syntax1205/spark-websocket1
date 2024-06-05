import java.time.LocalDate;

public class Rental {
    private Car car;
    private Customer customer;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    public Rental(Car car, Customer customer, LocalDate rentalDate){
        this.car = car;
        this.customer = customer;
        this.rentalDate = rentalDate;
    }

    public String rentalDate() {
        return rentalDate.toString();
    }
}
