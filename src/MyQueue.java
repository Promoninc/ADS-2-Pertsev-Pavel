public class MyQueue<T> {
    private MyLinkedList<T> list = new MyLinkedList<>(); //The main list for elements of queue

    public boolean isEmpty(){ //Function checks is the queue empty
        return list.size() != 0;
    }

    public int size(){ //Function returns number of elements of queue
        return list.size();
    }

    public T peek(){ //Function returns front element of queue
        return list.getFirst();
    }

    public void enqueue(T item){ //Function adds element to the front of queue
        list.addLast(item);
    }

    public void dequeue(){
        list.removeFirst();
    }
}
