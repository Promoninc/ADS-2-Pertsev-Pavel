import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> implements MyList<T>{
    private final static int DEFAULT_CAPACITY = 5;
    private Object[] arr; //Main array of list
    MyArrayList(){
        this(DEFAULT_CAPACITY);
    }
    MyArrayList(int initialCapacity){
        arr = new Object[initialCapacity];
    }
    private int length = 0;
    @Override
    //Function adds element to the end of array
    public void add(T item) {
        if(length == arr.length){
            increaseCapacity();
        }
        arr[length] = item;
        length++;
    }

    @Override
    //Function changes value of element by the index
    public void set(int index, T item) {
        arr[index] = item;
    }

    @Override
    //Function adds new element by index
    public void add(int index, T item) {
        if(length == arr.length){
            increaseCapacity();
        }
        validateIndex(index, length);
        for(int i = length; i > index; i--){
            arr[i] = arr[i-1];
        }
        arr[index] = item;
        length++;
    }

    @Override
    //Function adds element to the front of array
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    //Function adds element to the end of array
    public void addLast(T item) {
        add(item);
    }

    @Override
    public T get(int index) {
        validateIndex(index, length - 1);
        return (T) arr[index];
    }

    @Override
    //Function returns first element of list
    public T getFirst() {
        return get(0);
    }

    @Override
    //Function returns last element of list
    public T getLast() {
        return get(length - 1);
    }

    @Override
    //Function removes element by index
    public boolean remove(int index) {
        validateIndex(index, length - 1);
        for(int i = index; i < length - 1; i++){
            arr[i] = arr[i + 1];
        }
        length--;
        return true;
    }

    @Override
    //Function removes first element of list
    public void removeFirst() {
        remove(0);
    }

    @Override
    //Function removes last element of list
    public void removeLast() {
        remove(length - 1);
    }

    @Override
    //Function sorts the elements of list using Comparable
    public void sort() {
        T[] arrTemp = (T[]) new Comparable[length];
        System.arraycopy(arr, 0, arrTemp, 0, length);
        Arrays.sort(arrTemp);
        System.arraycopy(arrTemp, 0, arr, 0, length);
    }

    @Override
    //Function returns first index of element if it exists in list
    public int indexOf(Object object) {
        if(exists(object)){
            for(int i = 0; i < length; i++){
                if (arr[i] == object){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    //Function returns last index of element if it exists in list
    public int lastIndexOf(Object object) {
        if(exists(object)){
            for(int i = length - 1; i >= 0; i--){
                if (arr[i] == object){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    //Function checks existence of object in list
    public boolean exists(Object object) {
        for(int i = 0; i < length; i++){
            if (object == arr[i]){
                return true;
            }
        }
        return false;
    }

    @Override
    //Function returns list of objects as array
    public Object[] toArray() {
        return arr;
    }

    @Override
    //Function clears the list
    public void clear() {
        length = 0;
        arr = new Object[DEFAULT_CAPACITY];
    }
    @Override
    //Function returns size of list
    public int size() {
        return length;
    }

    //Function increases size of array automatically
    private void increaseCapacity(){
        Object[] arrTemp = new Object[arr.length*2];
        System.arraycopy(arr, 0, arrTemp, 0, length);
        arr = arrTemp;
    }

    //Function checks is index valid
    public void validateIndex(int index, int limit){
        if (index > limit){
            throw new IndexOutOfBoundsException("Index out of range!");
        }
    }

    @Override
    //Function need to implement Iterable. For example, to use foreach loops
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    //Class required for implementing Iterable
    private class MyIterator implements Iterator<T>{
        int cursor = 0;
        @Override
        //Function checks is this the end of list
        public boolean hasNext() {
            return cursor != length;
        }

        @Override
        //Function returns next element of list
        public T next() {
            T nextItem = get(cursor);
            cursor++;
            return nextItem;
        }
    }
}
