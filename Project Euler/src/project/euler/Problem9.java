/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.euler;

import java.util.ArrayList;

/**
 *
 * @author Joshua
 */
public class Problem9 {
    
    final int NUMBER = 1000;

    void start() {
        ArrayList<Integer> triplet = getTriplet(NUMBER);
        int answer = getProduct(triplet);
        System.out.println(answer);
    }
    
    public ArrayList<Integer> getTriplet(int n){
        ArrayList<Integer> triplet = new ArrayList<Integer>();
        double a, b, c;
        for(int i = 1; i < n; i++){
            a = i;
            for(int j = 0; j < n; j++){
                b = j;
                c = Math.sqrt(a*a + b*b);
                System.out.println(a + "^2 + " + b + "^2 = " + c + "^2");
                if(testSum(a, b, c, n)){
                    System.out.println(a + ", " + b + ", " + c);
                    triplet.add(new Integer((int) a));
                    triplet.add(new Integer((int) b));
                    triplet.add(new Integer((int) c));
                    return triplet;
                }
            }
        }
        return triplet;
    }
    
    public boolean testSum(double a, double b, double c, int n){
        return (a + b + c == n);
    }
    
    public int getProduct(ArrayList<Integer> n){
        int product = 1;
        for(int i = 0; i < n.size(); i++){
            product = product * n.get(i);
        }
        return product;
    }
    
    public String toString(){
        return "Problem 9";
    }
}
