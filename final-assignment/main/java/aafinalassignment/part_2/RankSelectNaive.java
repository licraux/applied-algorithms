package aafinalassignment.part_2;

public class RankSelectNaive {
    
    private int[] data;

    public RankSelectNaive(int[] data) {
        this.data = data;
    }

    public int Rank(int position) throws Exception {
        if (position < 1 || position > data.length) {
            throw new Exception("Searched position is out of bounds.");
        }

        int numberOfSetBits = 0;

        for (int i = 0; i < position; i++) {
           if (data[i] == 1) {
            numberOfSetBits++;
           } 
        }

        return numberOfSetBits;
    }


    public int Select(int r) throws Exception {
        if (r < 1) {
            throw new Exception("Invalid argument, must be a positive integer.");
        }

        int numberOfSetBits = 0;
    
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 1) {
                numberOfSetBits++;
                if (numberOfSetBits == r) {
                    return i + 1;
                }
            }
        }

        System.out.println("There was not enough set bits in the data structure.");
        return -1;
    }

    public void printDataStructure() {
        String text = "";
        for (int i = 0; i < data.length; i++) {
            text += Integer.toString(data[i]) + ", ";
        }
        System.out.println(text);
    }
}