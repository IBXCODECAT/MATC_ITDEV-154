package com.schmitt.trafficlights;

/**
 * @author Nathan Schmitt Represents a traffic light controller with methods for
 * changing state and waiting for a green signal.
 */
public class TrafficLights {

    public static void main(String[] args) {
        // Create an array of traffic light controllers for the intersection
        TrafficLightsController[] intersectionControllers = {
            new TrafficLightsController("North"),
            new TrafficLightsController("South"),
            new TrafficLightsController("East"),
            new TrafficLightsController("West")
        };

        // Start regulatory loops for each traffic light controller
        for (int i = 0; i < intersectionControllers.length; i++) {
            TrafficLightsController currentController = intersectionControllers[i];
            TrafficLightsController nextController = intersectionControllers[(i + 1) % intersectionControllers.length];

            new Thread(new SystemRegulatoryLoop(currentController, nextController)).start();
        }

        // Start the supervisory process
        new Thread(new TrafficSupervisoryProcess(intersectionControllers)).start();

        // Keep the main thread alive
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
