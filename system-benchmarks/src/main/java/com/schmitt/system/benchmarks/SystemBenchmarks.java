/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.schmitt.system.benchmarks;

import java.util.Formatter;
import java.text.DecimalFormat;

/**
 * @author Nathan Schmitt
 */
public class SystemBenchmarks {

    public static void main(String[] args) {

        // Initialize variables to store timing information
        long beginTime, endTime, elapsedTime;

        // Define a constant variable 'cnt' with value 1, this is our iteractions
        final int cnt = 1;

        // Use try-with-resources to automatically close the Formatter after its use
        try (Formatter fnt = new Formatter()) 
        {
            // Record the starting time using nanoTime for precise timing
            beginTime = System.nanoTime();

            // Loop 'cnt' number of times
            for (int i = 0; i < cnt; i++) {
                // Get the number of CPU cores available
                int cores = Runtime.getRuntime().availableProcessors();

                // Get the maximum memory allowed by the runtime
                long totalMemoryAllowed = Runtime.getRuntime().maxMemory();

                // Get the amount of free memory available in the runtime
                long freeMemory = Runtime.getRuntime().freeMemory();

                // Calculate the used memory by subtracting free memory from total memory allowed
                long used = totalMemoryAllowed - freeMemory;

                // Create a DecimalFormat object to format decimal numbers with three decimal places
                DecimalFormat df = new DecimalFormat(".###");

                // Print information about CPU cores, total memory, free memory, and used memory
                System.out.println("CPU Cores\t" + df.format(cores));
                System.out.println("Total Memory\t" + df.format(totalMemoryAllowed));
                System.out.println("Free Memory\t" + df.format(freeMemory));
                System.out.println("Used Memory\t" + df.format(used));
            }

            // Record the ending time using nanoTime
            endTime = System.nanoTime();

            // Calculate the elapsed time by subtracting the starting time from the ending time
            elapsedTime = endTime - beginTime;

            // Use the Formatter to format and append a message to the output
            fnt.format(
                    "\n%,d steps requires ~%,d ns (or ~%.2g ns/stop)\n",
                    cnt, elapsedTime, (double) elapsedTime / cnt);

            // Print the formatted message from the Formatter
            System.out.println(fnt);
            
        }
        // The try-with-resources block will automatically close the Formatter after this point due to socpe limit
    }
}
