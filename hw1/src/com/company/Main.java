package com.company;

import java.io.*;


import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static Long checkNumberEntering(String sumLine) throws IOException {
        try {
            long sum = Long.parseLong(sumLine);
            if (sum < 0) {
                throw new IllegalArgumentException("Value is negative, please input only positive integers");
            } else if (sum == 0) {
                throw new IllegalArgumentException("Value equals to 0, please input only positive integers");
            }
            return sum;
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("This is not an integer, please input only positive integers");
        }
    }

    public static ArrayList<Long> checkcoinsEntering(String coinsLine, Long sum) throws IOException {
        String[] coinStrings = coinsLine.split(" ");
        ArrayList<Long> allNominals = new ArrayList<>();
        for (String coinsString : coinStrings) {
            checkNumberEntering(coinsString);
            long i = Long.parseLong(coinsString);
            if (i < 0 || i > sum) {
                throw new IllegalArgumentException("Coins must be more than 0 and less than sum");
            }
            if (allNominals.contains(i)) {
                // обработка дублирования
            } else {
                allNominals.add(i);
            }

        }
        return allNominals;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите сумму и нажмите enter");
        String sumLine = br.readLine();
        Long sum = checkNumberEntering(sumLine);
        System.out.println("Введите купюры через пробел");
        String coinsLine = br.readLine();
        ArrayList<Long> coins = checkcoinsEntering(coinsLine, sum);
        HashMap<Long, Long> result1 = new HashMap<>();
        long g = Change.count(sum, coins, result1, 0, 0);
        System.out.println("Всего уникальных комбинаций: " + g);
    }
}
