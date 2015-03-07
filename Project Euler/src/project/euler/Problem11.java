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
public class Problem11 {
    
    private int globalCount = 0;

    void start() {
        final String grid = "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08\n" +
"49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00\n" +
"81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65\n" +
"52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91\n" +
"22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80\n" +
"24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50\n" +
"32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70\n" +
"67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21\n" +
"24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72\n" +
"21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95\n" +
"78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92\n" +
"16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57\n" +
"86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58\n" +
"19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40\n" +
"04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66\n" +
"88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69\n" +
"04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36\n" +
"20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16\n" +
"20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54\n" +
"01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48";
        ArrayList<String> numbers = stringToArrayString(grid);
        ArrayList<ArrayList<Integer>> matrix = stringToArrayInteger(numbers);
        for(int i = 0; i < matrix.size(); i++){
            System.out.println(matrix.get(i));
        }
        long answer = iterateForLargestProduct(matrix);
        System.out.println("Answer: " + answer);
    }
    
    private ArrayList<String> stringToArrayString(String s){
        String[] array = s.split("\n");
        ArrayList<String> arrayList = new ArrayList<String>();
        for(int i = 0; i < array.length; i++){
            arrayList.add(array[i]);
        }
        return arrayList;
    }
    
    private ArrayList<ArrayList<Integer>> stringToArrayInteger(ArrayList<String> m){
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < m.size(); i++){
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            String[] array = m.get(i).split(" ");
            for(int j = 0; j < array.length; j++){
                arrayList.add(Integer.parseInt(array[j]));
            }
            matrix.add(arrayList);
        }
        return matrix;
    }
    
    private long iterateForLargestProduct(ArrayList<ArrayList<Integer>> m){
        long largestProduct = 0;
        long temp;
        for(int i = 0; i < m.size(); i++){
            for(int j = 0; j < m.get(i).size(); j++){
                //UP
                if(i > 2){
                    temp = m.get(i).get(j) * m.get(i - 1).get(j) * m.get(i - 2).get(j) * m.get(i - 3).get(j);
                    System.out.println(m.get(i).get(j) + " " + m.get(i - 1).get(j) + " " +  m.get(i - 2).get(j) + " " + m.get(i - 3).get(j) + " = " + temp);
                    largestProduct = comparator(largestProduct, temp);
                }
                //DOWN
                /*
                if(i < m.size() - 3){
                    temp = m.get(i).get(j) * m.get(i + 1).get(j) * m.get(i + 2).get(j) * m.get(i + 3).get(j);
                    System.out.println(m.get(i).get(j) + " " + m.get(i + 1).get(j) + " " +  m.get(i + 2).get(j) + " " + m.get(i + 3).get(j) + " = " + temp);
                    largestProduct = comparator(largestProduct, temp);
                }*/
                //LEFT
                /*if(j > 2){
                    temp = m.get(i).get(j) * m.get(i).get(j - 1) * m.get(i).get(j - 2) * m.get(i).get(j - 3);
                    System.out.println(m.get(i).get(j) + " " + m.get(i).get(j - 1) + " " +  m.get(i).get(j - 2) + " " + m.get(i).get(j - 3) + " = " + temp);
                    largestProduct = comparator(largestProduct, temp);
                } */
                //RIGHT
                if(j < m.get(i).size() - 3){
                    temp = m.get(i).get(j) * m.get(i).get(j + 1) * m.get(i).get(j + 2) * m.get(i).get(j + 3);
                    System.out.println(m.get(i).get(j) + " " + m.get(i).get(j + 1) + " " +  m.get(i).get(j + 2) + " " + m.get(i).get(j + 3) + " = " + temp);
                    largestProduct = comparator(largestProduct, temp);
                }
                //DIAGONALLY RIGHT
                if(i < m.size() - 3 && j > 2){
                    temp = m.get(i).get(j) * m.get(i + 1).get(j - 1) * m.get(i + 2).get(j - 2) * m.get(i + 3).get(j - 3);
                    System.out.println(m.get(i).get(j) + " " + m.get(i + 1).get(j - 1) + " " +  m.get(i + 2).get(j - 2) + " " + m.get(i + 3).get(j - 3) + " = " + temp);
                    largestProduct = comparator(largestProduct, temp);
                }
                //DIAGONALLY LEFT
                if(i < m.size() - 3 && j < m.get(i).size() - 3){
                    temp = m.get(i).get(j) * m.get(i + 1).get(j + 1) * m.get(i + 2).get(j + 2) * m.get(i + 3).get(j + 3);
                    System.out.println(m.get(i).get(j) + " " + m.get(i + 1).get(j + 1) + " " +  m.get(i + 2).get(j + 2) + " " + m.get(i + 3).get(j + 3) + " = " + temp);
                    largestProduct = comparator(largestProduct, temp);
                }
            }
        }
        return largestProduct;
    }
    
    private long comparator(long a, long b){
        globalCount++;
        System.out.println("Comparing " + a + " with " + b + ". Comparison count: " + globalCount);
        if(a > b){
            return a;
        }
        else if(b > a){
            return b;
        }
        else{
            return a;
        }
    }
    
}
