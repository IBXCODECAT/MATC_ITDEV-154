/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.schmitt.trafficlights;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a traffic light controller with methods for changing state and waiting for a green signal.
 */
public class TrafficLightsController {
    private String name;
    private String state = "RED";
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * Initializes a TrafficLightController with the given name.
     *
     * @param name The name of the traffic light controller.
     */
    public TrafficLightsController(String name) {
        this.name = name;
    }

    /**
     * Changes the state of the traffic light controller and signals waiting threads.
     *
     * @param newState The new state to set.
     */
    public void changeState(String newState) {
        lock.lock();
        try {
            System.out.println(name + " Light: " + state + " -> " + newState);
            state = newState;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Waits until the traffic light is in the "GREEN" state.
     *
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    public void waitForGreen() throws InterruptedException {
        lock.lock();
        try {
            while (!state.equals("GREEN")) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the current state of the traffic light.
     *
     * @return The current state.
     */
    public String getState() {
        return state;
    }
}

/**
 * Represents a regulatory loop for a traffic light controller, controlling state transitions.
 */
class RegulatoryLoop implements Runnable {
    private TrafficLightsController controller;
    private int interArrivalRate;

    /**
     * Initializes a RegulatoryLoop with the given traffic light controller and inter-arrival rate.
     *
     * @param controller       The traffic light controller.
     * @param interArrivalRate The inter-arrival rate of cars.
     */
    public RegulatoryLoop(TrafficLightsController controller, int interArrivalRate) {
        this.controller = controller;
        this.interArrivalRate = interArrivalRate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(interArrivalRate);
                controller.changeState("GREEN");
                Thread.sleep(5000); // Green light duration
                controller.changeState("YELLOW");
                Thread.sleep(2000); // Yellow light duration
                controller.changeState("RED");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * Represents the supervisory process that checks synchronization conditions.
 */
class SupervisoryProcess implements Runnable {
    private TrafficLightsController[] controllers;

    /**
     * Initializes a SupervisoryProcess with an array of traffic light controllers.
     *
     * @param controllers The array of traffic light controllers.
     */
    public SupervisoryProcess(TrafficLightsController[] controllers) {
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