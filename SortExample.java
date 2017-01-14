import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.io.File;

/**
 * Created by up_ding on 24/12/2016.
 */
public class SortExample {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void exch(Comparable v, Comparable w) {
        Comparable t = v;
        v = w;
        w = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i] + " ");
        }
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i],a[i-1])) return false;
        }
        return true;
    }

    // selection sort
    /*public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j],a[i])) min = j;
            }
            exch(a,min,i);
        }
    }*/

    // insertion sort
  /*  public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            boolean sorted = true;
            Comparable tmp = a[i];
            int j = 0;
             // for ( j = i; j > 0 && less(tmp, a[j - 1]); j--) {

            for ( j = i; j > 0; j--) {
                if (less(tmp, a[j - 1])) {
                    a[j] = a[j - 1];
                    sorted = false;
                }
            }
            if (!sorted) a[j] = tmp; // 这样写的问题是比如数组是5,4,2,7,7,2,4,0,6,4; 当第二个2的时候就会出现问题，第2个2
            // 和前面排列好的2，4，5比较的时候，会将最开始的2换成2，以使得数组成为2,4,4,5,7,7,...
        }
    }*/
  // insertion sort
   /* public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            Comparable tmp = a[i];
            int j = 0;
            for (j = i; j > 0; j--) {
                if (less(tmp,a[j - 1])) { // 这样写的问题是,其实插入排序中a[0]到a[j-1]已经是有序的
                    a[j] = a[j - 1];
                }
            }
            a[j] = tmp; // 这么做的问题是不论前面的数组有没有需要交换的，最后终会将a[j] 置为tmp
        }
    } */

   // selection sort
   /* public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            int j = 0;
            Comparable tmp = a[i];
            for (j = i; j > 0 && less(tmp,a[j - 1]);j--) {
                a[j] = a[j - 1];
            }
            // exch(a,i,j);  // 这么写就错了，因为经过前面的比较a[i]的值有可能已经变了，因为a[j] = a[j - 1]
            a[j] = tmp;
        }
    } */

    // insertion sort
   /* public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            Comparable tmp = a[i];
            int j = 0;
            for (j = i; j > 0; j--) {
                if (less(tmp,a[j - 1]))
                    a[j] = a[j - 1];
                else break;
            }
            a[j] = tmp; //这个程序对的原因是，如果tmp比a[j-1]大，break就会将程序跳出去
            // j的值没有减

        }
    }*/
   /* public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j],a[j - 1])) {
                    exch(a,j,j - 1);
                }
                else break;
            }
        }
    } */

   // selection sort
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a,j,j-1);
        }
    }
    public static void main(String[] args) {
        /*Double[] a = new Double[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(1.0,1000.0);
        }*/
       /* Integer[] a = new Integer[10];
        int N = a.length;
        a[0] = 5;
        a[1] = 4;
        a[2] = 2;
        a[3] = 7;
        a[4] = 7;
        a[5] = 2;
        a[6] = 4;
        a[7] = 0;
        a[8] = 6;
        a[9] = 4;
        /*for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniform(0,10);
            */
        /*sort(a);
        assert isSorted(a);
        show(a);*/
        File directory = new File(args[0]);
        File[] files = directory.listFiles();
        show(files);
        StdOut.println("The files after sorted:");
        sort(files);
        show(files);

    }
}
