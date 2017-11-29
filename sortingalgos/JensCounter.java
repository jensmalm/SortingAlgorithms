import java.util.ArrayList;
import java.util.TreeMap;
public class JensCounter
{
    private JensTreeNode one;
    public JensCounter()
    {
        one=new JensTreeNode(1);
    }
    private JensTreeNode node(int num)
    {
        assert(num>0);
        if (num==1)
        {
            return one;
        }
        ListNode<Integer> stack=null;
        while (Integer.bitCount(num)>1)
        {
            int zeros=Integer.numberOfTrailingZeros(num);
            num=num/Integer.lowestOneBit(num);
            stack=new ListNode<Integer>(zeros, stack);
            num=(num+1)/2;
        }
        JensTreeNode node=one.child(Integer.numberOfTrailingZeros(num)-1);
        while (stack!=null)
        {
            node=node.child(stack.getValue());
            stack=stack.getNext();
        }
        return node;
    }
    public int getCount(int num)
    {
        return node(num).count();
    }
    public void add(int num)
    {
        node(num).add();
    }
    
    //Assumed: the first node in the recursive chain will always be _one_
    private ArrayList<JensTreeNode> postfixUpto(JensTreeNode node, int max)
    {
        ArrayList<JensTreeNode> nodes=new ArrayList<JensTreeNode>();
        TreeMap<Integer, JensTreeNode> children=node.children();
        for (int i:children.keySet())
        {
            JensTreeNode child=children.get(i);
            if (child.value()<=max)
            {
                ArrayList<JensTreeNode> subcall=postfixUpto(child, max);
                for (JensTreeNode j:subcall)
                {
                    nodes.add(j);
                }
            }
            else
            {
                break;
            }
        }
        nodes.add(node);
        return nodes;
    }
    public int[] toArray(int length) //length must be accurate
    {
        ArrayList<JensTreeNode> nodes=new ArrayList<JensTreeNode>();
        nodes.add(one);
        boolean proceed=true;
        int max=1;
        while (proceed)
        {
            proceed=false;
            ArrayList<JensTreeNode> postfix=postfixUpto(one, max);
            for (JensTreeNode node:postfix)
            {
                JensTreeNode child=node.nextChild();
                if (child!=null)
                {
                    nodes.add(child);
                    if (child.childrenRemaining())
                    {
                        proceed=true;
                    }
                }
                if (node.childrenRemaining())
                {
                    proceed=true;
                }
            }
            max*=2;
        }
        int[] array=new int[length];
        int index=0;
        for (JensTreeNode node:nodes)
        {
            for (int i=0; i<node.count(); i++)
            {
                array[index]=node.value();
                index++;
            }
        }
        return array;
    }
    public static int depth(int num)
    {
        if (num==0)
        {
            return 1;
        }
        int depth=1;
        while (Integer.bitCount(num)>1)
        {
            num=num/Integer.lowestOneBit(num);
            num=(num+1)/2;
            depth++;
        }
        return depth;
    }
    public static void surveyDepths()
    {
        for (int i=0; i<1000000; i++)
        {
            System.out.println(i+" "+(int)(Math.log(i)/Math.log(2))+" "+(int)(Math.log(Math.log(i)/Math.log(2))/Math.log(2))+" "+depth(i));
        }
    }
}