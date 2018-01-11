import java.util.*;

/**
 * Stores the games this Sort has played.
 */
class History
{
    Set<Game> wins;
    Set<Game> losses;
    Set<Game> unbrokenTies;
    Set<Game> brokenTies;
    public History()
    {
        wins=new HashSet<Game>();
        losses=new HashSet<Game>();
        unbrokenTies=new HashSet<Game>();
        brokenTies=new HashSet<Game>();
    }
    /**
     * Adds all of the games from the other History to this one.
     */
    public void addHistory(History other)
    {
        if (other.wins!=null)
        {
            for (Game g:other.wins)
            {
                wins.add(g);
            }
        }
        if (other.losses!=null)
        {
            for (Game g:other.losses)
            {
                losses.add(g);
            }
        }
        if (other.unbrokenTies!=null)
        {
            for (Game g:other.unbrokenTies)
            {
                unbrokenTies.add(g);
            }
        }
        if (other.brokenTies!=null)
        {
            for (Game g:other.brokenTies)
            {
                brokenTies.add(g);
            }
        }
    }
}

/**
 * Every competitor in the tournament will be an object from a class that extends Sort.
 * Each Sort object contains a different sort() algorithm, which is what it competes with.
 * Another instance variable is maxArrayLength, which is used to make sure that no algorithm tries to sort an array that it could not sort in 10 seconds or less. (If this was not required, some sorting attempts would never end.)
 * A Sort also keeps track of all the games it has played, in the form of History objects.
 */
public abstract class Sort
{
    private String name; // the name of the Sort, for example: "Counting", "Java", or "Insertion".
    double maxArrayLength; // the longest length that the Sort can reliably sort in 10 seconds
    private History oldHistory; // stores all games prior to this tournament
    private History history; //  stores all the games that took place in this tournament's playoffs
    private int points; // stores the number of points earned so far in the current segment of the tournament (usually the current RoundRobin)
    private Integer ranking; // only used in the playoffs, determined by the regular season

    /**
     * Every subclass' constructor will be using this one, with the name and maxArrayLength of that Sort.
     */
    public Sort(String st, double max, History hist)
    {
        name=st;
        maxArrayLength=max;
        oldHistory=hist;
        history=new History();
    }
    public Sort(String st, double max)
    {
        this(st, max, new History());
    }

    public String toString()
    {
        if (ranking==null)
        {
            return name;
        }
        return "#"+ranking+" "+name;
    }

    public void reset()
    {
        oldHistory.addHistory(history);
        history=new History();
        ranking=null;
        points=0;
    }

    // Methods to add Games to history
    public void addWin(Game game)
    {
        history.wins.add(game);
        points+=3;
    }
    public void addLoss(Game game)
    {
        history.losses.add(game);
    }
    public void addUnbrokenTie(Game game)
    {
        history.unbrokenTies.add(game);
        points+=1;
    }
    public void addBrokenTie(Game game)
    {
        history.brokenTies.add(game);
    }

    //Getters and setters
    public int points()
    {
        return points;
    }
    public void setRanking(int r)
    {
        ranking=r;
    }
    public String getName()
    {
        return name;
    }
    /*public void setMaxArrayLength(int newLength)
    {
        maxArrayLength=newLength;
    }*/
    public int getNumWins()
    {
        return history.wins.size();
    }
    public Set<Game> getWins(){
        return history.wins;
    }
    public int getNumLosses()
    {
        return history.losses.size();
    }
    public Set<Game> getLosses() {
        return history.losses;
    }
    public int getNumUnbrokenTies()
    {
        return history.unbrokenTies.size();
    }
    public Set<Game> getUnbrokenTies()
    {
        return history.unbrokenTies;
    }
    public int getNumBrokenTies()
    {
        return history.brokenTies.size();
    }
    public Set<Game> getBrokenTies()
    {
        return history.brokenTies;
    }

    /**
     * Each Sort will implement this method in a different way. Many will refer to the static methods in Algorithms.java
     */
    public abstract void sort(int[] array);

    /**
     * This method is only used when it is called by calculateMaxArrayLength()
     */
    protected int nextToTry(int lo, int hi)
    {
        if (hi>2*lo)
        {
            return 2*lo;
        }
        return (lo+hi)/2;
    }
    /**
     * Runs an experiment to find what the proper array length should be.
     */
    public void calculateMaxArrayLength() throws CorrectnessException
    {
        maxArrayLength=Double.POSITIVE_INFINITY;
        int lo=1;
        int hi=Integer.MAX_VALUE;
        while (lo<hi)
        {
            int mid=nextToTry(lo, hi);
            System.out.print(mid+" ");
            try
            {
                long time=Technical.getTime(this, Technical.getRandomArray(mid));
                if (time<=Technical.MAX_ALLOWED_TIME) {
                    lo = mid + 1;
                }
                else
                {
                    hi=mid-1;
                }
                System.out.println(Math.pow(10, -9)*time);
            }
            catch (OutOfMemoryError err)
            {
                System.out.println("out of memory exception");
                hi=mid-1;
            }
        }
        if (lo==Integer.MAX_VALUE)
        {
            maxArrayLength=Double.POSITIVE_INFINITY;
        }
        else
        {
            maxArrayLength=lo;
        }
        System.out.println("Answer: "+maxArrayLength);
    }

    public static String toString(Sort sort)
    {
        if (sort==null)
        {
            return "null";
        }
        return sort.toString();
    }

    /**
     * Here is a list of all the sorting algorithms that have been implemented. We might not want all of them to be used in a given tournament,  so the complete list will be stored here for easy look up.
     *
     * Java
     *
     * And here is a list of algorithms I hope to implement in the future:
     *
     * Insertion
     * Selection
     * Bubble
     * BinaryInsertion
     *
     * Tree
     * Heap
     * Quick
     * Merge
     *
     * Shell
     * Counting
     * Radix
     * Bogo
     * Bozo
     * SmartBozo
     * Stooge
     *
     */
}