import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CarWash {
    private int systemClock = 0;
    private int previousDeparture = 0;
    private CarWashQ<Car> carWashQ = new CarWashQ<>(6);
    private List<Car> cars = new LinkedList<>();
    private Scanner in;

    private void inputArrivalTime() {
        System.out.print("Input arrival time or press 'q' to quit: ");
        in = new Scanner(System.in);
    }

    public void run() throws Exception {
        while (true) {
            inputArrivalTime();
            if (in.hasNextInt()) {
                processArrival();
            } else if (in.next().equals("q")) {
                averageWaitTime();
                break;
            } else System.out.println("INVALID INPUT !!! TRY AGAIN.\n");
        }
    }

    private void processArrival() throws Exception {
        int arrival = in.nextInt();
        if (arrival < systemClock) {
            throw new Exception("Arrival time can not be less then " + systemClock);
        }
        while (!carWashQ.is_empty() && carWashQ.peek().getDepartureTime() <= arrival) {
            processDeparture();
        }
        Car car;
        if (carWashQ.is_empty()) {
            previousDeparture = arrival + 10;
            car = new Car(arrival, 0, arrival + 10);
            System.out.print("Processing arrival @ " + arrival + " min. ");
            carWashQ.add(car);
            System.out.println("Car added to car wash.\n");
        } else if (!carWashQ.is_full()) {
            car = new Car(arrival, previousDeparture - arrival, previousDeparture + 10);
            System.out.print("Processing arrival @ " + arrival + " min. ");
            carWashQ.add(car);
            System.out.println("Car added to carQ.\n");
        } else System.out.println("Car Wash Queue is full. Arrival not processed @ " + arrival + " min. Try an arrival\ntime greater than or equal to " + carWashQ.peek().getDepartureTime() + " min\n");
        previousDeparture = previousDeparture + 10;
        systemClock = arrival;
    }

    private void processDeparture() {
        Car car = carWashQ.remove();
        System.out.println("\nCar wash complete. Processing departure @ " + car.getDepartureTime() + " min");
        cars.add(car);
        systemClock = car.getDepartureTime();
    }

    private void averageWaitTime() {
        while (!carWashQ.is_empty()) {
            processDeparture();
        }
        double average = 0;
        for (Car car1 : cars) {
            average += car1.getWaitingTime();
        }
        if (cars.size() == 0) {
            System.out.println("No cars to process.");
            return;
        }
        System.out.printf("\nAverage waiting time is %.2f min %n", average/cars.size());
    }
}