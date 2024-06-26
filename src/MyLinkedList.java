import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T>{
    MyNode<T> head, tail; //Creating first and last nodes
    int length = 0;

    @Override
    //Function adds element to the end of array
    public void add(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        if(head == null){
            head = tail = newNode;
        }
        else{
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    @Override
    //Function changes value of element by the index
    public void set(int index, T item) {
        validateIndex(index, length - 1);
        MyNode<T> currentNode = head;
        for(int i = 0; i < index; i++){
            currentNode = currentNode.next;
        }
        currentNode.data = item;
    }

    @Override
    //Function adds new element by index
    public void add(int index, T item) {
        validateIndex(index, length);
        MyNode<T> newNode = new MyNode<>(item);
        MyNode<T> currentNode = head;
        if(index == 0){
            addFirst(item);
            return;
        }
        else if (index == length){
            addLast(item);
            return;
        }
        else{
            for(int i = 0; i < index; i++){
                currentNode = currentNode.next;
            }
            currentNode.prev.next = newNode;
            newNode.prev = currentNode.prev;
            currentNode.prev = newNode;
            newNode.next = currentNode;
            length++;
        }
    }

    @Override
    //Function adds element to the front of array
    public void addFirst(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        head.prev = newNode;
        newNode.next = head;
        head = newNode;
        length++;
    }

    @Override
    //Function adds element to the end of array
    public void addLast(T item) {
        add(item);
        length++;
    }

    @Override

    public T get(int index) {
        validateIndex(index, length - 1);
        MyNode<T> currentNode;
        if(index <= length / 2){
            currentNode = head;
            for(int i = 0; i < index; i++){
                currentNode = currentNode.next;
            }
        }
        else{
            currentNode = tail;
            for(int i = length - 1; i > index; i--){
                currentNode = currentNode.prev;
            }
        }
        return currentNode.data;
    }

    @Override
    //Function returns first element of list
    public T getFirst() {
        return head.data;
    }

    @Override
    //Function returns last element of list
    public T getLast() {
        return tail.data;
    }

    @Override
    //Function removes element by index
    public boolean remove(int index) {
        validateIndex(index, length - 1);
        if(index == 0){
            removeFirst();
        }
        else if(index == length - 1){
            removeLast();
        }
        else{
            MyNode<T> currentNode = head;
            validateIndex(index, length - 1);
            for(int i = 0; i < index - 1; i++){
                currentNode = currentNode.next;
            }
            currentNode.next = currentNode.next.next;
            currentNode.next.prev = currentNode;
        }
        length--;
        return false;
    }

    @Override
    //Function removes first element of list
    public void removeFirst() {
        if(head == null){
                throw new NegativeArraySizeException();
        }
        else if(head.next != null){
            head = head.next;
            head.prev = null;
        }
        else{
            head = tail;
            head.prev = null;
        }
        length--;
    }

    @Override
    //Function removes last element of list
    public void removeLast() {
        if(head == null){
            throw new NegativeArraySizeException();
        }
        else if(tail.prev == null && tail != head){
            head = tail;
        }
        else if(tail == head){
            head = tail = null;
        }
        else{
            tail = tail.prev;
            tail.next = null;
        }
        length--;
    }

    @Override
    //Function sorts the elements of list.
    public void sort() {
        if (head == null || head.next == null) {
            return;
        }

        for (int i = 0; i < length - 1; i++) {
            MyNode<T> current = head;
            for (int j = 0; j < length - 1; j++) {
                Comparable<T> currentComparable = (Comparable<T>) current.data;
                if (currentComparable.compareTo(current.next.data) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
                current = current.next;
            }
        }
    }

    @Override
    //Function returns first index of element if it exists in list
    public int indexOf(Object object) {
        if(exists(object)){
            MyNode<T> currentNode = head;
            for(int i = 0; i < length; i++){
                if (currentNode.data == object){
                    return i;
                }
                currentNode = currentNode.next;
            }
        }
        return -1;
    }

    @Override
    //Function returns last index of element if it exists in list
    public int lastIndexOf(Object object) {
        if(exists(object)){
            MyNode<T> currentNode = tail;
            for(int i = length - 1; i >= 0; i--){
                if (currentNode.data == object){
                    return i;
                }
                currentNode = currentNode.prev;
            }
        }
        return -1;
    }

    @Override
    //Function checks existence of object in list
    public boolean exists(Object object) {
        MyNode<T> currentNode = head;
        while(currentNode != null){
            if (currentNode.data == object){
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    //Function returns list of objects as array
    public Object[] toArray() {
        Object[] arr = new Object[length];
        MyNode<T> currentNode = head;
        for (int i = 0; i < length; i++){
            arr[i] = currentNode;
            currentNode = currentNode.next;
        }
        return arr;
    }

    @Override
    //Function clears the list
    public void clear() {
        head = tail = null;
    }

    @Override
    //Function returns size of list
    public int size() {
        return length;
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
        MyNode<T> current = head;
        @Override
        //Function checks is this the end of list
        public boolean hasNext() {
            return current != null;
        }

        @Override
        //Function returns next element of list
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }
    //Class that need to create nodes, that contains values of each element
    private static class MyNode<T>{
        T data; // Value of element
        MyNode<T> next; //Reference to the next node
        MyNode<T> prev; //Reference to the previous node
        MyNode(T newData){
            this.data = newData;
        }
    }
}
