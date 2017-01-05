import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by up_ding on 25/12/2016.
 */
public class SortCompare {
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Selection")) Selection.sort(a);
        else if (alg.equals("Insertion")) Insertion.sort(a);
        else if (alg.equals("Shell")) Shell.sort(a);
        //if (alg == "Merge") Merge.sort(a);
        // if (alg == "Quick") Quick.sort(a);
        // if (alg == "Heap") Heap.sort(a);
        else throw new IllegalArgumentException("Invalid algorithm: " + alg);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int n, int t) {
        double total = 0.0;
        Double[] a = new Double[n];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++) {
                a[j] = StdRandom.uniform(0.0,1.0);
            }
            total = total + time(alg,a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 ;
        t1 = timeRandomInput(alg1, N, T);
        double t2 ;
        t2 = timeRandomInput(alg2, N, T);
        //StdOut.println(t1);
        //StdOut.println(t2);
        StdOut.printf("For %d random Doubles\n  %s is",N,alg1);
        StdOut.printf(" %.1f times faster than %s \n", t2/t1, alg2);

    }
}


/*import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class SortCompare {

    public static double time(String alg, Double[] a) {
        Stopwatch sw = new Stopwatch();
        if      (alg.equals("Insertion"))       Insertion.sort(a);
        //else if (alg.equals("InsertionX"))      InsertionX.sort(a);
        //else if (alg.equals("BinaryInsertion")) BinaryInsertion.sort(a);
        else if (alg.equals("Selection"))       Selection.sort(a);
        //else if (alg.equals("Bubble"))          Bubble.sort(a);
        //else if (alg.equals("Shell"))           Shell.sort(a);
        //else if (alg.equals("Merge"))           Merge.sort(a);
       // else if (alg.equals("MergeX"))          MergeX.sort(a);
       // else if (alg.equals("MergeBU"))         MergeBU.sort(a);
      //  else if (alg.equals("Quick"))           Quick.sort(a);
       // else if (alg.equals("Quick3way"))       Quick3way.sort(a);
        //else if (alg.equals("QuickX"))          QuickX.sort(a);
       // else if (alg.equals("Heap"))            Heap.sort(a);
        else if (alg.equals("System"))          Arrays.sort(a);
        else throw new IllegalArgumentException("Invalid algorithm: " + alg);
        return sw.elapsedTime();
    }

    // Use alg to sort trials random arrays of length n.
    public static double timeRandomInput(String alg, int n, int trials)  {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform(0.0, 1.0);
            total += time(alg, a);
        }
        return total;
    }

    // Use alg to sort trials random arrays of length n.
    public static double timeSortedInput(String alg, int n, int trials) {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = 1.0 * i;
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int n = Integer.parseInt(args[2]);
        int trials = Integer.parseInt(args[3]);
        double time1, time2;
        if (args.length == 5 && args[4].equals("sorted")) {
            time1 = timeSortedInput(alg1, n, trials);   // Total for alg1.
            time2 = timeSortedInput(alg2, n, trials);   // Total for alg2.
        }
        else {
            time1 = timeRandomInput(alg1, n, trials);   // Total for alg1.
            time2 = timeRandomInput(alg2, n, trials);   // Total for alg2.
        }

        StdOut.printf("For %d random Doubles\n    %s is", n, alg1);
        StdOut.printf(" %.1f times faster than %s\n", time2/time1, alg2);
    }
} */
