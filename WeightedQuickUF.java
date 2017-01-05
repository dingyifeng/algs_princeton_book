import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
/**
 * Created by up_ding on 09/12/2016.
 */
public class WeightedQuickUF {
    private int number; // the number of the components
    private int[] id; // the id of the components
    private int[] size; // the size of the component

    // the function to throw exception when then input parameter is invalid
    private void  validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n)
            throw new IndexOutOfBoundsException("The input parameter should between "  + " 0 and " + (n - 1));
    }

    // Initialization of the WeightedQuickUF
    public WeightedQuickUF(int n) {
        number = n;
        id = new int[number];
        size = new int[number];
        for (int i = 0; i < number; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    // return  the root of p
    public int find(int p) {
        validate(p);
        while (p != id[p])
            p = id[p];
        return p;
    }

    // return true if p and q in the same component
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // if p and q don't connected connect them together
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        if (size[pRoot] < size[qRoot]) {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
        number--;
    }

    // return the number of the components;
    public int count() {
        return number;
    }

    public static void main(String[] args) {
        Stopwatch sw = new Stopwatch();
        int N = StdIn.readInt();
        WeightedQuickUF wuf = new WeightedQuickUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (wuf.connected(p,q)) continue;
            wuf.union(p,q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(wuf.count() + " components");
        StdOut.println(sw.elapsedTime() + " s");
    }
}
