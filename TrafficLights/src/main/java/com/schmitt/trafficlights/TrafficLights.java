package com.schmitt.trafficlights;

/**
 * @author Nathan Schmitt Represents a traffic light controller with methods for
 * changing state and waiting for a green signal.
 */
public class TrafficLights {

    public static void main(String[] args) {
        while (true) {
            // Create an array of traffic light controllers for the intersection
            TrafficLightsController[] intersectionControllers = {
                new TrafficLightsController("North"),
                new TrafficLightsController("South"),
                new TrafficLightsController("East"),
                new TrafficLightsController("West")
            };

            // Start regulatory loops for each traffic light controller
            for (int i = 0; i < intersectionControllers.length; i++) {
                TrafficLightsController ownController = intersectionControllers[i];
                TrafficLightsController otherController = intersectionControllers[(i + 2) % intersectionControllers.length];
                boolean isNSController = (i == 0 || i == 1); // N/S controllers have indices 0 and 1

                new Thread(new SystemRegulatoryLoop(ownController, otherController, isNSController)).start();
            }

            // Start the supervisory process
            new Thread(new TrafficSupervisoryProcess(intersectionControllers)).start();

            // Keep the main thread alive
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("No Change...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
