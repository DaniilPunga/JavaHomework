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
        double[] firstCoefficient = new double[number_of_eq];
        double[] secondCoefficient = new double[number_of_eq];
        double[] thirdCoefficient = new double[number_of_eq];
        for (int i = 0; i < number_of_eq; i++) {
            firstCoefficient[i] = (Math.random() * (coefficient_range + 1)) - (coefficient_range / 2);
            secondCoefficient[i] = (Math.random() * (coefficient_range + 1)) - (coefficient_range / 2);
            thirdCoefficient[i] = (Math.random() * (coefficient_range + 1)) - (coefficient_range / 2);
        }

        ResourcePool<Thread> threadPool = new ResourcePool<>(threadPoolSize, waitingTimeInMillis, new ThreadFactory());
        ResourcePool<FileWriter> filePool = new ResourcePool<>(filePoolSize, waitingTimeInMillis, new FileFactory());

        for (int i = 0; i < filePoolSize; i++) {
            start = (i * number_of_eq) / filePoolSize;
            end = ((i + 1) * number_of_eq) / filePoolSize;
            QuadraticEquationSolver solverThread = new QuadraticEquationSolver(firstCoefficient, secondCoefficient, thirdCoefficient, start, end, filePool, threadPool);
            solverThread.startSolve();
        }
        threadPool.shutdown();
    }
}
