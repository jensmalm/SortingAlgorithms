import java.util.ArrayList;
import java.util.TreeMap;
public class SingleEliminationTournament
{
    private ArrayList<Sort> sorts;
    private RoundRobin roundRobin;
    private TreeMap<Integer, Sort> rankings;
    private SingleEliminationBracket bracket;
    private boolean alreadyPlayed;
    private Sort champion;

    private int qualifyingSeeds;
    public SingleEliminationTournament(ArrayList<Sort> initSorts, int initQualifyingSeeds)
    {
        sorts=initSorts;
        qualifyingSeeds=initQualifyingSeeds;
    }
    public SingleEliminationTournament(ArrayList<Sort> initSorts)
    {
        this(initSorts, Math.max(2, Technical.lastPowerOfTwo(initSorts.size()-1)));
    }
    public void play(boolean display, boolean displayGames) throws Exception
    {
        if (!alreadyPlayed)
        {
            roundRobin = new RoundRobin(sorts, 1, qualifyingSeeds);
            rankings=roundRobin.play(display, displayGames);
            bracket = new SingleEliminationBracket(rankings);
            champion=bracket.play(display);
            alreadyPlayed=true;
        }
    }
    public TreeMap<Integer, Sort> getRankings()
    {
        return rankings;
    }
    public Sort getChampion()
    {
        return champion;
    }
}
