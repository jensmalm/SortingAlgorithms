import java.util.*;
public class RoundRobin
{
    private ArrayList<Sort> sorts;
    private TreeMap<Integer, Sort> rankings;
    private Set<Game> games;
    private RoundRobin subcall;
    private boolean alreadyPlayed;
    // Next two are to store which seeds (for example, 6 through 8) are being fought over here
    private int firstSeed; // So this would be equal to 6 if seeds 1 through 5 have been determined and this round robin is over spots 6 through 8.
    private int lastSeed; // the last seed that qualifies. In the example I've been using, this would be 8

    /**
     * When a RoundRobin is initialized, its games have not yet been played, so the only instance variable that gets set at all is "sorts".
     * "games", "rankings", and (if necessary) "subcall" get set by play()
     */
    public RoundRobin(ArrayList<Sort> initSorts, int initFirstSeed, int initLastSeed)
    {
        sorts=initSorts;
        alreadyPlayed=false;
        firstSeed=initFirstSeed;
        lastSeed=initLastSeed;
    }

    /**
     * Input: an ArrayList of Sorts, with size at least 1, such that either all or none of its elements are null.
     * Returns true if they are null, false if not.
     *
     * The reason this method gets used is that there is a "null tier" of null Sorts added incase there are more seeds available than Sorts to compete for them.
     */
    private boolean nullTier(ArrayList<Sort> list)
    {
        return list.get(0)==null;
    }

    /**
     * Break a tie between the firstSeed2-lastSeed2 ranked teams. (invariant: firstSeed<=firstSeed2<=lastSeed2<=lastSeed, lastSeed2-firstSeed2+1=list.size())
     *
     * These are for when these teams have already made the playoffs, and we are breaking ties within the bracket
     * If possible, sort them by points in head to head Games.
     * If not, use randomness.
     *
     * In this method, we redefine "tier" to mean a set of Sorts that are tied in *head-to-head* points.
     * This is not to be confused with how we define tier in play().
     */
    private TreeMap<Integer, Sort> sortByHeadToHead(ArrayList<Sort> list, int firstSeed2, boolean display) {
        if (display) {
            System.out.println("Breaking the tie between " + Technical.englishList(list) + " by head-to-head points.");
            Technical.sleep();
        }
        Map<Sort, Integer> h2hpoints = new HashMap<Sort, Integer>();
        for (Sort sort : list) {
            int total = 0;
            for (Game game : sort.getWins()) {
                if (list.contains(game.opponent(sort))) {
                    total += 3;
                }
            }
            for (Game game : sort.getUnbrokenTies()) {
                if (list.contains(game.opponent(sort))) {
                    total++;
                }
            }
            h2hpoints.put(sort, total);
        }
        TreeMap<Integer, ArrayList<Sort>> h2htiers = Technical.invertMap(h2hpoints);
        if (display) {
            boolean ties = false;
            System.out.println("Points earned in head-to-head play:");
            Technical.sleep();
            for (int i : h2htiers.descendingKeySet()) {
                ArrayList<Sort> h2htier = h2htiers.get(i);
                System.out.println(Technical.englishList(h2htier) + ": " + Integer.toString(i));
                if (h2htier.size() > 1) {
                    ties = true;
                }
            }
            System.out.println();
            Technical.sleep();
            if (ties) {
                System.out.println("Any ties still remaining will be broken randomly.");
                System.out.println();
                Technical.sleep();
            }
        }
        TreeMap<Integer, Sort> result = new TreeMap<Integer, Sort>();
        int index = firstSeed2;
        for (int i : h2htiers.descendingKeySet()) {
            ArrayList<Sort> h2htier = h2htiers.get(i);
            if (h2htier.size() > 1) {
                Technical.shuffle(h2htier);
            }
            for (Sort sort : h2htier) {
                result.put(index, sort);
                index++;
            }
        }
        return result;
    }

