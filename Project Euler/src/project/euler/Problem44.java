package project.euler;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Joshua
 */
public class Problem44 {

    void start() {
        Scanner stdin = new Scanner(System.in);
        System.out.print("Generate pentagonal numbers: ");
        int n = stdin.nextInt();
        ArrayList pentN = generatePentagonalNumbers(n);
        System.out.println(pentN);
        int answer = specialPentagonal(pentN);
        System.out.println(answer);
    }

    private ArrayList generatePentagonalNumbers(int n) {
        ArrayList pentN = new ArrayList();
        for (int i = 1; i < n + 1; i++) {
            int pN = 3 * i;
            --pN;
            pN *= i;
            pN /= 2;
            pentN.add(pN);
        }
        return pentN;
    }

    private int specialPentagonal(ArrayList list) {
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                int a = (int) list.get(i);
                int b = (int) list.get(j);
                int sum = a + b;
                int diff = Math.abs(a - b);
                if (a != b && diff < answer) {
                    if (list.contains(sum) && list.contains(diff)) {
                        System.out.println("Number found!");
                        answer = diff;
                    }
                }
            }
            if(i == list.size() * .1){
                System.out.println("10 percent...");
            }
            if(i == list.size() * .2){
                System.out.println("20 percent...");
            }
            if(i == list.size() * .3){
                System.out.println("30 percent...");
            }
            if(i == list.size() * .4){
                System.out.println("40 percent...");
            }
            if(i == list.size() * .5){
                System.out.println("50 percent...");
            }
            if(i == list.size() * .6){
                System.out.println("60 percent...");
            }
            if(i == list.size() * .7){
                System.out.println("70 percent...");
            }
            if(i == list.size() * .8){
                System.out.println("80 percent...");
            }
            if(i == list.size() * .9){
                System.out.println("90 percent...");
            }
        }
        return answer;
    }

    public String toString() {
        return "Problem 44";
    }
}
