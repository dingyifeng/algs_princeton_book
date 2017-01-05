import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.omg.CORBA.OBJECT_NOT_EXIST;

import java.util.Comparator;

/**
 * Created by up_ding on 16/12/2016.
 */
public class Insertion {
    // This class should not be instantiate
    private Insertion() {}

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i] + " ");
        StdOut.println();
    }

    /*public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j],a[j-1]);j--)
                exch(a,j,j-1);
        }
        assert isSorted(a);
    } */

    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            Comparable tmp = a[i];
            int j = 0;
            for (j = i; j > 0 && less(tmp,a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            // exch(a,i,j);
            a[j] = tmp;
        }
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i],a[i-1])) return false;
        return true;
    }

    private static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v,w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        for (int i = 1; i < N; i++)
            for (int j = i; j > 0 && less(comparator,a[i],a[i - 1]); j--)
                exch(a,i,i-1);
    }

    public static void main(String[] args) {
        Double[] a = new Double[100];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniform(1.0,100.0);
        Insertion.sort(a);
        Insertion.show(a);
    }
}