    /**
     * Stage one game (with ties allowed) between every pair of Sorts. Add each game to the regularSeason wins, losses, and unbrokenTies of the Sorts involved.
     *
     * The code inside this method has the following parts:
     * Reset all of the Sorts
     * Handle the special case where there is only one Sort.
     * Handle the special case where there are only two Sorts competing for one spot (unless firstSeed==1).
     * Create a set with all (n choose 2) games to be played. Do not play any of them yet.
     * Play these games. Display the standings between games.
     * Determine the rankings based on the standings, but keeping ties intact.
     * When ties are between qualifying spots (that is, all teams in the tie will qualify regardless of how the tie is broken), break them by head to hread record. When ties are for qualifying spots (for example, if teams 7-10 are tied and teams 1-8 get qualifying spots), break it by a smaller RoundRobin.
     *
     * The following should be displayed throughout the execution of this method (if display==true):
     * If firstSeed==1: "Round robin to determine the rankings" + "Teams 1-lastSeed qualify".
     * If firstSeed>1: If sorts.size()>2, "Round robin to determine spots firstSeed-lastSeed" + list of teams. If sorts.size()==2, "Game to determine the firstSeed spot: team1 vs team2
     * If displayGames: all the games as they get played.
     * The standings between games.
     * After all games, the rankings (with ties intact).
     * "Narration" if ties are broken by head-to-head.
     * If firstSeed==1, the final rankings
     *
     */
    public TreeMap<Integer, Sort> play(boolean display, boolean displayGames) throws Exception
    {

        // Make sure this RoundRobin has not already been played
        if (alreadyPlayed)
        {
            throw new AlreadyPlayedException();
        }

        //Handle special cases where there is only 1 Sort.
        if (sorts.size()==1)
        {
            rankings=new TreeMap<Integer, Sort>();
            rankings.put(firstSeed, sorts.get(0));
            sorts.get(0).setRanking(firstSeed);
            for (int i=firstSeed+1; i<=lastSeed; i++)
            {
                rankings.put(i, null);
            }
            alreadyPlayed=true;
            return rankings;
        }

        //Reset the sorts
        for (Sort sort:sorts)
        {
            sort.reset();
        }

        // Display introduction
        if (display)
        {
            if (firstSeed==1)
            {
                System.out.println("Round robin to determine the rankings.");
                Technical.sleep();
                System.out.println("Sorts 1 through "+lastSeed+" qualify.");
                Technical.sleep();
            }
            else if (sorts.size()==2 && firstSeed==lastSeed)
            {
                assert(firstSeed==lastSeed);
                System.out.println("1-game playoff to determine the "+firstSeed+" seed: "+Sort.toString(sorts.get(0))+" vs. "+Sort.toString(sorts.get(1)));
                Technical.sleep();
            }
            else
            {
                System.out.println("Round robin to determine spots "+firstSeed+" through "+lastSeed);
                Technical.sleep();
                System.out.println("Teams competing: "+Technical.englishList(sorts));
                Technical.sleep();
            }
        }

        //Special case where there are only two Sorts competing for one spot.
        if (sorts.size()==2 && firstSeed==lastSeed)
        {
            GameWithTiesBroken g=new GameWithTiesBroken(sorts.get(0), sorts.get(1));
            Sort winner=g.play(displayGames);
            rankings=new TreeMap<Integer, Sort>();
            rankings.put(firstSeed, winner);
            winner.setRanking(firstSeed);
            return rankings;
        }

        //Set up a Set of Games to be played. None of these will be played.
        Set<Game> games=new HashSet<Game>();
        for (int i=0; i<sorts.size(); i++)
        {
            for (int j=i+1; j<sorts.size(); j++)
            {
                Sort first=sorts.get(i);
                Sort second=sorts.get(j);
                boolean randBool=(Math.random()<.5);
                if (randBool)
                {
                    Sort temp=first;
                    first=second;
                    second=temp;
                }
                games.add(new Game(first, second, Technical.getRandomLength(), false));
            }
        }

        //Play all the games and display the standings between them

        if (display)
        {
            printStandings();
        }
        for (Game g:games)
        {
            g.play(displayGames);
            if (display)
            {
                printStandings();
            }
            if (displayGames)
            {
                Technical.sleep();
            }
        }

        // Determine the rankings, with all ties intact.
        // (This code segment generates an ArrayList "tiers", that lists the tiers by descending number of points)
        // (A tier is defined as a set of Sorts that have the same number of points)
        Map<Sort, Integer> points=new HashMap<Sort, Integer>();
        for (Sort sort:sorts)
        {
            points.put(sort, sort.points());
        }
        TreeMap<Integer, ArrayList<Sort>> tierMap=Technical.invertMap(points);
        ArrayList<ArrayList<Sort>> tiers=new ArrayList<ArrayList<Sort>>();
        for (int i:tierMap.descendingKeySet())
        {
            ArrayList<Sort> tier=tierMap.get(i);
            tiers.add(tier);
        }

        // Trim any tiers that contain no Sorts who will qualify. Add a tier of null Sorts to fill out the rankings if we are in the case where there are more seeds available than there are Sorts competing for them.
        int index=0;
        int taken=firstSeed-1;
        while (index<tiers.size() && taken<lastSeed)
        {
            taken+=tiers.get(index).size();
            index++;
        }
        if (taken<lastSeed)
        {
            ArrayList<Sort> nullTier=new ArrayList<Sort>();
            for (int i=taken+1; i<=lastSeed; i++)
            {
                nullTier.add(null);
            }
            tiers.add(nullTier);
        }
        while (index<tiers.size())
        {
            tiers.remove(index);
        }

        // If there are no ties, we are ready to print and return the results. If there are ties, we want to print the rankings with ties intact first.
        //Determine whether there are ties. If not, print and return the rankings. If so, print the rankings with ties intact.
        boolean ties=false;
        for (ArrayList<Sort> tier:tiers)
        {
            if (tier.size()>1 && !nullTier(tier)) // if the null tier has more than one Sort, that is OK. There is no need to
            {
                ties=true;
                break;
            }
        }
        if (!ties)
        {
            rankings=new TreeMap<Integer, Sort>();
            if (display)
            {
                System.out.println("Rankings:");
                Technical.sleep();
            }
            index=firstSeed;
            for (ArrayList<Sort> tier:tiers)
            {
                if (!nullTier(tier))
                {
                    rankings.put(index, tier.get(0));
                    tier.get(0).setRanking(index);
                    if (display)
                    {
                        System.out.println(Sort.toString(tier.get(0))+": "+tier.get(0).points());
                        Technical.sleep();
                    }
                    index++;
                }
                else
                {
                    while (index<=lastSeed)
                    {
                        rankings.put(index, null);
                        if (display)
                        {
                            System.out.println("#"+index+": null");
                        }
                        index++;
                    }
                }
            }
            System.out.println();
            Technical.sleep();
            return rankings;
        }
        else // From now on, we are assuming there are ties intact.
        {

            //Print the rankings with the ties intact.
            if (display)
            {
                System.out.println("Rankings with ties intact:");
                Technical.sleep();
                index=firstSeed;
                for (ArrayList<Sort> tier:tiers)
                {
                    if (nullTier(tier))
                    {
                        for (Sort s:tier)
                        {
                            System.out.println("#"+index+": null");
                            index++;
                        }
                        Technical.sleep();
                    }
                    else
                    {
                        if (tier.size()==1)
                        {
                            System.out.println("#"+index+": "+Sort.toString(tier.get(0))+": "+tier.get(0).points());
                            index++;
                            Technical.sleep();
                        }
                        else
                        {
                            System.out.println("#("+index+"-"+Integer.toString(index+tier.size()-1)+"): "+Technical.englishList(tier)+": "+tier.get(0).points());
                            Technical.sleep();
                            index+=tier.size();
                        }
                    }
                }
                System.out.println();
            }

            // Break the ties, by whichever method is appropriate. Only print anything for ties being broken
            // Then print the final rankings.
            rankings=new TreeMap<Integer, Sort>();
            index=firstSeed;
            for (ArrayList<Sort> tier:tiers)
            {
                if (nullTier(tier))
                {
                    for (int i=index; i<=lastSeed; i++)
                    {
                        rankings.put(index, null);
                        index++;
                    }
                }
                else if (tier.size()==1)
                {
                    rankings.put(index, tier.get(0));
                    tier.get(0).setRanking(index);
                    index++;
                }
                else // There is a tie to be broken
                {
                    Map<Integer, Sort> subRankings;
                    if (index-1+tier.size()<=lastSeed) // use head-to-head instead of playing any additional games
                    {
                        subRankings=sortByHeadToHead(tier, index, display);
                        index+=tier.size();
                    }
                    else // play a new round robin
                    {
                        RoundRobin subcall=new RoundRobin(tier, index, lastSeed);
                        subRankings=subcall.play(display, display);
                    }
                    for (int i:subRankings.keySet())
                    {
                        rankings.put(i, subRankings.get(i));
                        subRankings.get(i).setRanking(i);
                    }
                }
            }
            if (display)
            {
                if (firstSeed==1)
                {
                    System.out.println("Rankings:");
                    Technical.sleep();
                    for (int i=1; i<=lastSeed; i++)
                    {
                        System.out.println(Sort.toString(rankings.get(i)));
                        Technical.sleep();
                    }
                    System.out.println();
                }
            }
            return rankings;
        }
    }

