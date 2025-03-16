package aafinalassignment.part_1;

import aafinalassignment.part_1.helper.Node;

public class SearchTree {
    Node root;
    double alpha;

    public SearchTree(int[] inputArray, double alpha) {
        if(inputArray.length == 0) {
            throw new IllegalArgumentException("inputArray must not be empty");
        }

        if(alpha < 0 || alpha > 1) {
            throw new IllegalArgumentException("alpha must be between 0 and 1");  
          }   

        this.alpha = alpha;
        this.root = arrayToBST(inputArray, 0, inputArray.length - 1, alpha);
    }

    public static Node arrayToBST(int[] array, int start, int end, double alpha) {
        /* Base Case */
        if (start > end) {
            return null;
        }

        /* Get the middle element and make it root */
        // int mid = (start + end) / 2;
        int mid = (int) (start + (alpha * (end - start)));
        Node node = new Node(array[mid]);

        /*
         * Recursively construct the left subtree and make
         * it left child of root
         */
        node.setLeft(arrayToBST(array, start, mid - 1, alpha));

        /*
         * Recursively construct the right subtree and make
         * it right child of root
         */
        node.setRight(arrayToBST(array, mid + 1, end, alpha));

        int leftSide = 0;
        int rightSide = 0;

        if (node.getLeft() != null) {
            leftSide = node.getLeft().getWeight();
        }

        if (node.getRight() != null) {
            rightSide = node.getRight().getWeight();
        }

        int nodeWeight = leftSide + rightSide + 1;
    
        node.setWeight(nodeWeight);

        return node;
    }

    public static int findValue(int x, Node tree, int lastSeenValue) {
        if (tree.getKey() == x) {
            return tree.getKey();
        }
        if (tree.getKey() > x && tree.getLeft() != null) {
            return findValue(x, tree.getLeft(), lastSeenValue);
        } else if (tree.getKey() < x) {
            lastSeenValue = tree.getKey();
        }
        if (tree.getRight() != null) {
            return findValue(x, tree.getRight(), lastSeenValue);
        } else {
            return lastSeenValue;
        }
    }

    public int pred(int x) {
        return findValue(x, this.root, Integer.MIN_VALUE);
    }

    public void printTree() {
        printTreeHelper(this.root, "", true);
    }

    private void printTreeHelper(Node node, String prefix, boolean isTail) {
        if(node.getRight()!=null) {
            printTreeHelper(node.getRight(), prefix + (isTail ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isTail ? "└── " : "┌── ") + node.getKey());

        if(node.getLeft()!=null) {
            printTreeHelper(node.getLeft(), prefix + (isTail ? "    " : "│   "), true);
        }
    }

    public Node getRoot() {
        return root;
    }

    public int topSubTreeSize(int rootWeight) {
        return (int) Math.ceil(Math.sqrt(rootWeight));
    }
}
