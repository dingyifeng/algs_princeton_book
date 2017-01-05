import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by up_ding on 03/01/2017.
 */

/* ******************************************************************************
 * there are two performance optimize:
 * 1. when the array size is small cutoff to insertion sort
 * 2. the random number generator is an expensive operation, so the
 *    median of three partition method is used to replace the shuffle process
 * ******************************************************************************/
public class Quick3median {
    private static int CUTOFF = 10;

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static int median(Comparable[] a, int lo,  int hi) {
        int i = lo, k = hi - 1;
        int j = lo + (hi -  lo)/2;
        return (less(a[i],a[j]) ? (less(a[j],a[k]) ? j : less(a[i],a[k]) ? k : i ) :
        (less(a[k],a[j]) ? j : less(a[i],a[k]) ? i : k));
    }

    public static void sort(Comparable[] a) {
        sort(a,0,a.length);
    }

    private static void  exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi - lo <= CUTOFF) {
            insertionSort(a,lo,hi);
            return;
        }
        int mid = median(a,lo,hi);
        exch(a,lo,mid);

        int p = partition(a,lo,hi);
        sort(a,lo,p);
        sort(a,p+1,hi);

    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi;
        Comparable t = a[lo];
        while (true) {
            while (less(a[++i], t)){
                if (i == hi - 1) break;
            }
            while (less(t,a[--j])){

            }
            if (i >= j) break;
            exch(a,i,j);
        }
        exch(a,j,lo);
        return j;
    }

    /*private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--){
                exch(a,j,j-1);
            }
        }
    } */
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            Comparable tmp = a[i];
            int j = 0;
            for (j = i; j > lo && less(tmp,a[j-1]); j--) {
                    a[j] = a[j-1];
            }
            a[j] = tmp;
        }
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i] + " ");
    }

    public static void main(String[] args) {
        //Integer[] a = {6,1,4,9,0,3,5,2,7,8};
        //StdOut.println(a[median(a,0,a.length)]);
        Double[] a = new Double[100];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniform();
        sort(a);
        show(a);
    }
}
