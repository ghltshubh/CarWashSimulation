class Car {
    private int arrivalTime;
    private int waitingTime;
    private int departureTime;

    public Car(int arrivalTime, int waitingTime, int departureTime) {
        this.arrivalTime = arrivalTime;
        this.waitingTime = waitingTime;
        this.departureTime = departureTime;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }


    public int getArrivalTime() {
        return arrivalTime;
    }
}