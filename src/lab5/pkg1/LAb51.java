/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5.pkg1;

import java.io.File;
import java.io.FileNotFoundException;
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
    public void printGraph() throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String lul = s.nextLine();
            StringBuilder keppo = new StringBuilder();

            for (int i = 0; i < lul.length(); i++) {
                keppo.append(lul.charAt(i));
                if (i == 1) {
                    keppo.append(":");
                }

            }
            System.out.println(keppo);

        }

    }

    public void dfs(int start, boolean visited[], int pred[]) {

        if (!reach) {
            visited[start] = true;

            if (start == TO) {
                reach = true;
                return;
            }

            Iterator<Integer> it = adjList[start].iterator();

            while (it.hasNext()) {

                int node = it.next();

                if (!visited[node]) {
                    pred[node] = start;
                    dfs(node, visited, pred);

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
    private final static String FILE = "maze.grh";
    private final static int FROM = 0;
    private final static int TO = 15;

    public static void main(String[] args) throws FileNotFoundException {

        try {
            String arg1 = args[0];
            String arg2 = args[1];
            System.out.println(arg1);
            System.out.println(arg2);
        } catch (Exception e) {
        }

        LAb51 g = new LAb51();
//        // read the graph. and do the depth-first search 
        System.out.println("Graph Adjacent list");
        g.readGraph(new File(FILE));

        g.printGraph();

//        LinkedList linkedList = g.getList(2);
//        Iterator i = linkedList.iterator();
//        while (i.hasNext()) {
//
//            System.out.println(i.next());
//        }
        boolean visited[] = new boolean[g.nodes()];
        int pred[] = new int[g.nodes()];
        g.dfs(FROM, visited, pred);

        int steps[] = new int[g.nodes()];

        System.out.println("\nMaze solution from " + FROM + " to " + TO);
        int n = mazeSolution(FROM, TO, pred, steps);
        for (int i = 0; i < n; i++) {
            System.out.print(steps[i] + " ");
        }
        System.out.println();
    }
}
