package com.mycompany.kruskal;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.File;
import java.io.IOException;

class Kruskal {

    class Edge implements Comparable<Edge> {

        int src, dest, weight;

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    int V, E;
    Edge edge[];
    Edge result[];
    int leader[];

    Kruskal(int v, int e) {
        V = v;
        E = e;
        leader = new int[V+1];
        edge = new Edge[E];

        for (int i = 0; i < e; ++i) {
            edge[i] = new Edge();
        }
    }

    int Find(int n) {
        return leader[n];
    }

    void Union(int rootU, int rootV) {
        int newLeader;
        if (rootU < rootV) {
            newLeader = rootU;
            for (int i = 0; i < V; i++) {
                if (leader[i] == rootV) {
                    leader[i] = newLeader;
                }
            }
        } else {
            newLeader = rootV;

            for (int i = 0; i < V; i++) {
                if (leader[i] == rootU) {
                    leader[i] = newLeader;
                }
            }
        }
    }

    void KruskalMST() {
        result = new Edge[V - 1];

        int i = 0;
        for (i = 0; i < result.length; i++) {
            result[i] = new Edge();
        }
        Arrays.sort(edge);

        for (i = 0; i < V; ++i) {
            leader[i] = i;
        }

        i = 0;

        for (int e = 0; e < edge.length; e++) {
            Edge next_edge = edge[e];

            int x = Find(next_edge.src);
            int y = Find(next_edge.dest);

            if (x != y) {
                result[i++] = next_edge;
                Union(x, y);
            }
        }

        System.out.println("Following are the edges in " + "the constructed MST");

        int minimumCost = 0;
        for (i = 0; i < result.length; i++) {
            System.out.println(result[i].src + " --> " + result[i].dest + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree " + minimumCost);
    }

    void SecondBestMST() {
        Edge result1[] = new Edge[V - 1];
        Edge result2[] = new Edge[V - 1];
        int sbm = 999999999;
        int i = 0;
        for (i = 0; i < result1.length; i++) {
            result1[i] = new Edge();
            result2[i] = new Edge();
        }
        int minimumCost;
        int k = 0;

        while (k < V - 1) {
            for (i = 0; i < V; ++i) {
                leader[i] = i;
            }
            i = 0;
            for (Edge next_edge : edge) {
                if (next_edge != result[k]) {
                    int x = Find(next_edge.src);
                    int y = Find(next_edge.dest);
                    if (x != y) {
                        result1[i++] = next_edge;
                        Union(x, y);
                    }
                }
            }
            k++;
            minimumCost = 0;
            for (i = 0; i < result1.length; i++) {
                minimumCost += result1[i].weight;
            }

            if (minimumCost < sbm) {
                sbm = minimumCost;
                for (i = 0; i < result1.length; i++) {
                    result2[i] = result1[i];
                }
            }
        }

        System.out.println("Following are the edges in " + "the constructed Second Best MST");
        for (i = 0; i < result2.length; i++) {
            System.out.println(result2[i].src + " --> " + result2[i].dest + " == " + result2[i].weight);
        }
        System.out.println(" Second Best Minimum Spanning Tree Cost " + sbm);
    }

    public static void main(String[] args) throws FileNotFoundException {
        int V;
        int E;
        File file = new File("C:\\Users\\great computer\\Desktop\\Kruskal.txt");
        Scanner sc = new Scanner(file);
        V = sc.nextInt();
        E = sc.nextInt();
        Kruskal graph = new Kruskal(V, E);
        for (int i = 0; i < E; i++) {
            graph.edge[i].src = sc.nextInt();
            graph.edge[i].dest = sc.nextInt();
            graph.edge[i].weight = sc.nextInt();
        }
        graph.KruskalMST();
        graph.SecondBestMST();
    }
}