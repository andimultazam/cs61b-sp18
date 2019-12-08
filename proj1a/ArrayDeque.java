public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 1;
        nextLast = nextFirst + 1;
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = (((nextFirst-1)%items.length)+items.length)%items.length;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        size += 1;
        nextLast = (nextLast+1) % items.length;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int curLast = (((nextLast-1)%size)+size)%size;
        int curFirst = (nextFirst+1) % size;

        if (curLast > curFirst) {
            System.arraycopy(items, 0, a, 0, curLast+1);
            nextFirst = a.length-1;
            nextLast = size;
        } else {
            System.arraycopy(items, curFirst, a, a.length-(size-curFirst), size-curFirst);
            System.arraycopy(items, 0, a, 0, curLast+1);
            nextFirst += size;
        }
        items = a;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        int ptr = (nextFirst+1)%items.length;
        int edge = nextLast-1;
        do {
            System.out.println(items[ptr]);
            ptr = (ptr+1)%items.length;
        } while (ptr != edge);
        System.out.println(items[ptr]);
    }

    public T removeFirst() {
        int curFirst = (nextFirst+1)%items.length;
        T x = get(curFirst);
        items[curFirst] = null;
        nextFirst = curFirst;
        size -= 1;
        return x;
    }

    public T removeLast() {
        int curLast = (((nextLast-1)%items.length)+items.length)%items.length;
        T x = get(curLast);
        items[size] = null;
        nextLast = curLast;
        size -= 1;
        return x;
    }

    public int size() { return size; }

    public T get(int index) {
        return items[index%size];
    }
}
