public class BalancingBinarySearchTree
{
    private BalancingTreeNode root;
    private int value;
    int operations;
    int run;
    public BalancingBinarySearchTree()
    {
        root=null;
        value=0;
        operations=0;
        run=10000;
    }
    private int size(BalancingTreeNode node)
    {
        if (node==null)
        {
            return 0;
        }
        return node.size();
    }
    private BalancingTreeNode addHelper(BalancingTreeNode node, int item)
    {
        if (node==null)
        {
            return new BalancingTreeNode(item, 1, null, null);
        }
        if (item<node.getValue())
        {
            node.setLeft(addHelper(node.getLeft(), item));
            return node;
        }
        if (item>node.getValue())
        {
            node.setRight(addHelper(node.getRight(), item));
            return node;
        }
        node.setCount(node.getCount()+1);
        return node;
    }
    public void add(int item)
    {
        root=addHelper(root, item);
        operations++;
        if (operations==run)
        {
            operations=0;
            balance();
        }
    }
    private BalancingTreeNode removeHelper(BalancingTreeNode node)
    {
        if (node.getLeft()==null)
        {
            value=node.getValue();
            if (node.getCount()==1)
            {
                return node.getRight();
            }
            else
            {
                node.setCount(node.getCount()-1);
                return node;
            }
        }
        else
        {
            node.setLeft(removeHelper(node.getLeft()));
            return node;
        }
    }
    private BalancingTreeNode removeRightHelper(BalancingTreeNode node)
    {
        if (node.getRight()==null)
        {
            value=node.getValue();
            if (node.getCount()==1)
            {
                return node.getLeft();
            }
            else
            {
                node.setCount(node.getCount()-1);
                return node;
            }
        }
        else
        {
            node.setRight(removeRightHelper(node.getRight()));
            return node;
        }
    }
    public int remove()
    {
        root=removeHelper(root);
        int rv=value;
        operations++;
        if (operations==run)
        {
            operations=0;
            balance();
        }
        return rv;
    }
    private BalancingTreeNode shiftLeftHelper(BalancingTreeNode node)
    {
        if (node.getCount()==1)
        {
            node.setLeft(addHelper(node.getLeft(), node.getValue()));
            node.setRight(removeHelper(node.getRight()));
            node.setValue(value);
        }
        else
        {
            node.setCount(node.getCount()-1);
            node.setRight(addHelper(node.getRight(), node.getValue()));
        }
        return node;
    }
    private BalancingTreeNode shiftRightHelper(BalancingTreeNode node)
    {
        if (node.getCount()==1)
        {
            node.setRight(addHelper(node.getRight(), node.getValue()));
            node.setLeft(removeRightHelper(node.getLeft()));
            node.setValue(value);
        }
        else
        {
            node.setCount(node.getCount()-1);
            node.setLeft(addHelper(node.getLeft(), node.getValue()));
        }
        return node;
    }
    private BalancingTreeNode balanceHelper(BalancingTreeNode node)
    {
        if (size(node)<3)
        {
            return node;
        }
        while (Math.abs(size(node.getLeft())-size(node.getRight()))>1)
        {
            if (size(node.getRight())>size(node.getLeft()))
            {
                node=shiftLeftHelper(node);
            }
            else
            {
                node=shiftRightHelper(node);
            }
        }
        node.setLeft(balanceHelper(node.getLeft()));
        node.setRight(balanceHelper(node.getRight()));
        return node;
    }
    public void balance()
    {
        root=balanceHelper(root);
    }
    private boolean printLevel(BalancingTreeNode node, int level)
    {
        if (node!=null)
        {
            if (level==0)
            {
                System.out.print(node.getValue()+","+node.getCount()+" ");
                return (node.getLeft()!=null || node.getRight()!=null);
            }
            return (printLevel(node.getLeft(), level-1) || printLevel(node.getRight(), level-1));
        }
        return false;
    }
    public void print(BalancingTreeNode node)
    {
        boolean go=true;
        int level=0;
        while (go)
        {
            go=printLevel(node, level);
            System.out.println();
            level++;
        }
        System.out.println();
    }
    public void print()
    {
        boolean go=true;
        int level=0;
        while (go)
        {
            go=printLevel(root, level);
            System.out.println();
            level++;
        }
        System.out.println();
    }
}