import java.util.ArrayList;
import java.util.TreeMap;

public class DoubleEliminationTournament {
    private ArrayList<Sort> sorts;
    private RoundRobin roundRobin;
    private TreeMap<Integer, Sort> rankings;
    private DoubleEliminationBracket bracket;
    private boolean alreadyPlayed;
    private int qualifyingSeeds;

    public DoubleEliminationTournament(ArrayList<Sort> initSorts, int initQualifyingSeeds)
    {
        sorts=initSorts;
        qualifyingSeeds=initQualifyingSeeds;
        alreadyPlayed=false;
    }
    public DoubleEliminationTournament(ArrayList<Sort> initSorts)
    {
        this(initSorts, Math.max(4, Technical.lastPowerOfTwo(initSorts.size()-1)));
    }
    public void play(boolean display, boolean displayGames) throws Exception
    {
        if (!alreadyPlayed)
        {
            roundRobin = new RoundRobin(sorts, 1, qualifyingSeeds);
            rankings=roundRobin.play(display, displayGames);
            bracket = new DoubleEliminationBracket(rankings);
            bracket.play(display);
            alreadyPlayed=true;
        }
    }
}
