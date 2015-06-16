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
public class ProblemTest {

    void start() {
        EulerHelper helper = new EulerHelper();
        ArrayList triangles = helper.generateTriangulars(10);
        ArrayList pentagonals = helper.generatePentagonals(10);
        ArrayList hexagonals = helper.generateHexagonals(10);
        System.out.println(triangles);
        System.out.println(pentagonals);
        System.out.println(hexagonals);
    }
    
    public String toString(){
        return "Problem Test";
    }
}
