import java.util.Iterator;

public class Main {
    private static void practiceArrayQueue(){
        ArrayQueue<Integer> queue = new ArrayQueue<>(Integer.class);
        int m=10, n=5;
        for(int i=0; i < m; i++){  
            queue.add(i);
            if(queue.size() > n){
                Integer x = queue.remove();
                assert(x == i-n);
            }
            System.out.println("i is: "+ i);
        }
        System.out.println("After addition the queue contents are:");
        Iterator<Integer> iterator = queue.iterator();
        System.out.println("size: " + queue.size());
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    private static void practiceArrayStack(){
        ArrayStack < Integer > arrayStack = new ArrayStack <>(Integer.class);
        arrayStack.add(0, 1);
        arrayStack.add(1, 2);
        arrayStack.add(2, 3);
        System.out.println(arrayStack.size());
        int removedElement = arrayStack.remove(1);
        for(int number:arrayStack){
            System.out.println(number);
        }
    }

    private static void practiceArrayDeque(){
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(Integer.class);
        arrayDeque.add(0, 1);
        arrayDeque.add(1, 2);
        arrayDeque.add(1, 3);
        System.out.println("Deque: " + arrayDeque);

        int removedElement = arrayDeque.remove(1);
        System.out.println("Removed element: " + removedElement);

        System.out.println("Deque after removal: " + arrayDeque);

        arrayDeque.clear();
        System.out.println("Deque after clear: " + arrayDeque);
    } 

    public static void main(String[] args){
        // practiceArrayStack();
        // practiceArrayQueue();
        practiceArrayDeque();
    }

}