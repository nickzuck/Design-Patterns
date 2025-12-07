/*
You are required to design a elevator system, but since elevator can be in any state and operating accordingly ; write
a code to ensure the different states in which an elevator car can be in : IDLE, MAINTENANCE, GOING UP, GOING DOWN etc.

Solution : Since we need to put elevator in different state we will be using State Design Pattern
 */
import java.util.ArrayList ;
import java.util.PriorityQueue ;
import java.lang.Override ;

interface ElevatorState {
    void handleRequest(ElevatorCar car, int floor) ;
    void move(ElevatorCar car) ;
    String getCurrentState() ;
}

class IdleState implements ElevatorState{
    @Override
    public String getCurrentState() {
        return "IDLE" ;
    }

    @Override
    public void move(ElevatorCar car) {
        return ;
    }

    @java.lang.Override
    public void handleRequest(ElevatorCar car, int floor) {
        // We can check overload and maintenance state here before changing
        car.addStop(floor) ;
        if(floor > car.getCurrentFloor()){
            car.setState(new MovingUpState());
        } else {
            car.setState(new MovingDownState());
        }
    }
}

class MovingUpState implements ElevatorState{
    @Override
    public String getCurrentState() {
        return "MOVING UP";
    }

    @Override
    public void move(ElevatorCar car) {
        Integer nextFloor = car.peekNextUpStop() ;

        if(nextFloor != car.getCurrentFloor()){
            System.out.println("Moving one floor up") ;
            car.setCurrentFloor(car.getCurrentFloor() +1) ;
            move(car) ;
        } else {
            car.removeNextUpStop();
            System.out.println("At destination floor : " +  car.getCurrentFloor()) ;
            car.setState(new IdleState());
        }
    }

    @Override
    public void handleRequest(ElevatorCar car, int floor) {
       car.addStop(floor);
    }
}
// We can similarly add overload and maintenance state

class MovingDownState implements ElevatorState{
    @Override
    public String getCurrentState() {
        return "MOVING DOWN";
    }

    @Override
    public void move(ElevatorCar car) {
        Integer nextFloor = car.peekNextDownStop() ;

        if(nextFloor != car.getCurrentFloor()){
            System.out.println("Moving one floor down") ;
            car.setCurrentFloor(car.getCurrentFloor() -1) ;
            move(car) ;
        } else {
            car.removeNextDownStop();
            System.out.println("At destination floor : " +  car.getCurrentFloor()) ;
            car.setState(new IdleState());
        }
    }

    @Override
    public void handleRequest(ElevatorCar car, int floor) {
        car.addStop(floor);
    }
}

class ElevatorCar{
    private final int id ;
    private int currentFloor = 0 ;
    private ElevatorState state = new IdleState() ;

    private final PriorityQueue<Integer> upStops = new PriorityQueue<>();
    private final PriorityQueue<Integer> downStops =
            new PriorityQueue<>((a, b) -> b - a); // max heap

    private boolean isOverload = false;
    private boolean isMaintenance = false;

    public ElevatorCar(int id){
        this.id = id ;
    }

    public void setState(ElevatorState newState){
        this.state = newState;
    }

    public void addStop(int floor){
        if(floor > currentFloor){
            upStops.add(floor) ;
        } else {
            downStops.add(floor) ;
        }
    }

    public Integer peekNextUpStop() { return upStops.peek(); }
    public Integer peekNextDownStop() { return downStops.peek(); }
    public void removeNextUpStop() { upStops.poll(); }
    public void removeNextDownStop() { downStops.poll(); }

    public int getCurrentFloor(){return this.currentFloor;}
    public void setCurrentFloor(int floor){this.currentFloor = floor ; }

    // Calls
    public void handleRequest(int floor){
        state.handleRequest(this, floor);
    }

    public void move(){
        state.move(this) ;
    }

}

public class elevator_car_system{
    public static void main(String[] args){
        ElevatorCar car = new ElevatorCar(1) ;
        car.handleRequest(3);
        car.move() ;

        car.handleRequest(2);
        car.move();

        car.handleRequest(10);
        car.move();


        // Test for idle state
        car.move();
    }
}