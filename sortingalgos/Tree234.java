import java.util.*;
public class Tree234
{
    private Tree234Node root;
    public Tree234()
    {
        root=null;
    }
    private Tree234Node next(Tree234Node node, int item)
    {
        if (item<node.item1)
        {
            return node.child1;
        }
        else if ((node.items==1) || (item<node.item2))
        {
            return node.child2;
        }
        else if ((node.items==2) || (item<node.item3))
        {
            return node.child3;
        }
        return node.child4;
    }
    private void addToNode(Tree234Node node, int item)
    {
        if (node.items==1)
        {
            if (item<node.item1)
            {
                node.item2=node.item1;
                node.item1=item;
            }
            else
            {
                node.item2=item;
            }
            node.items=2;
        }
        else if (node.items==2)
        {
            if (item<node.item1)
            {
                node.item3=node.item2;
                node.item2=node.item1;
                node.item1=item;
            }
            else if (item<node.item2)
            {
                node.item3=node.item2;
                node.item2=item;
            }
            else
            {
                node.item3=item;
            }
            node.items=3;
        }
        else
        {
            addToNode(fix3(node, item), item);
        }
    }
    private Tree234Node fix3(Tree234Node node, int item)
    {
        if (node==root)
        {
            root=new Tree234Node(null, new Tree234Node(null, root.child1, root.item1, root.child2), root.item2, new Tree234Node(null, root.child3, root.item3, root.child4));
            return next(root, item);
        }
        else
        {
            Tree234Node parent=node.parent;
            if (parent.items==1)
            {
                if (parent.child1==node)
                {
                    parent.child3=parent.child2;
                    parent.child1=new Tree234Node(parent, node.child1, node.item1, node.child2);
                    parent.child2=new Tree234Node(parent, node.child3, node.item3, node.child4);
                }
                else
                {
                    parent.child2=new Tree234Node(parent, node.child1, node.item1, node.child2);
                    parent.child3=new Tree234Node(parent, node.child3, node.item3, node.child4);
                }
            }
            else
            {
                if (parent.child1==node)
                {
                    parent.child4=parent.child3;
                    parent.child3=parent.child2;
                    parent.child1=new Tree234Node(parent, node.child1, node.item1, node.child2);
                    parent.child2=new Tree234Node(parent, node.child3, node.item3, node.child4);
                }
                else if (parent.child2==node)
                {
                    parent.child4=parent.child3;
                    parent.child2=new Tree234Node(parent, node.child1, node.item1, node.child2);
                    parent.child3=new Tree234Node(parent, node.child3, node.item3, node.child4);
                }
                else
                {
                    parent.child3=new Tree234Node(parent, node.child1, node.item1, node.child2);
                    parent.child4=new Tree234Node(parent, node.child3, node.item3, node.child4);
                }
            }
            addToNode(parent, node.item2);
            return next(parent, item);
        }
    }
    private boolean leaf(Tree234Node node)
    {
        return (node.child1==null);
    }
    public void insert(int item)
    {
        if (root==null)
        {
            root=new Tree234Node(null, null, item, null);
        }
        else
        {
            Tree234Node node=root;
            while (!leaf(node))
            {
                if (node.items==3)
                {
                    node=fix3(node, item);
                }
                else
                {
                    node=next(node, item);
                }
            }
            addToNode(node, item);
        }
    }
    public boolean contains(int item)
    {
        Tree234Node node=root;
        while (node!=null)
        {
            if (item==node.item1)
            {
                return true;
            }
            else if ((node.items>1) && (item==node.item2))
            {
                return true;
            }
            else if ((node.items>2) && (item==node.item3))
            {
                return true;
            }
            if (item<node.item1)
            {
                node=node.child1;
            }
            else if ((node.items==1) || (item<node.item2))
            {
                node=node.child2;
            }
            else if ((node.items==2) || (item<node.item3))
            {
                node=node.child3;
            }
            else
            {
                node=node.child4;
            }
        }
        return false;
    }
    private int sizeHelper(Tree234Node node)
    {
        if (node==null)
        {
            return 0;
        }
        if (leaf(node))
        {
            return node.items;
        }
        if (node.items==1)
        {
            return 1+sizeHelper(node.child1)+sizeHelper(node.child2);
        }
        else if (node.items==2)
        {
            return 2+sizeHelper(node.child1)+sizeHelper(node.child2)+sizeHelper(node.child3);
        }
        else
        {
            return 3+sizeHelper(node.child1)+sizeHelper(node.child2)+sizeHelper(node.child3)+sizeHelper(node.child4);
        }
    }
    public int size()
    {
        return sizeHelper(root);
    }
    private void printLevel(Tree234Node node, int level)
    {
        if ((node!=null) && (level>=0))
        {
            if (level==0)
            {
                System.out.print("|"+node.item1+" ");
                if (node.items>1)
                {
                    System.out.print(node.item2+" ");
                    if (node.items>2)
                    {
                        System.out.print(node.item3+" ");
                    }
                }
                System.out.print("| ");
            }
            else
            {
                printLevel(node.child1, level-1);
                printLevel(node.child2, level-1);
                if (node.items>1)
                {
                    printLevel(node.child3, level-1);
                    if (node.items>2)
                    {
                        printLevel(node.child4, level-1);
                    }
                }
            }
        }
    }
    public void printTree()
    {
        for (int i=0; i<10; i++)
        {
            printLevel(root, i);
            System.out.println();
        }
        System.out.println();
    }
    public static void testTree()
    {
        Tree234 t=new Tree234();
        int[] array={11, 44, 77, 22, 99, 88, 66, 55, 28, 5, 15, 25, 35, 45, 65, 75, 85, 95};
        for (int i=0; i<array.length; i++)
        {
            System.out.println("Adding "+array[i]);
            t.insert(array[i]);
            t.printTree();
            System.out.println("size: "+t.size());
        }
    }
    private int toArrayHelper(int[] array, int index, Tree234Node node)
    {
        if (node==null)
        {
            return index;
        }
        index=toArrayHelper(array, index, node.child1);
        array[index]=node.item1;
        index++;
        index=toArrayHelper(array, index, node.child2);
        if (node.items>1)
        {
            array[index]=node.item2;
            index++;
            index=toArrayHelper(array, index, node.child3);
            if (node.items==3)
            {
                array[index]=node.item3;
                index++;
                index=toArrayHelper(array, index, node.child4);
            }
        }
        return index;
    }
    public int[] toArray()
    {
        int[] array=new int[size()];
        toArrayHelper(array, 0, root);
        return array;
    }
}