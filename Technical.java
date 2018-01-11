import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.NoSuchElementException;

/**
 * This class contains all of the methods that help with the logistical formatting of gameplay.
 */
public class Technical
{
    public static final long MAX_ALLOWED_TIME=(long)(Math.pow(10, 10)); // if an algorithm knows in advance that it can not sort an array in this amount of time, it will not try to sort it.

    /**
     * Checks if an array is sorted.
     * Input: an array of integers, array.
     * Output: true if array is sorted (in ascending order), false if not.
     */
    public static boolean sorted(int[] array)
    {
        for (int i=0; i<array.length-1; i++)
        {
            if (array[i]>array[i+1])
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a new array of ints, with the same length and the same element in each index as the original input array.
     * This method allows us to change an array (for example, by sorting it), while still having an original copy to look back at.
     */
    public static int[] copy(int[] array)
    {
        int[] returnme=new int[array.length];
        for (int i=0; i<array.length; i++)
        {
            returnme[i]=array[i];
        }
        return returnme;
    }

    /**
     * According to whatever distribution is desired, randomly picks an array length.
     * As of January 4, the distribution was uniform over the discrete set of powers of 2 between 2^3 and 2^20 (inclusive).
     * Now, instead, the logarithm base 2 is uniform over [0, 24].
     */
    public static int getRandomLength()
    {
        return (int)(Math.pow(2, 24*Math.random()));
    }

    /**
     * Returns an array of length n, where each entry is a (uniform) randomly chosen integer between 0 and Integer.MAX_VALUE-1.
     * The user may change the distribution if they wish.
     */
    public static int[] getRandomArray(int n)
    {
        int[] arr=new int[n];
        for (int i=0; i<arr.length; i++)
        {
            arr[i]=(int)(Math.random()*Math.sqrt(Integer.MAX_VALUE));
        }
        return arr;
    }

    /**
     * Returns the time (in nanoseconds) that it takes sort to sort array.Throws a CorrectnessException if the algorithm is not correct. Throws an ArrayTooLongException if the array is longer than the sort can handle
     */
    public static long getTime(Sort sort, int[] array) throws CorrectnessException
    {
        if (array.length>sort.maxArrayLength)
        {
            return Long.MAX_VALUE;
        }
        int[] copy1=copy(array);
        Arrays.sort(copy1);
        int[] copy2=copy(array);
        long t1=System.nanoTime();
        sort.sort(copy2);
        long t2=System.nanoTime();
        if (!equal(copy1, copy2))
        {
            String st=sort.getName()+" failed to sort an array of length "+Integer.toString(array.length);
            System.out.println(st);
            throw new CorrectnessException(st);
        }
        long time=t2-t1;
        if (time>MAX_ALLOWED_TIME)
        {
            System.out.println(sort.getName()+" failed to sort an array of length "+Integer.toString(array.length)+" in time. Time: "+Long.toString(t2-t1));
            sort.maxArrayLength=array.length-1;
            return Long.MAX_VALUE;
        }
        return time;
    }

    /**
     * For debugging purposes, represents the array in an easily readable String
     */
    public static String asString(int[] array)
    {
        String st="[";
        for (int i=0; i<array.length; i++)
        {
            st=st+Integer.toString(array[i]);
            if (i<array.length-1)
            {
                st=st+", ";
            }
        }
        return st+"]";
    }

    /**
     * For debugging purposes, prints the array in a human-readable way.
     */
    public static void print(int[] array)
    {
        System.out.println(asString(array));
    }

    /**
     * Returns true iff the two arrays are the same length and agree on each element.
     */
    public static boolean equal(int[] arr1, int[] arr2)
    {
        if (arr1.length==arr2.length)
        {
            for (int i=0; i<arr1.length; i++)
            {
                if (arr1[i]!=arr2[i])
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void sleep()
    {
        try
        {
            Thread.sleep(1500);
        }
        catch (InterruptedException ex)
        {}
    }

    public static Object other(Object first, Object second, Object item)
    {
        if (first==item)
        {
            return second;
        }
        if (second==item)
        {
            return first;
        }
        throw new NoSuchElementException();
    }

    /**
     * Returns a new map whose keys are the values of "map". For every possible Integer i, the value of the returned map corresponding to i is an ArrayList of all the Sorts whose value in the original map is i.
     *
     * For example, if the input is {Java: 3, Counting: 3, Insertion: 4, Selection: 2}, the output will be {3: [Java, Counting], 4: [Insertion], 2: [Selection]}.
     *
     * This method will be used by RoundRobin (and possibly elsewhere to establish tiers of sorts that have earned the same amount of points)
     *
     * The output is a TreeMap rather than a HashMap so that it can be ordered by key.
     */
    public static TreeMap<Integer, ArrayList<Sort>> invertMap(Map<Sort, Integer> map)
    {
        TreeMap<Integer, ArrayList<Sort>> pointMap=new TreeMap<Integer, ArrayList<Sort>>();
        for (Sort sort: map.keySet())
        {
            Integer i=map.get(sort);
            if (pointMap.containsKey(i))
            {
                pointMap.get(i).add(sort);
            }
            else
            {
                ArrayList<Sort> singleton=new ArrayList<Sort>();
                singleton.add(sort);
                pointMap.put(i, singleton);
            }
        }
        return pointMap;
    }

    /**
     * Returns an English phrase representing all of the elements in this list.
     * Invariant: The list must have at least one element.
     */
    public static String englishList(ArrayList list)
    {
        if (list.size()==1)
        {
            return list.get(0).toString();
        }
        else if (list.size()==2)
        {
            return list.get(0).toString()+" and "+list.get(1).toString();
        }
        String st="";
        for (int i=0; i<list.size()-1; i++)
        {
            st=st+list.get(i).toString()+", ";
        }
        return st+"and "+list.get(list.size()-1);
    }

    /**
     * Sort the ArrayList of Sorts so that the are in descending order of points.
     * This is implemented with BubbleSort because the array will usually be almost already sort.
     */
    public static void sortByPoints(ArrayList<Sort> list)
    {
        if (list.size()>1)
        {
            int index=0;
            while (index<list.size()-1)
            {
                if (list.get(index).points()>=list.get(index+1).points()) // correct order
                {
                    index++;
                }
                else // incorrect order, bubble list.get(index+1) down
                {
                    int j=index;
                    while (j>=0 && list.get(j).points()<list.get(j+1).points())
                    {
                        swap(list, j, j+1);
                        j--;
                    }
                    index++;
                }
            }
        }
    }

    public static void swap(ArrayList<Sort> list, int i, int j)
    {
        Sort temp=list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static void swap(int[] array, int i, int j)
    {
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    private static String addSpaces(String st, int length)
    {
        int diff=length-st.length();
        int left=diff/2;
        int right=(diff+1)/2;
        for (int i=0; i<left; i++)
        {
            st=" "+st;
        }
        for (int j=0; j<right; j++)
        {
            st=st+" ";
        }
        return st;
    }

    /**
     * Prints a table in a visually readable way.
     * Note: Every row in the table must have the same number of columns, and the table must have at least one row.
     */
    public static void print(String[][] table)
    {
        int r=table.length;
        int c=table[0].length;
        int[] longest=new int[c];
        for (int i=0; i<r; i++)
        {
            for (int j=0; j<c; j++)
            {
                if (table[i][j]==null)
                {
                    table[i][j]="";
                }
                if (table[i][j].length()+2>longest[j])
                {
                    longest[j]=table[i][j].length()+2;
                }
            }
        }
        String line="";
        for (int j=0; j<c; j++)
        {
            for (int k=0; k<longest[j]; k++)
            {
                line=line+"_";
            }
        }
        /* This commented-out block has been replaced with the block below it. If this introduces problems, can revert to the old block.
        System.out.println(line);
        for (int i=0; i<r; i++)
        {
            for (int j=0; j<c; j++)
            {
                System.out.print(addSpaces(table[i][j], longest[j]));
            }
            System.out.println();
            System.out.println(line);
        }
        System.out.println();
        System.out.println();
        */
        String[] strings=new String[2*r+1];
        for (int i=0; i<r; i++)
        {
            String string="";
            for (int j=0; j<c; j++)
            {
                string=string+addSpaces(table[i][j], longest[j]);
            }
            strings[2*i]=line;
            strings[2*i+1]=string;
        }
        strings[2*r]=line;
        for (String string:strings)
        {
            System.out.println(string);
        }
        System.out.println();
    }

    public static void shuffle(int[] array) {
        ArrayList<Integer> list=new ArrayList<Integer>();
        for (int i:array)
        {
            list.add(i);
        }
        for (int i=0; i<array.length; i++)
        {
            array[i]=list.remove((int)(Math.random()*list.size()));
        }
    }
    public static void shuffle(ArrayList<Sort> list)
    {
        ArrayList<Sort> list2=new ArrayList<Sort>();
        for (Sort s:list)
        {
            list2.add(s);
        }
        for (int i=0; i<list.size(); i++)
        {
            list.set(i, list2.remove((int)(Math.random()*list2.size())));
        }
    }

    public static int nextPowerOfTwo(int n)
    {
        int log=(int)(Math.log(n)/Math.log(2));
        if (Math.pow(2, log)==n)
        {
            return n;
        }
        return (int)(Math.pow(2, log+1));
    }
    public static int lastPowerOfTwo(int n)
    {
        int log=(int)(Math.log(n)/Math.log(2));
        return (int)(Math.pow(2, log));
    }

    public static void printLine()
    {
        System.out.println("_____________________________________________________________________");
    }

    /**
     * For example, bracketOrder(8) is {1, 8, 4, 5, 2, 7, 3, 6}, bracketOrder(16) is {1, 16, 8, 9, 4, 13, 5, 12, 2, 15, 7, 10, 3, 14, 6, 11}
     *
     * * @param n: a power of two.
     * @return An ArrayList of integers 1 through n, in the order that they are arranged in a bracket.
     */
    public static ArrayList<Integer> bracketOrder(int n)
    {
        if (n==1)
        {
            ArrayList<Integer> result=new ArrayList<Integer>();
            result.add(1);
            return result;
        }
        ArrayList<Integer> result=bracketOrder(n/2);
        for (int i=result.size()-1; i>=0; i--)
        {
            result.add(i+1, n+1-result.get(i));
        }
        return result;
    }
}