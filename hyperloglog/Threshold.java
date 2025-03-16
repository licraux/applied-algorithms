import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Threshold {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> inputData = new ArrayList<>();
        
        int threshold = scanner.nextInt();
        
        while (scanner.hasNextInt()) {
            int input = scanner.nextInt();
            inputData.add(input);
        }
        scanner.close();
        
        double result = hyperLogLog(inputData, 1024);
        
        if (result > threshold) {
            System.out.println("above");
        } else {
            System.out.println("below");
        }
    }

    public static double hyperLogLog(List<Integer> data, int m) {
        double magicConstant = 0.7213 / (1 + 1.079 / m);
        int[] M = new int[m]; // registers

        for (int i = 0; i < m; i++) { // Initialize registers
            M[i] = 0;
        }

        for (int value : data) {
            int index = hB(value);
            int hashedValue = hA(value);
            M[index] = Math.max(M[index], rho(hashedValue)); // Update the register
        }

        double rawEstimate = calculateEstimate(M, magicConstant);

        double emptyRegisters = getEmptyRegisters(M);

        if (rawEstimate <= ((5.0 / 2.0) * m) && emptyRegisters > 0) {
            double division = (m / emptyRegisters);
            return m * Math.log(division);
        } else if (rawEstimate > (1.0 / 30.0 * Math.pow(2, 32))) {
            rawEstimate = Math.pow(-2, 32) * Math.log(1 - (rawEstimate / Math.pow(2.0, 32.0)));
        }
        return rawEstimate;
    }

    public static double calculateEstimate(int[] M, double magicConstant) {
        double total = 0.0;
        for (int value : M) {
            total += Math.pow(2.0, -value);
        }
        return magicConstant * Math.pow(M.length, 2) * Math.pow(total, -1);
    }

    public static int getEmptyRegisters(int[] M) {
        int empty = 0;
        for (int value : M) {
            if (value == 0) {
                empty++;
            }
        }
        return empty;
    }

    public static int hA(int number) {
        int[] matrix = {0x21ae4036, 0x32435171, 0xac3338cf, 0xea97b40c, 0x0e504b22,
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
