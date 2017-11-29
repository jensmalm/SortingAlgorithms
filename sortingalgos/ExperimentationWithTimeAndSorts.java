import java.util.*;
public class ExperimentationWithTimeAndSorts
{
    public static long time()
    {
        GregorianCalendar g=new GregorianCalendar();
        return System.nanoTime()/1000;
    }
    private static boolean sorted(int[] array)
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
    private static int factorial(int n)
    {
        if (n==0)
        {
            return 1;
        }
        return factorial(n-1)*n;
    }
    public static long bogo(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.bogo(array);
        long t2=time();
        double elapsed=(double)(t2-t1);
        /*
        System.out.println("Bogo Sort, array of length "+length+":");
        System.out.println("Efficiency O(N*N!)");
        System.out.println("Time elapsed="+elapsed/1000);
        */
        double bt=elapsed/(length*factorial(length));
        /*
        System.out.println("Time for O(1)="+bt);
        System.out.println(("Log base 10="+Math.log(bt)/Math.log(10)));
        System.out.println();
        */
       assert(sorted(array));
        return t2-t1;
    }
    public static long bubble(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.bubble(array);
        long t2=time();
        double elapsed=(double)(t2-t1);
        /*
        System.out.println("Bogo Sort, array of length "+length+":");
        System.out.println("Efficiency O(N*N!)");
        System.out.println("Time elapsed="+elapsed/1000);
        */
        double bt=elapsed/(length*length);
        /*
        System.out.println("Time for O(1)="+bt);
        System.out.println(("Log base 10="+Math.log(bt)/Math.log(10)));
        System.out.println();
        */
       assert(sorted(array));
        return t2-t1;
    }
    public static long insertion(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.insertion(array);
        long t2=time();
        double elapsed=(double)(t2-t1);
        /*
        System.out.println("Bogo Sort, array of length "+length+":");
        System.out.println("Efficiency O(N*N!)");
        System.out.println("Time elapsed="+elapsed/1000);
        */
        double bt=elapsed/(length*length);
        /*
        System.out.println("Time for O(1)="+bt);
        System.out.println(("Log base 10="+Math.log(bt)/Math.log(10)));
        System.out.println();
        */
       assert(sorted(array));
        return t2-t1;
    }
    public static long selection(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.selection(array);
        long t2=time();
        double elapsed=(double)(t2-t1);
        /*
        System.out.println("Bogo Sort, array of length "+length+":");
        System.out.println("Efficiency O(N*N!)");
        System.out.println("Time elapsed="+elapsed/1000);
        */
        double bt=elapsed/(length*length);
        /*
        System.out.println("Time for O(1)="+bt);
        System.out.println(("Log base 10="+Math.log(bt)/Math.log(10)));
        System.out.println();
        */
       assert(sorted(array));
        return t2-t1;
    }
    public static long merge(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.merge(array);
        long t2=time();
        double elapsed=(double)(t2-t1);
        /*
        System.out.println("Bogo Sort, array of length "+length+":");
        System.out.println("Efficiency O(N*N!)");
        System.out.println("Time elapsed="+elapsed/1000);
        */
        double bt=elapsed/(length*Math.log(length)/Math.log(2));
        /*
        System.out.println("Time for O(1)="+bt);
        System.out.println(("Log base 10="+Math.log(bt)/Math.log(10)));
        System.out.println();
        */
       assert(sorted(array));
        return t2-t1;
    }
    public static long merge3(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.merge3(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long tree(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.tree(array);
        long t2=time();
        double elapsed=(double)(t2-t1);
        /*
        System.out.println("Bogo Sort, array of length "+length+":");
        System.out.println("Efficiency O(N*N!)");
        System.out.println("Time elapsed="+elapsed/1000);
        */
        double bt=elapsed/(length*Math.log(length)/Math.log(2));
        /*
        System.out.println("Time for O(1)="+bt);
        System.out.println(("Log base 10="+Math.log(bt)/Math.log(10)));
        System.out.println();
        */
       assert(sorted(array));
        return t2-t1;
    }
    public static long quick(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.quick(array);
        long t2=time();
        double elapsed=(double)(t2-t1);
        /*
        System.out.println("Bogo Sort, array of length "+length+":");
        System.out.println("Efficiency O(N*N!)");
        System.out.println("Time elapsed="+elapsed/1000);
        */
        double bt=elapsed/(length*Math.log(length)/Math.log(2));
        /*
        System.out.println("Time for O(1)="+bt);
        System.out.println(("Log base 10="+Math.log(bt)/Math.log(10)));
        System.out.println();
        */
       assert(sorted(array));
        return t2-t1;
    }
    public static long radix(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.radix(array);
        long t2=time();
        double elapsed=(double)(t2-t1);
        /*
        System.out.println("Bogo Sort, array of length "+length+":");
        System.out.println("Efficiency O(N*N!)");
        System.out.println("Time elapsed="+elapsed/1000);
        */
        double bt=elapsed/1000/(length);
        /*
        System.out.println("Time for O(1)="+bt);
        System.out.println(("Log base 10="+Math.log(bt)/Math.log(10)));
        System.out.println();
        */
        assert(sorted(array));
        return t2-t1;
    }
    public static long java(int length)
    {
        int[] array=SortBattle.getArray(length);
        ArrayList<Integer> list=new ArrayList<Integer>();
        for (int i:array)
        {
            list.add(i);
        }
        long t1=time();
        Collections.sort(list);
        long t2=time();
        double elapsed=(double)(t2-t1)/1000;
        /*
        System.out.println("Bogo Sort, array of length "+length+":");
        System.out.println("Efficiency O(N*N!)");
        System.out.println("Time elapsed="+elapsed/1000);
        */
        double bt=elapsed/(length*Math.log(length)/Math.log(2));
        /*
        System.out.println("Time for O(1)="+bt);
        System.out.println(("Log base 10="+Math.log(bt)/Math.log(10)));
        System.out.println();
        */
        return t2-t1;
    }
    public static long dualpivot(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.dualpivot(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long dualPivotBogo(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.dualPivotBogo(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long binaryInsertion(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.insertion2(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long shell(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.shell(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long comb(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.comb(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long clutch(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.clutch(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long heap(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.heap(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long counting(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.counting(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long map(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.map(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long stooge(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.stooge(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long gnome(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.gnome(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long bozo(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.bozo(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long cocktail(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.cocktail(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long heap2(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.heap2(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long triHeap(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.naryHeap(array, 3);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long quaternaryHeap(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.quaternaryHeap(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long quinaryHeap(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.quinaryHeap(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    /*public static long library(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.library(array, 3);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }*/
    public static long heap3(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.naryHeap(array, 3);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long naryHeap(int length, int n)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.naryHeap(array, n);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long bucket(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.bucket(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long smartBozo(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.smartBozo(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long flash(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.flash(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long inplaceMerge(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.inplaceMerge(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long test(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.test(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long guess(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.guess(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long primitiveGuess(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.primitiveGuess(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long balancingTree(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.balancingTree(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long mergesertion(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.mergesertion(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long tree234(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.tree234(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long jens(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.jens(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    public static long bitonic(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.bitonic(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    /*
    public static long medianquick(int length)
    {
        int[] array=SortBattle.getArray(length);
        long t1=time();
        array=Sorts.medianquick(array);
        long t2=time();
        assert(sorted(array));
        return t2-t1;
    }
    */
}