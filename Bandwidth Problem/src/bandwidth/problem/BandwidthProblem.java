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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgrapht.*;
import org.jgrapht.graph.*;

/**
 *
 * @author Joshua
 */
public class BandwidthProblem {

    final static String file1 = "Data/1.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UndirectedGraph<Integer, DefaultEdge> graph = createIntegerGraph();
        try {
            BufferedReader file;
            file = loadFile(file1);
            graph = substantiateGraph(file, graph);
        } catch (IOException ex) {
            Logger.getLogger(BandwidthProblem.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        for(int i = 0; i < vertices; i++){
            graph.addVertex(new Integer(i + 1));
        }
        for(int i = 0; i < edges; i++){
            String edge = file.readLine();
            //System.out.println(edge);
            String first = edge.substring(0, edge.indexOf(" "));
            //System.out.println(edge);
            edge = edge.substring(edge.indexOf(" ") + 1);
            edge.replaceAll("[^0-9]", "");
            //System.out.println(edge);
            String second = edge;
            //System.out.println("(" + first + "," + second + ")");
            Integer firstVertex = Integer.parseInt(first);
            Integer secondVertex = Integer.parseInt(second);
            //System.out.println("(" + firstVertex + "," + secondVertex + ")");
            graph.addEdge(firstVertex, secondVertex);
        }
        System.out.println(graph.toString());
        return graph;
    }

}
