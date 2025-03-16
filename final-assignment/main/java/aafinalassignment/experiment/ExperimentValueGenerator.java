package aafinalassignment.experiment;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ExperimentValueGenerator {
    
    public ExperimentValueGenerator(int seed) {
        this.seed = seed;
    }

    private int seed;

    public int[] createInput(int n) {
        Random rand = new Random(seed);
      
        int[] array = new int[n];
      
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt();
        }
      
        return array;
    }

    public int[] createQueries(int n, int min, int max) {
        Random rand = new Random(seed);
        IntStream random_queries = rand.ints((n-2), (min + 1), max);
        
        int[] worst_cases = {max, min};
        IntStream worst_cases_stream = Arrays.stream(worst_cases);

        return IntStream.concat(random_queries, worst_cases_stream).toArray();
    }    
}
