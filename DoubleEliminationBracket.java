import java.util.ArrayList;
import java.util.TreeMap;
public class DoubleEliminationBracket {

    private ArrayList<ArrayList<TournamentNode>> winnersBracket;
    private ArrayList<ArrayList<TournamentNode>> losersBracket;
    private TournamentNode winnersFinal;
    private TournamentNode losersFinal;
    private Sort[] medals;

    private void setUpTournamentNodes(int numSorts) {
        // Set up losersBracket
        losersBracket = new ArrayList<ArrayList<TournamentNode>>();
        losersFinal = new TournamentNode(null, null, 2);
        ArrayList<TournamentNode> losersFinalRound = new ArrayList<TournamentNode>();
        losersFinalRound.add(losersFinal);
        losersBracket.add(losersFinalRound);
        ArrayList<TournamentNode> losersPenultimateRound=new ArrayList<TournamentNode>();
        TournamentNode losersPenultimate=new TournamentNode(losersFinal, null, 1);
        losersPenultimateRound.add(losersPenultimate);
        losersBracket.add(0, losersPenultimateRound);
        while (losersBracket.get(0).size()<numSorts/4)
        {
            ArrayList<TournamentNode> newRound=new ArrayList<TournamentNode>();
            for (TournamentNode node:losersBracket.get(0))
            {
                for (int i=0; i<2; i++)
                {
                    newRound.add(new TournamentNode(node, null, 1));
                }
            }
            losersBracket.add(0, newRound);
            newRound=new ArrayList<TournamentNode>();
            for (TournamentNode node:losersBracket.get(0))
            {
                newRound.add(new TournamentNode(node, null, 1));
            }
            losersBracket.add(0, newRound);
        }
        // Set up winnersBracket
        winnersBracket=new ArrayList<ArrayList<TournamentNode>>();
        winnersFinal=new TournamentNode(null, losersFinal, 2);
        ArrayList<TournamentNode> winnersFinalRound=new ArrayList<TournamentNode>();
        winnersFinalRound.add(winnersFinal);
        winnersBracket.add(winnersFinalRound);
        int losersIndex=losersBracket.size()-3;
        while (winnersBracket.get(0).size()<numSorts/4) {
            ArrayList<TournamentNode> newRound = new ArrayList<TournamentNode>();
            assert (winnersBracket.get(0).size() == 2*losersBracket.get(losersIndex).size());
            for (int i=0; i<winnersBracket.get(0).size(); i++)
            {
                for (int j=0; j<2; j++)
                {
                    newRound.add(new TournamentNode(winnersBracket.get(0).get(i), losersBracket.get(losersIndex).get(2*i+j), 1));
                }
            }
            winnersBracket.add(0, newRound);
            losersIndex-=2;
        }
        assert(winnersBracket.get(0).size()==losersBracket.get(0).size());
        ArrayList<TournamentNode> winnersFirstRound=new ArrayList<TournamentNode>();
        for (int i=0; i<winnersBracket.get(0).size(); i++)
        {
            for (int j=0; j<2; j++) {
                winnersFirstRound.add(new TournamentNode(winnersBracket.get(0).get(i), losersBracket.get(0).get(i), 1));
            }
        }
        winnersBracket.add(0, winnersFirstRound);
    }

    public DoubleEliminationBracket(TreeMap<Integer, Sort> rankings) throws NoSuchMethodException {
        int numSorts = Math.max(4, Technical.nextPowerOfTwo(rankings.size()));
        for (int i = rankings.size() + 1; i <= numSorts; i++) {
            rankings.put(i, new Bye());
        }
        setUpTournamentNodes(numSorts);
        int index = 0;
        ArrayList<Integer> bracketOrder = Technical.bracketOrder(numSorts);
        for (TournamentNode node : winnersBracket.get(0)) {
            for (int i = 0; i < 2; i++) {
                node.add(rankings.get(bracketOrder.get(index)));
                index++;
            }
        }
    }

    /*
    We would like to allow a bracket to be played without a RoundRobin being played to determine its original seeds. Therefore, we add this other constructor.
    The Sorts will be placed randomly into their starting TournamentNodes.
    Since the nodes will have the same structure (and just different ways of populating them with Sorts), both constructors will call the same setUpTournamentNodes() method.

    After writing this so that it works for DoubleElimination, rewrite SingleElimination so that it works for SingleElimination.
    */

