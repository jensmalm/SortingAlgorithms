
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class SingleEliminationBracket
{
    private ArrayList<ArrayList<TournamentNode>> rounds;
    private boolean alreadyPlayed;
    private static final int consolationTarget=2;
    private TournamentNode consolation;
    private TournamentNode championship;
    private Sort[] medals;

    // Rewrite so that this method is not static and so that it takes the TournamentNode instead of the int.
    private static int[] getSeriesSizes(int n)
    {
        int[] seriesSizes=new int[n];
        seriesSizes[n-1]=4;
        if (n<5)
        {
            for (int i=0; i<n-1; i++)
            {
                seriesSizes[i]=i+1;
            }
        }
        else {
            for (int i = n - 2; i >= 0; i++)
            {
                seriesSizes[i]=Math.max(1, 4-n+i);
            }
        }
        return seriesSizes;
    }
    public SingleEliminationBracket(Map<Integer, Sort> rankings) throws NoSuchMethodException // As an invariant, assume the keys of rankings are 1 through rankings.size()
    {
        medals=new Sort[4];
        //If rankings.size() is not a power of two, add Bye's until it is.
        int n=Technical.nextPowerOfTwo(rankings.size());
        for (int i=rankings.size()+1; i<=n; i++)
        {
            rankings.put(i, new Bye());
        }

        //set up the number of rounds to be played and how long the series in each round should be
        int numRounds=(int)(Math.log(n)/Math.log(2));
        int[] seriesSizes=getSeriesSizes(numRounds);

        //Create the TournamentNodes and the ArrayList<ArrayList<TournamentNode>> that stores them.
        rounds=new ArrayList<ArrayList<TournamentNode>>();
        int index=numRounds-1;
        championship=new TournamentNode(null, null, seriesSizes[index]);
        ArrayList<TournamentNode> championshipRound=new ArrayList<TournamentNode>();
        championshipRound.add(championship);
        rounds.add(championshipRound);
        if (numRounds>1)
        {
            consolation=new TournamentNode(null, null, consolationTarget);
            rounds.get(0).add(0, consolation);
            index--;
            ArrayList<TournamentNode> semifinals=new ArrayList<TournamentNode>();
            for (int i=0; i<2; i++) {
                semifinals.add(new TournamentNode(championship, consolation, seriesSizes[index]));
            }
            rounds.add(0, semifinals);
            index--;
        }
        while (index>=0)
        {
            ArrayList<TournamentNode> round=new ArrayList<TournamentNode>();
            for (TournamentNode node:rounds.get(0))
            {
                for (int i=0; i<2; i++)
                {
                    round.add(new TournamentNode(node, null, seriesSizes[index]));
                }
            }
            rounds.add(0, round);
            index--;
        }
        index=0;
        ArrayList<Integer> bracketOrder=Technical.bracketOrder(rankings.size());
        for (TournamentNode node:rounds.get(0))
        {
            for (int i=0; i<2; i++)
            {
                node.add(rankings.get(bracketOrder.get(index)));
                index++;
            }
        }
    }
    public Sort play(boolean display) throws NoSuchMethodException
    {
        int numRounds=rounds.size();
        if (display)
        {
            System.out.println("Single elimination bracket:");
            System.out.println();
            Technical.sleep();
        }
        for (int i=0; i<rounds.size(); i++)
        {
            ArrayList<TournamentNode> round=rounds.get(i);
            if (i==numRounds-1)
            {
                for (TournamentNode node:round)
                {
                    if (display) {
                        Technical.printLine();
                        if (node == consolation) {
                            System.out.println("Consolation round: winner gets third place, loser gets fourth.");
                        } else if (node == championship) {
                            System.out.println("Championship round:");
                        }
                        System.out.println();
                        Technical.sleep();
                    }
                    node.play(display);
                }
            }
            else
            {
                if (display) {
                    Technical.printLine();
                    if (i == numRounds - 2) {
                        System.out.println("Semifinals:");
                    }
                    else if (i == numRounds - 3) {
                        System.out.println("Quarterfinals");
                    }
                    else {
                        System.out.println("Round of " + Integer.toString(2 * round.size()));
                    }
                    System.out.println();
                    Technical.sleep();
                    for (TournamentNode node : round) {
                        System.out.println(node.matchup());
                    }
                    System.out.println();
                    for (TournamentNode node : round) {
                        Technical.sleep();
                    }
                }
                for (TournamentNode node:round) {
                    node.play(display);
                }
            }
        }
        medals[0]=championship.getWinner();
        medals[1]=championship.getLoser();
        try {
            medals[2] = consolation.getWinner();
            medals[3] = consolation.getLoser();
        }
        catch (NullPointerException ex){}
        alreadyPlayed=true;
        for (int i=0; i<medals.length; i++)
        {
            medals[i].reset();
        }
        if (display)
        {
            Technical.printLine();
            System.out.println("Fourth place: "+medals[3]);
            Technical.sleep();
            System.out.println("Third place: "+medals[2]);
            Technical.sleep();
            System.out.println("Second place: "+medals[1]);
            Technical.sleep();
            System.out.println("First place: "+medals[0]);
            System.out.println();
        }
        return medals[0];
    }
    public Sort medal(int i)
    {
        return medals[i];
    }
}
