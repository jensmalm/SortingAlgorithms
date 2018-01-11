import java.util.*;
/**
 * This class contains the actual algorithms used by the various Sorts in the tournament.
 */
public class Algorithms {

    /**
     * Java's built in sorting algorithm.
     */
    public static void java(int[] array) {
        Arrays.sort(array);
    }

    public static void insertion(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            while (j >= 0 && array[i] < array[j]) {
                j--;
            }
            int temp = array[i];
            for (int k = i; k > j + 1; k--) {
                array[k] = array[k - 1];
            }
            array[j + 1] = temp;
        }
    }

    public static void binaryInsertion(int[] array)
    {
        System.out.println();
        for (int i=1; i<array.length; i++)
        {
            int lo=0;
            int hi=i;
            while (lo<hi)
            {
                int mid=(lo+hi)/2;
                if (array[i]<array[mid])
                {
                    hi=mid;
                }
                else
                {
                    lo=mid+1;
                }
            }
            int temp=array[i];
            for (int k=i; k>lo; k--)
            {
                array[k]=array[k-1];
            }
            array[lo]=temp;
        }
    }

    private static void insertion(int[] array, int lo, int hi) // used by QuickInsertionTagTeam
    {
        for (int i = lo + 1; i <= hi; i++) {
            int j = i - 1;
            while (j >= lo && array[i] < array[j]) {
                j--;
            }
            int temp = array[i];
            for (int k = i; k > j + 1; k--) {
                array[k] = array[k - 1];
            }
            array[j + 1] = temp;
        }
    }

    private static void insertion(int[] array, int space) // used by shell
    {
        if (array.length>space)
        {
            for (int residue=0; residue<space; residue++) {
                for (int i = residue + space; i < array.length; i += space) {
                    int j = i - space;
                    while (j >= 0 && array[i] < array[j]) {
                        j -= space;
                    }
                    int temp = array[i];
                    for (int k = i; k > j + space; k -= space) {
                        array[k] = array[k - space];
                    }
                    array[j + space] = temp;
                }
            }
        }
        /*

        Compare to insertion():

        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            while (j >= 0 && array[i] < array[j]) {
                j--;
            }
            int temp = array[i];
            for (int k = i; k > j + 1; k--) {
                array[k] = array[k - 1];
            }
            array[j + 1] = temp;
        }
         */
    }

    public static void bogo(int[] array) {
        while (!Technical.sorted(array)) {
            Technical.shuffle(array);
        }
    }

    public static void selection(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int index = i;
            int smallest = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < smallest) {
                    index = j;
                    smallest = array[j];
                }
            }
            Technical.swap(array, i, index);
        }
    }

    public static void bubble(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j - 1] > array[j]) {
                Technical.swap(array, j, j - 1);
                j--;
            }
        }
    }

    /**
     * Creates a binary search tree (where any node's left child is <= itself, and right child is >= itself).
     * Adds the elements of array to the tree
     * Gets them back in sorted order by removing the leftmost element successively until the tree is empty.
     */
    public static void tree(int[] array) {
        BinarySearchTree tree = new BinarySearchTree();
        for (int i : array) {
            tree.add(i);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = tree.removeSmallest();
        }
    }

    /**
     * HeapSort
     *
     * @param array
     */
    public static void heap(int[] array) {
        for (int i = (array.length) / 2; i > 0; i--) {
            int j = i;
            while (2 * j <= array.length) {
                if (2 * j + 1 <= array.length) {
                    if (array[array.length - 2 * j] < array[array.length - j]) {
                        if (array[array.length - 2 * j - 1] < array[array.length - 2 * j]) {
                            int temp = array[array.length - j];
                            array[array.length - j] = array[array.length - 2 * j - 1];
                            array[array.length - 2 * j - 1] = temp;
                            j = 2 * j + 1;
                        } else {
                            int temp = array[array.length - j];
                            array[array.length - j] = array[array.length - 2 * j];
                            array[array.length - 2 * j] = temp;
                            j = 2 * j;
                        }
                    } else if (array[array.length - 2 * j - 1] < array[array.length - j]) {
                        int temp = array[array.length - j];
                        array[array.length - j] = array[array.length - 2 * j - 1];
                        array[array.length - 2 * j - 1] = temp;
                        j = 2 * j + 1;
                    } else {
                        break;
                    }
                } else if ((2 * j <= array.length) && (array[array.length - 2 * j] < array[array.length - j])) {
                    int temp = array[array.length - 2 * j];
                    array[array.length - 2 * j] = array[array.length - j];
                    array[array.length - j] = temp;
                    j = 2 * j;
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            int min = array[array.length - 1];
            array[array.length - 1] = array[i];
            array[i] = min;
            int j = 1;
            while (true) {
                if (array.length - 2 * j <= i) {
                    break;
                } else if (array.length - 2 * j - 1 <= i) {
                    if (array[array.length - 2 * j] < array[array.length - j]) {
                        int temp = array[array.length - 2 * j];
                        array[array.length - 2 * j] = array[array.length - j];
                        array[array.length - j] = temp;
                    }
                    break;
                } else {
                    if (array[array.length - j] > array[array.length - 2 * j]) {
                        if (array[array.length - 2 * j] < array[array.length - 2 * j - 1]) {
                            int temp = array[array.length - j];
                            array[array.length - j] = array[array.length - 2 * j];
                            array[array.length - 2 * j] = temp;
                            j = 2 * j;
                        } else {
                            int temp = array[array.length - j];
                            array[array.length - j] = array[array.length - 2 * j - 1];
                            array[array.length - 2 * j - 1] = temp;
                            j = 2 * j + 1;
                        }
                    } else if (array[array.length - j] > array[array.length - 2 * j - 1]) {
                        int temp = array[array.length - j];
                        array[array.length - j] = array[array.length - 2 * j - 1];
                        array[array.length - 2 * j - 1] = temp;
                        j = 2 * j + 1;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public static void counting(int[] array) {
        if (array.length > 2) {
            int greatest = array[0];
            int smallest = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] < smallest) {
                    smallest = array[i];
                }
                if (array[i] > greatest) {
                    greatest = array[i];
                }
            }
            int[] counts = new int[greatest - smallest + 1];
            for (int i = 0; i < array.length; i++) {
                counts[array[i] - smallest]++;
            }
            int index = 0;
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] != 0) {
                    for (int j = 0; j < counts[i]; j++) {
                        array[index] = i + smallest;
                        index++;
                    }
                }
            }
        }
    }

    /**
     * The standard QuickSort, where the last index is taken to be the pivot. In other variation that we will write later, the pivot will be chosen differently.
     *
     * @param array
     */
    public static void quick(int[] array) {
        quick(array, 0, array.length - 1);
    }

    private static void quick(int[] array, int lo, int hi) // sorts the array from index lo to hi, inclusive
    {
        if (hi == lo + 1) {
            if (array[hi] < array[lo]) {
                Technical.swap(array, lo, hi);
            }
        } else if (hi > lo) {
            int pivot = hi;
            int wall = lo;
            for (int i = lo; i < pivot; i++) {
                if (array[i] < array[pivot]) {
                    Technical.swap(array, wall, i);
                    wall++;
                }
            }
            Technical.swap(array, pivot, wall);
            pivot = wall;
            quick(array, lo, pivot - 1);
            quick(array, pivot + 1, hi);
        }
    }

    public static void medianOf3Quick(int[] array) {
        medianOf3Quick(array, 0, array.length - 1);
    }

    private static void medianOf3Quick(int[] array, int lo, int hi) {
        if (hi == lo + 1) {
            if (array[hi] < array[lo]) {
                Technical.swap(array, lo, hi);
            }
        } else if (hi > lo) {
            int mid = (lo + hi) / 2;
            int pivot;
            if (array[lo] < array[hi]) {
                if (array[mid] < array[lo]) {
                    pivot = lo;
                } else if (array[mid] > array[hi]) {
                    pivot = hi;
                } else {
                    pivot = mid;
                }
            } else {
                if (array[mid] < array[hi]) {
                    pivot = hi;
                } else if (array[mid] > array[lo]) {
                    pivot = lo;
                } else {
                    pivot = mid;
                }
            }
            Technical.swap(array, pivot, hi);
            pivot = hi;
            int wall = lo;
            for (int i = lo; i < pivot; i++) {
                if (array[i] < array[pivot]) {
                    Technical.swap(array, wall, i);
                    wall++;
                }
            }
            Technical.swap(array, pivot, wall);
            pivot = wall;
            medianOf3Quick(array, lo, pivot - 1);
            medianOf3Quick(array, pivot + 1, hi);
        }
    }

    public static void merge(int[] array) {
        merge(array, 0, array.length - 1);
    }

    private static void merge(int[] array, int lo, int hi) {
        if (hi > lo) {
            int mid = (lo + hi + 1) / 2;
            merge(array, lo, mid - 1);
            merge(array, mid, hi);
            int[] results = new int[hi - lo + 1];
            int i = lo;
            int j = mid;
            int index = 0;
            while (i < mid || j <= hi) {
                if (i == mid) {
                    results[index] = array[j];
                    j++;
                } else if (j == hi + 1) {
                    results[index] = array[i];
                    i++;
                } else {
                    if (array[j] < array[i]) {
                        results[index] = array[j];
                        j++;
                    } else {
                        results[index] = array[i];
                        i++;
                    }
                }
                index++;
            }
            for (int k = lo; k <= hi; k++) {
                array[k] = results[k - lo];
            }
        }
    }

    public static void smartBozo(int[] array) {
        while (!Technical.sorted(array)) {
            int i = (int) (Math.random() * array.length);
            int j = (int) (Math.random() * array.length);
            if (j < i) {
                int temp = i;
                i = j;
                j = temp;
            }
            if (array[i] > array[j]) {
                Technical.swap(array, i, j);
            }
        }
    }

    public static void bozo(int[] array) {
        while (!Technical.sorted(array)) {
            int i = (int) (Math.random() * array.length);
            int j = (int) (Math.random() * array.length);
            Technical.swap(array, i, j);
        }
    }

    public static void quickInsertionTagTeam(int[] array, int threshold) {
        quickInsertionTagTeam(array, 0, array.length-1, threshold);
    }

    private static void quickInsertionTagTeam(int[] array, int lo, int hi, int threshold) {
        if (hi - lo < threshold) {
            insertion(array, lo, hi);
        } else {
            int pivot = hi;
            int wall = lo;
            for (int i = lo; i < pivot; i++) {
                if (array[i] < array[pivot]) {
                    Technical.swap(array, wall, i);
                    wall++;
                }
            }
            Technical.swap(array, pivot, wall);
            pivot = wall;
            quickInsertionTagTeam(array, lo, pivot - 1, threshold);
            quickInsertionTagTeam(array, pivot + 1, hi, threshold);
        }
    }

    public static void shell(int[] array)
    {
        int[] spaces={701, 301, 132, 57, 23, 10, 4, 1};
        for (int space:spaces)
        {
            insertion(array, space);
        }
    }

    public static void cocktail(int[] array)
    {
        for (int i=0; i<=array.length/2; i++)
        {
            for (int j=i; j<array.length-1-i; j++)
            {
                if (array[j]>array[j+1])
                {
                    Technical.swap(array, j, j+1);
                }
            }
            for (int j=array.length-2-i; j>=i+1; j--)
            {
                if (array[j]<array[j-1])
                {
                    Technical.swap(array, j-1, j);
                }
            }
        }
    }

    public static void stooge(int[] array)
    {
        stooge(array, 0, array.length-1);
    }
    private static void stooge(int[] array, int lo, int hi)
    {
        if (hi>lo)
        {
            if (array[lo]>array[hi])
            {
                Technical.swap(array, lo, hi);
            }
            if (hi-lo>1)
            {
                int third=(hi+1-lo)/3;
                stooge(array, lo, hi-third);
                stooge(array, lo+third, hi);
                stooge(array, lo, hi-third);
            }
        }
    }

    public static void gnome(int[] array)
    {
        int position=0;
        while (position<array.length-1)
        {
            if (array[position]>array[position+1])
            {
                Technical.swap(array, position, position+1);
                position=Math.max(0, position-1);
            }
            else
            {
                position++;
            }
        }
    }
}