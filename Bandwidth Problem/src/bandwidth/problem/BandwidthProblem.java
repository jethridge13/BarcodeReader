/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bandwidth.problem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.element.Element;
import org.jgrapht.*;
import static org.jgrapht.WeightedGraph.DEFAULT_EDGE_WEIGHT;
import org.jgrapht.graph.*;

/**
 *
 * @author Joshua
 */
public class BandwidthProblem {

    final static String file1 = "Data/1.txt";
    final static String file2 = "Data/2.txt";
    final static String file3 = "Data/3.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        UndirectedGraph<Integer, DefaultEdge> graph = createIntegerGraph();
        try {
            BufferedReader file;
            file = loadFile(file3);
            graph = substantiateGraph(file, graph);
            ArrayList<ArrayList> combinations = createCombinations(graph);
            ArrayList<Integer> weights = generateWeights(combinations, graph);
            getBandwidth(weights, combinations);
            //System.out.println(combinations);
            //ArrayList<WeightedMultigraph> graphs = createGraphs(combinations, graph);
            //System.out.println(graphs.toString());
        } catch (IOException ex) {
            Logger.getLogger(BandwidthProblem.class.getName()).log(Level.SEVERE, null, ex);
        }
        long endTime = System.currentTimeMillis();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("Program finished in " + formatter.format((endTime - startTime) / 1000d) + " seconds.");
    }

    private static UndirectedGraph<Integer, DefaultEdge> createIntegerGraph() {
        UndirectedGraph<Integer, DefaultEdge> g = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
        return g;
    }

    public static BufferedReader loadFile(String fileName) throws FileNotFoundException,
            IOException {
        System.out.println("Loading file: " + fileName);
        FileReader fr = new FileReader(fileName);
        BufferedReader file = new BufferedReader(fr);
        return file;
    }

    private static UndirectedGraph<Integer, DefaultEdge> substantiateGraph(BufferedReader file, UndirectedGraph<Integer, DefaultEdge> graph) throws IOException {
        String stringVertices = file.readLine();
        String stringEdges = file.readLine();
        int edges = Integer.parseInt(stringEdges);
        int vertices = Integer.parseInt(stringVertices);
        for (int i = 0; i < vertices; i++) {
            graph.addVertex(new Integer(i + 1));
        }
        for (int i = 0; i < edges; i++) {
            String edge = file.readLine();
            //System.out.println(edge);
            String first = edge.substring(0, edge.indexOf(" "));
            //System.out.println(edge);
            edge = edge.substring(edge.indexOf(" ") + 1);
            edge = edge.replaceAll("[^0-9]", "");
            //System.out.println(edge);
            String second = edge;
            //System.out.println("(" + first + "," + second + ")");
            Integer firstVertex = Integer.parseInt(first);
            Integer secondVertex = Integer.parseInt(second);
            //System.out.println("(" + firstVertex + "," + secondVertex + ")");
            graph.addEdge(firstVertex, secondVertex);
        }
        //System.out.println(graph.toString());
        return graph;
    }

    private static ArrayList<ArrayList> createCombinations(UndirectedGraph<Integer, DefaultEdge> graph) {
        System.out.println("Generating combinations...");
        ArrayList<int[]> combinations = new ArrayList<>();
        Set set = graph.vertexSet();
        ArrayList list = new ArrayList<ArrayList>();
        list.addAll(set);
        //System.out.println(set.toString());
        //permutation(combinations, list);
        //System.out.println(list);
        int[] ints = new int[list.size()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) list.get(i);
        }
        combinations = permutations(ints);
        ArrayList returnable = new ArrayList();
        for (int i = 0; i < combinations.size(); i++) {
            ArrayList temp = new ArrayList();
            for (int j = 0; j < combinations.get(i).length; j++) {
                temp.add(combinations.get(i)[j]);
            }
            returnable.add(temp);
        }
        //System.out.println(returnable);
        return returnable;
    }

    static ArrayList<int[]> permutations(int[] a) {
        ArrayList<int[]> ret = new ArrayList<int[]>();
        permutation(a, 0, ret);
        return ret;
    }

    public static void permutation(int[] arr, int pos, ArrayList<int[]> list) {
        if (arr.length - pos == 1) {
            list.add(arr.clone());
        } else {
            for (int i = pos; i < arr.length; i++) {
                swap(arr, pos, i);
                permutation(arr, pos + 1, list);
                swap(arr, pos, i);
            }
        }
    }

    public static void swap(int[] arr, int pos1, int pos2) {
        int h = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = h;
    }

    private static ArrayList<ArrayList> permutations(ArrayList a) {
        ArrayList<ArrayList> returnable = new ArrayList<ArrayList>();
        permutation(a, 0, returnable);
        return returnable;
    }

    private static void permutation(ArrayList arr, int position, ArrayList<ArrayList> list) {
        if (arr.size() - position == 1) {
            list.add((ArrayList) arr.clone());
        } else {

        }
    }

    private static ArrayList createGraphs(ArrayList<ArrayList> combinations, UndirectedGraph g) {
        ArrayList returnable = new ArrayList();
        Set edgeSet = g.getAllEdges(1, combinations.get(0).size());
        for (int i = 0; i < combinations.size(); i++) {
            ArrayList vertices = combinations.get(i);
            WeightedMultigraph<Integer, DefaultWeightedEdge> graph = new WeightedMultigraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
            for (int j = 0; j < vertices.size(); j++) {
                Integer vertex = (Integer) vertices.get(j);
                graph.addVertex(vertex);
            }
            Set edgesSet = g.edgeSet();
            //System.out.println(edgesSet);
            ArrayList edges = new ArrayList();
            edges.addAll(edgesSet);
            //System.out.println(edges);
            for (int j = 0; j < edges.size(); j++) {
                DefaultEdge edge = (DefaultEdge) edges.get(j);
                String edgeString = edge.toString();
                String edgeStringSource = edgeString.substring(0, edgeString.indexOf(":"));
                //System.out.println(edgeStringSource);
                String edgeStringTarget = edgeString.substring(edgeString.indexOf(":"));
                //System.out.println(edgeStringTarget);
                edgeStringSource = edgeStringSource.replaceAll("[^0-9]", "");
                edgeStringTarget = edgeStringTarget.replaceAll("[^0-9]", "");
                int source = Integer.parseInt(edgeStringSource);
                int target = Integer.parseInt(edgeStringTarget);
                double weight = 0.0;
                DefaultWeightedEdge test;
                //graph.setEdgeWeight(DEFAULT_EDGE_WEIGHT, weight);
                //graph.setEdgeWeight(test, weight);
            }
            returnable.add(graph);
        }
        return returnable;
    }

    private static ArrayList<Integer> generateWeights(ArrayList<ArrayList> combinations, UndirectedGraph g) {
        System.out.println("Generating weights...");
        ArrayList<Integer> weights = new ArrayList<Integer>();
        for (int i = 0; i < combinations.size(); i++) {
            ArrayList<Integer> combo = combinations.get(i);
            Set edgeSet = g.edgeSet();
            ArrayList edges = new ArrayList();
            edges.addAll(edgeSet);
            int weight = 0;
            for(int j = 0; j < edges.size(); j++){
                String set = edges.get(j).toString();
                String edgeStringSource = set.substring(0, set.indexOf(":"));
                String edgeStringTarget = set.substring(set.indexOf(":"));
                edgeStringSource = edgeStringSource.replaceAll("[^0-9]", "");
                edgeStringTarget = edgeStringTarget.replaceAll("[^0-9]", "");
                Integer source = Integer.parseInt(edgeStringSource);
                Integer target = Integer.parseInt(edgeStringTarget);
                boolean count = false;
                weight = 0;
                for(int k = 0; k < combo.size(); k++){
                    if(count){
                        weight++;
                    }
                    if(!count && (combo.get(k).equals(source) || combo.get(k).equals(target))){
                        count = true;
                        k++;
                        weight++;
                    }
                    if(count && (combo.get(k).equals(source) || combo.get(k).equals(target))){
                        weight++;
                        count = false;
                    }
                }
            }
            weights.add(weight);
        }
        //System.out.println(weights);
        return weights;
    }


    private static void getBandwidth(ArrayList<Integer> weights, ArrayList<ArrayList> combinations) {
        if(weights.size() != combinations.size()){
            System.out.println("Something is wrong.");
        }
        int min = Integer.MAX_VALUE;
        int pos = 0;
        for(int i = 0; i < weights.size(); i++){
            if (weights.get(i) < min){
                min = weights.get(i);
                pos = i;
            }
        }
        System.out.println("The minimum bandwidth is: " + min + " with the combination: " + combinations.get(pos));
    }

}
