public class MyStack<T>{
    private MyLinkedList<T> list = new MyLinkedList<>(); //The main list that contains all elements of stack

    //Function checks is the stack empty
    public boolean isEmpty(){
        return list.size() != 0;
    }

    //Function return number of elements in stack
    public int size(){
        return list.size();
    }

    //Function returns last element of stack
    public T peek(){
        if(list.size() == 0){
            throw new IndexOutOfBoundsException();
        }
        return list.getLast();
    }

    //Function adds new element to the end of stack
    public void push(T item){
        list.addLast(item);
    }

    //Function deletes last element in stack
    public void pop(){
        if(list.size() == 0){
            throw new NegativeArraySizeException();
        }
        list.removeLast();
    }
}
