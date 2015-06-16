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
public class Problem45 {

    void start() {
        int n = 1000000;
        EulerHelper helper = new EulerHelper();
        ArrayList tris = helper.generateTriangulars(n);
        ArrayList pents = helper.generatePentagonals(n);
        ArrayList hex = helper.generateHexagonals(n);
        ArrayList allThree = new ArrayList();
        for(int i = 0; i < n; i++){
            if(pents.contains(tris.get(i)) && hex.contains(tris.get(i))){
                allThree.add(tris.get(i));
                System.out.println(tris.get(i) + " has been added.");
            }
        }
        System.out.println(allThree);
    }
    
    public String toString(){
        return "Problem 45";
    }
}
