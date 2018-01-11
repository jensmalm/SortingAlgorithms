import java.util.*;

public class Main
{
    private static ArrayList<Sort> sorts()
    {
        ArrayList<Sort> sorts=new ArrayList<Sort>();
        sorts.add(new BinaryInsertion());
        sorts.add(new Bogo());
        sorts.add(new Bozo());
        sorts.add(new Bubble());
        sorts.add(new Cocktail());
        sorts.add(new Counting());
        sorts.add(new Gnome());
        sorts.add(new Heap());
        sorts.add(new Insertion());
        sorts.add(new Java());
        sorts.add(new MedianOf3Quick());
        sorts.add(new Merge());
        sorts.add(new Quick());
        sorts.add(new QuickInsertionTagTeam(14));
        sorts.add(new Selection());
        sorts.add(new Shell());
        sorts.add(new SmartBozo());
        sorts.add(new Stooge());
        sorts.add(new Tree());
        return sorts;
    }
    private static void singleEliminationWithRoundRobin(boolean displayGames)
    {
        try{
            ArrayList<Sort> sorts=sorts();
            SingleEliminationTournament tournament=new SingleEliminationTournament(sorts);
            tournament.play(true, displayGames);
        }
        catch (Exception ex){}
    }
    private static void singleEliminationWithoutRoundRobin(boolean displayGames)
    {
        try {
            ArrayList<Sort> sorts=sorts();
            Technical.shuffle(sorts);
            TreeMap<Integer, Sort> rankings=new TreeMap<Integer, Sort>();
            for (int i=0; i<sorts.size(); i++)
            {
                rankings.put(i+1, sorts.get(i));
            }
            SingleEliminationBracket bracket=new SingleEliminationBracket(rankings);
            bracket.play(true);
        }
        catch (Exception ex){System.out.println(ex);}
    }
    private static void doubleEliminationWithRoundRobin(boolean displayGames)
    {
        try {
            ArrayList<Sort> sorts=sorts();
            DoubleEliminationTournament tourn=new DoubleEliminationTournament(sorts);
            tourn.play(true, displayGames);
        }
        catch (Exception ex){}
    }
    private static void doubleEliminationWithoutRoundRobin(boolean displayGames)
    {
        try{
            ArrayList<Sort> sorts=new ArrayList<Sort>();
            DoubleEliminationBracket brack=new DoubleEliminationBracket(sorts);
            brack.play(true);
        }
        catch (Exception ex){}
    }
    public static void main(String[] args)
    {
        boolean duble=false;
        boolean displayGames=false;
        boolean roundRobin=true;
        if (args.length>0)
        {
            if (args[0].length()>0 && (args[0].charAt(0)=='d' || args[0].charAt(0)=='D'))
            {
                duble=true;
            }
            if (args.length>1 && args[1].length()>0)
            {
                char c=args[1].charAt(0);
                if (c=='n' || c=='N')
                {
                    roundRobin=false;
                }
            }
        }
        if (duble)
        {
            if (roundRobin)
            {
                doubleEliminationWithRoundRobin(displayGames);
            }
            else
            {
                doubleEliminationWithoutRoundRobin(displayGames);
            }
        }
        else
        {
            if (roundRobin)
            {
                singleEliminationWithRoundRobin(displayGames);
            }
            else
            {
                singleEliminationWithoutRoundRobin(displayGames);
            }
        }
    }
}