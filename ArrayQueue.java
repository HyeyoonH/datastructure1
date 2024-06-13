import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue <T> extends AbstractQueue<T>{
    protected Factory <T> f;
    protected T[] array;
    protected int startIdx; // Index of next element to de-queue
    protected int n; // Number of elements in the queue
    
    protected void resize(){
        int numberOfElements = Math.max(n*2, 1);
        T[] newArray = f.newArray(numberOfElements);

        for(int i=0; i<n; i++){
            int newIndx = (startIdx+i)%array.length;
            newArray[newIndx] = array[i];
        }
        array = newArray;
        startIdx=0;
    }
    
    public ArrayQueue(Class <T> t){
        f = new Factory<T> (t);
        array = f.newArray(1);
        n = 0;
        startIdx = 0;
    }

    // This is a method named iterator that returns an Iterator<T>. T is a generic type parameter
    public Iterator<T> iterator(){
        // nside the iterator method, there is a local class called QueueIterator defined. 
        // This class implements the Iterator<T> interface.
        class QueueIterator implements Iterator<T>{
            int queueIdx;    
        
            public QueueIterator(){
                queueIdx=0;
            }

            public boolean hasNext(){
                return (queueIdx <n);
            }

            public T next(){
                if (queueIdx > n) throw new NoSuchElementException();
                T element = array[(startIdx+queueIdx)%array.length];
                queueIdx++;
                return element;
            }

            public void remove(){
                throw new UnsupportedOperationException();
            }
        }
        return new QueueIterator();
    }

    public int size(){
        return n;
    }

    public boolean offer(T x){
        return add(x);
    }

    public boolean add(T x){
        if ((n+1) > array.length) resize();
        array[(startIdx+n)%array.length] = x;
        n++;
        return true;
    }

    // Retrieves the element at the front without removing it.
    public T peek(){
        T result = (n>0)? array[startIdx]: null;
        return result;
    }

    // Removes & returns the element at the front of the collection
    public T remove(){
        if (n ==0) throw new NoSuchElementException();
        T result = array[startIdx];
        startIdx = (startIdx+1) % array.length;
        n--;
        if (array.length >= 3*n) resize();
        return result;
    }

    public T poll(){
        return n ==0 ? null : remove();
    }
}