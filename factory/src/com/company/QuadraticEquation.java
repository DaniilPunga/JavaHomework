package com.company;

import java.io.FileWriter;

public class QuadraticEquation {

    public static void main(String[] args) {
        int filePoolSize = Runtime.getRuntime().availableProcessors();
        int threadPoolSize = Runtime.getRuntime().availableProcessors();
        if (args.length == 2) {
            filePoolSize = Integer.parseInt(args[0]);
            threadPoolSize = Integer.parseInt(args[1]);
        }
        int waitingTimeInMillis = 1000;
        int start, end;
        int number_of_eq = 10000;
        int coefficient_range = 20000;
        double[] coefficientA = new double[number_of_eq];
        double[] coefficientB = new double[number_of_eq];
        double[] coefficientC = new double[number_of_eq];
        for (int i = 0; i < number_of_eq; i++) {
            coefficientA[i] = (Math.random() * (coefficient_range + 1)) - (coefficient_range / 2);
            coefficientB[i] = (Math.random() * (coefficient_range + 1)) - (coefficient_range / 2);
            coefficientC[i] = (Math.random() * (coefficient_range + 1)) - (coefficient_range / 2);
        }

        ResourcePool<Thread> threadPool = new ResourcePool<>(threadPoolSize, waitingTimeInMillis, new ThreadFactory());
        ResourcePool<FileWriter> filePool = new ResourcePool<>(filePoolSize, waitingTimeInMillis, new FileFactory());

        for (int i = 0; i < filePoolSize; i++) {
            start = (i * number_of_eq) / filePoolSize;
            end = ((i + 1) * number_of_eq) / filePoolSize;
            QuadraticEquationSolver solverThread = new QuadraticEquationSolver(coefficientA, coefficientB, coefficientC, start, end, filePool, threadPool);
            solverThread.execute(solverThread);
        }
        threadPool.shutdown();
    }
}
