import java.util.AbstractList;

public class ArrayDeque<T> extends AbstractList<T> {
    protected Factory<T> f;
    protected T[] array;
    protected int head; // Indext of next element to de-queue
    protected int n; // number of elements in the queue

    public ArrayDeque (Class <T> t){
        f = new Factory <T> (t);
        array = f.newArray(1);
        head = 0;
        n = 0;
    }

    protected void resize(){
        T[] newArray = f.newArray(Math.max(2*n,1));
        for(int i=0; i < n; i++){
            newArray[i] = array[(head+i)% array.length];
        }
        array = newArray;
        head =0;
    }
    public int size(){
        return n;
    }
    public T get(int i){
        if(i <0 || i > n-1) throw new IndexOutOfBoundsException();
        return array[(head+i)%array.length];
    }
    // Set and returns the original element. 
    public T set(int i, T x){
        if(i<0 || i > n-1) throw new IndexOutOfBoundsException();
        T originalElement = array[(i+head)%array.length];
        array[(i+head)%array.length] = x;
        return originalElement;
    }

    public void add(int i, T x){
        if (n+1 > array.length) resize();
        if(i < n/2) {
            head = (head ==0) ? array.length-1 : head-1;
            for(int idx = 0; idx < i; idx++){
                array[(head+idx)%array.length] = array[(head+1+idx)%array.length];
            }
        }else {
            for(int idx=n; idx > i; idx--){
                array[(head+idx)%array.length] = array[(head+idx-1)%array.length];
            }
        }
        array[(head+i)%array.length] = x;
        n++;
    }

    public T remove(int i){
        T result = array[(head+i)%array.length];
        if(i< n/2){
            for(int idx=i; idx > 0; idx--){
                array[(head+idx)%array.length] = array[(head+idx-1)%array.length];
            }
            head = (head +1) % array.length;
        } else {
            for(int idx=i; idx < n; idx++){
                array[(idx+head)%array.length] = array[(idx+head+1)%array.length];
            }
        }
        n--;
        if(array.length > 3*n) resize();
        return result;
    }
    public void clear(){
        n=0;
        resize();
    }
}