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
public class EulerHelper {

    /**
     * Generates n triangular numbers
     *
     * @param n The number of triangular numbers to generate
     * @return Returns an ArrayList of triangular numbers
     */
    public ArrayList generateTriangulars(int n) {
        ArrayList triangles = new ArrayList();
        int answer;
        for (int i = 1; i < n + 1; i++) {
            answer = i + 1;
            answer *= i;
            answer /= 2;
            triangles.add(answer);
        }
        return triangles;
    }

    /**
     * Generates n pentagonal numbers
     *
     * @param n The number of pentagonal numbers to generate
     * @return Returns an ArrayList of pentagonal numbers
     */
    public ArrayList generatePentagonals(int n) {
        ArrayList pentagonals = new ArrayList();
        int answer;
        for (int i = 1; i < n + 1; i++) {
            answer = 3 * i;
            answer -= 1;
            answer *= i;
            answer /= 2;
            pentagonals.add(answer);
        }
        return pentagonals;
    }

    /**
     * Generates n hexagonal numbers
     *
     * @param n The number of hexagonal numbers to generate
     * @return Returns an ArrayList of hexagonal numbers
     */
    public ArrayList generateHexagonals(int n) {
        ArrayList hexagonals = new ArrayList();
        int answer;
        for (int i = 1; i < n + 1; i++) {
            answer = 2 * i;
            answer -= 1;
            answer *= i;
            hexagonals.add(answer);
        }
        return hexagonals;
    }
}
