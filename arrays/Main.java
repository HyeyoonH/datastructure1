import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {

    protected static void selectionSort(int[] array){
        for(int lastUnsortedIdx=array.length-1; lastUnsortedIdx > 0; lastUnsortedIdx--){
            int maxIdx = 0;
            for(int i =1; i <= lastUnsortedIdx; i++){
                if(array[i] > array[maxIdx]){
                    maxIdx = i;
                }
            }
            // swap
            int tmp = array[lastUnsortedIdx];
            array[lastUnsortedIdx] = array[maxIdx];
            array[maxIdx] = tmp;
        }
    }

    protected static void bubbleSort(int[] array){
        int sortedIdx = array.length;
        while(sortedIdx > 0){
            for(int i=1; i < sortedIdx; i++){
                if(array[i-1]> array[i]){
                    int tmp = array[i];
                    array[i] = array[i-1];
                    array[i-1] = tmp;
                }
            }    
            sortedIdx--;    
        }
    }

    protected static void insertionSort(int[] array){
        // unsorted
        for(int j=1; j < array.length; j++){
            int elementToInsert = array[j];
            int i=j;

            while((i >0) && (elementToInsert < array[i-1])){
                array[i] = array[i-1];
                i--;
            }
            array[i] = elementToInsert;
        }
    }

    protected static void shellSort(int[] array){
        for(int gap = array.length/2; gap >=1; gap= gap/2){
            for(int i=gap; i < array.length; i++){
                int elementToInsert = array[i];
                int j = i;
                while((j - gap >= 0) && array[j-gap] > elementToInsert){
                    array[j] = array[j-gap];
                    j=j-gap;
                }
                array[j] = elementToInsert;
            }
        }
    }
    
    protected static void divideMergeAndSort(int[] array, int startIdx, int endIdx){
        if(endIdx-startIdx < 2){
            return;
        }

        int midIdx = (endIdx-startIdx)/2 + startIdx;
        divideMergeAndSort(array, startIdx, midIdx);
        divideMergeAndSort(array, midIdx, endIdx);

        int partitionLength=endIdx-startIdx;
        int[] tempArray = new int[partitionLength];
        
        int i=startIdx, j = midIdx, k=0;
        while (i< midIdx && j < endIdx){
            if(array[i] <= array[j]){
                    tempArray[k++] = array[i];
                    i++;
            } else {
                tempArray[k++] = array[j];
                    j++;
            }
        }
        
        while(i < midIdx && k < partitionLength) {
            tempArray[k++] = array[i++];
        }
        while(j <= endIdx && k < partitionLength){
            tempArray[k++] = array[j++];
        }

        System.arraycopy(tempArray, 0, array, startIdx, partitionLength);
    }
    protected static void mergeSort(int[] array){
        // divideMergeAndSort(array, 0, array.length-1);
        divideMergeAndSort(array, 0, array.length);
    }
    
    protected static int partition(int[] array, int start, int end){
        int i = start, j=end;
        int pivotValue=array[i];
        j = j-1;

        while(i<j){
            while(i<j && array[j] >= pivotValue){
                j--;
            }
            if(i < j){
                array[i++] = array[j];
            }
            while(i<j && array[i] <= pivotValue){
                i++;
            }
            if(i < j){
                array[j--] = array[i];
            }
        }
        array[j] = pivotValue;
        return j;
    }
    protected static void quickSort(int[] array, int start, int end){
        if(end - start < 2){
            return;
        }
        int pivot = partition(array, start, end);
        quickSort(array, start, pivot);
        quickSort(array, pivot+1, end);
    }

    protected static void countingSort(int[] array){
        int min = array[0];
        int max = array[0];

        for(int i=1; i < array.length; i++){
            max = (array[i] > max) ? array[i]: max;
            min = (array[i] < min) ? array[i]: min;
        }

        int[] countingArray = new int[max - min + 1];
        for(int i=0; i < array.length; i++){
            int valIdx = array[i] - min;
            countingArray[valIdx]++;
        }

        for(int i=0, j=0; i < countingArray.length; i++){
            while(countingArray[i] > 0 ){
                array[j++] = i+min;
                countingArray[i]--;
            }
        }
    }

    protected static void radixSort(int[] array, int radix, int width){
        for(int i=0; i < width; i++){
            radixSingleSort(array, i, radix);
         }
    }

    protected static void radixSingleSort(int[] array, int i, int radix){
       int[] countingArray = new int[radix];

       for(int elementIdx=0; elementIdx < array.length; elementIdx++){
        int digit = getDigit(i, array[elementIdx], radix);
        countingArray[digit]++;
       }

       // adjust counting array
       for(int j=1; j < countingArray.length; j++){
        countingArray[j] += countingArray[j-1]; 
       }

       int[] tempArray = new int[array.length];
       for(int j = (array.length-1); j >=0; j--){
        int idx = --(countingArray[getDigit(i, array[j], radix)]); 
        tempArray[idx] = array[j];
       }

       for(int j=0; j < tempArray.length; j++){
        array[j] = tempArray[j];
       }

    }

    protected static int getDigit(int position, int value, int radix){
        // int result = value % 10;
        // value /= 10;

        // for(int i=0; i < position; i++){
        //     result = value % 10;
        //     value /= 10;
        // }
        // return result;

        return value/(int)Math.pow(10, position) % radix;
    }

    // end is exclusive
    protected static void descendingMergeSort(int[] array, int start, int end){
        if(end - start < 2){
            return;
        }
        int midIdx = (end-start)/2 + start;
        descendingMergeSort(array, start, midIdx);
        descendingMergeSort(array, midIdx, end);
        int tmpArrayLength = end - start;
        int[] tmpArray = new int[tmpArrayLength];

        int i=start, j=midIdx, k=0;
        while(i< midIdx && j < end){
            // Has to be equal to because, the merge sort needs to be a stable sort
            if(array[i] >= array[j]){
                tmpArray[k++] = array[i++];
            } else{
                tmpArray[k++] = array[j++];
            }
        }
        if(i< midIdx){
            tmpArray[k++] = array[i++];
        }
        if(j< end){
            tmpArray[k++] = array[j++];
        }

        k=0;
        for(int idx = start; idx < end; idx++){
            array[idx] = tmpArray[k++];
        }
    }    
    protected static void descendingMergeSort(int[] array){
        descendingMergeSort(array, 0, array.length);
    }


    protected static void insertionRecusrion(int[] array, int start, int end){
        if(start == end){
            return;
        }
        int i = start;
        int tmp = array[i];

        while(i >0 && tmp < array[i-1]){
            array[i] = array[i-1];
            --i;
        }
        array[i] = tmp;

        // System.out.println(" === ");
        // for(int num: array){
        //     System.out.println(" Number: " + num);
        // }

        insertionRecusrion(array, ++start, end);
    }
    protected static void insertionRecusrion(int[] array){
        insertionRecusrion(array, 1, array.length);
    }

    protected static void radixSortWithAlphabet(String[] strArray, int width){
        while(--width >= 0){
            int[] radixCounting = new int[26];
            for(int i= strArray.length-1; i >= 0; i--){
                char character = strArray[i].charAt(width);
                // ++radixCounting[character - 'a'];
                radixCounting[character - 'a']++;
            }

            for(int i=1; i < radixCounting.length; i++){
                radixCounting[i] += radixCounting[i-1];
            }

            // Sorting using a temp array
            String[] tmpArray = new String[strArray.length];
            for(int i= strArray.length-1; i >= 0; i--){
                char character = strArray[i].charAt(width);
                int idx = --radixCounting[character - 'a'];
                tmpArray[idx] = strArray[i];
            }

            System.arraycopy(tmpArray, 0, strArray, 0, tmpArray.length);

        }
    }

    public static void main(String[] args){
        int[] intArray = {20, 35, -15, 7, 55, 1, -22};
        // bubbleSort(intArray);
        // selectionSort(intArray);
        // insertionSort(intArray);
        // shellSort(intArray);
        // mergeSort(intArray);
        // quickSort(intArray, 0, intArray.length);

        // int[] intArray = {20, 35, 15, 7, 55, 1, 22, 22};
        // countingSort(intArray);

        // int[] radixArray = { 4725, 4586, 1330, 8792, 1594, 5729 };
        // radixSort(radixArray, 10, 4);

        // for(int number: radixArray){
        //     System.out.println(number);
        // }

        ////////////////////
        // CHALLENGES     //
        ////////////////////
        // descendingMergeSort(intArray);
        // insertionRecusrion(intArray);
        // for(int number: intArray){
        //     System.out.println(number);
        // }

        // String[] stringsArray = {"bcdef", "dbaqc", "abcde", "omadd", "bbbbb"};
        // radixSortWithAlphabet(stringsArray, 5);
        // for(String str: stringsArray){
        //     System.out.println(str);
        // }

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Jane", "Jones", 123));
        employeeList.add(new Employee("John", "Doe", 4567));
        employeeList.add(new Employee("Mary", "Smith", 22));
        employeeList.add(new Employee("Mike", "Wilson", 3245));

        employeeList.forEach(employee -> System.out.println(employee));
    }
}