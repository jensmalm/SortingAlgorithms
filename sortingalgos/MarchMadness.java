import java.util.*;
public class MarchMadness extends ExperimentationWithTimeAndSorts
{
    private static String[] teams={"DualPivotQuick", "MedianOf3Quick", "Quick", "TernaryMerge", "TernaryHeap", "Heap", "Merge", "Counting", "Flash", "Shell", "Java", "Tree", "234Tree", "Radix", "Insertion", "BinaryInsertion", "Selection", "Cocktail", "Bubble", "Gnome", "PrimitiveGuess", "Stooge", "Guess", "SmartBozo", "Bozo", "Bogo"};
    //private static String[] teams={"Stooge", "PrimitiveGuess", "Guess", "SmartBozo", "Bozo", "Bogo"};
    //private static String[] teams={"Insertion", "BinaryInsertion", "Selection", "Comb", "Cocktail", "Bubble", "Gnome"};
    //private static String[] teams={"TernaryMerge", "Merge", "TernaryHeap", "Java", "DualPivotQuick", "QuaternaryHeap", "Quick", "Heap", "Map", "234Tree", "Tree"};
    //private static String[] teams={"Counting", "TernaryMerge", "Merge", "Java", "Shell", "DualPivotQuick", "Quick", "Heap", "Radix", "Tree", "Map", "Insertion", "Selection", "Comb", "Bubble", "Bogo"};
    //Some teams currently not in the list: DualPivotQuick, Clutch, Map, QuaternaryHeap, Comb, MedianOf3Quick (code commented out)
    private static int[] points=new int[teams.length];
    public static void play()
    {
        for (int i=0; i<points.length; i++)
        {
            points[i]=0;
        }
        int int1=(int)(Math.random()*teams.length);
        int int2=(int)(Math.random()*teams.length);
        System.out.println("Ceremonial first pitch:");
        play(int1, int2);
        points[int1]=0;
        points[int2]=0;
        //Round robin:
        for (int i=0; i<teams.length; i++)
        {
            for (int j=i+1; j<teams.length; j++)
            {
                quickplay(i, j);
                printStandings();//prints the standings during the round robin
                /*
                String st=console.readLine();
                 */
                System.out.println();
                System.out.println();
            }
        }
        //Determine rankings:
        ArrayList<String> teamsCopy=new ArrayList<String>();
        ArrayList<Integer> scores=new ArrayList<Integer>();
        for (int i=0; i<teams.length; i++)
        {
            teamsCopy.add(teams[i]);
            scores.add(points[i]);
        }
        ArrayList<String> rankings=new ArrayList<String>();
        for (int i=0; i<teams.length; i++)
        {
            int most=-1;
            int index=-1;
            for (int j=0; j<scores.size(); j++)
            {
                if (scores.get(j)>most)
                {
                    index=j;
                    most=scores.get(j);
                }
            }
            rankings.add(teamsCopy.remove(index));
            scores.remove(index);
        }
        for (int i=0; i<teams.length; i++)
        {
            System.out.println(i+1+": "+rankings.get(i));
        }
        try
        {
            Thread.sleep(2500);
        }
        catch (InterruptedException ex){};
        Map<Integer, String> map=new HashMap<Integer, String>();
        for (int i=0; i<rankings.size(); i++)
        {
            map.put(i+1, rankings.get(i));
        }
        ArrayList<String> bracket=new ArrayList<String>();
        bracket.add(map.get(1));
        bracket.add(map.get(16));
        bracket.add(map.get(8));
        bracket.add(map.get(9));
        bracket.add(map.get(4));
        bracket.add(map.get(13));
        bracket.add(map.get(5));
        bracket.add(map.get(12));
        bracket.add(map.get(2));
        bracket.add(map.get(15));
        bracket.add(map.get(7));
        bracket.add(map.get(10));
        bracket.add(map.get(3));
        bracket.add(map.get(14));
        bracket.add(map.get(6));
        bracket.add(map.get(11));
        tournament(bracket);
    }

    public static void quickplay()
    {
        for (int i=0; i<points.length; i++)
        {
            points[i]=0;
        }
        //Round robin:
        for (int i=0; i<teams.length; i++)
        {
            for (int j=i+1; j<teams.length; j++)
            {
                quickplay(i, j);
                printStandings();//prints the standings during the round robin
                /*
                String st=console.readLine();
                 */
                System.out.println();
                System.out.println();
            }
        }/*
        //Attempt to do it using TournamentTree:
        TournamentTree tree=new TournamentTree(teams, points);
        tree.play();*/

        //Determine rankings:
        ArrayList<String> teamsCopy=new ArrayList<String>();
        ArrayList<Integer> scores=new ArrayList<Integer>();
        for (int i=0; i<teams.length; i++)
        {
            teamsCopy.add(teams[i]);
            scores.add(points[i]);
        }
        ArrayList<String> rankings=new ArrayList<String>();
        for (int i=0; i<teams.length; i++)
        {
            int most=-1;
            int index=-1;
            for (int j=0; j<scores.size(); j++)
            {
                if (scores.get(j)>most)
                {
                    index=j;
                    most=scores.get(j);
                }
            }
            rankings.add(teamsCopy.remove(index));
            scores.remove(index);
        }
        for (int i=0; i<teams.length; i++)
        {
            System.out.println(i+1+": "+rankings.get(i));
        }
        try
        {
            Thread.sleep(2500);
        }
        catch (InterruptedException ex){};
        Map<Integer, String> map=new HashMap<Integer, String>();
        for (int i=0; i<rankings.size(); i++)
        {
            map.put(i+1, rankings.get(i));
        }
        ArrayList<String> bracket=new ArrayList<String>();
        bracket.add(map.get(1));
        bracket.add(map.get(16));
        bracket.add(map.get(8));
        bracket.add(map.get(9));
        bracket.add(map.get(4));
        bracket.add(map.get(13));
        bracket.add(map.get(5));
        bracket.add(map.get(12));
        bracket.add(map.get(2));
        bracket.add(map.get(15));
        bracket.add(map.get(7));
        bracket.add(map.get(10));
        bracket.add(map.get(3));
        bracket.add(map.get(14));
        bracket.add(map.get(6));
        bracket.add(map.get(11));
        tournament(bracket);
    }