    private void printStandings()
    {
        Technical.sortByPoints(sorts);
        String[][] table=new String[sorts.size()+1][7];
        table[0][1]="Name";
        table[0][2]="Wins";
        table[0][3]="Losses";
        table[0][4]="Ties";
        table[0][5]="Points";
        table[0][6]="Points per game";
        for (int i=0; i<sorts.size(); i++)
        {
            table[i+1][1]=sorts.get(i).getName();
            table[i+1][2]=Integer.toString(sorts.get(i).getNumWins());
            table[i+1][3]=Integer.toString(sorts.get(i).getNumLosses());
            table[i+1][4]=Integer.toString(sorts.get(i).getNumUnbrokenTies());
            table[i+1][5]=Integer.toString(sorts.get(i).points());
            table[i+1][6]=Double.toString(((double)(sorts.get(i).points()))/(sorts.get(i).getNumWins()+sorts.get(i).getNumUnbrokenTies()+sorts.get(i).getNumLosses()));
        }
        for (int i=1; i<=sorts.size(); i++)
        {
            int j = i;
            while (j>=2 && sorts.get(j - 1).points()==sorts.get(j-2).points())
            {
                j--;
            }
            table[i][0]=Integer.toString(j);
        }
        Technical.print(table);
    }

    public TreeMap<Integer, Sort> getRankings()
    {
        return rankings;
    }
}