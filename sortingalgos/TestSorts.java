public class TestSorts
{
    public static int[] bogobogo(int[] array)
    {
        return Sorts.bogobogo(array);
    }
    public static int[] tree(int[] array)
    {
        return Sorts.tree(array);
    }
    public static int[] tree234(int[] array)
    {
        return Sorts.tree234(array);
    }
    public static int[] naryHeap(int[] array, int n)
    {
        return Sorts.naryHeap(array, n);
    }
    public static int[] quick(int[] array)
    {
        return Sorts.quick(array);
    }
    public static int[] heap(int[] array)
    {
        return Sorts.heap(array);
    }
    public static int[] tenMinute(int[] array)
    {
        return Sorts.tenMinute(array);
    }
    public static int maximumLengthTenSeconds()
    {
        int lo=1;
        int hi=10000;
        while (lo<hi)
        {
            int middle=(lo+hi)/2;
            System.out.println("Trying: "+middle);
            if (Sorts.sorted(tenMinute(SortBattle.getArray(middle))))
            {
                lo=middle+1;
                System.out.println("Sorted");
            }
            else
            {
                hi=middle-1;
                System.out.println("Not sorted");
            }
        }
        return lo-1;
    }
    public static int nToTheN(int n)
    {
        int total=0;
        for (int i=0; i<(int)(Math.pow(n, n)); i++)
        {
            total++;
        }
        return total;
    }
    public static int[] radix(int[] array)
    {
        return Sorts.radix(array);
    }
    public static int[] bitonic(int[] array)
    {
        return Sorts.bitonic(array);
    }
    public static void bitonic(int[] array, int lo, int hi)
    {
        Sorts.bitonic(array, lo, hi, true);
    }
}