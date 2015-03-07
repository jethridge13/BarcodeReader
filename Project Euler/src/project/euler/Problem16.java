/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.euler;

import java.math.BigInteger;

/**
 *
 * @author Joshua
 */
public class Problem16 {
    final int NUM = 1000;

    void start() {
        BigInteger two = new BigInteger("2");
        BigInteger number = two.pow(NUM);
        long answer = sumDigits(number);
        System.out.println("Answer: " + answer);
    }
    
    private long sumDigits(BigInteger b){
       String number = b.toString();
       long sum = 0;
       System.out.println(number);
       for(int i = 0; i < number.length(); i++){
           //System.out.println(number.charAt(i));
           String charThing = "";
           charThing += number.charAt(i);
           sum += Integer.valueOf(charThing);
           System.out.println(sum);
       }
       return sum;
    }
    
    public String toString(){
        return "Problem 16";
    }
}
