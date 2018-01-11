import java.util.ArrayList;

public class GameWithTiesBroken implements Bout
{
    private Sort first;
    private Sort second;
    private ArrayList<Game> games;
    private Sort winner;
    private boolean alreadyPlayed;

    public GameWithTiesBroken(Sort f, Sort s)
    {
        first=f;
        second=s;
        games=new ArrayList<Game>();
        alreadyPlayed=false;
    }

    /**
     * Plays Games between the two sorts until one of them wins.
     * Returns the eventual winner.
     * Throws an AlreadyPlayedException if the game has already been played.
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
        while (!alreadyPlayed)
        {
            int length=Technical.getRandomLength();
            while (length>first.maxArrayLength && length>second.maxArrayLength)
            {
                length=Technical.getRandomLength();
            }
            Game game=new Game(first, second, length, true);
            winner=game.play(display);
            if (winner==null)
            {
                if (display)
                {
                    Technical.sleep();
                }
            }
            else
            {
                alreadyPlayed=true;
                return winner;
            }
        }
        assert(false);
        return null;
    }

    @Override
    public String matchup() {
        return Sort.toString(first)+" vs. "+Sort.toString(second);
    }
}