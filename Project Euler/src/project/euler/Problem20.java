/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.euler;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Joshua
 */
public class Problem20 {

    void start() {
        Scanner stdin = new Scanner(System.in);
        boolean isBadInput = true;
        System.out.println("Please enter the number: ");
        int number = stdin.nextInt();
        BigInteger product = genFact(number);
        System.out.println(number + "! = " + product);
        BigInteger sumDigits = sumDigits(product);
        System.out.println("The sum of the digits of " + number + "! = " + sumDigits);
    }

    private BigInteger genFact(int i) {
        if (i == 1) {
            return new BigInteger("1");
        }
        String integer = Integer.toString(i);
        BigInteger product = genFact(--i).multiply(new BigInteger(integer));
        return product;
    }

    private BigInteger sumDigits(BigInteger number) {
        BigInteger sum = new BigInteger("0");
        //System.out.println(number);
        String digits = number.toString();
        //System.out.println(digits);
        while (digits.length() > 0) {
            //System.out.println(digits);
            String digit = digits.substring(0, 1);
            //System.out.println(digit);
            if (digits.length() != 1) {
                digits = digits.substring(1);
            } else {
                digits = "";
            }
            sum = sum.add(new BigInteger(digit));
        }
        return sum;
    }

    public String toString() {
        return "Problem 20";
    }

}
