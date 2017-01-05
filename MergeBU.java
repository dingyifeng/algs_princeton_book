import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by up_ding on 27/12/2016.
 */
public class MergeBU {
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
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        if (hi - lo <= 1) return true;
        for (int k = lo + 1; k < a.length; k++) {
            if (less(a[k],a[k-1])) return false;
        }
        return true;
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a,lo,mid);
        assert isSorted(a,mid,hi);

        int i = lo, j= mid;

        for (int k = lo; k < hi; k++)
            aux[k] = a[k];

        for (int k = lo; k < hi; k++) {
            if (i == mid) a[k] = aux[j++];
            else if (j == hi) a[k] = aux[i++];
            else if (less(aux[i],aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }

        assert isSorted(a,lo,hi);
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) { // every time to merge two sz size sub-array, then merge two sz*2 size sub-array
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                /*
                    N - sz is to make sure if  the final sub-array doesn't exit a pair sub-array to merge (for example if the
                     size of array is odd), then don't care the final sub-array(just leave it,  it has been sorted and not to
                     be merged with any other sub-array)

                     N-sz表示如果排序的索引位置连1组数据个数都不够了,那就没必要处理了,因为排序至少需要1组多的数据.(至少两组数据，最后一组的size可以小于sz.)

                     调用归并操作将相邻的一对子文件进行归并时，必须对子文件的个数可能是奇数、以及最后一个子文件的长度小于length这两种特殊情况进行特殊处理：
                     ① 若子文件个数为奇数，则最后一个子文件无须和其它子文件归并(即本趟轮空)；
                     ② 若子文件个数为偶数，则要注意最后一对子文件中后一子文件的区间上界是n。


                 */
                int mid = lo + sz;
                int hi = Math.min(lo + sz + sz,N);
                merge(a,aux,lo,mid, hi);
            }
        }
    }

    public static void main(String[] args) {
        Double[] a = new Double[10];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniform();
        sort(a);
        assert isSorted(a); // java -ea(enable assertion) Program
        show(a);
    }
}