    private static void play(int i, int j)
    {
        System.out.println(teams[i]+" vs. "+teams[j]);
        try
        {
            Thread.sleep(1700);
        }
        catch (InterruptedException ex){};
        int l1=3+(int)(Math.random()*18);
        long score1=getTime(i, l1);
        System.out.println(teams[i]+": array of length "+(int)Math.pow(2, l1));
        System.out.println("Time: "+score1);
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException ex){};
        long score2=getTime(j, l1);
        System.out.println(teams[j]+": array of length "+(int)Math.pow(2, l1));
        System.out.println("Time: "+score2);
        try
        {
            Thread.sleep(1700);
        }
        catch (InterruptedException ex){};
        System.out.print("Winner: ");
        if (score1<score2)
        {
            System.out.println(teams[i]);
            points[i]+=3;
        }
        else if (score1==score2)
        {
            System.out.println("Tie");
            points[i]++;
            points[j]++;
        }
        else
        {
            System.out.println(teams[j]);
            points[j]+=3;
        }
        try
        {
            Thread.sleep(1700);
        }
        catch (InterruptedException ex){};
        System.out.println();
    }

    private static void quickplay(int i, int j)
    {
        int l1=3+(int)(Math.random()*18);
        long score1=getTime(i, l1);
        //try
        //{
            ////Thread.sleep(180);
        //}
        //catch (InterruptedException ex){};
        long score2=getTime(j, l1);
        if (score1<score2)
        {
            points[i]+=3;
        }
        else if (score1==score2)
        {
            points[i]++;
            points[j]++;
        }
        else
        {
            points[j]+=3;
        }
    }

    public static long getTime(int index, int pow)
    {
        if (index<0)
        {
            return Long.MAX_VALUE;
        }
        if (teams[index].equals("Quick")) //Quick
        {
            return ExperimentationWithTimeAndSorts.quick((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Java")) //Java
        {
            return ExperimentationWithTimeAndSorts.java((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Radix")) //Radix
        {
            return ExperimentationWithTimeAndSorts.radix((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Selection")) //Selection
        {
            if (pow>16)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.selection((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Insertion")) //Insertion
        {
            if (pow>17)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.insertion((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Bubble")) //Bubble
        {
            if (pow>16)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.bubble((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Bogo"))
        {
            if (pow>3)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.bogo((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Tree"))
        {
            return ExperimentationWithTimeAndSorts.tree((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Merge")) //Merge
        {
            return ExperimentationWithTimeAndSorts.merge((int)Math.pow(2, pow));
        }
        if (teams[index].equals("DualPivotQuick")) //DPQ
        {
            return ExperimentationWithTimeAndSorts.dualpivot((int)Math.pow(2, pow));
        }
        if (teams[index].equals("DualPivotBogo"))
        {
            if (pow>3)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.dualPivotBogo((int)Math.pow(2, pow));
        }
        if (teams[index].equals("BinaryInsertion"))
        {
            if (pow>17)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.binaryInsertion((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Shell"))
        {
            return ExperimentationWithTimeAndSorts.shell((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Comb"))
        {
            if (pow>16)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.comb((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Clutch"))
        {
            return ExperimentationWithTimeAndSorts.clutch((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Heap"))
        {
            return ExperimentationWithTimeAndSorts.heap((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Counting")) //Counting
        {
            return ExperimentationWithTimeAndSorts.counting((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Mergesertion")) //Counting
        {
            return ExperimentationWithTimeAndSorts.mergesertion((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Map"))
        {
            return ExperimentationWithTimeAndSorts.map((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Stooge"))
        {
            if (pow>11)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.stooge((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Gnome"))
        {
            if (pow>12)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.gnome((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Library"))
        {
            //return ExperimentationWithTimeAndSorts.library((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Bozo"))
        {
            if (pow>3)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.bozo((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Cocktail"))
        {
            if (pow>16)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.cocktail((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Heap2"))
        {
            return ExperimentationWithTimeAndSorts.heap2((int)Math.pow(2, pow));
        }
        if ((teams[index].equalsIgnoreCase("TriHeap")) || (teams[index].equalsIgnoreCase("trinaryHeap")) || (teams[index].equalsIgnoreCase("ternaryheap")))
        {
            return ExperimentationWithTimeAndSorts.triHeap((int)Math.pow(2, pow));
        }
        if ((teams[index].equalsIgnoreCase("TernaryMerge")) || (teams[index].equalsIgnoreCase("Merge3")))
        {
            return ExperimentationWithTimeAndSorts.merge3((int)Math.pow(2, pow));
        }
        if (teams[index].equalsIgnoreCase("QuaternaryHeap"))
        {
            return ExperimentationWithTimeAndSorts.quaternaryHeap((int)Math.pow(2, pow));
        }
        if (teams[index].equalsIgnoreCase("QuinaryHeap"))
        {
            return ExperimentationWithTimeAndSorts.quinaryHeap((int)Math.pow(2, pow));
        }
        /*if (teams[index].equalsIgnoreCase("Library"))
        {
        return ExperimentationWithTimeAndSorts.library((int)Math.pow(2, pow));
        }*/
        if (teams[index].equalsIgnoreCase("Test"))
        {
            return ExperimentationWithTimeAndSorts.test((int)Math.pow(2, pow));
        }
        if (teams[index].equalsIgnoreCase("Bucket"))
        {
            return ExperimentationWithTimeAndSorts.bucket((int)Math.pow(2, pow));
        }
        if (teams[index].equalsIgnoreCase("Bitonic"))
        {
            return ExperimentationWithTimeAndSorts.bitonic((int)Math.pow(2, pow));
        }
        if (teams[index].equalsIgnoreCase("SenaryHeap"))
        {
            return ExperimentationWithTimeAndSorts.naryHeap((int)Math.pow(2, pow), 6);
        }
        if (teams[index].equals("SmartBozo"))
        {
            if (pow>11)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.smartBozo((int)Math.pow(2, pow));
        }
        if (teams[index].equals("Guess"))
        {
            if (pow>12)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.guess((int)Math.pow(2, pow));
        }
        if (teams[index].equals("PrimitiveGuess"))
        {
            if (pow>14)
            {
                return Long.MAX_VALUE;
            }
            return ExperimentationWithTimeAndSorts.primitiveGuess((int)Math.pow(2, pow));
        }
        if (teams[index].equalsIgnoreCase("Flash"))
        {
            return ExperimentationWithTimeAndSorts.flash((int)Math.pow(2, pow));
        }
        if (teams[index].equalsIgnoreCase("BalancingTree"))
        {
            return ExperimentationWithTimeAndSorts.balancingTree((int)Math.pow(2, pow));
        }
        if (teams[index].equalsIgnoreCase("InplaceMerge"))
        {
            return ExperimentationWithTimeAndSorts.inplaceMerge((int)Math.pow(2, pow));
        }
        if (teams[index].equalsIgnoreCase("234Tree"))
        {
            return ExperimentationWithTimeAndSorts.tree234((int)Math.pow(2, pow));
        }
        if (teams[index].equalsIgnoreCase("Jens"))
        {
            return ExperimentationWithTimeAndSorts.jens((int)Math.pow(2, pow));
        }
        /*
        if (teams[index].equalsIgnoreCase("MedianOf3Quick"))
        {
            return ExperimentationWithTimeAndSorts.medianquick((int)Math.pow(2, pow));
        }
        */
        if ((teams[index].length()>=7) && (teams[index].substring(teams[index].length()-7).equalsIgnoreCase("aryheap")))
        {
            int number=Integer.valueOf(teams[index].substring(0, teams[index].length()-7));
            return ExperimentationWithTimeAndSorts.naryHeap((int)Math.pow(2, pow), number);
        }
        return Long.MAX_VALUE;
    }

    public static long getTime(String st, int pow)
    {
        int index=0;
        while (true)
        {
            if (teams[index].equals(st))
            {
                return getTime(index, pow);
            }
            index++;
        }
    }

    public static void printStandings()
    {
        for (int i=0; i<teams.length; i++)
        {
            System.out.println(teams[i]+": "+points[i]);
        }
    }

    public static void play(String st1, String st2)
    {
        if (st1.equalsIgnoreCase("dpq"))
        {
            st1="DualPivotQuick";
        }
        if (st2.equalsIgnoreCase("dpq"))
        {
            st2="DualPivotQuick";
        }
        System.out.println(st1+" vs. "+st2);
        int i=-1;
        int j=-1;
        for (int k=0; k<teams.length; k++)
        {
            if (teams[k].equals(st1))
            {
                i=k;
            }
            if (teams[k].equals(st2))
            {
                j=k;
            }
        }
        try
        {
            Thread.sleep(1700);
        }
        catch (InterruptedException ex){};
        int l1=3+(int)(Math.random()*18);
        long score1=getTime(i, l1);
        System.out.println(teams[i]+": array of length "+(int)Math.pow(2, l1));
        System.out.println("Time: "+score1);
        try
        {
            Thread.sleep(1700);
        }
        catch (InterruptedException ex){};
        long score2=getTime(j, l1);
        System.out.println(teams[j]+": array of length "+(int)Math.pow(2, l1));
        System.out.println("Time: "+score2);
        try
        {
            Thread.sleep(1700);
        }
        catch (InterruptedException ex){};
        System.out.print("Winner: ");
        if (score1<score2)
        {
            System.out.println(teams[i]);
            points[i]+=3;
        }
        else if (score1==score2)
        {
            System.out.println("Tie");
            points[i]++;
            points[j]++;
            play(st1, st2);
        }
        else
        {
            System.out.println(teams[j]);
            points[j]+=3;
        }
        try
        {
            Thread.sleep(1700);
        }
        catch (InterruptedException ex){};
        System.out.println();
    }

    private static String fixLength(String st, int n)
    {
        if (st.length()>n)
        {
            return st.substring(0, n);
        }
        if (st.length()==n)
        {
            return st;
        }
        String st1="";
        for (int i=0; i<n-st.length(); i++)
        {
            st1=st1+" ";
        }
        return st+st1;
    }

    private static String fixLength(double d, int n)
    {
        return fixLength(Double.toString(d), n);
    }

    public static void sleep(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException ex){};
    }

    public static void headToHead(String team1, String team2, int trials)
    {
        int overallWins1=0;
        int overallWins2=0;
        int overallTies=0;
        int int1=4;
        int int2=4;
        for (int i=0; i<teams.length; i++)
        {
            if (teams[i].equals(team1))
            {
                int1=i;
            }
            if (teams[i].equals(team2))
            {
                int2=i;
            }
        }
        for (int i=3; i<=21; i++)
        {
            System.out.println("Array of length "+(int)Math.pow(2, i));
            double total1=0;
            int wins1=0;
            double total2=0;
            int wins2=0;
            int ties=0;
            for (int j=0; j<trials; j++)
            {
                sleep(180);
                long t1=getTime(int1, i);
                sleep(180);
                long t2=getTime(int2, i);
                if (t1<t2)
                {
                    wins1++;
                    overallWins1++;
                    System.out.println(team1+" "+t1+"-"+t2);
                }
                else if (t1>t2)
                {
                    wins2++;
                    overallWins2++;
                    System.out.println(team2+" "+t2+"-"+t1);
                }
                else
                {
                    ties++;
                    System.out.println("tie "+t1+"-"+t2);
                }
                total1+=(double)(t1);
                total2+=(double)(t2);
            }
            total1=total1/trials;
            total2=total2/trials;
            if (wins1>wins2)
            {
                System.out.println("By games: "+team1+" "+wins1+"-"+wins2+"-"+ties);
            }
            else if (wins1<wins2)
            {
                System.out.println("By games: "+team2+" "+wins2+"-"+wins1+"-"+ties);
            }
            else
            {
                System.out.println("By games: Tie "+wins2+"-"+wins1+"-"+ties);
            }
            if (total1<total2)
            {
                System.out.println("By average: "+team1+" "+total1+"-"+total2);
            }
            else if (total1>total2)
            {
                System.out.println("By average: "+team2+" "+total2+"-"+total1);
            }
            else
            {
                System.out.println("By average: Tie "+total2+"-"+total1);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Overall wins: "+team1+" "+overallWins1+", "+team2+" "+overallWins2);
        System.out.println();
    }

    public static void tournament(ArrayList<String> bracket)
    {
        String[] eliteEight=new String[8];
        assert(bracket.size()==16);
        HashMap<String, Integer> seeds=new HashMap<String, Integer>();
        seeds.put(bracket.get(0), 1);
        seeds.put(bracket.get(1), 16);
        seeds.put(bracket.get(2), 8);
        seeds.put(bracket.get(3), 9);
        seeds.put(bracket.get(4), 4);
        seeds.put(bracket.get(5), 13);
        seeds.put(bracket.get(6), 5);
        seeds.put(bracket.get(7), 12);
        seeds.put(bracket.get(8), 2);
        seeds.put(bracket.get(9), 15);
        seeds.put(bracket.get(10), 7);
        seeds.put(bracket.get(11), 10);
        seeds.put(bracket.get(12), 3);
        seeds.put(bracket.get(13), 14);
        seeds.put(bracket.get(14), 6);
        seeds.put(bracket.get(15), 11);
        sleep(4000);
        System.out.println("First round:");
        System.out.println();
        for (int i=0; i<bracket.size(); i+=2)
        {
            System.out.println("#"+seeds.get(bracket.get(i))+" "+bracket.get(i)+" vs. #"+seeds.get(bracket.get(i+1))+" "+bracket.get(i+1));
        }
        System.out.println();
        sleep(4000);
        for (int i=0; i<16; i+=2)
        {
            String winner=getWinner(bracket.get(i), bracket.get(i+1));
            eliteEight[i/2]=winner;
        }

        System.out.println();
        sleep(1700);
        System.out.println("Quarterfinals:");
        System.out.println();
        for (int i=0; i<eliteEight.length; i+=2)
        {
            System.out.println("#"+seeds.get(eliteEight[i])+" "+eliteEight[i]+" vs. #"+seeds.get(eliteEight[i+1])+" "+eliteEight[i+1]);
        }
        System.out.println();
        sleep(4000);
        String[] four=new String[4];
        for (int i=0; i<8; i+=2)
        {
            four[i/2]=getWinner(eliteEight[i], eliteEight[i+1]);
        }
        System.out.println();
        sleep(1700);
        System.out.println("Semifinals:");
        System.out.println();
        for (int i=0; i<four.length; i+=2)
        {
            System.out.println("#"+seeds.get(four[i])+" "+four[i]+" vs. "+"#"+seeds.get(four[i+1])+" "+four[i+1]);
        }
        System.out.println();
        sleep(4000);
        String[] finals=new String[2];
        String[] consolation=new String[2];
        for (int i=0; i<four.length; i+=2)
        {
            finals[i/2]=getSeriesWinner(four[i], four[i+1], 2);
        }
        if (finals[0].equals(four[0]))
        {
            consolation[0]=four[1];
        }
        else
        {
            consolation[0]=four[0];
        }
        if (finals[1].equals(four[2]))
        {
            consolation[1]=four[3];
        }
        else
        {
            consolation[1]=four[2];
        }
        System.out.println();
        sleep(1700);
        System.out.println("Third place series:");
        System.out.println();
        System.out.println("#"+seeds.get(consolation[0])+" "+consolation[0]+" vs. #"+seeds.get(consolation[1])+" "+consolation[1]);
        String thirdPlace=getSeriesWinner(consolation[0], consolation[1], 2);
        System.out.println("Third place: "+thirdPlace);
        System.out.println();
        sleep(1700);
        System.out.println("Finals:");
        System.out.println();
        System.out.println("#"+seeds.get(finals[0])+" "+finals[0]+" vs. #"+seeds.get(finals[1])+" "+finals[1]);
        System.out.println();
        String champion=getSeriesWinner(finals[0], finals[1], 4);
        String secondPlace="";
        if (champion.equals(finals[0]))
        {
            secondPlace=finals[1];
        }
        else
        {
            secondPlace=finals[0];
        }
        System.out.println("Second place: "+secondPlace);
        sleep(1500);
        System.out.println();
        System.out.println("CHAMPION: "+champion);
        System.out.println();
        System.out.println();
        System.out.println("             #1: "+champion);
        System.out.println("#2: "+secondPlace);
        System.out.println("                         #3 "+thirdPlace); 
    }

    public static void tournament()
    {
        ArrayList<String> bracket=new ArrayList<String>();
        for (int i=0; i<16; i++)
        {
            bracket.add(teams[i]);
        }
        tournament(bracket);
    }

    public static String getWinner(String st1, String st2)
    {
        if (st2.equals("null"))
        {
            System.out.println(st1+" vs. "+st2+": winner = "+st1);
            System.out.println();
            return st1;
        }
        if (st1.equals("null"))
        {
            System.out.println(st1+" vs. "+st2+": winner = "+st2);
            System.out.println();
            return st2;
        }
        int t1=-1;
        int t2=-1;
        for (int i=0; i<teams.length; i++)
        {
            if (teams[i].equals(st1))
            {
                t1=i;
                break;
            }
        }
        for (int i=0; i<teams.length; i++)
        {
            if (teams[i].equals(st2))
            {
                t2=i;
                break;
            }
        }
        System.out.println(st1+" vs. "+st2+":");
        sleep(1700);
        int pow=3+(int)(Math.random()*18);
        int length=(int)(Math.pow(2, pow));
        System.out.println(st1+": array of length "+length);
        long time1=getTime(t1, pow);
        System.out.println("Time: "+time1);
        sleep(1700);
        System.out.println(st2+": array of length "+length);
        long time2=getTime(t2, pow);
        System.out.println("Time: "+time2);
        sleep(1700);
        System.out.print("Winner: ");
        if (time1<time2)
        {
            System.out.println(st1);
            System.out.println();
            sleep(1700);
            return st1;
        }
        else if (time1>time2)
        {
            System.out.println(st2);
            System.out.println();
            sleep(1700);
            return st2;
        }
        else
        {
            System.out.println("Tie");
            sleep(1700);
            return getWinner(st1, st2);
        }
    }

    public static String getSeriesWinner(String st1, String st2, int upto)
    {
        if (st1.equalsIgnoreCase("dpq"))
        {
            st1="DualPivotQuick";
        }
        if (st2.equalsIgnoreCase("dpq"))
        {
            st2="DualPivotQuick";
        }
        System.out.println(st1+" vs. "+st2+": best of "+(2*upto-1)+" series.");
        System.out.println();
        sleep(1700);
        int games1=0;
        int games2=0;
        while (true)
        {
            if (games1+games2>0)
            {
                if (games1>games2)
                {
                    System.out.println(st1+" leads series "+games1+"-"+games2);
                }
                else if (games2>games1)
                {
                    System.out.println(st2+" leads series "+games2+"-"+games1);
                }
                else
                {
                    System.out.println("Series tied "+games1+"-"+games2);
                }
                System.out.println();
                sleep(1700);
            }
            System.out.println("Game "+(games1+games2+1));
            sleep(1700);
            String winner=getWinner(st1, st2);
            if (winner.equals(st1))
            {
                games1++;
                if (games1==upto)
                {
                    System.out.println();
                    System.out.println(st1+" wins series "+games1+"-"+games2);
                    System.out.println();
                    sleep(1700);
                    return st1;
                }
            }
            else if (winner.equals(st2))
            {
                games2++;
                if (games2==upto)
                {
                    System.out.println();
                    System.out.println(st2+" wins series "+games2+"-"+games1);
                    System.out.println();
                    sleep(1700);
                    return st2;
                }
            }
        }
    }

    public static void playWithHeats()
    {
        String[] copy=new String[teams.length];
        for (int i=0; i<teams.length; i++)
        {
            copy[i]=teams[i];
        }
        teams=Randomizer.shuffle(teams);
        int[] eliteEight=new int[8];
        int index=0;
        for (int i=0; i<points.length; i++)
        {
            points[i]=0;
        }
        ArrayList<Integer> pool=new ArrayList<Integer>();
        for (int i=0; i<teams.length; i++)
        {
            pool.add(i);
        }
        ArrayList<ArrayList<Integer>> heats=new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<4; i++)
        {
            heats.add(new ArrayList<Integer>());
        }
        while (pool.size()>=4)
        {
            heats.get(0).add(pool.remove(0));
            heats.get(1).add(pool.remove(0));
            heats.get(2).add(pool.remove(0));
            heats.get(3).add(pool.remove(0));
        }
        int full=teams.length/4+1;
        ArrayList<Integer> decoy=new ArrayList<Integer>();
        for (int i=0; i<full; i++)
        {
            decoy.add(null);
        }
        while (pool.size()>0)
        {
            ArrayList<Integer> addToMe=decoy;
            while (addToMe.size()==full)
            {
                double rand=Math.random();
                if (rand<.25)
                {
                    addToMe=heats.get(0);
                }
                else if (rand<.5)
                {
                    addToMe=heats.get(1);
                }
                else if (rand<.75)
                {
                    addToMe=heats.get(2);
                }
                else
                {
                    addToMe=heats.get(3);
                }
            }
            addToMe.add(pool.remove(0));
        }
        for (int i=0; i<4; i++)
        {
            System.out.println("Heat "+(i+1));
            sleep(1700);
            for (int j=0; j<heats.get(i).size(); j++)
            {
                System.out.println(teams[heats.get(i).get(j)]);
                sleep(1000);
            }
            System.out.println();
            sleep(1700);
        }
        for (int i=0; i<4; i++)
        {
            System.out.println("HEAT "+(i+1));
            sleep(1700);
            System.out.println();
            for (int j=0; j<heats.get(i).size(); j++)
            {
                for (int k=j+1; k<heats.get(i).size(); k++)
                {
                    play(heats.get(i).get(j), heats.get(i).get(k));
                    sleep(1700);
                    System.out.println("Heat "+(i+1)+" standings:");
                    printHeatStandings(heats.get(i));
                    System.out.println();
                    sleep(1700);
                }
            }
            int firstIndex=-1;
            int maximum=-1;
            for (int j=0; j<heats.get(i).size(); j++)
            {
                if (points[heats.get(i).get(j)]>maximum)
                {
                    maximum=points[heats.get(i).get(j)];
                    firstIndex=j;
                }
            }
            int secondIndex=0;
            maximum=-1;
            for (int j=0; j<heats.get(i).size(); j++)
            {
                if ((points[heats.get(i).get(j)]>maximum) && (j!=firstIndex))
                {
                    maximum=points[heats.get(i).get(j)];
                    secondIndex=j;
                }
            }
            eliteEight[index]=heats.get(i).get(firstIndex);
            eliteEight[index+1]=heats.get(i).get(secondIndex);
            index+=2;
            System.out.println("Teams advancing: "+teams[eliteEight[index-2]]+", "+teams[eliteEight[index-1]]);
        }
        int[] newEight=new int[8];
        newEight[0]=eliteEight[0];
        newEight[1]=eliteEight[7];
        newEight[2]=eliteEight[3];
        newEight[3]=eliteEight[4];
        newEight[4]=eliteEight[1];
        newEight[5]=eliteEight[6];
        newEight[6]=eliteEight[2];
        newEight[7]=eliteEight[5];
        eliteEight=newEight;
        System.out.println("Quarterfinals:");
        System.out.println();
        for (int i=0; i<eliteEight.length; i+=2)
        {
            System.out.println(teams[eliteEight[i]]+" vs. "+teams[eliteEight[i+1]]);
        }
        System.out.println();
        sleep(4000);
        String[] four=new String[4];
        for (int i=0; i<8; i+=2)
        {
            four[i/2]=getWinner(teams[eliteEight[i]], teams[eliteEight[i+1]]);
        }
        System.out.println();
        sleep(1700);
        System.out.println("Semifinals:");
        System.out.println();
        for (int i=0; i<four.length; i+=2)
        {
            System.out.println(four[i]+" vs. "+four[i+1]);
        }
        System.out.println();
        sleep(4000);
        String[] finals=new String[2];
        String[] consolation=new String[2];
        for (int i=0; i<four.length; i+=2)
        {
            finals[i/2]=getSeriesWinner(four[i], four[i+1], 2);
        }
        if (finals[0].equals(four[0]))
        {
            consolation[0]=four[1];
        }
        else
        {
            consolation[0]=four[0];
        }
        if (finals[1].equals(four[2]))
        {
            consolation[1]=four[3];
        }
        else
        {
            consolation[1]=four[2];
        }
        System.out.println();
        sleep(1700);
        System.out.println("Third place series:");
        System.out.println();
        System.out.println(consolation[0]+" vs. "+consolation[1]);
        String thirdPlace=getSeriesWinner(consolation[0], consolation[1], 2);
        System.out.println("Third place: "+thirdPlace);
        System.out.println();
        sleep(1700);
        System.out.println("Finals:");
        System.out.println();
        System.out.println(finals[0]+" vs. "+finals[1]);
        System.out.println();
        String champion=getSeriesWinner(finals[0], finals[1], 4);
        String secondPlace="";
        if (champion.equals(finals[0]))
        {
            secondPlace=finals[1];
        }
        else
        {
            secondPlace=finals[0];
        }
        System.out.println("Second place: "+secondPlace);
        sleep(1500);
        System.out.println();
        System.out.println("CHAMPION: "+champion);
        teams=copy;
    }

    public static void playWithFairHeats()
    {
        for (int i=0; i<points.length; i++)
        {
            points[i]=0;
        }
        int[] eliteEight=new int[8];
        ArrayList<ArrayList<Integer>> heats=new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<4; i++)
        {
            heats.add(new ArrayList<Integer>());
        }
        int gothrough=teams.length/4;
        boolean yes=true;
        int index=0;
        for (int i=0; i<gothrough; i++)
        {
            if (yes)
            {
                heats.get(0).add(index);
                index++;
                heats.get(1).add(index);
                index++;
                heats.get(2).add(index);
                index++;
                heats.get(3).add(index);
                index++;
            }
            else
            {
                heats.get(3).add(index);
                index++;
                heats.get(2).add(index);
                index++;
                heats.get(1).add(index);
                index++;
                heats.get(0).add(index);
                index++;
            }
            yes=!yes;
        }
        while (index<teams.length)
        {
            heats.get(3-(teams.length%index)).add(index);
            index++;
        }
        heats.set(0, Randomizer.shuffle(heats.get(0)));
        heats.set(1, Randomizer.shuffle(heats.get(1)));
        heats.set(2, Randomizer.shuffle(heats.get(2)));
        heats.set(3, Randomizer.shuffle(heats.get(3)));
        index=0;
        for (int i=0; i<4; i++)
        {
            System.out.println("Heat "+(i+1));
            sleep(1700);
            for (int j=0; j<heats.get(i).size(); j++)
            {
                System.out.println(teams[heats.get(i).get(j)]);
                sleep(1000);
            }
            System.out.println();
            sleep(1700);
        }
        for (int i=0; i<4; i++)
        {
            System.out.println("HEAT "+(i+1));
            sleep(1700);
            System.out.println();
            for (int j=0; j<heats.get(i).size(); j++)
            {
                for (int k=j+1; k<heats.get(i).size(); k++)
                {
                    play(heats.get(i).get(j), heats.get(i).get(k));
                    sleep(1700);
                    System.out.println("Heat "+(i+1)+" standings:");
                    printHeatStandings(heats.get(i));
                    System.out.println();
                    sleep(1700);
                }
            }
            int firstIndex=-1;
            int secondIndex=-1;
            ArrayList<Integer> most=new ArrayList<Integer>();
            int max=-1;
            ArrayList<Integer> secondMost=new ArrayList<Integer>();
            int secondMax=-1;
            for (int j=0; j<heats.get(i).size(); j++)
            {
                if (points[heats.get(i).get(j)]>max)
                {
                    secondMost=most;
                    secondMax=max;
                    most=new ArrayList<Integer>();
                    most.add(j);
                    max=points[heats.get(i).get(j)];
                }
                else if (points[heats.get(i).get(j)]==max)
                {
                    most.add(j);
                }
                else if (points[heats.get(i).get(j)]>secondMax)
                {
                    secondMost=new ArrayList<Integer>();
                    secondMax=points[heats.get(i).get(j)];
                    secondMost.add(j);
                }
                else if (points[heats.get(i).get(j)]==secondMax)
                {
                    secondMost.add(j);
                }
            }
            if (most.size()>1)
            {
                most=Randomizer.shuffle(most);
                firstIndex=most.get(0);
                secondIndex=most.get(1);
            }
            else
            {
                firstIndex=most.get(0);
                secondMost=Randomizer.shuffle(secondMost);
                secondIndex=secondMost.get(0);
            }
            eliteEight[index]=heats.get(i).get(firstIndex);
            eliteEight[index+1]=heats.get(i).get(secondIndex);
            index+=2;
            System.out.println("Teams advancing: "+teams[eliteEight[index-2]]+", "+teams[eliteEight[index-1]]);
        }
        int[] newEight=new int[8];
        newEight[0]=eliteEight[0];
        newEight[1]=eliteEight[7];
        newEight[2]=eliteEight[3];
        newEight[3]=eliteEight[4];
        newEight[4]=eliteEight[1];
        newEight[5]=eliteEight[6];
        newEight[6]=eliteEight[2];
        newEight[7]=eliteEight[5];
        eliteEight=newEight;
        System.out.println("Quarterfinals:");
        System.out.println();
        for (int i=0; i<eliteEight.length; i+=2)
        {
            System.out.println(teams[eliteEight[i]]+" vs. "+teams[eliteEight[i+1]]);
        }
        System.out.println();
        sleep(4000);
        String[] four=new String[4];
        for (int i=0; i<8; i+=2)
        {
            four[i/2]=getWinner(teams[eliteEight[i]], teams[eliteEight[i+1]]);
        }
        System.out.println();
        sleep(1700);
        System.out.println("Semifinals:");
        System.out.println();
        for (int i=0; i<four.length; i+=2)
        {
            System.out.println(four[i]+" vs. "+four[i+1]);
        }
        System.out.println();
        sleep(4000);
        String[] finals=new String[2];
        String[] consolation=new String[2];
        for (int i=0; i<four.length; i+=2)
        {
            finals[i/2]=getSeriesWinner(four[i], four[i+1], 2);
        }
        if (finals[0].equals(four[0]))
        {
            consolation[0]=four[1];
        }
        else
        {
            consolation[0]=four[0];
        }
        if (finals[1].equals(four[2]))
        {
            consolation[1]=four[3];
        }
        else
        {
            consolation[1]=four[2];
        }
        System.out.println();
        sleep(1700);
        System.out.println("Third place series:");
        System.out.println();
        System.out.println(consolation[0]+" vs. "+consolation[1]);
        String thirdPlace=getSeriesWinner(consolation[0], consolation[1], 2);
        System.out.println("Third place: "+thirdPlace);
        System.out.println();
        sleep(1700);
        System.out.println("Finals:");
        System.out.println();
        System.out.println(finals[0]+" vs. "+finals[1]);
        System.out.println();
        String champion=getSeriesWinner(finals[0], finals[1], 4);
        String secondPlace="";
        if (champion.equals(finals[0]))
        {
            secondPlace=finals[1];
        }
        else
        {
            secondPlace=finals[0];
        }
        System.out.println("Second place: "+secondPlace);
        sleep(1500);
        System.out.println();
        System.out.println("CHAMPION: "+champion);
    }

    private static void printHeatStandings(ArrayList<Integer> heat)
    {
        for (int i=0; i<heat.size(); i++)
        {
            System.out.println(teams[heat.get(i)]+": "+points[heat.get(i)]);
        }
    }

    public static String get(int i)
    {
        return teams[i];
    }

    private static int play(int i1, int i2, int i3, boolean ties, boolean show)
    {
        int l=3+(int)(Math.random()*18);
        int pow=(int)(Math.pow(2, l));
        if (show)
        {
            System.out.println(teams[i1]+" vs. "+teams[i2]+" vs. "+teams[i3]);
            sleep(2000);
        }
        long t1=getTime(i1, l);
        if (show)
        {
            System.out.println(teams[i1]+": array of length "+pow);
            System.out.println("Time: "+t1);
            sleep(2000);
        }
        long t2=getTime(i2, l);
        if (show)
        {
            System.out.println(teams[i2]+": array of length "+pow);
            System.out.println("Time: "+t2);
            sleep(2000);
        }
        long t3=getTime(i3, l);
        if (show)
        {
            System.out.println(teams[i3]+": array of length "+pow);
            System.out.println("Time: "+t3);
            sleep(2000);
        }
        long best=t1;
        ArrayList<Integer> teamsWithMax=new ArrayList<Integer>();
        teamsWithMax.add(i1);
        if (t2<best)
        {
            teamsWithMax=new ArrayList<Integer>();
            teamsWithMax.add(i2);
            best=t2;
        }
        else if (t2==best)
        {
            teamsWithMax.add(i2);
        }
        if (t3<best)
        {
            teamsWithMax=new ArrayList<Integer>();
            teamsWithMax.add(i3);
            best=t3;
        }
        else if (t3==best)
        {
            teamsWithMax.add(i3);
        }
        if (teamsWithMax.size()==1)
        {
            if (show)
            {
                System.out.println("Winner: "+teams[teamsWithMax.get(0)]);
                sleep(2000);
            }
            points[teamsWithMax.get(0)]+=3;
            return teamsWithMax.get(0);
        }
        else if (teamsWithMax.size()==2)
        {
            if (show)
            {
                System.out.println("Winner: tie between "+teams[teamsWithMax.get(0)]+" and "+teams[teamsWithMax.get(1)]);
                sleep(2000);
                if (ties)
                {
                    points[teamsWithMax.get(0)]+=1;
                    points[teamsWithMax.get(1)]+=1;
                    return -1;
                }
                else
                {
                    String a= getWinner(teams[teamsWithMax.get(0)], teams[teamsWithMax.get(1)]);
                    for (int k=0; k<teams.length; k++)
                    {
                        if (teams[k].equals(a))
                        {
                            return k;
                        }
                    }
                    return -1;
                }
            }
            else
            {
                if (ties)
                {
                    points[teamsWithMax.get(0)]+=1;
                    points[teamsWithMax.get(1)]+=1;
                    return -1;
                }
                return -1;
            }
        }
        else
        {
            if (ties)
            {
                points[i1]++;
                points[i2]++;
                points[i3]++;
                return -21;
            }
            else
            {
                if (show)
                {
                    System.out.println("Winner: three-way tie.");
                    sleep(2000);
                }
                return play(i1, i2, i3, ties, show);
            }
        }
    }

    public static String getWinner(String s1, String s2, String s3)
    {
        int i1=-1;
        int i2=-1;
        int i3=-3;
        for (int i=0; i<teams.length; i++)
        {
            if (teams[i].equalsIgnoreCase(s1))
            {
                i1=i;
            }
            if (teams[i].equalsIgnoreCase(s2))
            {
                i2=i;
            }
            if (teams[i].equalsIgnoreCase(s3))
            {
                i3=i;
            }
        }
        if ((i1==-1) || (i2==-1) || (i3==-1))
        {
            return "No such team";
        }
        return teams[play(i1, i2, i3, false, true)];
    }

    public static String getSeriesWinner(String s1, String s2, String s3, int upto)
    {
        System.out.println(s1+" vs. "+s2+" vs. "+s3+": best of "+(3*upto-2)+" series");
        sleep(2000);
        int games1=0;
        int games2=0;
        int games3=0;
        while (true)
        {
            if (games1+games2+games3!=0)
            {
                System.out.println();
                System.out.println(s1+" "+games1+", "+s2+" "+games2+", "+s3+" "+games3);
                sleep(2000);
                System.out.println();
            }
            String winner=getWinner(s1, s2, s3);
            if (winner.equalsIgnoreCase(s1))
            {
                games1++;
                if (games1==upto)
                {
                    System.out.println(s1+" wins series "+upto+"-"+games2+" ("+s2+")-"+games3+" ("+s3+")");
                    return s1;
                }
            }
            if (winner.equalsIgnoreCase(s2))
            {
                games2++;
                if (games2==upto)
                {
                    System.out.println(s2+" wins series "+upto+"-"+games1+" ("+s1+")-"+games3+" ("+s3+")");
                    return s2;
                }
            }
            if (winner.equalsIgnoreCase(s3))
            {
                games3++;
                if (games3==upto)
                {
                    System.out.println(s3+" wins series "+upto+"-"+games2+" ("+s2+")-"+games1+" ("+s1+")");
                    return s3;
                }
            }
        }
    }

    public static void triRobin()
    {
        for (int i=0; i<teams.length; i++)
        {
            points[i]=0;
        }
        /*for (int i=0; i<teams1.length; i++)
        {
        for (int j=i+1; j<teams1.length; j++)
        {
        for (int k=j+1; k<teams1.length; k++)
        {
        play(teams1[i]-1, teams1[j]-1, teams1[k]-1, true, false);
        System.out.println();
        System.out.println();
        printStandings();
        System.out.println();
        System.out.println();

        play(teams2[i]-1, teams2[j]-1, teams2[k]-1, true, false);
        System.out.println();
        System.out.println();
        printStandings();
        System.out.println();
        System.out.println();

        play(teams3[i]-1, teams3[j]-1, teams3[k]-1, true, false);
        System.out.println();
        System.out.println();
        printStandings();
        System.out.println();
        System.out.println();
        }
        }
        }*/
        for (int i=0; i<teams.length; i++)
        {
            for (int j=i+1; j<teams.length; j++)
            {
                for (int k=j+1; k<teams.length; k++)
                {
                    play(i, j, k, true, false);
                    System.out.println();
                    System.out.println();
                    printStandings();
                    System.out.println();
                    System.out.println();
                }
            }
        }
    }

    public static String trinaryPostseason()
    {
        ArrayList<Integer> rankings=new ArrayList<Integer>();
        Map<String, Integer> map=new HashMap<String, Integer>();
        for (int i=0; i<27; i++)
        {
            if (i>=teams.length)
            {
                rankings.add(-1);
                map.put(null, (i+1));
            }
            else
            {
                int index=-1;
                int maximum=-1;
                for (int j=0; j<teams.length; j++)
                {
                    if ((!rankings.contains(j)) && (points[j]>maximum))
                    {
                        maximum=points[j];
                        index=j;
                    }
                }
                rankings.add(index);
                map.put(teams[index], i+1);
            }
        }
        System.out.println();
        System.out.println();
        sleep(2500);
        System.out.println("Regular season:");
        for (int i=0; i<27; i++)
        {
            System.out.println("#"+(i+1)+": "+teams[rankings.get(i)]);
        }
        System.out.println();
        System.out.println();
        sleep(2500);
        ArrayList<String> bracket=new ArrayList<String>();
        int[] order={1, 18, 19, 6, 13, 24, 7, 12, 25, 2, 17, 20, 5, 14, 23, 8, 11, 26, 3, 16, 21, 4, 15, 22, 9, 10, 27};
        for (int i=0; i<order.length; i++)
        {
            bracket.add(teams[rankings.get(order[i]-1)]);
        }
        return findChampion(bracket, map);
    }

    public static void trinaryMadness()
    {
        triRobin();
        trinaryPostseason();
    }

    public static String findChampion(ArrayList<String> bracket, Map<String, Integer> map)
    {
        //bracket.size() must be a power of 3
        ArrayList<String> nextBracket=new ArrayList<String>();
        if (bracket.size()==3)
        {
            System.out.println("Finals: "+"#"+map.get(bracket.get(0))+" "+bracket.get(0)+" vs. "+"#"+map.get(bracket.get(1))+" "+bracket.get(1)+" vs. "+"#"+map.get(bracket.get(2))+" "+bracket.get(2));
            sleep(2000);
            String champion=getSeriesWinner(bracket.get(0), bracket.get(1), bracket.get(2), 4);
            sleep(2000);
            System.out.println("CHAMPION: "+champion);
            return champion;
        }
        else if (bracket.size()==9)
        {
            System.out.println("Semifinals");
            for (int i=0; i<3; i++)
            {
                int i0=3*i;
                int i1=3*i+1;
                int i2=3*i+2;
                System.out.println("#"+map.get(bracket.get(i0))+" "+bracket.get(i0)+" vs. "+"#"+map.get(bracket.get(i1))+" "+bracket.get(i1)+" vs. "+"#"+map.get(bracket.get(i2))+" "+bracket.get(i2));
                sleep(2000);
                String advancing=getSeriesWinner(bracket.get(i0), bracket.get(i1), bracket.get(i2), 2);
                sleep(2000);
                System.out.println("Series winner: "+advancing);
                nextBracket.add(advancing);
                sleep(2000);
                System.out.println();
            }
        }
        else
        {
            System.out.println("Round of "+bracket.size());
            for (int i=0; i<bracket.size()/3; i++)
            {
                int i0=3*i;
                int i1=3*i+1;
                int i2=3*i+2;
                System.out.println("#"+map.get(bracket.get(i0))+" "+bracket.get(i0)+" vs. "+"#"+map.get(bracket.get(i1))+" "+bracket.get(i1)+" vs. "+"#"+map.get(bracket.get(i2))+" "+bracket.get(i2));
                sleep(2000);
                String advancing=getWinner(bracket.get(i0), bracket.get(i1), bracket.get(i2));
                nextBracket.add(advancing);
                sleep(2000);
                System.out.println();
            }
        }
        return findChampion(nextBracket, map);
    }

    private static String makeFit(String st, int n)
    {
        if (st.length()<=n)
        {
            int add=n-st.length();
            for (int i=0; i<add; i++)
            {
                st=st+" ";
            }
            return st;
        }
        return st.substring(0, n);
    }

    public static double[][] census(int trials)
    {
        double[][] entries=new double[teams.length][19];
        for (int i=0; i<entries.length; i++)
        {
            for (int j=0; j<entries[i].length; j++)
            {
                long total=0;
                for (int k=0; k<trials; k++)
                {
                    total+=getTime(i, 3+j);
                }
                entries[i][j]=((double)(total))/trials;
            }
        }
        String[][] strings=new String[entries.length][entries[0].length];
        for (int i=0; i<strings.length; i++)
        {
            for (int j=0; j<strings[0].length; j++)
            {
                strings[i][j]=makeFit(Double.toString(entries[i][j]), 9);
            }
        }
        System.out.print("                    ");
        for (int i=0; i<entries[0].length; i++)
        {
            String st=makeFit(Integer.toString((int)(Math.pow(2, i+3))), 9);
            System.out.print(st+" ");
        }
        System.out.println();
        for (int i=0; i<strings.length; i++)
        {
            System.out.print(makeFit(teams[i], 20));
            for (int j=0; j<strings[i].length; j++)
            {
                System.out.print(makeFit(strings[i][j], 9)+" ");
            }
            System.out.println();
        }
        return entries;
    }

    public static int numTeams()
    {
        return teams.length;
    }

    public static void printTeams()
    {
        System.out.println();
        for (int i=0; i<teams.length; i++)
        {
            System.out.println((i+1)+" "+teams[i]);
        }
        System.out.println();
    }

    public static int[] fairSequence(int length)
    {
        if (length==1)
        {
            int[] returnme=new int[1];
            returnme[0]=0;
            return returnme;
        }
        else
        {
            int[] recursion=fairSequence(length/2);
            length=recursion.length*2;
            int[] array=new int[length];
            for (int i=0; i<recursion.length; i++)
            {
                array[2*i]=recursion[i];
                array[2*i+1]=length-recursion[i]-1;
            }
            return array;
        }
    }

    public static void doubleElimination()
    {
         for (int i=0; i<points.length; i++)
        {
            points[i]=0;
        }
        //Round robin:
        for (int i=0; i<teams.length; i++)
        {
            for (int j=i+1; j<teams.length; j++)
            {
                quickplay(i, j);
                printStandings();//prints the standings during the round robin
                /*
                String st=console.readLine();
                 */
                System.out.println();
                System.out.println();
            }
        }
        ArrayList<Integer> rankings=new ArrayList<Integer>();
        Map<String, Integer> map=new HashMap<String, Integer>();
        for (int i=0; i<fairSequence(teams.length).length; i++)
        {
            if (i>=teams.length)
            {
                rankings.add(-1);
                map.put(null, (i+1));
            }
            else
            {
                int index=-1;
                int maximum=-1;
                for (int j=0; j<teams.length; j++)
                {
                    if ((!rankings.contains(j)) && (points[j]>maximum))
                    {
                        maximum=points[j];
                        index=j;
                    }
                }
                rankings.add(index);
                map.put(teams[index], i+1);
            }
        }
        System.out.println();
        System.out.println();
        sleep(2500);
        System.out.println("Regular season:");
        for (int i=0; i<rankings.size(); i++)
        {
            System.out.println("#"+(i+1)+": "+teams[rankings.get(i)]);
        }
        System.out.println();
        System.out.println();
        sleep(2500);
        ArrayList<String> bracket=new ArrayList<String>();
        int[] order=fairSequence(teams.length);
        for (int i=0; i<order.length; i++)
        {
            bracket.add(teams[rankings.get(order[i])]);
        }
        doubleElimination(bracket, new ArrayList<String>(), map);
    }
    
    public static void tripleElimination()
    {
        for (int i=0; i<points.length; i++)
        {
            points[i]=0;
        }
        //Round robin:
        for (int i=0; i<teams.length; i++)
        {
            for (int j=i+1; j<teams.length; j++)
            {
                quickplay(i, j);
                printStandings();//prints the standings during the round robin
                /*
                String st=console.readLine();
                 */
                System.out.println();
                System.out.println();
            }
        }
        ArrayList<Integer> rankings=new ArrayList<Integer>();
        Map<String, Integer> map=new HashMap<String, Integer>();
        for (int i=0; i<fairSequence(teams.length).length; i++)
        {
            if (i>=teams.length)
            {
                rankings.add(-1);
                map.put(null, (i+1));
            }
            else
            {
                int index=-1;
                int maximum=-1;
                for (int j=0; j<teams.length; j++)
                {
                    if ((!rankings.contains(j)) && (points[j]>maximum))
                    {
                        maximum=points[j];
                        index=j;
                    }
                }
                rankings.add(index);
                map.put(teams[index], i+1);
            }
        }
        System.out.println();
        System.out.println();
        sleep(2500);
        System.out.println("Regular season:");
        for (int i=0; i<rankings.size(); i++)
        {
            System.out.println("#"+(i+1)+": "+teams[rankings.get(i)]);
        }
        sleep(60000);
        System.out.println();
        System.out.println();
        sleep(2500);
        ArrayList<String> bracket=new ArrayList<String>();
        int[] order=fairSequence(teams.length);
        for (int i=0; i<order.length; i++)
        {
            bracket.add(teams[rankings.get(order[i])]);
        }
        tripleElimination(bracket, new ArrayList<String>(), new ArrayList<String>(), map);
    }

    public static void doubleElimination(ArrayList<String> winners, ArrayList<String> losers, Map<String, Integer> map)
    {
        if ((winners.size()==2) && (losers.size()==2))
        {
            System.out.println("Medal qualification series: #"+map.get(losers.get(0))+" "+losers.get(0)+" vs. #"+map.get(losers.get(1))+" "+losers.get(1));
            System.out.println("Winner faces loser of winners' bracket final, loser is disqualified.");
            String st3=getSeriesWinner(losers.get(0), losers.get(1), 2);
            sleep(2000);
            System.out.println();
            System.out.println("Winners' bracket finals: #"+map.get(winners.get(0))+" "+winners.get(0)+" vs. #"+map.get(winners.get(1))+" "+winners.get(1));
            System.out.println("Winner goes to finals, loser goes to losers' bracket.");
            String st1=getSeriesWinner(winners.get(0), winners.get(1), 3);
            String st2="";
            if (st1.equals(winners.get(0)))
            {
                st2=winners.get(1);
            }
            else
            {
                st2=winners.get(0);
            }
            sleep(2000);
            System.out.println();
            System.out.println("Semifinals: #"+map.get(st2)+" "+st2+" vs. #"+map.get(st3)+" "+st3);
            System.out.println("Winner goes on to play "+st1);
            String st4=getSeriesWinner(st2, st3, 3);
            String thirdPlace="";
            if (st4.equals(st2))
            {
                thirdPlace=st3;
            }
            else
            {
                thirdPlace=st2;
            }
            sleep(2000);
            System.out.println();
            System.out.println("Finals: #"+map.get(st1)+" "+st1+" vs. #"+map.get(st4)+" "+st4);
            sleep(2000);
            System.out.println("If "+st1+" wins, they clinch the championship.");
            sleep(2000);
            System.out.println("If "+st4+" wins, they force another series.");
            String champion="";
            String secondPlace="";
            if (getSeriesWinner(st1, st4, 4).equals(st1))
            {
                champion=st1;
                secondPlace=st4;
            }
            else
            {
                System.out.println("Grand finals: #"+map.get(st4)+" "+st4+" vs. #"+map.get(st1)+" "+st1);
                sleep(2000);
                System.out.println("The winner of this series is crowned champion.");
                sleep(2000);
                champion=getSeriesWinner(st4, st1, 4);
                if (champion.equals(st4))
                {
                    secondPlace=st1;
                }
                else
                {
                    secondPlace=st4;
                }
            }
            System.out.println(champion+" WINS THE CHAMPIONSHIP!");
            sleep(2000);
            System.out.println();
            System.out.println("Third place: "+thirdPlace);
            sleep(2000);
            System.out.println();
            System.out.println("Second place: "+secondPlace);
            sleep(2000);
            System.out.println();
            System.out.println("CHAMPION: "+champion);
            return;
        }
        if (losers.size()==0)
        {
            System.out.println();
            System.out.println();
            //sleep(60000);
            System.out.println("Playoffs:");
            System.out.println();
            sleep(2000);
            for (int i=0; i<winners.size(); i+=2)
            {
                System.out.println("#"+map.get(winners.get(i))+" "+winners.get(i)+" vs. "+"#"+map.get(winners.get(i+1))+" "+winners.get(i+1));
            }
            System.out.println();
            sleep(2000);
            for (int i=0; i<winners.size(); i+=2)
            {
                if (getWinner(winners.get(i), winners.get(i+1)).equals(winners.get(i)))
                {
                    losers.add(winners.set(i+1, null));
                }
                else
                {
                    losers.add(winners.set(i, null));
                }
                System.out.println();
                sleep(1000);
            }
            for (int i=winners.size()-1; i>=0; i--)
            {
                if (winners.get(i)==null)
                {
                    winners.remove(i);
                }
            }
        }
        else if (winners.size()<losers.size())
        {
            System.out.println("Losers' bracket: round of "+losers.size());
            for (int i=0; i<losers.size(); i+=2)
            {
                System.out.println("#"+map.get(losers.get(i))+" "+losers.get(i)+" vs. "+"#"+map.get(losers.get(i+1))+" "+losers.get(i+1));
            }
            System.out.println();
            sleep(2000);
            for (int i=0; i<losers.size(); i+=2)
            {
                if (getWinner(losers.get(i), losers.get(i+1)).equals(losers.get(i)))
                {
                    losers.set(i+1, null);
                }
                else
                {
                    losers.set(i, null);
                }
                System.out.println();
                sleep(1000);
            }
            for (int i=losers.size()-1; i>=0; i--)
            {
                if (losers.get(i)==null)
                {
                    losers.remove(i);
                }
            }
        }
        else if (winners.size()==losers.size())
        {
            System.out.println("Winners' bracket:");
            for (int i=0; i<winners.size(); i+=2)
            {
                System.out.println("#"+map.get(winners.get(i))+" "+winners.get(i)+" vs. "+"#"+map.get(winners.get(i+1))+" "+winners.get(i+1));
            }
            System.out.println();
            sleep(2000);
            System.out.println("Losers' bracket:");
            for (int i=0; i<losers.size(); i+=2)
            {
                System.out.println("#"+map.get(losers.get(i))+" "+losers.get(i)+" vs. "+"#"+map.get(losers.get(i+1))+" "+losers.get(i+1));
            }
            System.out.println();
            sleep(2000);
            for (int i=0; i<winners.size(); i+=2)
            {
                System.out.print("(Winners' bracket) ");
                boolean iwinner=(getWinner(winners.get(i), winners.get(i+1)).equals(winners.get(i)));
                System.out.print("(Losers' bracket) ");
                boolean iloser=getWinner(losers.get(i), losers.get(i+1)).equals(losers.get(i));
                if (iwinner)
                {
                    if (iloser)
                    {
                        losers.set(i+1, winners.set(i+1, null));
                    }
                    else
                    {
                        losers.set(i, winners.set(i+1, null));
                    }
                }
                else
                {
                    if (iloser)
                    {
                        losers.set(i+1, winners.set(i, null));
                    }
                    else
                    {
                        losers.set(i, winners.set(i, null));
                    }
                }
            }
            for (int i=winners.size()-1; i>=0; i--)
            {
                if (winners.get(i)==null)
                {
                    winners.remove(i);
                }
            }
        }
        doubleElimination(winners, losers, map);
    }
    
    public static void tripleElimination(ArrayList<String> winners, ArrayList<String> losers, ArrayList<String> elimination, Map<String, Integer> map)
    {
        if ((winners.size()==1) && (losers.size()==1) && (elimination.size()==1))
        {
            System.out.println("Final three:");
            sleep(2000);
            String w=winners.get(0);
            String l=losers.get(0);
            String e=elimination.get(0);
            System.out.println("Winners' bracket: "+w);
            System.out.println("Losers' bracket: "+l);
            System.out.println("Elimination bracket: "+e);
            System.out.println();
            sleep(2000);
            System.out.println("#"+map.get(l)+" "+l+" vs. #"+map.get(e)+" "+e+", best of 5 series.");
            sleep(2000);
            System.out.println("If "+l+" wins, they advance to face "+w+" in the finals.");
            System.out.println("If "+e+" wins, they force another series.");
            System.out.println();
            sleep(2000);
            int lLosses=1;
            if (getSeriesWinner(l, e, 3).equals(e))
            {
                lLosses=2;
                System.out.println("#"+map.get(l)+" "+l+" vs. #"+map.get(e)+" "+e+", best of 5 series.");
                sleep(2000);
                System.out.println("Winner moves on to face "+w+" for the championship.");
                System.out.println();
                sleep(2000);
                if (getSeriesWinner(l, e, 3).equals(e))
                {
                    l=e;
                }
            }
            int wLosses=0;
            System.out.println();
            sleep(2000);
            while ((lLosses<3) && (wLosses<3))
            {
                if (lLosses==2)
                {
                    if (wLosses==2)
                    {
                        System.out.println("#"+map.get(w)+" "+w+" vs. #"+map.get(l)+" "+l+", best of 7 series.");
                        sleep(2000);
                        System.out.println("The winner of the series will be crowned champion.");
                    }
                    else
                    {
                        System.out.println("#"+map.get(w)+" "+w+" vs. #"+map.get(l)+" "+l+", best of 7 series.");
                        sleep(2000);
                        System.out.println(w+" "+"("+wLosses+" losses) tries to clinch the championship, "+l+" tries to force another series.");
                        sleep(2000);
                    }
                }
                else
                {
                    System.out.println("#"+map.get(w)+" "+w+" ("+wLosses+" losses) vs. #"+map.get(l)+" "+l+" ("+lLosses+" losses) , best of 7 series.");
                    sleep(2000);
                }
                if (getSeriesWinner(w, l, 4).equals(w))
                {
                    lLosses++;
                }
                else
                {
                    wLosses++;
                }
                if (wLosses>=lLosses)
                {
                    String temp=w;
                    w=l;
                    l=temp;
                }
            }
            System.out.println("Champion: "+w);
            return;
        }
        else if (losers.size()==0)
        {
            assert(elimination.size()==0);
            System.out.println("Playoffs:");
            for (int i=0; i<winners.size(); i+=2)
            {
                System.out.println("#"+map.get(winners.get(i))+" "+winners.get(i)+" vs. #"+map.get(winners.get(i+1))+" "+winners.get(i+1));
            }
            System.out.println();
            sleep(2000);
            for (int i=0; i<winners.size(); i+=2)
            {
                System.out.println("#"+map.get(winners.get(i))+" "+winners.get(i)+" vs. #"+map.get(winners.get(i+1))+" "+winners.get(i+1));
                System.out.println();
                sleep(2000);
                if (getWinner(winners.get(i), winners.get(i+1)).equals(winners.get(i)))
                {
                    losers.add(winners.set(i+1, null));
                }
                else
                {
                    losers.add(winners.set(i, null));
                }
            }
            for (int i=winners.size()-1; i>=0; i--)
            {
                if (winners.get(i)==null)
                {
                    winners.remove(i);
                }
            }
            System.out.println();
            sleep(2000);
            System.out.println("Second round:");
            System.out.println("Winners' bracket:");
            for (int i=0; i<winners.size(); i+=2)
            {
                System.out.println("#"+map.get(winners.get(i))+" "+winners.get(i)+" vs. #"+map.get(winners.get(i+1))+" "+winners.get(i+1));
            }
            System.out.println();
            sleep(2000);
            System.out.println("Losers' bracket:");
            for (int i=0; i<losers.size(); i+=2)
            {
                System.out.println("#"+map.get(losers.get(i))+" "+losers.get(i)+" vs. #"+map.get(losers.get(i+1))+" "+losers.get(i+1));
            }
            System.out.println();
            sleep(2000);
            for (int i=0; i<winners.size(); i+=2)
            {
                System.out.println("(Winners' bracket) #"+map.get(winners.get(i))+" "+winners.get(i)+" vs. #"+map.get(winners.get(i+1))+" "+winners.get(i+1));
                boolean iwinner=getWinner(winners.get(i), winners.get(i+1)).equals(winners.get(i));
                System.out.println();
                sleep(2000);
                System.out.println("(Losers' bracket) #"+map.get(losers.get(i))+" "+losers.get(i)+" vs. #"+map.get(losers.get(i+1))+" "+losers.get(i+1));
                boolean iloser=getWinner(losers.get(i), losers.get(i+1)).equals(winners.get(i));
                System.out.println();
                sleep(2000);
                if (iwinner)
                {
                    if (iloser)
                    {
                        elimination.add(losers.set(i+1, winners.set(i+1, null)));
                    }
                    else
                    {
                        elimination.add(losers.set(i, winners.set(i+1, null)));
                    }
                }
                else
                {
                    if (iloser)
                    {
                        elimination.add(losers.set(i+1, winners.set(i, null)));
                    }
                    else
                    {
                        elimination.add(losers.set(i, winners.set(i, null)));
                    }
                }
            }
            System.out.println("Losers' bracket:");
            for (int i=0; i<losers.size(); i+=2)
            {
                System.out.println("#"+map.get(losers.get(i))+" "+losers.get(i)+" vs. #"+map.get(losers.get(i+1))+" "+losers.get(i+1));
            }
            System.out.println();
            sleep(2000);
            for (int i=0; i<losers.size(); i+=2)
            {
                System.out.println("#"+map.get(losers.get(i))+" "+losers.get(i)+" vs. #"+map.get(losers.get(i+1))+" "+losers.get(i+1));
                if (getWinner(losers.get(i), losers.get(i+1)).equals(losers.get(i)))
                {
                    elimination.add(i+1, losers.set(i+1, null));
                }
                else
                {
                    elimination.add(i+1, losers.set(i, null));
                }
                System.out.println();
                sleep(2000);
            }
            for (int i=winners.size()-1; i>=0; i--)
            {
                if (winners.get(i)==null)
                {
                    winners.remove(i);
                }
            }
            for (int i=losers.size()-1; i>=0; i--)
            {
                if (losers.get(i)==null)
                {
                    losers.remove(i);
                }
            }
        }
        else if (elimination.size()>losers.size())
        {
            System.out.println(winners.size()+" teams in winners' bracket, "+losers.size()+" in losers' bracket, "+elimination.size()+" in elimination bracket.");
            System.out.println("Elimination' bracket:");
            System.out.println();
            sleep(2000);
            for (int i=0; i<elimination.size(); i+=2)
            {
                System.out.println("#"+map.get(elimination.get(i))+" "+elimination.get(i)+" vs. "+"#"+map.get(elimination.get(i+1))+" "+elimination.get(i+1));
                System.out.println();
                sleep(2000);
                boolean ielim=true;
                if (elimination.size()==2)
                {
                    ielim=getSeriesWinner(elimination.get(i), elimination.get(i+1), 2).equals(elimination.get(i));
                }
                else
                {
                    ielim=getWinner(elimination.get(i), elimination.get(i+1)).equals(elimination.get(i));
                }
                if (ielim)
                {
                    elimination.set(i+1, null);
                }
                else
                {
                    elimination.set(i, null);
                }
            }
            for (int i=elimination.size()-1; i>=0; i--)
            {
                if (elimination.get(i)==null)
                {
                    elimination.remove(i);
                }
            }
        }
        else if (losers.size()>winners.size()) // bracket sizes are n, 2n, 2n
        {
            System.out.println(winners.size()+" teams in winners' bracket, "+losers.size()+" in losers' bracket, "+elimination.size()+" in elimination bracket.");
            System.out.println("Losers' bracket:");
            for (int i=0; i<losers.size(); i+=2)
            {
                System.out.println("#"+map.get(losers.get(i))+" "+losers.get(i)+" vs. "+"#"+map.get(losers.get(i+1))+" "+losers.get(i+1));
            }
            System.out.println();
            sleep(2000);
            System.out.println("Elimination' bracket:");
            for (int i=0; i<elimination.size(); i+=2)
            {
                System.out.println("#"+map.get(elimination.get(i))+" "+elimination.get(i)+" vs. "+"#"+map.get(elimination.get(i+1))+" "+elimination.get(i+1));
            }
            System.out.println();
            sleep(2000);
            for (int i=0; i<losers.size(); i+=2)
            {
                System.out.print("(Losers' bracket) ");
                boolean iloser=true;
                if (losers.size()==2)
                {
                    iloser=(getSeriesWinner(losers.get(i), losers.get(i+1), 2)).equals(losers.get(i));
                }
                else
                {
                    iloser=(getWinner(losers.get(i), losers.get(i+1)).equals(losers.get(i)));
                }
                System.out.print("(Elimination bracket) ");
                boolean ielim=getWinner(elimination.get(i), elimination.get(i+1)).equals(elimination.get(i));
                if (iloser)
                {
                    if (ielim)
                    {
                        elimination.set(i+1, losers.set(i+1, null));
                    }
                    else
                    {
                        elimination.set(i, losers.set(i+1, null));
                    }
                }
                else
                {
                    if (ielim)
                    {
                        elimination.set(i+1, losers.set(i, null));
                    }
                    else
                    {
                        elimination.set(i, losers.set(i, null));
                    }
                }
            }
            for (int i=losers.size()-1; i>=0; i--)
            {
                if (losers.get(i)==null)
                {
                    losers.remove(i);
                }
            }
        }
        else if (losers.size()==winners.size()) //all 3 brackets have the same size
        {
            System.out.println(winners.size()+" teams in winners' bracket, "+losers.size()+" in losers' bracket, "+elimination.size()+" in elimination bracket.");
            System.out.println("Winners' bracket:");
            for (int i=0; i<winners.size(); i+=2)
            {
                System.out.println("#"+map.get(winners.get(i))+" "+winners.get(i)+" vs. "+"#"+map.get(winners.get(i+1))+" "+winners.get(i+1));
            }
            System.out.println();
            sleep(2000);
            System.out.println("Losers' bracket:");
            for (int i=0; i<losers.size(); i+=2)
            {
                System.out.println("#"+map.get(losers.get(i))+" "+losers.get(i)+" vs. "+"#"+map.get(losers.get(i+1))+" "+losers.get(i+1));
            }
            System.out.println();
            sleep(2000);
            System.out.println("Elimination bracket:");
            for (int i=0; i<elimination.size(); i+=2)
            {
                System.out.println("#"+map.get(elimination.get(i))+" "+elimination.get(i)+" vs. "+"#"+map.get(elimination.get(i+1))+" "+elimination.get(i+1));
            }
            System.out.println();
            sleep(2000);
            for (int i=0; i<winners.size(); i+=2)
            {
                System.out.print("(Winners' bracket) ");
                boolean iwinner=true;
                if (winners.size()==2)
                {
                    iwinner=(getSeriesWinner(winners.get(i), winners.get(i+1), 3).equals(winners.get(i)));
                }
                else if (winners.size()==4)
                {
                    iwinner=(getSeriesWinner(winners.get(i), winners.get(i+1), 2).equals(winners.get(i)));
                }
                else
                {
                    iwinner=(getWinner(winners.get(i), winners.get(i+1)).equals(winners.get(i)));
                }
                System.out.print("(Losers' bracket) ");
                boolean iloser=getWinner(losers.get(i), losers.get(i+1)).equals(losers.get(i));
                System.out.print("(Elimination bracket) ");
                boolean ielim=getWinner(elimination.get(i), elimination.get(i+1)).equals(elimination.get(i));
                if (!iwinner)
                {
                    String temp=winners.get(i);
                    winners.set(i, winners.get(i+1));
                    winners.set(i+1, temp);
                }
                if (!iloser)
                {
                    String temp=losers.get(i);
                    losers.set(i, losers.get(i+1));
                    losers.set(i+1, temp);
                }
                if (!ielim)
                {
                    String temp=elimination.get(i);
                    elimination.set(i, elimination.get(i+1));
                    elimination.set(i+1, temp);
                }
                elimination.set(i+1, losers.get(i+1));
                losers.set(i+1, winners.get(i+1));
                winners.set(i+1, null);
            }
            for (int i=winners.size()-1; i>=0; i--)
            {
                if (winners.get(i)==null)
                {
                    winners.remove(i);
                }
            }
        }
        tripleElimination(winners, losers, elimination, map);
    }
    
    public static void doubleEliminationTournament()
    {
        String[] storeTeams=teams;
        int[] storePoints=points;
        int newSize=1;
        while (newSize<teams.length)
        {
            newSize*=2;
        }
        teams=new String[newSize];
        points=new int[newSize];
        for (int i=0; i<teams.length; i++)
        {
            if (i<storeTeams.length)
            {
                teams[i]=storeTeams[i];
            }
            else
            {
                teams[i]="null";
            }
        }
        teams=Randomizer.shuffle(teams);
        ArrayList<Integer> rankings=new ArrayList<Integer>();
        Map<String, Integer> map=new HashMap<String, Integer>();
        for (int i=0; i<fairSequence(teams.length).length; i++)
        {
            if (i>=teams.length)
            {
                rankings.add(-1);
                map.put(null, (i+1));
            }
            else
            {
                int index=-1;
                int maximum=-1;
                for (int j=0; j<teams.length; j++)
                {
                    if ((!rankings.contains(j)) && (points[j]>maximum))
                    {
                        maximum=points[j];
                        index=j;
                    }
                }
                rankings.add(index);
                map.put(teams[index], i+1);
            }
        }
        System.out.println();
        System.out.println();
        sleep(2500);
        System.out.println("Regular season:");
        for (int i=0; i<rankings.size(); i++)
        {
            System.out.println("#"+(i+1)+": "+teams[rankings.get(i)]);
        }
        System.out.println();
        System.out.println();
        sleep(2500);
        ArrayList<String> bracket=new ArrayList<String>();
        int[] order=fairSequence(teams.length);
        for (int i=0; i<order.length; i++)
        {
            bracket.add(teams[rankings.get(order[i])]);
        }
        doubleElimination((bracket), new ArrayList<String>(), map);
        points=storePoints;
        teams=storeTeams;
    }
}