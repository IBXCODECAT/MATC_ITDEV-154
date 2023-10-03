/** * title: Code Timer Demo
 * author: Nathan
 * date: 02.02.2020
 * version: 0.1.0
 */
package com.schmitt.codetimer;

import java.util.*;

public class Codetimer {

    public static void main(String[] args) {
            
        
        int executionBatchSize = 1;
        int maxExecutions = 1000;

        for (int i = 0; i < maxExecutions; i += executionBatchSize) {
            long begin, end, etime;
            int cnt = i; // # iterations

            try (Formatter fmt = new Formatter()) {
                begin = System.nanoTime(); // start time
                f(cnt); // synthetic bit of "work"
                end = System.nanoTime(); // stop time
                etime = end - begin; // elapsed time (sec)
                
                System.out.println(etime);
                
                //fmt.format("\n%,d steps requires ~%,d ns (or ~%.2g ns/step)\n",
                //        cnt, etime, (double) etime / cnt);
                
                //System.out.println(fmt);
            }
        }
    }

    /*
    // mock "work" 
    static long f(long n) {
        if (n <= 1) 
            return n;
        return f(n - 50) + f(n - 20);
    }*/
    
    static List<Integer> f(int n) {
        boolean[] isPrime = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();

        // Initialize all numbers as prime
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        // Mark non-prime numbers
        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        // Collect prime numbers
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }
}
