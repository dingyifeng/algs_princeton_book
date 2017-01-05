import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.io.File;

/**
 * Created by up_ding on 26/12/2016.
 */
public class Shell {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i] + " ");
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N/3) h = h*3 + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                Comparable tmp = a[i];
                int j = 0;
                for ( j = i; j >= h && less(a[j], a[j-h]); j-=h) {
                    //exch(a,j,j-h);
                    a[j] = a[j-h];
                }
                a[j] = tmp;
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        /*Double[] a = new Double[100];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniform(1.0, 100.0);
        sort(a);
        show(a);*/

        File directory = new File(args[0]);
        File[] files = directory.listFiles();
        sort(files);
        show(files);


    }
}
