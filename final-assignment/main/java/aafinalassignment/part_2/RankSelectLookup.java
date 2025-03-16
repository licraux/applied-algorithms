package aafinalassignment.part_2;

public class RankSelectLookup {
    
    private int[] data;

    public RankSelectLookup(int[] input) {
        data = new int[input.length];
    
        int numberOfSetBits = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 1) {
                numberOfSetBits++;
            }
            data[i] = numberOfSetBits;
        }
    }

    public int Rank(int position) throws Exception {
        if (position < 1 || position > data.length) {
            throw new Exception("Searched position is out of bounds.");
        }

        return data[position-1];  
    }

    public int Select(int r) throws Exception {
        if (r < 1) {
            throw new Exception("Invalid argument, must be a positive integer.");
        }
    
        int low = 0;
        int high = data.length - 1;

        while (true) {
            if (high < low) {
                System.out.println("There was not enough set bits in the data structure.");
                return -1;
            }

            int mid = low + (high-low)/2;
            if (Rank(mid+1) < r) {
                low = mid + 1;
            } else if (Rank(mid+1) > r) {
                high = mid - 1;
            } else if (Rank(mid+1) == r) {
                while (Rank(mid) == r) {
                    mid--;
                }
                return mid + 1;
            }
        }

    }

    public void printDataStructure() {
        String text = "";
        for (int i = 0; i < data.length; i++) {
            text += Integer.toString(data[i]) + ", ";
        }
        System.out.println(text);
    }
}
