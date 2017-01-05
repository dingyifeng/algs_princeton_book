import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

/**
 * Created by up_ding on 02/01/2017.
 */
public class Quick {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean less (Object v, Object w, Comparator comparator) {
        return comparator.compare(v,w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        if (i == j) return;
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void shuffle(Object[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            int j = StdRandom.uniform(i + 1);
            exch(a,i,j);
        }
    }

    private static void show(Comparable[] a) {
        for (Comparable t : a)
            StdOut.println(t + " ");
    }

   /* public static void sort(Comparable[] a) {
        //shuffle(a);
        sort(a,0,a.length);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        /* **********************************************
         * the sort is first partition the array to two part: [lo,p),[p,p+1)[p+1,hi)
         * after partition the left part of p would be no more than(equal or less) a[p], the right
         * part would be no less than(equal or larger) a[p]
         *
         * after this then the sort procedure just to sort the left part and right part respectively and recursively
         * the whole array would be sorted
         ************************************************/
     /*   if (hi - lo <= 1) return;
        int p = partition(a,lo,hi);
        sort(a,lo,p);
        sort(a,p + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi;
        Comparable t = a[lo];

        while (true) {
            /* *****************************************
             * if there exists duplicated elems, after partition, the left part
             * would be less and equal to a[p], the right part would be larger and
             * equal to a[p]
             * while (less(a[++i],t) make the elems equal to v exchange to right part
             * while (less(t,a[--j]) make the elems equal to v exchange to left part
             *
             * consider the partition progress of 2,4,2,7,4,2, after partition the array would be
             * 2，2，2，7，4，4
             * conside the partition progress of 2,2,4,2,7,4,2, after partition the array would be
             * 2,2,2,4,7,4,2, the next progress of sort(a,p+1,hi) would sort the subarray4,7,4,2to2,4,4,7
             * *****************************************/
     /*       while(less(a[++i],t)) if (i == hi -1)break;
            while(less(t,a[--j])) if (j == lo) break;

            if (i >= j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    } */

    /* *******************************************
     * 2.3.17 Sentinels. Modify the code in Algorithm 2.5 to remove both bounds checks in the inner while loops.
     * The test against the left end of the subarray is redundant since the partitioning item acts as a sentinel
     * (v is never less than a[lo]). To enable removal of the other test, put an item whose key is the largest in
     * the whole array into a[length-1] just after the shufﬂe. This item will never move (except possibly to
     * be swapped with an item having the same key) and will serve as a sentinel in all subarrays involving
     * the end of the array. Note : When sorting interior subarrays, the leftmost entry in the subarray
     * to the right serves as a sentinel for the right end of the subarray.
     * *******************************************/

    private static void Max(Comparable[] a) {
        int max = 0;
        for (int i = 1; i < a.length; i++) {
            if (less(a[max], a[i])) max = i;
        }
        exch(a,max,a.length - 1);
    }

    public static void sort(Comparable[] a) {
        //shuffle(a);
        Max(a);
        sort(a,0,a.length);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi - lo <= 1) return;
        int p = partition(a,lo,hi);
        sort(a,lo,p);
        sort(a,p+1,hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        Comparable t = a[lo];

        int i = lo, j = hi;

        while (true) {
            while (less(a[++i],t)) {
                /* ****************************************************************************************
                 * after make the largest entry to be the right sentinel, then remove the statement i == hi
                 * can be make sense,imagine when a[lo] is the largest entry of the left subarray(a[lo] is
                 * less or equal than a[pivot] otherwise a[lo]
                 * should in the right subarray), so a[pivot] would be the right sentinel, that means
                 * if a[lo] is the largest entry  of the left subarray, then the maximum of i would be p,
                 * while j equals pivot-1 which would make the entry a[lo] exchange with a[j]
                 * *****************************************************************************************/

            }
            while (less(t,a[--j])) {

            }

            if (i >= j) break;

            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }

    public static void main(String[] args) {
       /*   Double a[] = new Double[10];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniform(); */
       /*Integer[] a = new Integer[7];
        int N = a.length;
        a[0] = 5;
        a[1] = 4;
        a[2] = 2;
        a[3] = 7;
        a[4] = 7;
        a[5] = 2;
        a[6] = 4;
        /*a[7] = 0;
        a[8] = 6;
        a[9] = 4;
        a[10] = 5;
        a[11] = 6;
        a[12] = 6;
        a[13] = 2;
        a[14] = 0;
        a[15] = 4;
        /*a[0] = 2; a[1] = 2; a[2] = 4;
        a[3] = 2; a[4] = 7; a[5] = 4;
        a[6] = 2;*/
       // show(a);
       // StdOut.println("after shuffle");
       // shuffle(a);
       // show(a);
        //Max(a);
        Integer[] a = {9,8,7,6,5,4,3,2,1};
        sort(a);
        show(a);
    }
}
