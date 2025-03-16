package aafinalassignment.part_2;

public class RankSelectSpaceEfficient {
    
    private int[] bitVector;
    private int k;
    private int[] Rs;
    private byte[] populationCountLookup;

    public RankSelectSpaceEfficient(int[] vector, int k) {
        bitVector = buildSuccinctBitVector(vector);
        this.k = k;
        Rs = buildRs();
        populationCountLookup = buildPopulationCountLookup();
    }

    private static int[] buildSuccinctBitVector(int[] vector) {
        int[] arr = new int[vector.length / 32];

        for (int i = 0; i < vector.length; i++) {
            int resultingIndex = i / 32;
            int bitPosition = i % 32;
            arr[resultingIndex] |= (vector[i] & 1) << (31 - bitPosition);
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] vector = new int[]{0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0};
        int[] arr = buildSuccinctBitVector(vector);
        String result = "";
        String bitCount = "";
        for (int i = 0; i < arr.length; i++) {
            result += Integer.toString(arr[i]) + " ";
            bitCount += Integer.toString(Integer.bitCount(arr[i])) + " ";
        }        
        System.out.println(result);
        System.out.println(bitCount);

        
    }

    private int[] buildRs() {
        int[] arr = new int[bitVector.length / k];
        
        for (int i = 0; i < arr.length; i++) {
            int sum = i != 0 ? arr[i-1] : 0;
            for (int j = 0; j < k; j++) {
                sum += Integer.bitCount(bitVector[i * k + j]);
            }
            arr[i] = sum;
        }

        return arr;
    }

    public int Rank(int position) throws Exception {
        if (position < 1 || position > bitVector.length * 32) {
            throw new Exception("Searched position is out of bounds.");
        }

        int blockIndex = position / (k*32);
        int countUntilBlock = blockIndex != 0 ? Rs[blockIndex - 1] : 0;

        int numberOfBlocksBeforeGivenBlock = blockIndex * k;
        int indexOfGiven32BitInteger = position / 32;
        int countUntilGiven32BitInteger = 0;
        for (int i = numberOfBlocksBeforeGivenBlock; i < indexOfGiven32BitInteger; i++) {
            countUntilGiven32BitInteger += countInInteger(bitVector[i]);
        }

        int numberOfBitsNeededToCheck = position - numberOfBlocksBeforeGivenBlock * 32;
        int shift = 32 - numberOfBitsNeededToCheck;
        int countOfLastPart = numberOfBitsNeededToCheck == 0 ? 0 : countInInteger(bitVector[indexOfGiven32BitInteger] >>> shift);
        
        return countUntilBlock + countUntilGiven32BitInteger + countOfLastPart;
    }

    private int countInInteger(int integer) {
        int mask = 0xFF;
        return populationCountLookup[integer & mask] + populationCountLookup[(integer >>> 8) & mask] + populationCountLookup[(integer >>> 16) & mask] + populationCountLookup[(integer >>> 24)];
    }

    public int Select(int r) throws Exception {
        if (r < 1) {
            throw new Exception("Invalid argument, must be a positive integer.");
        }

        int low = 0;
        int high = bitVector.length * 32 - 1;

        while (true) {
            if (high < low) {
                System.out.println("There was not enough set bits in the data structure.");
                return -1;
            }

            int mid = low + (high-low)/2;
            if (Rank(mid) < r) {
                low = mid + 1;
            } else if (Rank(mid) > r) {
                high = mid - 1;
            } else if (Rank(mid) == r) {
                while (Rank(mid - 1) == r) {
                    mid--;
                }
                return mid;
            }
        }
    }

    private byte[] buildPopulationCountLookup() {
        byte[] arr = new byte[256];
        for (int i = 0; i < arr.length; i++) {
            for (byte b = 0; b < 9; b++) {
                arr[i] += (byte) ((i >>> b) & 1);
            }
        }
        return arr;
    }
}