    public DoubleEliminationBracket(ArrayList<Sort> sorts) throws NoSuchMethodException
    {
        int numSorts = Math.max(4, Technical.nextPowerOfTwo(sorts.size()));
        setUpTournamentNodes(numSorts);
        Technical.shuffle(sorts);
        while (sorts.size()<numSorts)
        {
            sorts.add(new Bye());
        }
        int index = 0;
        ArrayList<Integer> bracketOrder = Technical.bracketOrder(numSorts);
        for (TournamentNode node : winnersBracket.get(0)) {
            for (int i = 0; i < 2; i++) {
                node.add(sorts.get(bracketOrder.get(index)-1));
                index++;
            }
        }
    }




    public Sort play(boolean display) throws NoSuchMethodException {
        if (display) {
            System.out.println("Double elimination bracket:");
            System.out.println();
            Technical.sleep();
        }
        int winnersIndex = 0;
        int losersIndex = 0;
        if (display) {
            System.out.println("Winners' bracket:");
            for (TournamentNode node : winnersBracket.get(0)) {
                System.out.println(node.matchup());
            }
            System.out.println();
            Technical.sleep();
        }
        for (TournamentNode node : winnersBracket.get(0)) {
            node.play(display);
        }
        winnersIndex++;
        while (losersIndex < losersBracket.size()) {
            boolean losersPlayable = (losersIndex < losersBracket.size() && losersBracket.get(losersIndex).get(0).sortsBeenSet());
            if (losersPlayable) {
                // Play the next round of the loser's bracket
                ArrayList<TournamentNode> round = losersBracket.get(losersIndex);
                if (display) {
                    Technical.printLine();
                    if (round.size() == 1 && round.get(0).equals(losersFinal)) {
                        System.out.println("Losers' bracket finals. Winner goes to the finals, where they must beat " + winnersFinal.getWinner().toString() + " twice.");
                    } else {
                        System.out.println("Losers' bracket:");
                        for (TournamentNode node : round) {
                            System.out.println(node.matchup());
                        }
                    }
                    System.out.println();
                    Technical.sleep();
                }
                for (TournamentNode node : round) {
                    node.play(display);
                }
                losersIndex++;
            } else {
                // Play the next round of the winner's bracket.
                ArrayList<TournamentNode> round = winnersBracket.get(winnersIndex);
                if (display) {
                    if (round.size() == 1) {
                        System.out.println("Winners' bracket finals. Best of 5 series");
                        Technical.sleep();
                        System.out.println("Winner goes onto the finals, where they have two chances to win the championship.");
                        Technical.sleep();
                        System.out.println("Loser goes to the Losers' bracket finals");
                    } else {
                        System.out.println("Winners' bracket:");
                        for (TournamentNode node : round) {
                            System.out.println(node.matchup());
                        }
                    }
                    System.out.println();
                    Technical.sleep();
                }
                for (TournamentNode node : round) {
                    node.play(display);
                }
                winnersIndex++;
            }
        }
        TournamentNode firstFinal = new TournamentNode(null, null, 4);
        firstFinal.add(winnersFinal.getWinner());
        firstFinal.add(losersFinal.getWinner());
        if (display) {
            System.out.println("Finals: " + firstFinal.matchup());
            Technical.sleep();
            System.out.println("Best of 7 series.");
            Technical.sleep();
            System.out.println("If " + winnersFinal.getWinner().getName() + " wins, they win the championship.");
            Technical.sleep();
            System.out.println("If " + losersFinal.getWinner().getName() + " wins, they force another series.");
            System.out.println();
            Technical.sleep();
        }
        firstFinal.play(display);
        medals = new Sort[3];
        medals[2] = losersFinal.getLoser();
        if (firstFinal.getWinner() == winnersFinal.getWinner()) {
            medals[0] = firstFinal.getWinner();
            medals[1] = firstFinal.getLoser();
        } else {
            TournamentNode secondFinal = new TournamentNode(null, null, 4);
            secondFinal.add(firstFinal.getWinner());
            secondFinal.add(firstFinal.getLoser());
            if (display) {
                System.out.println("Finals: " + secondFinal.matchup());
                Technical.sleep();
                System.out.println("Winner of this series wins the tournament.");
                Technical.sleep();
                System.out.println();
                Technical.sleep();
            }
            secondFinal.play(display);
            medals[0] = secondFinal.getWinner();
            medals[1] = secondFinal.getLoser();
        }
        for (Sort sort:medals)
        {
            sort.reset();
        }
        if (display) {
            Technical.printLine();
            System.out.println("Third place: " + medals[2]);
            Technical.sleep();
            System.out.println("Second place: " + medals[1]);
            Technical.sleep();
            System.out.println("First place: " + medals[0]);
            System.out.println();
        }
        return medals[0];
    }
}