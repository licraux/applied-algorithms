package assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Hash {
    public static void main(String[] args) {
        // Scanner in = new Scanner(System.in);

        try {
            FileWriter fileWriter = new FileWriter("python/numberDistribution.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 1; i < 1_000_001; i++) {
                int result = rho(hA(i));
                String output = result + " ";
                //System.out.print(output); 
                bufferedWriter.write(output); 
            }

            bufferedWriter.close(); 
            fileWriter.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * while (in.hasNext()) {
         * String input = in.next();
         * int number = Integer.parseUnsignedInt(input, 16);
         * int result = rho(hA(number));
         * // String hexResult = String.format("%08x", result);
         * System.out.println(result);
         * }
         */
        // in.close();
    }

    public static int hA(int number) {
        int[] matrix = { 0x21ae4036, 0x32435171, 0xac3338cf, 0xea97b40c, 0x0e504b22,
                0x9ff9a4ef, 0x111d014d, 0x934f3787, 0x6cd079bf, 0x69db5c31, 0xdf3c28ed,
                0x40daf2ad, 0x82a5891c, 0x4659c7b0, 0x73dc0ca8, 0xdad3aca2, 0x00c74c7e,
                0x9a2521e2, 0xf38eb6aa, 0x64711ab6, 0x5823150a, 0xd13a3a9a, 0x30a5aa04,
                0x0fb9a1da, 0xef785119, 0xc9f0b067, 0x1e7dde42, 0xdda4a7b2, 0x1a1c2640, 0x297c0633, 0x744edb48,
                0x19adce93 };

        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            int product = matrix[i] & number;
            int bitCount = Integer.bitCount(product);
            int resultingBit = bitCount & 1;
            resultingBit <<= i;
            result |= resultingBit;
        }
        return result;
    }

    public static int hB(int x) {
        return ((x * 0xbc164501) & 0x7fffffff) >> 21;
    }

    public static int rho(int x) {
        return Integer.numberOfLeadingZeros(x) + 1;
    }

}
