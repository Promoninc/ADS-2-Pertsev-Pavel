public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap; //Main list for min heap

    public MyMinHeap() {
        heap = new MyArrayList<>();
    }

    public boolean empty()//Function checks is the heap empty
    {
        return heap.size() == 0;
    }

    public int size()//Function returns size of heap
    {
        return heap.size();
    }

    public T getMin()//Function returns minimal element of heap
    {
        if (empty())
            throw new IllegalStateException("Heap is empty");
        return heap.get(0);
    }

    public T extractMin()//function returns and deletes min element of heap
    {
        if (empty())
            throw new IllegalStateException("Heap is empty");

        T min = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapify(0);

        return min;
    }

    public void insert(T value)//Function adds new element to heap
    {
        heap.add(value);
        traverseUp(heap.size() - 1);
    }

    private void heapify(int index)//Function processes heapifying of elements by index
    {
        int smallest = index;
        int left = leftChildOf(index);
        int right = rightChildOf(index);

        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0)
            smallest = left;
        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0)
            smallest = right;

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    private void traverseUp(int index) //Function move element up through the heap
    {
        while (index > 0 && heap.get(parentOf(index)).compareTo(heap.get(index)) > 0) {
            swap(index, parentOf(index));
            index = parentOf(index);
        }
    }

    private int leftChildOf(int index)//Function returns left child of element
    {
        return 2 * index + 1;
    }

    private int rightChildOf(int index)//Function returns right child of element
    {
        return 2 * index + 2;
    }

    private int parentOf(int index)//Function returns parent of element
    {
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2)//Function swaps elements of list
    {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}