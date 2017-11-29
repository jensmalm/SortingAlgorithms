import java.util.TreeMap;
public class JensTreeNode
{
    private int count;
    private TreeMap<Integer, JensTreeNode> children;
    private int maxChild;
    private int value;
    private int next;
    public JensTreeNode(int val)
    {
        count=0;
        children=new TreeMap<Integer, JensTreeNode>();
        maxChild=-1;
        value=val;
        next=0;
    }
    public void add()
    {
        count++;
    }
    public JensTreeNode child(int n)
    {
        JensTreeNode c=children.get(n);
        if (c==null)
        {
            if (value==1)
            {
                c=new JensTreeNode((int)(Math.pow(2, n+1)));
            }
            else
            {
                c=new JensTreeNode((2*value-1)*((int)(Math.pow(2, n))));
            }
            children.put(n, c);
            if (n>maxChild)
            {
                maxChild=n;
            }
        }
        return c;
    }
    /*
    public JensTreeNode childOrNull(int n)
    {
        return children.get(n);
    }
    */
    public int count()
    {
        return count;
    }
    public int maxChild()
    {
        return maxChild;
    }
    public TreeMap<Integer, JensTreeNode> children()
    {
        return children;
    }
    public int value()
    {
        return value;
    }
    public int hashCode()
    {
        return value();
    }
    public JensTreeNode nextChild()
    {
        JensTreeNode temp=children.get(next);
        next++;
        return temp;
    }
    public boolean childrenRemaining()
    {
        return next<=maxChild;
    }
}