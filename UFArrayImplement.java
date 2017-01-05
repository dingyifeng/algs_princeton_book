import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by up_ding on 09/12/2016.
 */
public class UFArrayImplement {
    private int sizeComponent; // the number of the Component
    private int[] id; // the id of the Component

    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n)
            throw new IndexOutOfBoundsException("The input argument should between  0 and "  + (n - 1));
    }

    // Initialize the UFArrayImplement
    public UFArrayImplement(int n) {
        sizeComponent = n;
        id = new int[sizeComponent];
        for (int i = 0; i < sizeComponent; i++) {
            id[i] = i;
        }
    }

    // component identifier for p
    public int find(int p) {
        validate(p);
        return id[p];
    }

    // return true if site q and site q are in the same component
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // if p and q are not connected, connect them together
    public void union(int p, int q) {
        int PId = find(p);
        int QId = find(q);

        if (PId == QId) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == PId) id[i] = QId;
        }

        sizeComponent--;
    }

    // return the total number of the component in the Unoin-find forest
    public int count() {
        return sizeComponent;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        UFArrayImplement uf = new UFArrayImplement(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p,q)) continue;
            uf.union(p,q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
