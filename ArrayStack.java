import java.util.AbstractList;
// import java.util.Collection;
// import java.lang.reflect.Array;
public class ArrayStack <T> extends AbstractList <T> {
    Factory <T> f;
    T[] array;
    int n;

    protected void resize(){
        T[] newArray = f.newArray(Math.max(2*n, 1));
        // for(int i=0; i < n; i++){
        //     newArray[i] = array[i];
        // }
        System.arraycopy(array, 0, newArray, 0, n);
        array = newArray;
    }
    public ArrayStack(Class <T> t){
        f = new Factory<T>(t);
        array = f.newArray(1);
        this.n = 0;
    } 
    public T get(int i){
        if(i < 0 || i > n-1) throw new IndexOutOfBoundsException();
        return array[i];
    }
    public int size(){
        return n;
    }
    // Replace an existing element. i should be between (0 =< i <= n-1)
    public T set(int i, T x){
        if(i < 0 || i >= n) throw new IndexOutOfBoundsException();
        T tmp = array[i];
        array[i] = x;
        return tmp;
    }

    // Adding an element. The last index that is ok to add is when i = n
    // Hence 0 <= i <= n But (n+1) is not ok because we don't want anything that is not occupied.
    public void add(int i, T x){
        if(i < 0 || i > n) throw new IndexOutOfBoundsException();
        if(n+1 > array.length) resize();

        T tmp = array[i];
        array[i] = x;
        for(int idx = i+1; idx < n; idx++){
            T elementToShift = array[idx];
            array[idx] = tmp;
            tmp = elementToShift;
        }
        n++;
    }
    // We can only remove elements that are occupied.
    // 0 <= i <= n-1 is occupied. Any i bigger than that will be OutOfBounds.
    public T remove(int i){
        if(i < 0 || i > n-1) throw new IndexOutOfBoundsException();
        T removedElement = array[i];

        for(int idx = i; idx < n; idx++){
            array[idx] = array[idx+1];
        }
        n--;
        if(array.length > 3*n) resize();
        return removedElement;
    } 

}