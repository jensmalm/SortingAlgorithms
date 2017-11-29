import java.util.*;
public class TeamMadness
{
    public static ArrayList<Team> getTeams()
    {
        ArrayList<Team> teams=new ArrayList<Team>();
        teams.add(new Team("Nlogn", "Java", "Merge", "TernaryHeap", "TernaryMerge"));
        teams.add(new Team("Other","Radix", "Map", "Shell", "Counting"));
        teams.add(new Team("Quadratic", "Bubble", "Selection", "BinaryInsertion", "Insertion"));
        teams.add(new Team("Rejects", "Gnome", "Tree","Heap",  "Quick"));
        teams.add(new Team("Dream", "Java", "Merge", "Counting", "Clutch"));
        teams.add(new Team("Team1", "Quick", "Shell", "Java", "Clutch"));
        teams.add(new Team("Team2", "DualPivotQuick", "TernaryHeap", "Merge", "Counting"));
        return teams;
    }
    public static void sleep(int t)
    {
        try
        {
            Thread.sleep(t);
        }
        catch (InterruptedException ex){};
    }
    public static Team team(String st)
    {
        for (Team t:getTeams())
        {
            if (t.getName().equalsIgnoreCase(st))
            {
                return t;
            }
        }
        return null;
    }
    public static Team getWinner(Team t1, Team t2)
    {
        System.out.println(t1.getName()+" vs. "+t2.getName()+":");
        System.out.println();
        sleep(2000);
        long total1=0;
        long total2=0;
        int[] pow=new int[4];
        for (int i=0; i<4; i++)
        {
            pow[i]=3+(int)(Math.random()*18);
        }
        for (int i=0; i<4; i++)
        {
            System.out.println("Leg "+(i+1));
            sleep(2000);
            int length=(int)Math.pow(2, pow[i]);
            String st1=t1.get(i);
            String st2=t2.get(i);
            System.out.println(st1+" ("+t1.getName()+") vs. "+st2+" ("+t2.getName()+"):");
            sleep(2000);
            long time1=Math.min(MarchMadness.getTime(st1, pow[i]), Long.MAX_VALUE/5);
            System.out.println(st1+" array of length "+length);
            System.out.println("Time: "+time1);
            total1+=time1;
            sleep(2000);
            long time2=Math.min(MarchMadness.getTime(st2, pow[i]), Long.MAX_VALUE/5);
            System.out.println(st2+" array of length "+length);
            System.out.println("Time: "+time2);
            total2+=time2;
            System.out.println();
            sleep(2000);
            System.out.println("Total times so far: "+t1.getName()+" "+total1+", "+t2.getName()+" "+total2);
            System.out.println();
            sleep(2000);
        }
        while (total1==total2)
        {
            total1=0;
            total2=0;
            int power=3+(int)(Math.random()*18);
            int length=(int)Math.pow(2, power);
            String st1=t1.get(3);
            String st2=t2.get(3);
            System.out.println(st1+" ("+t1.getName()+") vs. "+st2+" ("+t2.getName()+"):");
            sleep(2000);
            long time1=Math.min(MarchMadness.getTime(st1, power), Long.MAX_VALUE/5);
            System.out.println(st1+" array of length "+length);
            System.out.println("Time: "+time1);
            total1+=time1;
            sleep(2000);
            long time2=Math.min(MarchMadness.getTime(st2, power), Long.MAX_VALUE/5);
            System.out.println(st2+" array of length "+length);
            System.out.println("Time: "+time2);
            total2+=time2;
            System.out.println();
            sleep(2000);
            System.out.println("Total times so far: "+t1.getName()+" "+total1+", "+t2.getName()+" "+total2);
            System.out.println();
            sleep(2000);
        }
        if (total1<total2)
        {
            System.out.println("Winner: "+t1.getName());
            return t1;
        }
        else
        {
            System.out.println("Winner: "+t2.getName());
            return t2;
        }
    }
    public static void printALot()
    {
        for (int i=0; i<9; i++)
        {
            System.out.println();
        }
    }
    public static Team play(String st1, String st2)
    {
        return getWinner(team(st1), team(st2));
    }
    public static Team getSeriesWinner(String st1, String st2, int upto)
    {
        int games1=0;
        int games2=0;
        while ((games1<upto) && (games2<upto))
        {
            if ((games1+games2)!=0)
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
                sleep(2000);
                System.out.println();
            }
            String winner=play(st1, st2).getName();
            if (winner.equals(st1))
            {
                games1++;
            }
            else
            {
                games2++;
            }
        }
        if (games1==upto)
        {
            System.out.println();
            System.out.println(st1+" wins series "+games1+"-"+games2);
            return team(st1);
        }
        else
        {
            System.out.println();
            System.out.println(st2+" wins series "+games2+"-"+games1);
            return team(st2);
        }
    }
    public static void tournament()
    {
        Team t1=play("Nlogn", "Quadratic");
        printALot();
        Team t2=play("Other", "Rejects");
        printALot();
        System.out.println("Championship:");
        System.out.println();
        sleep(2000);
        Team champion=getWinner(t1, t2);
        System.out.println("CHAMPION: "+champion.getName());
    }
}