public class BalancingTreeNode
{
    private int value;
    int count;
    private BalancingTreeNode left;
    private BalancingTreeNode right;
    private int size;
    public BalancingTreeNode(int v, int c, BalancingTreeNode l, BalancingTreeNode r)
    {
        value=v;
        count=c;
        left=l;
        right=r;
        size=1+size(left)+size(right);
    }
    private int size(BalancingTreeNode node)
    {
        if (node==null)
        {
            return 0;
        }
        return node.size();
    }
    public void setValue(int v)
    {
        value=v;
    }
    public void setCount(int c)
    {
        count=c;
    }
    public void setLeft(BalancingTreeNode l)
    {
        left=l;
        size=1+size(left)+size(right);
    }
    public void setRight(BalancingTreeNode r)
    {
        right=r;
        size=1+size(left)+size(right);
    }
    public int getValue()
    {
        return value;
    }
    public int getCount()
    {
        return count;
    }
    public BalancingTreeNode getLeft()
    {
        return left;
    }
    public BalancingTreeNode getRight()
    {
        return right;
    }
    public int size()
    {
        return size;
    }
}