import java.util.*;
/**
 * Represents one occurence of two algorithms each sorting an array, competing to sort it faster.
 * Note that in the case where games can not end in a tie (say, if they tie once, then play again to break the tie), multiple Game objects will be created to store these results.
 * Each Game object will be stored inside a History object corresponding to each of the Sorts involved. This will be used to determine rankings after the regular season going into the playoffs, and also for curiosity to see the entire history.
 *
 * first, second, length, and tiesBroken are determined at the time the object is created.
 * The Game is not played until play() is called. This populates times and winner, adds the Game to the Histories of the Sorts, and returns the winner.
 */
public class Game
{
    public static final boolean USE_MICRO=false; // times displayed in microseconds are more visually pleasing, and depending on the viewer, it might feel more fair if an extremely narrow victory is conaidered a tie. If you want to make the time that gets printed out and used to determine who wins the number of microseconds rather than the number of nanoseconds (so ties are more common and short arrays matter less), set this to true.
    private Sort first;
    private Sort second;
    private int length;
    private Map<Sort, Long> times;
    private boolean tiesBroken;
    private Sort winner; // null in the case of a tie (regardless of whether the tie was later broken).
    private boolean alreadyPlayed;

    public Game(Sort initFirst, Sort initSecond, int initLength, boolean breakTies)
    {
        first=initFirst;
        second=initSecond;
        length=initLength;
        tiesBroken=breakTies;
        alreadyPlayed=false;
    }

    /**
     * Plays the Game.
     * Populates the times map.
     * Sets winner and returns it.
     */
    public Sort play(boolean display) throws AlreadyPlayedException
    {
        if (first.getClass()==Bye.class)
        {
            return second;
        }
        if (second.getClass()==Bye.class)
        {
            return first;
        }
        if (alreadyPlayed)
        {
            throw new AlreadyPlayedException();
        }
        if (display)
        {
            System.out.println(Sort.toString(first)+" vs. "+Sort.toString(second));
            Technical.sleep();
        }
        int[] array=Technical.getRandomArray(length);
        times=new HashMap<Sort, Long>();

        Map<Sort, Long> scores=new HashMap<Sort, Long>();
        Sort[] sorts={first, second};
        for (Sort sort:sorts)
        {
            if (display)
            {
                System.out.println(sort.getName()+": array of length "+array.length);
                Technical.sleep();
            }
            long time;
            long score;
            try
            {
                time=Technical.getTime(sort, array);
            }
            catch (FailureToSortException ex)
            {
                System.out.println("Failure to sort exception: "+sort.getName()+" on an array of length "+array.length);
                System.out.println(ex);
                time=Long.MAX_VALUE;
            }
            times.put(sort, time);
            if (USE_MICRO)
            {
                score=time/1000;
            }
            else
            {
                score=time;
            }
            scores.put(sort, score);
            if (display)
            {
                System.out.println("Time: "+score);
                Technical.sleep();
            }
        }
        long score1=scores.get(first);
        long score2=scores.get(second);

        if (score1<score2)
        {
            winner=first;
        }
        else if (score1>score2)
        {
            winner=second;
        }
        else
        {
            winner=null;
        }
        if (display)
        {
            if (winner==null)
            {
                System.out.println("Tie");
            }
            else
            {
                System.out.println("Winner: "+winner.getName());
            }
            System.out.println();
            Technical.sleep();
        }

        // Add the Game to each of the Sorts' Histories
        if (winner==null)
        {
            if (tiesBroken)
            {
                first.addBrokenTie(this);
                second.addBrokenTie(this);
            }
            else
            {
                first.addUnbrokenTie(this);
                second.addUnbrokenTie(this);
            }
        }
        else
        {
            Sort loser=(Sort)(Technical.other(first, second, winner));
            winner.addWin(this);
            loser.addLoss(this);
        }

        alreadyPlayed=true;
        return winner;
    }

    public Sort opponent(Sort sort)
    {
        return (Sort)(Technical.other(first, second, sort));
    }
}