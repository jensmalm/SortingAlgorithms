import java.util.*;
public class Sorts
{
    private static void print(int[] array)
    {
        for (int i=0; i<array.length; i++)
        {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    public static boolean sorted(int[] array)
    {
        for (int i=1; i<array.length; i++)
        {
            if (array[i]<array[i-1])
            {
                return false;
            }
        }
        return true;
    }

    public static int[] bozo(int[] array) //O(N*N!)
    {
        while (!sorted(array))
        {
            int r1=(int)(Math.random()*array.length);
            int r2=(int)(Math.random()*array.length);
            int temp=array[r2];
            array[r2]=array[r1];
            array[r1]=temp;
        }
        return array;
    }

    public static int[] bubble(int[] array) //O(N^2)
    {
        for (int i=array.length-1; i>=0; i--)
        {
            int switches=0;
            for (int j=0; j<i; j++)
            {
                if (array[j]>array[j+1])
                {
                    int temp=array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                    switches++;
                }
            }
            if (switches==0)
            {
                return array;
            }
        }
        return array;
    }

    public static int[] cocktail(int[] array)
    {
        for (int i=0; i<=array.length/2; i++)
        {
            for (int j=i; j<array.length-1-i; j++)
            {
                if (array[j]>array[j+1])
                {
                    int temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
            for (int j=array.length-2-i; j>=i+1; j--)
            {
                if (array[j]<array[j-1])
                {
                    int temp=array[j-1];
                    array[j-1]=array[j];
                    array[j]=temp;
                }
            }
        }
        return array;
    }

    public static int[] selection(int[] array)//O(N^2)
    {
        for (int i=0; i<array.length; i++)
        {
            int smallest=array[i];
            int index=i;
            for (int j=i+1; j<array.length; j++)
            {
                if (array[j]<smallest)
                {
                    index=j;
                    smallest=array[j];
                }
            }
            for (int k=index; k>i; k--)
            {
                array[k]=array[k-1];
            }
            array[i]=smallest;
        }
        return array;
    }

    private static int[] insertion(int[] array, int lo, int hi)
    {
        for (int i=lo; i<=hi; i++)
        {
            int smallest=array[i];
            int index=i;
            for (int j=i+1; j<array.length; j++)
            {
                if (array[j]<smallest)
                {
                    index=j;
                    smallest=array[j];
                }
            }
            for (int k=index; k>i; k--)
            {
                array[k]=array[k-1];
            }
            array[i]=smallest;
        }
        return array;
    }

    public static int[] insertion(int[] array)//O(N^2)
    {
        for (int i=0; i<array.length; i++)
        {
            int compare=array[i];
            array[i]=0;
            int j=i-1;
            while (true)
            {
                if (j<0)
                {
                    array[0]=compare;
                    break;
                }
                if (compare>=array[j])
                {
                    array[j+1]=compare;
                    break;
                }
                else
                {
                    array[j+1]=array[j];
                    j--;
                }
            }
        }
        return array;
    }

    public static int[] tree(int[] array)
    {
        //Without using iterators:
        /*BinarySearchTree bt=new BinarySearchTree();
        for (int i:array)
        {
        bt.add(i);
        }
        for (int i=0; i<array.length; i++)
        {
        array[i]=bt.remove();
        }
        return array;*/

        //Using iterators:
        BinarySearchTree bt=new BinarySearchTree();
        for (int i=0; i<array.length; i++)
        {
            bt.add(array[i]);
        }
        Iterator<Integer> itr=bt.iterator();
        assert(itr!=null);
        for (int i=0; i<array.length; i++)
        {
            array[i]=(int)(itr.next());
        }
        return array;
    }

    public static int[] balancingTree(int[] array)
    {
        BalancingBinarySearchTree tree=new BalancingBinarySearchTree();
        for (int i:array)
        {
            tree.add(i);
        }
        for (int i=0; i<array.length; i++)
        {
            array[i]=tree.remove();
        }
        return array;
    }

    /*
    public static int[] tree(int[] array)//O(NlogN)
    {
    Integer[] tree=new Integer[Math.min(3000000, (int)(Math.pow(2,
    array.length)))];
    for (int i=0; i<array.length; i++)//add to the tree
    {
    int position=1;
    while (tree[position]!=null)
    {
    if (array[i]<=tree[position])
    {
    position=2*position;
    }
    else
    {
    position=2*position+1;
    }
    }
    tree[position]=array[i];
    }
    for (int i=0; i<array.length; i++)
    {
    //remove the lowest value from the tree and put it in array[i]
    int position=1;
    while (true)
    {
    if (tree[2*position]!=null)
    {
    tree[position]=null;
    break;
    }
    else
    {
    position*=2;
    }
    }
    }
    return array;
    }
     */
    public static int[] merge2(int[] array)//O(NlogN)
    {
        if (array.length<2)
        {
            return array;
        }
        int[] left=new int[array.length/2];
        int[] right=new int[array.length-left.length];
        for (int i=0; i<left.length; i++)
        {
            left[i]=array[i];
        }
        for (int i=0; i<right.length; i++)
        {
            right[i]=array[left.length+i];
        }
        left=merge(left);
        right=merge(right);
        int[] newarray=new int[array.length];
        int leftIndex=0;
        int rightIndex=0;
        for (int i=0; i<array.length; i++)
        {
            if (leftIndex>=left.length)
            {
                newarray[i]=right[rightIndex];
                rightIndex++;
            }
            else if (rightIndex>=right.length)
            {
                newarray[i]=left[leftIndex];
                leftIndex++;
            }
            else
            {
                if (left[leftIndex]<right[rightIndex])
                {
                    newarray[i]=left[leftIndex];
                    leftIndex++;
                }
                else
                {
                    newarray[i]=right[rightIndex];
                    rightIndex++;
                }
            }
        }
        return newarray;
    }

    /*
    public static int[] quick(int[] array)
    {
    if (array.length<2)
    {
    return array;
    }
    if (array[0]>array[array.length-1])
    {
    int temp=array[0];
    array[0]=array[array.length-1];
    array[array.length-1]=temp;
    }
    int leftLength=1;
    int rightLength=0;
    for (int i=1; i<array.length; i++)
    {
    if (array[i]<array[0])
    {
    leftLength++;
    }
    else
    {
    rightLength++;
    }
    }
    int[] left=new int[leftLength];
    int[] right=new int[rightLength];
    left[0]=array[0];
    int leftIndex=1;
    int rightIndex=0;
    for (int i=1; i<array.length; i++)
    {
    if (array[i]<array[0])
    {
    left[leftIndex]=array[i];
    leftIndex++;
    }
    else
    {
    right[rightIndex]=array[i];
    rightIndex++;
    }
    }
    left=quick(left);
    right=quick(right);
    for (int i=0; i<left.length; i++)
    {
    array[i]=left[i];
    }
    for (int i=0; i<right.length; i++)
    {
    array[i+left.length]=right[i];
    }
    return array;
    }
     */

    /*public static int[] radix(int[] array)
    {
    /*
    int greatest=0;
    for (int i=0; i<array.length; i++)
    {
    if (array[i]>greatest)
    {
    greatest=array[i];
    }
    }
    for (int pow=1; pow<=greatest; pow*=10)
    {
    ArrayList<Queue<Integer>> digits=new ArrayList<Queue<Integer>>();
    for (int i=0; i<10; i++)
    {
    digits.add(new Queue<Integer>());
    }
    for (int i=0; i<array.length; i++)
    {
    int edited=array[i]/pow;
    edited=edited%10;
    digits.get(edited).add(array[i]);
    }
    int i=0;
    for (int j=0; j<10; j++)
    {
    while (!digits.get(j).isEmpty())
    {
    array[i]=digits.get(j).remove();
    i++;
    }
    }
    if (pow==1410065408)
    {
    break;
    }
    }
    return array;
    if (array.length<2)
    {
    return array;
    }
    int base=array.length;
    ArrayList<Queue<Integer>> queues=new ArrayList<Queue<Integer>>();
    for (int i=0; i<base; i++)
    {
    queues.add(new Queue<Integer>());
    }
    int greatest=Integer.MIN_VALUE;
    int least=Integer.MAX_VALUE;
    for (int i=0; i<array.length; i++)
    {
    if (array[i]>greatest)
    {
    greatest=array[i];
    }
    else if (array[i]<least)
    {
    least=array[i];
    }
    }
    for (int i=0; i<array.length; i++)
    {
    array[i]=array[i]-least;
    }
    int loops=(int)(Math.log(greatest-least)/Math.log(base))+1;
    for (int i=0; i<loops; i++)
    {
    int power=(int)(Math.pow(base, i));
    for (int j=0; j<array.length; j++)
    {
    int figure=(array[j]/power)%base;
    queues.get(figure).add(array[j]);
    }
    int index=0;
    for (int j=0; j<base; j++)
    {
    Queue<Integer> q=queues.get(j);
    int size=q.size();
    for (int k=0; k<size; k++)
    {
    array[index]=q.remove();
    index++;
    }
    }
    }
    for (int i=0; i<array.length; i++)
    {
    array[i]+=least;
    }
    return array;
    }
     */

    public static int[] counting(int[] array)
    {
        int greatest=array[0];
        int smallest=array[0];
        for (int i=1; i<array.length; i++)
        {
            if (array[i]<smallest)
            {
                smallest=array[i];
            }
            if (array[i]>greatest)
            {
                greatest=array[i];
            }
        }
        int[] counts=new int[greatest-smallest+1];
        for (int i=0; i<array.length; i++)
        {
            counts[array[i]-smallest]++;
        }
        int index=0;
        for (int i=0; i<counts.length; i++)
        {
            if (counts[i]!=0)
            {
                for (int j=0; j<counts[i]; j++)
                {
                    array[index]=i+smallest;
                    index++;
                }
            }
        }
        return array;
    }

    public static int[] insertion2(int[] array)
    {
        for (int i=0; i<array.length; i++)
        {
            int compare=array[i];
            int lo=0;
            int hi=i;
            while (lo<hi)
            {
                int j=(hi+lo)/2;
                if (hi==1)
                {
                    if (compare<array[0])
                    {
                        hi=0;
                    }
                    else
                    {
                        lo=1;
                    }
                }
                else if (j==i-1)
                {
                    if (compare>array[j])
                    {
                        lo++;
                    }
                    else
                    {
                        hi--;
                    }
                }
                else
                {
                    if (compare>array[j])
                    {
                        lo=j+1;
                    }
                    else if (compare==array[j])
                    {
                        lo=j;
                        hi=j;
                    }
                    else
                    {
                        hi=j;
                    }
                }
            }
            for (int j=i; j>hi; j--)
            {
                array[j]=array[j-1];
            }
            array[hi]=compare;
        }
        return array;
    }

    public static int[] dualpivot(int[] array)
    {
        if (array.length<2)
        {
            return array;
        }
        if (array.length==2)
        {
            if (array[0]<=array[1])
            {
                return array;
            }
            else
            {
                int[] returnme=new int[2];
                returnme[0]=array[1];
                returnme[1]=array[0];
                return returnme;
            }
        }
        int a=array[(int)(Math.random()*array.length)];
        int b=array[(int)(Math.random()*array.length)];
        if (b==a)
        {
            for (int i=0; i<array.length; i++)
            {
                if (array[i]!=a)
                {
                    b=array[i];
                }
            }
            if (a==b)
            {
                return array;
            }
        }
        int min=Math.min(a, b);
        int max=Math.max(a, b);
        int leftLength=0;
        int midLength=0;
        int rightLength=0;
        for (int i=0; i<array.length; i++)
        {
            if (array[i]<=min)
            {
                leftLength++;
            }
            else if (array[i]>=max)
            {
                rightLength++;
            }
            else
            {
                midLength++;
            }
        }
        int[] left=new int[leftLength];
        int[] mid=new int[midLength];
        int[] right=new int[rightLength];
        int leftIndex=0;
        int midIndex=0;
        int rightIndex=0;
        for (int i=0; i<array.length; i++)
        {
            if (array[i]<=min)
            {
                left[leftIndex]=array[i];
                leftIndex++;
            }
            else if (array[i]>=max)
            {
                right[rightIndex]=array[i];
                rightIndex++;
            }
            else
            {
                mid[midIndex]=array[i];
                midIndex++;
            }
        }
        left=dualpivot(left);
        mid=dualpivot(mid);
        right=dualpivot(right);
        for (int i=0; i<left.length; i++)
        {
            array[i]=left[i];
        }
        for (int i=0; i<mid.length; i++)
        {
            array[i+left.length]=mid[i];
        }
        for (int i=0; i<right.length; i++)
        {
            array[i+left.length+mid.length]=right[i];
        }
        return array;
    }

    public static int[] insertion(int[] array, int size)
    {
        for (int i=0; i<size; i++)
        {
            int littleLength=array.length/size;
            if (array.length%size>i)
            {
                littleLength++;
            }
            int[] insertionMe=new int[littleLength];
            for (int j=0; j<littleLength; j++)
            {
                insertionMe[j]=array[size*j+i];
            }
            insertionMe=insertion(insertionMe);
            for (int j=0; j<littleLength; j++)
            {
                array[size*j+i]=insertionMe[j];
            }
        }
        return array;
    }

    public static int[] shell(int[] array, int[] spaces)
    {
        for (int i=0; i<spaces.length; i++)
        {
            if (spaces[i]<array.length)
            {
                insertion(array, spaces[i]);
            }
        }
        return array;
    }

    public static int[] shell(int[] array)
    {
        int[] spaces={701, 301, 132, 57, 23, 10, 4, 1};
        return shell(array, spaces);
    }

    /*
    public static int[] shell(int[] array) //without creating a new
    array. One would think this would be
    {
    int[] spaces={701, 301, 132, 57, 23, 10, 4, 1};//better but for
    some reason it is worse
    for (int i=0; i<spaces.length; i++)
    {
    if (spaces[i]<array.length)
    {
    for (int j=0; j<array.length; j+=spaces[i])
    {
    int compare=array[j];
    array[j]=0;
    int k=j-spaces[i];
    boolean done=false;
    while (!done)
    {
    if (k<0)
    {
    array[k+spaces[i]]=compare;
    done=true;
    }
    else if (compare>=array[k])
    {
    array[k+spaces[i]]=compare;
    done=true;
    }
    else
    {
    array[k+spaces[i]]=array[k];
    array[k]=0;
    k-=spaces[i];
    }
    }
    }
    }
    }
    return array;
    }
     */
    public static int[] dualPivotBogo(int[] array)
    {
        while (!sorted(array))
        {
            for (int i=0; i<2; i++)
            {
                int a=(int)(Math.random()*array.length);
                int b=(int)(Math.random()*array.length);
                int temp=array[a];
                array[a]=array[b];
                array[b]=temp;
            }
        }
        return array;
    }

    private static int[] bubble(int[] array, int size)
    {
        for (int i=0; i<size; i++)
        {
            int littleLength=array.length/size;
            if (array.length%size>i)
            {
                littleLength++;
            }
            int[] bubbleMe=new int[littleLength];
            for (int j=0; j<littleLength; j++)
            {
                bubbleMe[j]=array[size*j+i];
            }
            bubbleMe=bubble(bubbleMe);
            for (int j=0; j<littleLength; j++)
            {
                array[size*j+i]=bubbleMe[j];
            }
        }
        return array;
    }

    public static int[] comb(int[] array)
    {
        int[] spaces={4200, 3231, 2485, 1912, 1471, 1131, 870, 669, 515, 396, 305, 234, 180, 139, 107, 82, 63, 49, 37, 29, 22, 17, 13, 10, 7, 5, 3, 2, 1};
        for (int i=0; i<spaces.length; i++)
        {
            if (spaces[i]<array.length)
            {
                array=bubble(array, spaces[i]);
            }
        }
        return array;
    }

    public static int[] clutch(int[] array)//Might still need improvement
    {
        if (array.length<500)
        {
            return insertion(array);
        }
        if (array.length<1000)
        {
            return quick(array);
        }
        return counting(array);
    }

    public static int[] heap2(int[] array)
    {
        return naryHeap(array, 2);
    }

    public static int[] originalheap(int[] array) //The first heap sort I wrote (senior year in high school)
    //Not quite as efficient as possible since it is not inplace and does not remove the proper way.
    {
        Integer[] heap=new Integer[array.length+1];
        for (int i=0; i<array.length; i++)
        {
            int index=i+1;
            heap[index]=array[i];
            while ((heap[index/2]!=null) && (heap[index]<heap[index/2]))
            {
                int temp=heap[index];
                heap[index]=heap[index/2];
                heap[index/2]=temp;
                index=index/2;
            }
        }
        for (int i=0; i<array.length; i++)
        {
            array[i]=heap[1];
            int index=1;
            while (true)
            {
                int left=2*index;
                int right=2*index+1;
                boolean hasleft=((left<heap.length) && (heap[left]!=null));
                boolean hasright=((right<heap.length) && (heap[right]!=null));
                int nextIndex=left;
                if ((!hasleft) && (!hasright))
                {
                    heap[index]=null;
                    break;
                }
                else if (!hasleft)
                {
                    nextIndex=right;
                }
                else if (hasleft && hasright)
                {
                    if (heap[left]>heap[right])
                    {
                        nextIndex=right;
                    }
                }
                heap[index]=heap[nextIndex];
                index=nextIndex;
            }
        }
        return array;
    }

    public static int[] triHeap(int[] array)
    {
        Integer[] heap=new Integer[array.length];
        for (int i=0; i<array.length; i++)
        {
            heap[i]=i;
            int index=i;
            while ((heap[(index-1)/3]!=null) && (heap[index]<heap[(index-1)/3]))
            {
                int prev=(index-1)/3;
                int temp=heap[index];
                heap[index]=heap[prev];
                heap[prev]=temp;
                index=prev;
            }
        }
        for (int i=0; i<array.length; i++)
        {
            array[i]=heap[0];
            int index=0;
            while (true)
            {
                int left=3*index+1;
                int mid=3*index+2;
                int right=3*index+3;
                boolean hasLeft=((left<heap.length) && (heap[left]!=null));
                boolean hasMid=((mid<heap.length) && (heap[mid]!=null));
                boolean hasRight=((right<heap.length) && (heap[right]!=null));
                int nextIndex=left;
                if (hasLeft)
                {
                    if ((hasMid) && (heap[mid]<heap[left]))
                    {
                        nextIndex=mid;
                    }
                    if ((hasRight) && (heap[right]<heap[nextIndex]))
                    {
                        nextIndex=right;
                    }
                }
                else if (hasMid)
                {
                    if (hasRight)
                    {
                        if (heap[mid]<=heap[right])
                        {
                            nextIndex=mid;
                        }
                        else
                        {
                            nextIndex=right;
                        }
                    }
                    else
                    {
                        nextIndex=mid;
                    }
                }
                else if (hasRight)
                {
                    nextIndex=right;
                }
                else
                {
                    heap[index]=null;
                    break;
                }
                heap[index]=heap[nextIndex];
                index=nextIndex;
            }
        }
        return array;
    }

    public static int[] naryHeap(int[] array, int n) //unlike the previous naryHeap algorithm, runs inplace!
    {
        int temp=0;
        int min=0;
        int minChild=0;
        for (int i=1; i<=array.length; i++)
        {
            int j=i;
            while (j>1)
            {
                if (array[array.length-j]<array[array.length-(j+n-2)/n])
                {
                    temp=array[array.length-j];
                    array[array.length-j]=array[array.length-(j+n-2)/n];
                    array[array.length-(j+n-2)/n]=temp;
                    j=(j+n-2)/n;
                }
                else
                {
                    break;
                }
            }
        }
        for (int i=0; i<array.length; i++)
        {
            temp=array[array.length-1];
            array[array.length-1]=array[i];
            array[i]=temp;
            int j=1;
            while (array.length-j>i)
            {
                min=array[array.length-j];
                minChild=-1;
                for (int k=0; k<n; k++)
                {
                    int child=n*(j-1)+k+2;
                    if (array.length-child<=i)
                    {
                        break;
                    }
                    if (array[array.length-child]<min)
                    {
                        min=array[array.length-child];
                        minChild=child;
                    }
                }
                if (minChild!=-1)
                {
                    temp=array[array.length-j];
                    array[array.length-j]=array[array.length-minChild];
                    array[array.length-minChild]=temp;
                    j=minChild;
                }
                else
                {
                    break;
                }
            }
        }
        return array;

        /*for (int i=(array.length+n-2)/n; i>=1; i--)
        {
        print(array);
        int j=i;
        while (j<=array.length)
        {
        int nextJ=-1;
        int value=array[array.length-j];
        for (int k=2; k<n+2; k++)
        {
        if ((j-1)*n+k<=array.length)
        {
        if (array[array.length-((j-1)*n+k)]<value)
        {
        value=array[array.length-((j-1)*n+k)];
        nextJ=(j-1)*n+k;
        }
        }
        else
        {
        break;
        }
        }
        if (nextJ==-1)
        {
        break;
        }
        else
        {
        int temp=array[array.length-j];
        array[array.length-j]=array[array.length-nextJ];
        array[array.length-nextJ]=temp;
        j=nextJ;
        }
        }
        }
        for (int i=0; i<array.length-1; i++)
        {
        print(array);
        int temp=array[i];
        array[i]=array[array.length-1];
        array[array.length-1]=temp;
        int j=1;
        while (j<=array.length-i)
        {
        int nextJ=-1;
        int value=array[array.length-j];
        for (int k=2; k<n+2; k++)
        {
        if ((j-1)*n+k<=array.length-i)
        {
        if (array[array.length-((j-1)*n+k)]<value)
        {
        value=array[array.length-((j-1)*n+k)];
        nextJ=(j-1)*n+k;
        }
        }
        else
        {
        break;
        }
        }
        if (nextJ==-1)
        {
        break;
        }
        else
        {
        temp=array[array.length-j];
        array[array.length-j]=array[array.length-nextJ];
        array[array.length-nextJ]=temp;
        j=nextJ;
        }
        }
        }
        return array;
        }*/}

    public static int[] map(int[] array)
    {
        java.util.TreeSet<Integer> set=new java.util.TreeSet<Integer>();
        java.util.HashMap<Integer, Integer> map=new java.util.HashMap<Integer, Integer>();
        for (int i=0; i<array.length; i++)
        {
            if (map.containsKey(array[i]))
            {
                int a=map.get(array[i]);
                map.put(array[i], 1+a);
            }
            else
            {
                set.add(array[i]);
                map.put(array[i], 1);
            }
        }
        int index=0;
        for (Integer i:set)
        {
            int instances=map.get(i);
            for (int j=0; j<instances; j++)
            {
                array[index]=i;
                index++;
            }
        }
        return array;
    }

    public static int[] stoogeHelper(int[] array, int i, int j)
    {
        if (j-i<2)
        {
            return array;
        }
        if (array[i]>array[j-1])
        {
            int temp=array[i];
            array[i]=array[j-1];
            array[j-1]=temp;
        }
        if (j-i==2)
        {
            return array;
        }
        int t=(j-i)/3;
        array=stoogeHelper(array, i, j-t);
        array=stoogeHelper(array, i+t, j);
        array=stoogeHelper(array, i, j-t);
        return array;
    }

    public static int[] stooge(int[] array)
    {
        return stoogeHelper(array, 0, array.length);
    }

    public static int[] gnome(int[] array)
    {
        int position=0;
        while (true)
        {
            int index=-1;
            for (int i=0; i<array.length-1; i++)
            {
                if (array[i+1]<array[i])
                {
                    index=i;
                    break;
                }
            }
            if (index==-1)
            {
                return array;
            }
            int temp=array[index+1];
            array[index+1]=array[index];
            array[index]=temp;
            position=Math.max(0, index-1);
        }
    }

    public static int[] java(int[] array)
    {
        Arrays.sort(array);
        return array;
    }

    private static void mergeHelper(int[] array, int start, int end)
    {
        if (end-start<1)
        {
            return;
        }
        int middle=(start+end+1)/2;
        mergeHelper(array, start, middle-1);
        mergeHelper(array, middle, end);
        int[] mergeArray=new int[end-start+1];
        int index1=start;
        int index2=middle;
        for (int i=0; i<mergeArray.length; i++)
        {
            if (index1==middle)
            {
                mergeArray[i]=array[index2];
                index2++;
            }
            else if (index2==end+1)
            {
                mergeArray[i]=array[index1];
                index1++;
            }
            else if (array[index1]<=array[index2])
            {
                mergeArray[i]=array[index1];
                index1++;
            }
            else
            {
                mergeArray[i]=array[index2];
                index2++;
            }
        }
        for (int i=0; i<mergeArray.length; i++)
        {
            array[start+i]=mergeArray[i];
        }
    }

    public static int[] merge(int[] array)
    {
        mergeHelper(array, 0, array.length-1);
        return array;
    }

    public static int[] bogo(int[] array)
    {
        while (!sorted(array))
        {
            ArrayList<Integer> list=new ArrayList<Integer>();
            int[] newArray=new int[array.length];
            for (int i=0; i<array.length; i++)
            {
                list.add(i);
            }
            for (int i=0; i<newArray.length; i++)
            {
                int rand=(int)(Math.random()*list.size());
                newArray[i]=array[list.get(rand)];
                list.remove(rand);
            }
            array=newArray;
        }
        return array;
    }

    public static int[] bogobogo(int[] array)
    {
        if (array.length<2)
        {
            return array;
        }
        int num=2;
        while (num<=array.length)
        {
            ArrayList<Integer> indices=new ArrayList<Integer>();
            for (int i=0; i<num; i++)
            {
                indices.add(i);
            }
            int[] newArray=new int[num];
            for (int i=0; i<num; i++)
            {
                int rand=(int)(Math.random()*(indices.size()));
                newArray[i]=array[indices.remove(rand)];
            }
            for (int i=0; i<num; i++)
            {
                array[i]=newArray[i];
            }
            boolean sorted=true;
            for (int i=1; i<num; i++)
            {
                if (array[i]<array[i-1])
                {
                    num=2;
                    sorted=false;
                    break;
                }
            }
            if (sorted)
            {
                num++;
            }
        }
        return array;
    }

    /*public static int[] bogobogo(int[] array)
    {
    int num=1;
    while (num<array.length)
    {
    if (array[num]>=array[num-1])
    {
    num++;
    break;
    }
    ArrayList<Integer> list=new ArrayList<Integer>();
    for (int i=0; i<num+1; i++)
    {
    list.add(i);
    }
    int[] newArray=new int[num+1];
    for (int i=0; i<newArray.length; i++)
    {
    newArray[i]=array[list.remove((int)(Math.random()*list.size()))];
    }
    boolean sorted=true;
    for (int i=0; i<num; i++)
    {
    if (array[i]>array[i+1])
    {
    sorted=false;
    break;
    }
    }
    if (sorted)
    {
    num++;
    }
    else
    {
    num=1;
    }
    }
    return array;
    }*/

    private static int[] merge3helper(int[] array, int start, int end)
    {
        if (end-start<1)
        {
            return array;
        }
        if (end-start==1)
        {
            if (array[start]<=array[end])
            {
                return array;
            }
            int temp=array[end];
            array[end]=array[start];
            array[start]=temp;
            return array;
        }
        int start1=start+(end-start+1)/3;
        int start2=start+(2*(end-start+1))/3;
        merge3helper(array, start, start1-1);
        merge3helper(array, start1, start2-1);
        merge3helper(array, start2, end);
        int index1=start;
        int index2=start1;
        int index3=start2;
        int[] mergeArray=new int[end-start+1];
        for (int i=0; i<mergeArray.length; i++)
        {
            int min=0;
            int third=0;
            if (index1<start1)
            {
                min=array[index1];
                third=1;
                if ((index2<start2) && (array[index2]<min))
                {
                    min=array[index2];
                    third=2;
                }
                if ((index3<end+1) && (array[index3]<min))
                {
                    min=array[index3];
                    third=3;
                }
            }
            else
            {
                if (index2<start2)
                {
                    min=array[index2];
                    third=2;
                    if ((index3<end+1) && (array[index3]<min))
                    {
                        min=array[index3];
                        third=3;
                    }
                }
                else
                {
                    min=array[index3];
                    third=3;
                }
            }
            mergeArray[i]=min;
            if (third==1)
            {
                index1++;
            }
            else if (third==2)
            {
                index2++;
            }
            else if (third==3)
            {
                index3++;
            }
            else
            {
                assert(false);
            }
        }
        for (int i=0; i<mergeArray.length; i++)
        {
            array[start+i]=mergeArray[i];
        }
        return array;
    }

    public static int[] merge3(int[] array)
    {
        return merge3helper(array, 0, array.length-1);
    }

    private static int[] mergesertion(int[] array, int start, int end)
    {
        if (end-start<500)
        {
            return insertion(array, start, end);
        }
        int start1=start+(end-start+1)/3;
        int start2=start+(2*(end-start+1))/3;
        mergesertion(array, start, start1-1);
        mergesertion(array, start1, start2-1);
        mergesertion(array, start2, end);
        int index1=start;
        int index2=start1;
        int index3=start2;
        int[] mergeArray=new int[end-start+1];
        for (int i=0; i<mergeArray.length; i++)
        {
            int min=0;
            int third=0;
            if (index1<start1)
            {
                min=array[index1];
                third=1;
                if ((index2<start2) && (array[index2]<min))
                {
                    min=array[index2];
                    third=2;
                }
                if ((index3<end+1) && (array[index3]<min))
                {
                    min=array[index3];
                    third=3;
                }
            }
            else
            {
                if (index2<start2)
                {
                    min=array[index2];
                    third=2;
                    if ((index3<end+1) && (array[index3]<min))
                    {
                        min=array[index3];
                        third=3;
                    }
                }
                else
                {
                    min=array[index3];
                    third=3;
                }
            }
            mergeArray[i]=min;
            if (third==1)
            {
                index1++;
            }
            else if (third==2)
            {
                index2++;
            }
            else if (third==3)
            {
                index3++;
            }
            else
            {
                assert(false);
            }
        }
        for (int i=0; i<mergeArray.length; i++)
        {
            array[start+i]=mergeArray[i];
        }
        return array;
    }

    public static int[] mergesertion(int[] array)
    {
        return mergesertion(array, 0, array.length-1);
    }

    public static int[] quaternaryHeap(int[] array)
    {
        return naryHeap(array, 4);
    }

    public static int[] quinaryHeap(int[] array)
    {
        return naryHeap(array, 5);
    }

    public static int[] originalNaryHeap(int[] array, int n)
    {
        if (array.length<2)
        {
            return array;
        }
        Integer[] heap=new Integer[array.length];
        heap[0]=array[0];
        for (int i=0; i<array.length; i++)
        {
            heap[i]=array[i];
            int index=i;
            while ((heap[(index-1)/n]!=null) && (index>0) && (heap[(index-1)/n]>heap[index]))
            {
                int nextIndex=(index-1)/n;
                Integer temp=heap[nextIndex];
                heap[nextIndex]=heap[index];
                heap[index]=temp;
                index=nextIndex;
            }
        }
        for (int i=0; i<array.length; i++)
        {
            array[i]=heap[0];
            int index=0;
            while (true)
            {
                int next=-1;
                long min=Long.MAX_VALUE;
                for (int j=1; j<=n; j++)
                {
                    if (((n*index+j)<heap.length) && (heap[n*index+j]!=null) && ((long)heap[n*index+j]<min))
                    {
                        next=j;
                        min=heap[n*index+j];
                    }
                }
                if (next==-1)
                {
                    heap[index]=null;
                    break;
                }
                else
                {
                    heap[index]=heap[n*index+next];
                    index=n*index+next;
                }
            }
        }
        return array;
    }

    public static void print(ArrayList<Integer> list)
    {
        for (int i=0; i<list.size(); i++)
        {
            System.out.print(list.get(i));
        }
        System.out.println();
    }

    public static void print(Integer[] array)
    {
        for (int i=0; i<array.length; i++)
        {
            System.out.print(array[i]);
        }
        System.out.println();
    }

    public static int[] bucket(int[] array)
    {
        return bucket(array, 12);
    }

    public static int[] bucket(int[] array, int base)
    {
        if (array.length<2)
        {
            return array;
        }
        int max=0;
        for (int i=0; i<array.length; i++)
        {
            if (array[i]>max)
            {
                max=array[i];
            }
        }
        int power=base;
        while (power*base<=max)
        {
            power*=base;
        }
        return bucketHelper(array, base, power, array.length);
    }

    public static int[] bucketHelper(int[] array, int base, int power, int length)//use length to disregard things after a certain point
    {
        if (array.length<2)
        {
            return array;
        }
        if (power==0)
        {
            return array;
        }
        int[] counts=new int[base];
        //no member of array is >= base*mod
        for (int i=0; i<length; i++)
        {
            counts[array[i]/power]++;
        }
        int maxCount=0;
        for (int i=0; i<counts.length; i++)
        {
            if (counts[i]>maxCount)
            {
                maxCount=counts[i];
            }
        }
        int[][] sorting=new int[base][maxCount];
        int[] indices=new int[base];
        for (int i=0; i<length; i++)
        {
            int quot=array[i]/power;
            int rem=array[i]%power;
            sorting[quot][indices[quot]]=rem;
            indices[quot]++;
        }
        int index=0;
        for (int i=0; i<base; i++)
        {
            if (counts[i]!=0)
            {
                int[] newArray=bucketHelper(sorting[i], base, power/base, counts[i]);
                for (int j=0; j<counts[i]; j++)
                {
                    int rem=newArray[j];
                    int quot=i;
                    array[index]=power*quot+rem;
                    index++;
                }
            }
        }
        return array;
    }

    public static int[] smartBozo(int[] array)
    {
        while (!sorted(array))
        {
            int r1=(int)(Math.random()*array.length);
            int r2=(int)(Math.random()*array.length);
            if (array[Math.min(r1, r2)]>array[Math.max(r1, r2)])
            {
                int temp=array[r2];
                array[r2]=array[r1];
                array[r1]=temp;
            }
        }
        return array;
    }

    public static int[] flash(int[] array)
    {
        if (array.length<2)
        {
            return array;
        }
        int gap=(int)(Math.log(array.length));
        Integer[] sorting=new Integer[Math.min(array.length*(1+gap), 1048000*7)];
        int min=array[0];
        int max=array[0];
        for (int i=1; i<array.length; i++)
        {
            if (min>array[i])
            {
                min=array[i];
            }
            if (max<array[i])
            {
                max=array[i];
            }
        }
        for (int i=0; i<array.length; i++)
        {
            int guess=(sorting.length-1)/(max-min)*(array[i]-min);
            if (guess<0)
            {
                System.out.println("array.length="+array.length+", array[i]="+array[i]+", min="+min+", max="+max);
            }
            if (sorting[guess]==null)
            {
                sorting[guess]=array[i];
            }
            else if (sorting[guess]>array[i])
            {
                boolean done=false;
                for (int j=guess-1; j>=0; j--)
                {
                    if (sorting[j]==null)
                    {
                        sorting[j]=array[i];
                        done=true;
                        break;
                    }
                }
                if (!done)
                {
                    for (int j=guess+1; j<sorting.length; j++)
                    {
                        if (sorting[j]==null)
                        {
                            sorting[j]=array[i];
                            break;
                        }   
                    }
                }
            }
            else
            {
                boolean done=false;
                for (int j=guess+1; j<sorting.length; j++)
                {
                    if (sorting[j]==null)
                    {
                        sorting[j]=array[i];
                        done=true;
                        break;
                    }
                }
                if (!done)
                {
                    for (int j=guess-1; j<sorting.length; j--)
                    {
                        if (sorting[j]==null)
                        {
                            sorting[j]=array[i];
                            break;
                        }
                    }
                }
            }
        }
        int index=0;
        for (int i=0; i<array.length; i++)
        {
            while (sorting[index]==null)
            {
                index++;
            }
            array[i]=sorting[index];
            index++;
        }
        return insertion(array);
    }

    public static int[] test(int[] array)
    {
        return triHeap(array);
    }

    public static int[] inplaceMerge(int[] array, int start, int end) //inclusive
    {
        if (end<=start)
        {
            return array;
        }
        if (end==start+1)
        {
            if (array[start]>array[end])
            {
                int temp=array[start];
                array[start]=array[end];
                array[end]=temp;
            }
            return array;
        }
        int middle=(start+end)/2+1;
        inplaceMerge(array, start, middle-1);
        inplaceMerge(array, middle, end);
        int index1=start;
        int index2=middle;
        for (int i=start; i<end; i++)
        {
            return array;
        }
        return array;
    }

    public static int[] inplaceMerge(int[] array)
    {
        return inplaceMerge(array, 0, array.length-1);
    }

    public static int[] guess(int[] array)
    {
        if (array.length<2)
        {
            return array;
        }
        while (!sorted(array))
        {
            for (int i=0; i<array.length; i++)
            {
                int rand1=(int)(Math.random()*array.length);
                int rand2=(int)(Math.random()*array.length);
                while (rand2==rand1)
                {
                    rand2=(int)(Math.random()*array.length);
                }
                int randMin=Math.min(rand1, rand2);
                int randMax=Math.max(rand1, rand2);
                if (array[randMin]>array[randMax])
                {
                    int temp=array[randMin];
                    array[randMin]=array[randMax];
                    array[randMax]=temp;
                }
            }
        }
        return array;
    }

    public static int[] primitiveGuess(int[] array)
    {
        if (array.length<2)
        {
            return array;
        }
        while (!sorted(array))
        {
            for (int i=0; i<array.length; i++)
            {
                int rand=(int)(Math.random()*(array.length-1));
                if (array[rand]>array[rand+1])
                {
                    int temp=array[rand];
                    array[rand]=array[rand+1];
                    array[rand+1]=temp;
                }
            }
        }
        return array;
    }

    public static int[] tree234(int[] array)
    {
        Tree234 t=new Tree234();
        for (int i=0; i<array.length; i++)
        {
            t.insert(array[i]);
        }
        return t.toArray();
    }

    public static int[] heap(int[] array)//Inplace!!!! :)
    {
        /*
        for (int i=2; i<=array.length; i++)
        {
        int j=i;
        while ((j>1) && (array[array.length-j]<array[array.length-j/2]))
        {
        int temp=array[array.length-j];
        array[array.length-j]=array[array.length-j/2];
        array[array.length-j/2]=temp;
        j=j/2;
        }
        }
         */

        for (int i=(array.length)/2; i>0; i--)
        {
            int j=i;
            while (2*j<=array.length)
            {
                if (2*j+1<=array.length)
                {
                    if (array[array.length-2*j]<array[array.length-j])
                    {
                        if (array[array.length-2*j-1]<array[array.length-2*j])
                        {
                            int temp=array[array.length-j];
                            array[array.length-j]=array[array.length-2*j-1];
                            array[array.length-2*j-1]=temp;
                            j=2*j+1;
                        }
                        else
                        {
                            int temp=array[array.length-j];
                            array[array.length-j]=array[array.length-2*j];
                            array[array.length-2*j]=temp;
                            j=2*j;
                        }
                    }
                    else if (array[array.length-2*j-1]<array[array.length-j])
                    {
                        int temp=array[array.length-j];
                        array[array.length-j]=array[array.length-2*j-1];
                        array[array.length-2*j-1]=temp;
                        j=2*j+1;
                    }
                    else
                    {
                        break;
                    }
                }
                else if ((2*j<=array.length) && (array[array.length-2*j]<array[array.length-j]))
                {
                    int temp=array[array.length-2*j];
                    array[array.length-2*j]=array[array.length-j];
                    array[array.length-j]=temp;
                    j=2*j;
                }
                else
                {
                    break;
                }
            }
        }
        for (int i=0; i<array.length; i++)
        {
            int min=array[array.length-1];
            array[array.length-1]=array[i];
            array[i]=min;
            int j=1;
            while (true)
            {
                if (array.length-2*j<=i)
                {
                    break;
                }
                else if (array.length-2*j-1<=i)
                {
                    if (array[array.length-2*j]<array[array.length-j])
                    {
                        int temp=array[array.length-2*j];
                        array[array.length-2*j]=array[array.length-j];
                        array[array.length-j]=temp;
                    }
                    break;
                }
                else
                {
                    if (array[array.length-j]>array[array.length-2*j])
                    {
                        if (array[array.length-2*j]<array[array.length-2*j-1])
                        {
                            int temp=array[array.length-j];
                            array[array.length-j]=array[array.length-2*j];
                            array[array.length-2*j]=temp;
                            j=2*j;
                        }
                        else
                        {
                            int temp=array[array.length-j];
                            array[array.length-j]=array[array.length-2*j-1];
                            array[array.length-2*j-1]=temp;
                            j=2*j+1;
                        }
                    }
                    else if (array[array.length-j]>array[array.length-2*j-1])
                    {
                        int temp=array[array.length-j];
                        array[array.length-j]=array[array.length-2*j-1];
                        array[array.length-2*j-1]=temp;
                        j=2*j+1;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
        return array;
    }

    public static void quick(int[] array, int lo, int hi)
    {
        if (hi>lo)
        {
            int pivotIndex=lo; //for now
            if (hi-lo>256)
            {
                int a=array[lo];
                int b=array[(lo+hi)/2];
                int c=array[hi];
                if (a<b)
                {
                    if (c<a)
                    {
                        pivotIndex=lo;
                    }
                    else if (c>b)
                    {
                        pivotIndex=hi;
                    }
                    else
                    {
                        pivotIndex=(lo+hi)/2;
                    }
                }
                else
                {
                    if (c>a)
                    {
                        pivotIndex=lo;
                    }
                    else if (c<b)
                    {
                        pivotIndex=hi;
                    }
                    else
                    {
                        pivotIndex=(lo+hi)/2;
                    }
                }
            }
            int pivot=array[pivotIndex];
            array[pivotIndex]=array[hi];
            array[hi]=pivot;
            int i=lo-1;
            int j=hi;
            while (i<j)
            {
                i++;
                while (array[i]<pivot)
                {
                    i++;
                }
                j--;
                while ((array[j]>pivot) && (j>lo))
                {
                    j--;
                }
                if (i<j)
                {
                    int temp=array[i];
                    array[i]=array[j];
                    array[j]=temp;
                }
            }
            array[hi]=array[i];
            array[i]=pivot;
            quick(array, lo, i-1);
            quick(array, i+1, hi);
        }
    }

    public static int[] quick(int[] array)
    {
        quick(array, 0, array.length-1);
        return array;
    }

    public static int[] jens(int[] array)
    {
        int zero=0;
        JensCounter pos=new JensCounter();
        JensCounter neg=new JensCounter();
        int posLength=0;
        int negLength=0;
        for (int i=0; i<array.length; i++)
        {
            if (array[i]==0)
            {
                zero++;
            }
            else if (array[i]>0)
            {
                posLength++;
                pos.add(array[i]);
            }
            else
            {
                negLength++;
                neg.add(-array[i]);
            }
        }
        int[] negative=neg.toArray(negLength);
        int[] positive=pos.toArray(posLength);
        for (int i=0; i<negLength; i++)
        {
            array[negLength-i-1]=-negative[i];
        }
        for (int i=0; i<zero; i++)
        {
            array[i+negLength]=0;
        }
        for (int i=0; i<posLength; i++)
        {
            array[i+negLength+zero]=positive[i];
        }
        return array;
    }

    public static int[] tenMinute(int[] array)
    {
        long t0=System.nanoTime();
        while (System.nanoTime()-t0<(long)(10000) * (long)(100000))
        {
            int i1=(int)(Math.random()*array.length);
            int i2=(int)(Math.random()*array.length);
            if (i1>i2)
            {
                int temp=i1;
                i1=i2;
                i2=temp;
            }
            if (array[i1]>array[i2])
            {
                int temp=array[i1];
                array[i1]=array[i2];
                array[i2]=temp;
            }
        }
        return array;
    }

    private static int[] counting(int[] array, int figure)
    {
        int[] counts=new int[16];
        for (int i=0; i<array.length; i++)
        {
            counts[((array[i]/(int)(Math.pow(16, figure))))%16]++;
        }
        int total=0;
        for (int i=0; i<16; i++)
        {
            int temp=counts[i];
            counts[i]=total;
            total+=temp;
        }
        int[] newArray=new int[array.length];
        for (int i=0; i<array.length; i++)
        {
            int digit=((array[i])/((int)(Math.pow(16, figure))))%16;
            newArray[counts[digit]]=array[i];
            counts[digit]++;
        }
        return newArray;
    }

    public static int[] radix(int[] array)
    {
        for (int i=0; i<=8; i++)
        {
            array=counting(array, i);
        }
        return array;
    }
    
    public static int[] bitonic(int[] array)
    {
        bitonic(array, 0, array.length, true);
        return array;
    }
    
    public static void bitonic(int[] array, int lo, int hi, boolean order)
    {
        if (hi>lo+1)
        {
            int mid=(lo+hi+1)/2;
            bitonic(array, lo, mid, order);
            bitonic(array, mid, hi, !order);
            if (order)
            {
                int i=lo;
                while (i<mid)
                {
                    if (array[hi-1]<array[i])
                    {
                        int j=hi-1;
                        while (array[j]<array[i])
                        {
                            if (j==mid)
                            {
                                break;
                            }
                            j--;
                        }
                        int temp=array[hi-1];
                        for (int k=hi-1; k>j; k--)
                        {
                            array[k]=array[k-1];
                        }
                        array[j]=array[i];
                        array[i]=temp;
                    }
                    i++;
                }
            }
            else
            {
                int i=lo;
                while (i<mid)
                {
                    if (array[hi-1]>array[i])
                    {
                        int j=hi-1;
                        while (array[j]>array[i])
                        {
                            if (j==mid)
                            {
                                break;
                            }
                            j--;
                        }
                        int temp=array[hi-1];
                        for (int k=hi-1; k>j; k--)
                        {
                            array[k]=array[k-1];
                        }
                        array[j]=array[i];
                        array[i]=temp;
                    }
                    i++;
                }
            }
            int i=mid;
            int j=hi-1;
            while (i<j)
            {
                int temp=array[i];
                array[i]=array[j];
                array[j]=temp;
            }
        }
    }
}