package allocation;
import java.util.*;
import Vehicle.*;
public class EVAllocationSystem {

    public ArrayList<Vehicle> vehicleList;

    public EVAllocationSystem() {
        vehicleList = new ArrayList<Vehicle>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    public  ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }
}
