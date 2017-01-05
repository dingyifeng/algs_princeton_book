import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by up_ding on 26/12/2016.
 */



//所有的区间都是左闭右开[ )


public class Merge {
     // private static Comparable[] aux;
    // This class should not be instantiated
    private Merge() {}

    private static final int CUTOFF = 15;

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /*public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a,0,a.length);
    }*/

    /****************************************************************************************************
     * 1.assume that the address of array[] a and aux is 427, 428 respectively, then no matter
     * how the pointer exchanges, finally aux disappear and a is sorted, meanwhile the address of a is never
     * changed during the progress. The address of a is copy to formal parameter(src), what changes is the
     * address of src and dst, the address of a would never change.
     * 2.when the formal parameter of function is array or pointer, it is just a pointer(address),
     * all the operations in the function are based on the formal parameter
     * 3.in this procedure, for example sort(aux,a,...) the address of aux would copy to src, the address of
     * a would copy to dst, thus src would be the pointer of aux, dst would be the pointer of a. And then after
     * sort(dst,src) then pointer exchanges
     ****************************************************************************************************/

    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();

        /* *********************************
         * keep in mind the last process of sort is to merge two sorted sub-array, and the result of
         * merge is stored in array dst[], so a should be the dst
         * *********************************/
        sort(aux,a,0,a.length); //aux 是src，a是 dst，目的是为了排序a
    }

    /*private static void sort(Comparable[] a, int lo, int hi) { //[lo,hi)
        if ((hi - lo) <= 1) return;
        int mid = lo + (hi - lo) / 2;
        sort(a,lo,mid);
        sort(a,mid,hi);
        // the logic is after the sub array sorted then to test whether the array is sorted, so the statement below
        // can't be moved to before sort(a,lo,mid);
        if (!less(a[mid],a[mid-1]))return; // this means that the array or subarray is already sorted

        merge(a,lo,mid,hi);
    }*/


    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if ((hi - lo) <= 1) return;
       /* if (hi - lo <= CUTOFF) {
           insertionSort(dst,lo,hi);
            return;
        }*/
        int mid = lo + (hi - lo) / 2;
        /* ***********************************************
         * the variable is effective in a function is the
         * formal parameter, in this case is src and dst.
         * so after sort(dst,src,lo,mid) the dst is new
         * src, src is new dst.
         * **********************************************
         * the dst and src in following function, they are exchanged
         * while after the function call, the src and dst would return
         * because the domain of src of the outer bound function is larger
         * **********************************************/

        sort (dst,src,lo,mid);
        // the dst after merge becomes src, because the parameter of sort is sort(src,dst,lo,hi)
        // when recursive call sort(src,dst), the parameter in the function is formal parameter
        // src and dst
        sort (dst,src,mid,hi);  // the src before becomes as dst
        //if (!less(a[mid],a[mid-1])) return;
        if (!less(src[mid],src[mid-1])) {
            System.arraycopy(src,lo,dst,lo,hi - lo + 1);
            return;
        }
        merge(src,dst,lo,mid,hi); // 原来的程序是每次merge的时候都要复制一次数组aux[i] = a[i], 这么做的目的是
    }

    /*private static void merge(Comparable[] a, int lo, int mid, int hi) {
        for (int i = lo; i < hi; i++) { //开销很大
            aux[i] = a[i];
        }

        int i = lo, j = mid;
        for (int k = lo; k < hi; k++) {
            if (i == mid) a[k] = aux[j++];
            else if (j == hi) a[k] = aux[i++];
            else if (less(aux[i],aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }*/

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        assert isSorted(src,lo,mid);
        assert isSorted(src,mid,hi);
        int i = lo, j = mid;
        for (int k = lo; k < hi; k++) {
            if (i == mid) dst[k] = src[j++];
            else if (j == hi) dst[k] = src[i++];
            // else if (less(src[i],src[j])) dst[k] = src[i++]; // this make the sort not stable, when src[i] == src[j]
            // this statement would choose the element later, which would make the sort unstable
            // else dst[k] = src[j++];
            else if (less(src[j],src[i])) dst[k] = src[j++];
            else dst[k] = src[i++];
        }
        assert isSorted(dst,lo,hi);
    }
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i] + " ");
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i],a[i-1]))return false;
        return true;
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        if (hi - lo <= 1) return true;
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i],a[i-1])) return false;
        return true;
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            for (int j = i; j > lo && less(a[j],a[j-1]); j--)
                exch(a,j,j-1);
        }
    }
    public static void main(String[] args) {
        /*Double[] a = new Double[1000];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniform(); */

        Integer[] a = {8,7,6,5,4,3,2,1};
        sort(a);
        assert isSorted(a); // java -ea(enable assertion) Program
        show(a);
    }
}
