package aafinalassignment.part_1;

import java.util.Arrays;



public class SortedArray {

    int[] array;
    double alpha;

    public SortedArray(int[] inputArray, double alpha) {
        if(inputArray.length == 0) {
            throw new IllegalArgumentException("inputArray must not be empty");
        }

        this.array = inputArray;

        if(alpha < 0 || alpha > 1) {
          throw new IllegalArgumentException("alpha must be between 0 and 1");  
        } 

        this.alpha = alpha;

        Arrays.sort(this.array);
    }

    public int pred(int x) {
        int right = array.length - 1;
        int left = 0;
        int result = Integer.MIN_VALUE;
    
        while (left <= right) {
            int root = (int) (left + (alpha * (right - left)));

            // ensure that root is a valid index
            if (root >= 0 && root < array.length) {
                if (x == array[root]) {
                    return array[root];
                } else if (x < array[root]) {
                    right = root - 1;
                } else {
                    left = root + 1;
                    result = array[root];
                }
            } else {
                // handle the case where root is out of bounds
                System.out.println("Index out of bounds, returning the last result.");
                return result;
            }
        }
        if (result == 0) {
            System.out.println("No result found, returning the default value for variable result.");
            return result;
        }
        return result;
        
    }

}

