/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.euler;

/**
 *
 * @author Joshua
 */
public class Problem4 {
    void start() {
        final int numberToStart = 999;
        for(int i = numberToStart; i > 100; i--){
            for(int j = numberToStart; j > 100; j--){
                int product = i * j;
                if(isPalindrome(product)){
                    System.out.println(product + " = " + i + " * " + j);
                    break;
                }
            }
        }
    }
    private boolean isPalindrome(int n){
        StringBuilder number = new StringBuilder();
        number.append(n);
        String numberString = number.toString();
        String numberReversed = number.reverse().toString();
        //System.out.println(numberString);
        //System.out.println(numberReversed);
        if(numberReversed.compareTo(numberString) == 0){
            return true;
        }
        else{
            return false;
        }
    }
    public String toString(){
        return "Problem 4";
    }
}
