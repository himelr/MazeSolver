/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5.pkg1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author HimelR
 */
public class LAb51 {

    private File f;
    private LinkedList<Integer> adjList[];
    private boolean reach = false;

    public int nodes() throws FileNotFoundException {
        Scanner s = new Scanner(f);
        int i = 0;
        while (s.hasNext()) {
            String lul = s.nextLine();
            i++;

        }
        return i;

    }

    public boolean readGraph(File file) throws FileNotFoundException {
        f = file;
        Scanner s = new Scanner(f);
        int i = 0;
        adjList = new LinkedList[nodes()];
        int n = nodes();

        while (s.hasNext()) {
            String lul = s.nextLine();
            String[] ar = lul.split("\\s+");
            //System.out.println(ar[i]);
        }
        for (int a = 0; a < n; ++a) {
            adjList[a] = getList(a);

        }
//        for (int o = 0; o < adjList.length; o++) {
//            Iterator c = adjList[o].iterator();
//            while (c.hasNext()) {
//
//                System.out.println(c.next());
//            }
//
//        }

        return true;

    }

    public LinkedList getList(int po) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        LinkedList list = new LinkedList<>();

        for (int i = 0; i <= po; i++) {

            String lul = s.nextLine();
            String[] ar = lul.split("\\s+");
            if (i == po) {
                for (int a = 1; a < ar.length; a++) {

                    list.add(Integer.parseInt(ar[a]));
                }

            }
            //System.out.println(ar[i]);

        }
        return list;
    }

//    public int edges() throws FileNotFoundException {
//
//        Scanner s = new Scanner(f);
//        int ed = 0;
//        while (s.hasNext()) {
//            String lul = s.nextLine();
//            String[] ar = lul.split("\\s+");
//            ed += ar.length - 1;
//            //System.out.println(ar[i]);
//
//        }
//        return ed;
//
//    }
    public String printGraph() throws FileNotFoundException {
        Scanner s = new Scanner(f);
        StringBuilder kappa = new StringBuilder();
        while (s.hasNext()) {
            String lul = s.nextLine();
            StringBuilder keppo = new StringBuilder();

            for (int i = 0; i < lul.length(); i++) {
                keppo.append(lul.charAt(i));
                if (i == 1) {
                    keppo.append(":");
                }

            }
            kappa.append(keppo.toString() + "\n");
            //System.out.println(keppo);

        }
        return kappa.toString();

    }

    public void dfs(int start, boolean visited[], int pred[], int find) {

        if (!reach) {
            visited[start] = true;

            if (start == find) {
                reach = true;
                return;
            }

            Iterator<Integer> it = adjList[start].iterator();

            while (it.hasNext()) {

                int node = it.next();

                if (!visited[node]) {
                    pred[node] = start;
                    dfs(node, visited, pred, find);

                }

            }
        }
    }

//    public void dfsSt(int start, boolean visited[], int pred[]) {
//        Stack<Integer> st = new Stack<Integer>();
//        st.push(start);
//        int count = 0;
//
//        while (!st.empty()) {
//            int loc = st.pop();
//
//            if (!visited[loc]) {
//                
//                System.out.print(loc + " loc ");
//                visited[loc] = true;
//                if(loc == TO){
//                    return;
//                    
//                }
//              
//                Stack<Integer> st2 = new Stack<Integer>();
//                Iterator<Integer> it = adjList[loc].iterator();
//                int temp = 0;
//                while (it.hasNext()) {
//                    temp = it.next();
//                    if (!visited[temp]) {
//                        //pred[count] = temp;
//                        count++;
//                        st2.push(temp);
//                    }
//                }
//                while (!st2.isEmpty()) {
//                    int stps = st2.pop();
//                    st.push(stps);
//                    //pred[stps] = temp;
//                    
//                }
//            }
//
//        }
//
//    }
    private static int mazeSolution(int from, int to, int pred[], int steps[]) {
        int i, n, node;
        // first count how many edges between the end and the start 
        node = to;
        n = 1;
        while ((node = pred[node]) != from) {
            n++;
        }
        // then reverse pred[] array to steps[] array 
        node = to;
        i = n;
        while (i >= 0) {
            steps[i--] = node;
            node = pred[node];
        }

        // include also the end vertex 
        return (n + 1);
    }

    private final static int FROM = 0;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        String[] mazes = new String[4];
        mazes[0] = "maze.grh";
        mazes[1] = "maz2.grh";
        mazes[2] = "maze3.grh";
        mazes[3] = "maze4.grh";

        int count = 4;
        try {
            String arg1 = args[0];

            count = Integer.parseInt(arg1);
            if (count > 4) {
                count = 4;

                System.out.println("Max is 4");
            }
        } catch (Exception e) {
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < count; i++) {

            LAb51 g = new LAb51();
            g.readGraph(new File(mazes[i]));
            builder.append(g.printGraph());
            int to = g.nodes() - 1;
            boolean visited[] = new boolean[g.nodes()];
            int pred[] = new int[g.nodes()];
            g.dfs(FROM, visited, pred, to);
            int steps[] = new int[g.nodes()];

            builder.append("\nMaze " + (i + 1) + " solution from " + FROM + " to " + to + "\n" + "Graph Adjacent list\n");

//            System.out.println("\nMaze " + (i + 1) + " solution from " + FROM + " to " + to);
//            System.out.println("Graph Adjacent list");
            int n = mazeSolution(FROM, to, pred, steps);
            String sol = "";
            if (g.reach) {
                for (int a = 0; a < n; a++) {
//                System.out.print(steps[a] + " ");
                    sol += steps[a] + " ";
                }
            } else {
                sol = "No solution available";
            }
            builder.append(sol + "\n \n");
            System.out.println();
            System.out.println();

        }
        try (PrintWriter writer = new PrintWriter("solution.txt", "UTF-8")) {
            writer.println(builder.toString());

        }
//        File file = new File("solution.txt");

//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                // process the line.
//                System.out.println(line);
//            }
//        }
    }
}
