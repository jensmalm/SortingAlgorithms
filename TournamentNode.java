public class TournamentNode
{
    private Sort first;
    private Sort second;
    private TournamentNode winDestination;
    private TournamentNode loseDestination;
    private int target;
    private Bout bout;
    private boolean alreadyPlayed;
    private Sort winner;
    private Sort loser;
    public TournamentNode(TournamentNode winDest, TournamentNode loseDest, int initTarget)
    {
        winDestination = winDest;
        loseDestination = loseDest;
        target = initTarget;
        alreadyPlayed=false;
    }

    public TournamentNode getLoseDestination() {
        return loseDestination;
    }

    public TournamentNode getWinDestination() {
        return winDestination;
    }

    public void add(Sort sort) throws NoSuchMethodException
    {
        if (first==null)
        {
            first=sort;
        }
        else if (second==null)
        {
            second=sort;
        }
        else
        {
            throw new NoSuchMethodException();
        }
    }
    private void generateBout()
    {
        if (bout==null) {
            if (target == 1) {
                bout = new GameWithTiesBroken(first, second);
            } else {
                bout = new Series(first, second, target);
            }
        }
    }
    public void play(boolean display) throws NoSuchMethodException
    {
        generateBout();
        try
        {
            winner = bout.play(display);
            loser = (Sort) (Technical.other(first, second, winner));
            if (winDestination!=null) {
                winDestination.add(winner);
            }
            if (loseDestination!=null) {
                loseDestination.add(loser);
            }
            alreadyPlayed=true;
        }
        catch (AlreadyPlayedException ex)
        {
            assert(false);
        }
    }
    public Sort getWinner()
    {
        return winner;
    }
    public Sort getLoser()
    {
        return loser;
    }
    public String matchup()
    {
        generateBout();
        return bout.matchup();
    }
    public boolean sortsBeenSet()
    {
        return first!=null && second!=null;
    }
}