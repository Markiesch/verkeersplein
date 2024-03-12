package vehicles;

import javafx.animation.AnimationTimer;
import movement.Waypoint;
import movement.WaypointManager;

import java.util.ArrayList;
import java.util.List;

public final class VehicleManager {
    private final List<Vehicle> vehicles = new ArrayList<>();

    private VehicleManager() {
        AnimationTimer anim = new AnimationTimer() {
            @Override
            public void handle(long now) {
                vehicles.forEach(Vehicle::update);
            }
        };

        anim.start();
    }

    public Vehicle spawnVehicle(Class<? extends Vehicle> clazz) throws Exception {
        Vehicle vehicle = clazz.getConstructor(Waypoint.class).newInstance(WaypointManager.getInstance().getRandomStartingWaypoint());
        vehicles.add(vehicle);
        return vehicle;
    }

    public static VehicleManager getInstance() {
        return VehicleManagerHolder.INSTANCE;
    }

    private static class VehicleManagerHolder {
        private static final VehicleManager INSTANCE = new VehicleManager();
    }
}
