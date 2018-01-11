import java.util.ArrayList;

public class Series implements Bout
{
    private Sort first;
    private Sort second;
    private int target; // the number of wins needed to win the series. So the series is best of 2*target-1.
    private ArrayList<GameWithTiesBroken> games;
    private Sort winner;
    private boolean alreadyPlayed;

    public Series(Sort f, Sort s, int t)
    {
        first=f;
        second=s;
        target=t;
        alreadyPlayed=false;
    }

    private void printSeriesScore(int wins1, int wins2)
    {
        if (wins1==target)
        {
            System.out.println(first.getName()+" wins series "+Integer.toString(wins1)+"-"+Integer.toString(wins2));
        }
        else if (wins2==target)
        {
            System.out.println(second.getName()+" wins series "+Integer.toString(wins2)+"-"+Integer.toString(wins1));
        }
        else if (wins1>wins2)
        {
            System.out.println(first.getName()+" leads series "+Integer.toString(wins1)+"-"+Integer.toString(wins2));
        }
        else if (wins2>wins1)
        {
            System.out.println(second.getName()+" leads series "+Integer.toString(wins2)+"-"+Integer.toString(wins1));
        }
        else
        {
            System.out.println("Series tied "+wins1+"-"+wins2);
        }
        System.out.println();
        Technical.sleep();
    }

    @Override
    public String matchup() {
        return Sort.toString(first)+" vs. "+Sort.toString(second);
    }

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
        games=new ArrayList<GameWithTiesBroken>();
        int wins1=0;
        int wins2=0;
        if (display)
        {
            System.out.println(Sort.toString(first)+" vs. "+Sort.toString(second)+": best of "+Integer.toString(2*target-1)+" series.");
            System.out.println();
            Technical.sleep();
        }
        while (wins1<target && wins2<target)
        {
            GameWithTiesBroken g=new GameWithTiesBroken(first, second);
            Sort winner=g.play(display);
            if (winner==first)
            {
                wins1++;
            }
            else if (winner==second)
            {
                wins2++;
            }
            else
            {
                if (display)
                {
                    System.out.println("Error: the winner of the last game was neither "+Sort.toString(first)+" nor "+Sort.toString(second));
                    Technical.sleep();
                }
            }
            printSeriesScore(wins1, wins2);
        }
        alreadyPlayed=true;
        if (wins1==target)
        {
            winner=first;
        }
        else
        {
            winner=second;
        }
        alreadyPlayed=true;
        return winner;
    }
}