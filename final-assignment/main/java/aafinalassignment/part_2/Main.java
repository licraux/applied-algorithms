package aafinalassignment.part_2;

import java.util.Random;

public class Main {
    
    public static void main(String[] args) throws Exception {
        int[] randomArray = generateRandomIntArray(2048);
        int[] anotherSmall = new int[]{1, 0, 1, 1, 0, 1, 0, 1};

        RankSelectNaive rankSelectNaive = new RankSelectNaive(randomArray);
        RankSelectLookup rankSelectLookup = new RankSelectLookup(randomArray);
        RankSelectSpaceEfficient rankSelectSpaceEfficient = new RankSelectSpaceEfficient(randomArray, 4);
        System.out.println(rankSelectNaive.Rank(1773));
        System.out.println(rankSelectNaive.Select(60));  
        //rankSelectNaive.printDataStructure();
        System.out.println("-----");
        //rankSelectLookup.printDataStructure();
        System.out.println(rankSelectLookup.Rank(1773));
        System.out.println(rankSelectLookup.Select(60));  
        System.out.println("---------------");
        System.out.println(rankSelectSpaceEfficient.Rank(1773));
        System.out.println(rankSelectSpaceEfficient.Select(60));  
        System.out.println("..........................................");
        RankSelectNaive rankSelectNaive2 = new RankSelectNaive(anotherSmall);
        RankSelectLookup rankSelectLookup2 = new RankSelectLookup(anotherSmall);
        System.out.println(rankSelectNaive2.Rank(8));
        System.out.println(rankSelectNaive2.Select(4));
        System.out.println(rankSelectLookup2.Rank(8));
        System.out.println(rankSelectLookup2.Select(4));
    }

    private static int[] generateRandomIntArray(int length) {
        int[] array = new int[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(2); // Generates either 0 or 1
        }

        return array;
    }
}
