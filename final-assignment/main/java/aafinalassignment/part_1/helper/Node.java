package aafinalassignment.part_1.helper;

public class Node implements Comparable<Node>{
        int key;
        int weight;
        Node left, right;

        public Node(int item) {
            key = item;
            left = null;
            right = null;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int getKey() {
            return key;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            if(this.getWeight() > o.getWeight()){
                return 1;
            }
            else if(this.getWeight() < o.getWeight()){
                return -1;
            }
            else{
                return 0;
            }
        }
}
