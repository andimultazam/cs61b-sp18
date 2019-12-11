public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = nextFirst + 1;
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = (((nextFirst - 1) % items.length) + items.length) % items.length;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        size += 1;
        nextLast = (nextLast + 1) % items.length;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int curLast = (((nextLast - 1) % items.length) + items.length) % items.length;
        int curFirst = (nextFirst + 1) % items.length;

        if (curLast > curFirst) {
            if (a.length < items.length) {
                System.arraycopy(items, curFirst, a, 0, (curLast - curFirst) + 1);
                nextFirst = capacity - 1;
                nextLast = (curLast - curFirst) + 1;
            } else {
                System.arraycopy(items, curFirst, a, 0, (curLast - curFirst) + 1);
                nextFirst = capacity - 1;
                nextLast = items.length;
            }
        } else {
            int len = items.length - curFirst;
            System.arraycopy(items, curFirst, a, capacity - len, len);
            System.arraycopy(items, 0, a, 0, curLast + 1);
            nextFirst = (nextFirst + items.length) % capacity;
        }
        items = a;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        int ptr = (nextFirst + 1) % items.length;
        int edge = nextLast - 1;
        do {
            System.out.println(items[ptr]);
            ptr = (ptr + 1) % items.length;
        } while (ptr != edge);
        System.out.println(items[ptr]);
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if ((double) size / (items.length) < 0.25 && items.length >= 16) {
            resize((int) (0.25 * items.length));
        }
        int curFirst = (nextFirst + 1) % items.length;
        T x = get(0);
        items[curFirst] = null;
        nextFirst = curFirst;
        size -= 1;
        return x;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if ((double) size / (items.length) < 0.25 && items.length >= 16) {
            resize((int) (0.25 * items.length));
        }
        int curLast = (((nextLast - 1) % items.length) + items.length) % items.length;
        T x = getLast();
        items[curLast] = null;
        nextLast = curLast;
        size -= 1;
        return x;
    }

    public int size() {
        return size;
    }

    private T getLast() {
        int curLast = (((nextLast - 1) % items.length) + items.length) % items.length;
        return items[curLast];
    }

    public T get(int index) {
        int cirIndex = (((nextFirst + 1) % items.length) + index) % items.length;
        return items[cirIndex];
    }
}
