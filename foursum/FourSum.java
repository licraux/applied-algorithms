package foursum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FourSum {
    
    public static int[] fourSumQuartic (int[] x){
        int n = x.length;

        for (int i = 0; i < n; ++i) {
            int a = x[i];
            for (int j = i+1; j < n; ++j) {
                int b = x[j];
                for (int k = j+1; k < n; ++k) {
                    int c = x[k];
                    for (int l = k+1; l < n; ++l) {
                        int d = x[l];
                        if (a + b + c + d == 0) {
                            return new int[] { a, b, c, d };
                        }
                    }
                }
            }
        }
        return null;
    }

    public static int[] fourSumCubic (int[] x) {
        int n = x.length;
        int[] y = x.clone(); //copies an array of primitive types. they hold different reference addesses in memory (only for primitives)
        Arrays.sort(y);

        for (int i = 0; i < n; i++) {
            int a = y[i];
            for (int j = i+1; j < n; j++) {
                int b = y[j];
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    int c = y[left];
                    int d = y[right];
                    if (a + b + c + d == 0) {
                        return new int[] { a, b, c, d };
                    }
                    else if (a + b + c + d < 0) {
                        ++left;
                    }
                    else {
                        --right;
                    }
                }

            }
        }
        return null;

    }

    public static int[] hashMapFourSum(int[] x) {
        int n = x.length;
        Map<Integer, Pair> H = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = x[i] + x[j];
                H.put(sum, new Pair(i, j));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = x[i];
                int b = x[j];
                int targetSum = -a - b;

                if (H.containsKey(targetSum)) {
                    Pair pair = H.get(targetSum);
                    int k = pair.first;
                    int l = pair.second;

                    if (j < k) {
                        int c = x[k];
                        int d = x[l];
                        return new int[] {a, b, c, d};
                    }
                }
            }
        }
        return null;
    }



    public static class Pair {
        public int first;
        public int second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
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
        
         if ("quartic".equals(args[0])) {
             y = fourSumQuartic(x);
         }
         else if ("cubic".equals(args[0])) {
             y = fourSumCubic(x);
         }
         else if ("hashmap".equals(args[0])) {
             y = hashMapFourSum(x);
         }
    
         if (y == null) {
             System.out.println("null");
         }
         else {
             System.out.println(String.format("%d %d %d %d", 
                 y[0], y[1], y[2], y[3]));
         }

/*         int[] sampleInts = new int[] { 1, 2, -6, 3};
        int[] sampleInts2 = new int[] {3, 3, 3, 3};
        int[] sampleInts3 = new int[] {3, -2, 4, -1, -4, 0}; //should return 3, -2, -1, 0
        int[] sampleInts4 = new int[] {3, -2, 4, -1, -4, 0, -7}; //should return 3, -2, -1, 0

        int[] quarticResults = fourSumQuartic(sampleInts4);
        int[] cubicResults = fourSumCubic(sampleInts4);
        int[] hashMapResults = hashMapFourSum(sampleInts);

        System.out.println("Quartic results: " + Arrays.toString(quarticResults));
        System.out.println("Cubic results: " + Arrays.toString(cubicResults));
        System.out.println("HashMap results: " + Arrays.toString(hashMapResults)); */


    }

}

