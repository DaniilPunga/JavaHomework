package com.company;

import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Math.*;

public class QuadraticEquationSolver implements Runnable {
    double[] coefficientA;
    double[] coefficientB;
    double[] coefficientC;
    int start, end;
    ResourcePool<FileWriter> filePool;
    ResourcePool<Thread> threadPool;

    public QuadraticEquationSolver(double[] a, double[] b, double[] c, int  i, int number_of_eq,int filePoolSize, ResourcePool<FileWriter> filePool, ResourcePool<Thread> threadPool) {
        coefficientA = a;
        coefficientB = b;
        coefficientC = c;
        start = (i * number_of_eq) / filePoolSize;
        end = ((i + 1) * number_of_eq) / filePoolSize;
        this.filePool = filePool;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        try (FileWriter logWriter = filePool.takeObject()) {
            for (int i = start; i < end; i++) {
                logWriter.write(calculateRoots(coefficientA[i], coefficientB[i], coefficientC[i]));
            }
            filePool.dropRes(logWriter);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void startSolve() {
        Thread worker = threadPool.takeObject();
        this.run();
        threadPool.dropRes(worker);
    }


    static String calculateRoots(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return "Дискриминант меньше нуля\n";
        }
        if (a == 0) {
            double x;
            if (discriminant != 0) {
                x = -(c / b);
                return String.format("Один корень: %f\n", x);
            } else {
                if (c == 0) {
                    return "x - любое число";
                } else {
                    return "Нет решений (a = 0,b = 0,c != 0)";
                }
            }
        }
        if (discriminant == 0) {
            double x;
            if (b != 0) {
                x = (-b / (2 * a));
            } else {
                x = 0;
            }
            return String.format("Один корень: %f\n", x);
        }
        discriminant = sqrt(discriminant);
        double x1 = (-b + discriminant) / (2 * a);
        double x2 = (-b - discriminant) / (2 * a);
        return String.format("Два корня: %f, %f\n", x1, x2);
    }
}