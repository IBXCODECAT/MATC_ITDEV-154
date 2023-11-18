package com.schmitt.trafficlights;

/**
 * Represents a regulatory loop for a traffic light controller, controlling state transitions.
 */
public class SystemRegulatoryLoop implements Runnable {
    private TrafficLightsController ownController;
    private TrafficLightsController otherController;
    private boolean isNSController;

    /**
     * Initializes a RegulatoryLoop with the given traffic light controllers.
     *
     * @param ownController  The traffic light controller for N/S or E/W direction.
     * @param otherController The traffic light controller for the opposite direction.
     * @param isNSController Whether this loop is for N/S (true) or E/W (false).
     */
    public SystemRegulatoryLoop(TrafficLightsController ownController, TrafficLightsController otherController, boolean isNSController) {
        this.ownController = ownController;
        this.otherController = otherController;
        this.isNSController = isNSController;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // N/S lights remain red while E/W does one cycle
                if (isNSController) {
                    System.out.println("N/S Controller wait for E/W green...");
                    ownController.waitForGreen();
                }

                // N/S Green - E/W Red
                ownController.changeState("GREEN");
                otherController.changeState("RED");
                Thread.sleep(5000); // Green light duration

                // N/S Yellow - E/W Red
                ownController.changeState("YELLOW");
                //otherController.changeState("RED");
                Thread.sleep(2000); // Yellow light duration

                // N/S Red - E/W Red
                ownController.changeState("RED");
                //otherController.changeState("RED");
                Thread.sleep(1000); // Red light duration

                // E/W lights remain red while N/S does one cycle
                if (!isNSController) {
                    System.out.println("E/W Controller Wait for N/S green...\n");
                //    otherController.changeState("GREEN");
                    ownController.waitForGreen();
                }

                // N/S Red - E/W Green
                ownController.changeState("RED");
                otherController.changeState("GREEN");
                System.out.println();
                Thread.sleep(5000); // Green light duration

                // N/S Red - E/W Yellow
                //ownController.changeState("RED");
                otherController.changeState("YELLOW");
                Thread.sleep(2000); // Yellow light duration

                // N/S Red - E/W Red
                //ownController.changeState("RED");
                otherController.changeState("RED");
                Thread.sleep(1000); // Red light duration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

