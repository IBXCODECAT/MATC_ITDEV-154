package com.schmitt.trafficlights;

/**
 * Represents a regulatory loop for a traffic light controller, controlling state transitions.
 */
public class SystemRegulatoryLoop implements Runnable {
    private final TrafficLightsController northSouthController;
    private final TrafficLightsController eastWestController;

    /**
     * Initializes a RegulatoryLoop with the given traffic light controllers.
     *
     * @param northSouthController The traffic light controller for N/S direction.
     * @param eastWestController   The traffic light controller for E/W direction.
     */
    public SystemRegulatoryLoop(TrafficLightsController northSouthController, TrafficLightsController eastWestController) {
        this.northSouthController = northSouthController;
        this.eastWestController = eastWestController;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // N/S Green - E/W Red
                northSouthController.changeState("GREEN");
                eastWestController.changeState("RED");
                Thread.sleep(5000); // Green light duration

                // N/S Yellow - E/W Red
                northSouthController.changeState("YELLOW");
                Thread.sleep(2000); // Yellow light duration

                // N/S Red - E/W Red
                northSouthController.changeState("RED");
                Thread.sleep(1000); // Red light duration

                // N/S Red - E/W Green
                eastWestController.changeState("GREEN");
                Thread.sleep(5000); // Green light duration

                // N/S Red - E/W Yellow
                eastWestController.changeState("YELLOW");
                Thread.sleep(2000); // Yellow light duration

                // N/S Red - E/W Red
                eastWestController.changeState("RED");
                Thread.sleep(1000); // Red light duration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

