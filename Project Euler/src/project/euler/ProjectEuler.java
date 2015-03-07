/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.euler;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Joshua
 */
public class ProjectEuler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Problem16 test = new Problem16();
        System.out.println("Running " + test.toString());
        test.start();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        long endTime = System.currentTimeMillis();
        System.out.println("Program finished in " + formatter.format((endTime - startTime) / 1000d) + " seconds.");
    }
    
}
