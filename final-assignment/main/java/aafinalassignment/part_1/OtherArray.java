package aafinalassignment.part_1;

import java.util.ArrayList;
import java.util.PriorityQueue;

import aafinalassignment.part_1.helper.Node;

public class OtherArray {
    
    ArrayList<Node> ua = new ArrayList<Node>();
    SearchTree st;
    int[] otherArray;

    public OtherArray(int[] inputArray, double alpha) {
        if(inputArray.length == 0) {
            throw new IllegalArgumentException("inputArray must not be empty");
        }

        otherArray = new int[inputArray.length * 3];

        if(alpha < 0 || alpha > 1) {
            throw new IllegalArgumentException("alpha must be between 0 and 1");  
          }   

        st = new SearchTree(inputArray, alpha);
        veb(st.getRoot(), st.topSubTreeSize(st.getRoot().getWeight()));
        populateOtherArray();
    }

    public void veb(Node root, int topSize) {
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>((e1, e2) -> e2.getWeight() - e1.getWeight());

        if (root != null) {
            pq.add(root);
        }

        for (int i = 0; i < topSize; i++) {
            if(pq.isEmpty()) {
                break;
            }
            Node largest = pq.poll();
            ua.add(largest);
            
            if(largest.getLeft() != null) {
                pq.add(largest.getLeft());
            }

            if(largest.getRight() != null) {
                pq.add(largest.getRight());
            }
        }
        
        while(!pq.isEmpty()) {
            veb(pq.poll(), topSize);
        }

    }

    public ArrayList<Node> getUa() {
        return ua;
    }

    public void populateOtherArray() {
        for (int i = 0; i < ua.size(); i++) {
            Node node = ua.get(i);
            int indexOfLeft = ua.indexOf(node.getLeft());
            int indexOfRight = ua.indexOf(node.getRight());
            otherArray[i*3] = (indexOfLeft*3 + 1) < 0 ? -1 : indexOfLeft*3 + 1;
            otherArray[i*3 + 1] = node.getKey();
            otherArray[i*3 + 2] = (indexOfRight*3 + 1) < 0 ? -1 : indexOfRight*3 + 1;
        }
    }

    public int[] getOtherArray() {
        return otherArray;
    }

    public int findValue(int x, int index, int lastSeen) {
        if (index == -1) {
            return lastSeen;
        }
        int root = otherArray[index]; 
        if (x == root) {
            return root;
        } else if (x < root) {
            return findValue(x, otherArray[index - 1], lastSeen);
        } else {
            return findValue(x, otherArray[index + 1], root);
        }
    }

    public int pred(int x) {
        return findValue(x, 1, Integer.MIN_VALUE);
    }

    public int[] getotherArray() {
        return otherArray;
    }

    public SearchTree getSt() {
        return st;
    }
}