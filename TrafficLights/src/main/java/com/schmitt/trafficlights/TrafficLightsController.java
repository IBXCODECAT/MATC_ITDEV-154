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