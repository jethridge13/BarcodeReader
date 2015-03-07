/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.euler;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Joshua
 */
public class Problem3 {
    public void start(){
        //System.out.println("Running...");
        final BigInteger theBigN = new BigInteger("600851475143");
        long theN = theBigN.longValue();
        //theN = 13195;
        ArrayList<Integer> factors = factors(theN);
        System.out.println(factors);
        ArrayList<Integer> primeFactors = narrowToPrimes(factors);
        System.out.println(primeFactors);
        System.out.println("The largest prime factor of " + theN + " is " + primeFactors.get(primeFactors.size() - 1));
    }
    private ArrayList factors(long n){
        ArrayList<Integer> factors = new ArrayList<Integer>();
        for(int i = 1; i < Math.sqrt(n); i++){
            if(n % i == 0){
                factors.add(new Integer(i));
            }
        }
        return factors;
    }
    private ArrayList<Integer> narrowToPrimes(ArrayList<Integer> n){
        for(int i = 0; i < n.size(); i++){
            if(!isPrime(n.get(i).intValue())){
                n.remove(i);
                i--;
            }
        }
        return n;
    }
    private boolean isPrime(long n){
        int factors = 0;
        for(int i = 1; i < Math.sqrt(n); i++){
            if(n % i == 0){
                factors++;
            }
        }
        if (factors < 2){
            return true;
        }
        else
            return false;
    }
    public String toString(){
        return "Problem 3";
    }
}
