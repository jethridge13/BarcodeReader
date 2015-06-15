/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.euler;

import java.util.ArrayList;
import java.util.Collections;

/**     NOT FINISHED
 *
 * 
 */
public class Problem21 {

    void start() {
        ArrayList sumOfDivisors = generateDivisorArray(10000);
        //System.out.println(sumOfDivisors.get(220));
        ArrayList amicableNumbers = generateAmicableNumbers(sumOfDivisors);
        System.out.println("Amicable numbers: " + amicableNumbers);
        int sum = 0;
        for (int i = 0; i < amicableNumbers.size(); i++) {
            sum += (int) amicableNumbers.get(i);
        }
        System.out.println(sum);
    }

    private ArrayList generateDivisorArray(int n) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < n; i++) {
            int sumOfDivisors = sumOfDivisors(i);
            list.add(sumOfDivisors);
        }
        //System.out.println(list);
        return list;
    }

    private int sumOfDivisors(int n) {
        int sum = 0;
        for (int i = 1; i < (n / 2) + 1; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum;
    }

    private ArrayList generateAmicableNumbers(ArrayList divisors) {
        ArrayList amicableNumbers = new ArrayList();
        for (int i = 0; i < divisors.size(); i++) {
            try {
                int divisor = (int) divisors.get(i);    //divisor: d(a) = b
                int check = (int) divisors.get(divisor);    //divisor: d(b) = a
                if ((check != divisor)) {                     //a != b
                    if(!amicableNumbers.contains(check))
                        amicableNumbers.add(check);
                    if(!amicableNumbers.contains(divisor))
                        amicableNumbers.add(divisor);
                }
            } catch (IndexOutOfBoundsException e) {

            }
        }

        Collections.sort(amicableNumbers);
        return amicableNumbers;
    }

    public String toString() {
        return "Problem 21";
    }
}
