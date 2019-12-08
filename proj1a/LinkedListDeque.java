public class LinkedListDeque<T> {

    private class ItemNode {
        private T item;
        private ItemNode next;
        private ItemNode prev;

        public ItemNode(T i, ItemNode n, ItemNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private ItemNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ItemNode(null, sentinel, sentinel);
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new ItemNode(item, sentinel.next, sentinel);
        sentinel.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev = new ItemNode(item, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        ItemNode p = sentinel.next;
        while (p != sentinel) {
            System.out.println(p.item);
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return sentinel.next.item;
    }

    public T removeLast() {
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return sentinel.prev.item;
    }

    public int size() { return size; }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        ItemNode p = sentinel;
        for (int i = 0; i < index + 1; i++) {
            p = p.next;
        }
        return p.item;
    }

    private T getRecursiveHelper(ItemNode p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(p.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }
}
