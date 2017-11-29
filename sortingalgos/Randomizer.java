import java.util.*;
public class Randomizer
{
    public static String[] shuffle(String[] array)
    {
        ArrayList<String> list=new ArrayList<String>();
        for (int i=0; i<array.length; i++)
        {
            int index=(int)(Math.random()*(i+1));
            list.add(index, array[i]);
        }
        for (int i=0; i<array.length; i++)
        {
            array[i]=list.get(i);
        }
        return array;
    }
    public static int[] shuffle(int[] array)
    {
        ArrayList<Integer> list=new ArrayList<Integer>();
        for (int i=0; i<array.length; i++)
        {
            int index=(int)(Math.random()*(i+1));
            list.add(index, array[i]);
        }
        for (int i=0; i<array.length; i++)
        {
            array[i]=list.get(i);
        }
        return array;
    }
    public static ArrayList<Integer> shuffle(ArrayList<Integer> list)
    {
        int[] array=new int[list.size()];
        for (int i=0; i<array.length; i++)
        {
            array[i]=list.get(i);
        }
        array=shuffle(array);
        for (int i=0; i<array.length; i++)
        {
            list.set(i, array[i]);
        }
        return list;
    }
}