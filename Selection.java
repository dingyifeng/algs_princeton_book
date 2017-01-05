import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/**
 * Created by up_ding on 16/12/2016.
 */
public class Selection {
    public Selection() {}

    // if v less than w then return true
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

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (less(a[j],a[min])) min = j; // if j less than min, then replace the min with the less j
            }
            exch(a,i,min);
        }
    }

    /*public static void sort(Comparable[] a) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            int min = 0;
            int i = 0;
            for ( i = 1; i < a.length; i++) {
                 min = i - 1;
                if (less(a[i],a[min])) {
                    exch(a,min,i);
                    sorted = false;
                }
        }
            //exch(a,min,i);
    }
    }*/


    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if(less(a[i],a[i-1])) return false;
        return true;
    }

    public static void main(String[] args) { // static method can't call a non-static method, only can use reference method
        Double[] a = new Double[100];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniform(1.0,100.0);
        sort(a);
        show(a); // static method(main func is static) can only call static method, if the static method wanna
        // to call non-static method then should define an object
        // if the show method and sort method is static then can call the method without instantiate an object
        // however if the sort method is non-static, then should first instantiate an object, just like the program annotated below
        /*Integer[] b = new Integer[100];
        for (int i = 0; i < b.length; i++) {
            b[i] = StdRandom.uniform(1,1000);
        }
        Selection test = new Selection();
        test.sort(b);
        test.show(b);*/
    }
}
