/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.schmitt.trafficlights;

/**
 * Represents the supervisory process that checks synchronization conditions.
 */
public class TrafficSupervisoryProcess implements Runnable {

    private final TrafficLightsController[] controllers;

    /**
     * Initializes a SupervisoryProcess with an array of traffic light
     * controllers.
     *
     * @param controllers The array of traffic light controllers.
     */
    public TrafficSupervisoryProcess(TrafficLightsController[] controllers) {
        this.controllers = controllers;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Synchronization condition, e.g., all lights are RED
                boolean allRed = true;
                for (TrafficLightsController controller : controllers) {
                    if (!controller.getState().equals("RED")) {
                        allRed = false;
                        break;
                    }
                }

                if (allRed) {
                    System.out.println("Supervisory Controller: Synchronize");
                    // Perform synchronization actions if needed

                    
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
