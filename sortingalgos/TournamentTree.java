import java.util.*;
public class TournamentTree
{
    private TournamentNode root;
    public TournamentTree(String[] teams, int[] points)
    {
        assert(teams.length==points.length);
        int log=(int)(Math.log(teams.length)/Math.log(2));
        int size=(int)Math.pow(2, log);
        TreeMap<Integer, ArrayList<Integer>> map=new TreeMap<Integer, ArrayList<Integer>>();
        for (int i=0; i<points.length; i++)
        {
            if (map.containsKey(-points[i]))
            {
                map.get(-points[i]).add(i);
            }
            else
            {
                ArrayList<Integer> list=new ArrayList<Integer>();
                list.add(i);
                map.put(-points[i], list);
            }
        }
        int[] playoffs=new int[(int)Math.pow(2, (int)(Math.log(teams.length)/Math.log(2)))];
        int index=0;
        ArrayList<Integer> decidefrom=null;
        for (Integer i:map.keySet())
        {
            if (index+map.get(i).size()>playoffs.length)
            {
                decidefrom=map.get(i);
                break;
            }
            else
            {
                ArrayList<Integer> list=map.get(i);
                for (int j=0; j<list.size(); j++)
                {
                    playoffs[index]=list.get(j);
                    index++;
                }
            }
        }
        if (index!=playoffs.length)
        {
            int[] qualifiers=extract(decidefrom, playoffs.length-index);
            for (int i=0; i<qualifiers.length; i++)
            {
                playoffs[index]=qualifiers[i];
                index++;
            }
        }
        //sort them into 1 16 8 9 4 13 5 12 2 15 7 10 3 14 6 11 form
        int[] newplayoffs=new int[playoffs.length];
        int[] fairSequence=fairSequence(playoffs.length);
        for (int i=0; i<playoffs.length; i++)
        {
            newplayoffs[i]=playoffs[fairSequence[i]];
        }
        playoffs=newplayoffs;
        root=buildTree(playoffs, teams);
    }
    public int[] fairSequence(int length)
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
            length=2*recursion.length;
            int[] array=new int[length];
            for (int i=0; i<recursion.length; i++)
            {
                array[2*i]=recursion[i];
                array[2*i+1]=length-recursion[i]-1;
            }
            return array;
        }
    }
    public TournamentNode buildTree(int[] playoffs, String[] teams)
    {
        ArrayList<TournamentNode> nodes=new ArrayList<TournamentNode>();
        for (int i=0; i<playoffs.length; i++)
        {
            nodes.add(new TournamentNode(teams[playoffs[i]]));
        }
        while (nodes.size()>1)
        {
            ArrayList<TournamentNode> newnodes=new ArrayList<TournamentNode>();
            for (int i=0; i<nodes.size(); i+=2)
            {
                newnodes.add(new TournamentNode(nodes.get(i), nodes.get(i+1)));
            }
            nodes=newnodes;
        }
        return nodes.get(0);
    }
    public int[] extract(ArrayList<Integer> tied, int n)
    {
        if (n==0)
        {
            return new int[0];
        }
        int[] subpoints=new int[tied.size()];
        for (int i=0; i<tied.size(); i++)
        {
            for (int j=i+1; j<tied.size(); j++)
            {
                String winner=MarchMadness.getWinner(MarchMadness.get(tied.get(i)), MarchMadness.get(tied.get(j)));
                if (winner.equals(MarchMadness.get(tied.get(i))))
                {
                    subpoints[i]+=3;
                }
                else if (winner.equals(MarchMadness.get(tied.get(j))))
                {
                    subpoints[j]+=3;
                }
                else
                {
                    subpoints[i]++;
                    subpoints[j]++;
                }
            }
        }
        TreeMap<Integer, ArrayList<Integer>> map=new TreeMap<Integer, ArrayList<Integer>>();
        for (int i=0; i<tied.size(); i++)
        {
            if (map.containsKey(-subpoints[i]))
            {
                map.get(-subpoints[i]).add(i);
            }
            else
            {
                ArrayList<Integer> list=new ArrayList<Integer>();
                list.add(i);
                map.put(-subpoints[i], list);
            }
        }
        int[] returnme=new int[n];
        int index=0;
        for (Integer i:map.keySet())
        {
            if (index==n)
            {
                return returnme;
            }
            ArrayList<Integer> list=map.get(i);
            if (index+list.size()>n)
            {
                while (index<n)
                {
                    int index2=0;
                    returnme[index]=list.get(index2);
                    index++;
                    index2++;
                }
                return returnme;
            }
            else
            {
                for (int j=0; j<list.size(); j++)
                {
                    returnme[index]=tied.get(list.get(j));
                    index++;
                }
            }
        }
        return returnme;
    }
    public boolean playHelper(TournamentNode node)
    {
        if (node.complete())
        {
            return false;
        }
        if (node.ready())
        {
            node.evaluate();
            return true;
        }
        else
        {
            playHelper(node.getLeft());
            return playHelper(node.getRight());
        }
    }
    public void play()
    {
        while (playHelper(root)){}
    } 
}