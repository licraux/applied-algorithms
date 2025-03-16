/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package threesum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ThreeSum {
    public String getGreeting() {
        return "Hello World!";
    }

    public static int[] threeSumCubic(int[] x) {
        int n = x.length;
        for (int i = 0; i < n; ++i) {
            int a = x[i];
            for (int j = i+1; j < n; ++j) {
                int b = x[j];
                for (int k = j+1; k < n; ++k) {
                    int c = x[k];
                    if (a + b + c == 0) {
                        return new int[] { a, b, c };
                    }
                }
            }
        }
        return null;
    }


public static int[] threeSumQuadratic(int[] x) {
    int n = x.length;
    int[] y = x.clone(); //copies an array of primitive types. they hold different reference addesses in memory (only for primitives)
    Arrays.sort(y);
    for (int i = 0; i < n; ++i) {
        int a = y[i];
        int left = i+1;
        int right = n-1;
        while (left < right) {
            int b = y[left];
            int c = y[right];
            if (a+b+c == 0) {
                return new int[] { a, b, c};
            }
            else if (a+b+c < 0) {
                ++left;
            }
            else {
                --right;
            }
        }
    }

    return null;
}

public static int[] threeSumHashMap(int[] x) {
    int n = x.length;
    HashMap<Integer,Integer> H = 
        new HashMap<Integer,Integer>(); //hashmap decreases in size if two integers in the array are the same. cannot have duplicate keys.
    for (int i = 0; i < n; ++i) {
        H.put(x[i], i);
    }
    for (int i = 0; i < n; ++i) {
        int a = x[i];
        for (int j = i+1; j < n; ++j) {
            int b = x[j];
            int c = -a - b;
            Integer k = H.get(c);
            if (k != null && j < k) {
                return new int[] { a, b, c };
            }
        }
    }
    return null;
}

public static int[] threeSumHashMap2 (int[] x) {
    int n = x.length;
    HashMap<Integer,Integer> H = 
        new HashMap<Integer,Integer>(); //hashmap decreases in size if two integers in the array are the same. cannot have duplicate keys.
    for (int i = 0; i < n; ++i) {
        H.put(x[i], i);
    }
    for (int i = 0; i < n; ++i) {
        int a = x[i];
        for (int j = i+1; j < n; ++j) {
            int b = x[j];
            int c = -a - b;
            Integer k = H.get(c);
            if (k != null) {
                return new int[] { a, b, c };
            }
        }
    }
    return null;
}

public static void threeSumHashMapPrint(int[] x) {
    int n = x.length;
    boolean found = false;
    HashMap<Integer,Integer> H = 
        new HashMap<Integer,Integer>(); //hashmap decreases in size if two integers in the array are the same. cannot have duplicate keys.
    for (int i = 0; i < n; ++i) {
        H.put(x[i], i);
    }
    for (int i = 0; i < n; ++i) {
        int a = x[i];
        for (int j = i+1; j < n; ++j) {
            int b = x[j];
            int c = -a - b;
            Integer k = H.get(c);
            if (k != null && j < k) {
                System.out.println(a + ", " + b + ", " + c); //changed the output to a print statement to check for all possible triplets
                found = true;
            }
        }
    }
    if (!found) {
    System.out.println("null"); //added a null statement to check for the null case
    }
}

public static void threeSumHashMapPrint2(int[] x) {
    int n = x.length;
    boolean found = false;
    HashMap<Integer,Integer> H = 
        new HashMap<Integer,Integer>(); //hashmap decreases in size if two integers in the array are the same. cannot have duplicate keys.
    for (int i = 0; i < n; ++i) {
        H.put(x[i], i);
    }
    for (int i = 0; i < n; ++i) {
        int a = x[i];
        for (int j = i+1; j < n; ++j) {
            int b = x[j];
            int c = -a - b;
            Integer k = H.get(c);
            if (k != null) { //changed the if statement to not include j < k
                System.out.println(a + ", " + b + ", " + c); //changed the output to a print statement to check for all possible triplets
                found = true;
            }
        }
    }
    if (!found) {
        System.out.println("null"); //added a null statement to check for the null case
    }

}

public static int [] readData() {
    Scanner sc = new Scanner(System.in);
    int [] x = null;
    try {
        int n = sc.nextInt();
        x = new int[n];
        for (int i = 0; i < n; ++i) {
            x[i] = sc.nextInt();
        }
    }
    finally {
        sc.close();
    }
    return x;
}

    public static void main(String[] args) {

        int[] x = readData();
        int[] y = null;
        
        if ("cubic".equals(args[0])) {
             y = threeSumCubic(x);
         }
         else if ("quadratic".equals(args[0])) {
             y = threeSumQuadratic(x);
         }
         else if ("hashmap".equals(args[0])) {
             y = threeSumHashMap(x);
         }
    
         if (y == null) {
             System.out.println("null");
         }
         else {
             System.out.println(String.format("%d %d %d", 
                 y[0], y[1], y[2]));
         }

        //  System.out.println(new ThreeSum().getGreeting());

        // int [] sampleInts = new int[] { 3, -2, -1 };
        // int [] sampleInts2 = new int[] { 1, 2, -3};
        // int [] sampleInts3 = new int[] { 3, -2, 4, -1, -4, 0 };
        // int [] sampleInts4 = new int[] { 5, -2, -3 };
        // int [] sampleInts5 = new int[] { 0, 0 };

        // int[] cubicResults = threeSumCubic(sampleInts3);
        // int[] quadraticResults = threeSumQuadratic(sampleInts3);
        // int[] hashMapResults = threeSumHashMap(sampleInts3);
        // int[] hashMapResults2 = threeSumHashMap2(sampleInts5);
        // threeSumHashMapPrint(sampleInts);
        // System.out.println("------------");
        // threeSumHashMapPrint2(sampleInts); //prints out all possible triplets including duplicates due to the absence of j < k in the if statement

        // System.out.println("Cubic results: " + Arrays.toString(cubicResults));
        // System.out.println("Quadratic results: " + Arrays.toString(quadraticResults));
        // System.out.println("HashMap results: " + Arrays.toString(hashMapResults));
        // System.out.println("HashMap2 results: " + Arrays.toString(hashMapResults2));
        //System.out.println("HashMap2 results: " + Arrays.toString(hashMapResults2)); 
    }
}
