package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

class QuadraticEquationSolverTest {

    @Test
    void testCalculateRoots1() {
        double a, b, c, x;
        a = 0;
        b = 0;
        c = 7;
        Assertions.assertEquals("Нет решений (a = 0,b = 0,c != 0)", QuadraticEquationSolver.calculateRoots(a, b, c));
    }

    @Test
    void testCalculateRoots2() {
        double a, b, c, x;
        a = 0;
        b = 0;
        c = 0;
        Assertions.assertEquals("x - любое число", QuadraticEquationSolver.calculateRoots(a, b, c));
    }

    @Test
    void testCalculateRoots3() {
        double a, b, c, x;
        a = 0;
        b = 5;
        c = 6;
        x = -(c / b);
        Assertions.assertEquals(String.format("Один корень: %f\n", x), QuadraticEquationSolver.calculateRoots(a, b, c));
    }

    @Test
    void testCalculateRoots3_2() {
        double a, b, c, x;
        a = 1;
        b = 0;
        c = 0;
        x = 0;
        Assertions.assertEquals(String.format("Один корень: %f\n", x), QuadraticEquationSolver.calculateRoots(a, b, c));
    }

    @Test
    void testCalculateRoots4() {
        double a, b, c;
        a = 2;
        b = 3;
        c = 2;
        Assertions.assertEquals("Дискриминант меньше нуля\n", QuadraticEquationSolver.calculateRoots(a, b, c));
    }

    @Test
    void testCalculateRoots5() {
        double a, b, c, x;
        a = 2;
        c = 2;
        b = 4;
        x = (-b / (2 * a));
        Assertions.assertEquals(String.format("Один корень: %f\n", x), QuadraticEquationSolver.calculateRoots(a, b, c));
    }

    @Test
    void testCalculateRoots6() {
        double a, b, c, x;
        a = 1;
        c = 1;
        b = 4;
        double discriminant = sqrt(b * b - 4 * a * c);
        double x1 = (-b + discriminant) / (2 * a);
        double x2 = (-b - discriminant) / (2 * a);
        Assertions.assertEquals(String.format("Два корня: %f, %f\n", x1, x2), QuadraticEquationSolver.calculateRoots(a, b, c));
    }
}