import java.util.*;
public class BinarySearchTree implements Iterable<Integer>
{
    private TreeNode<Integer> root;
    public BinarySearchTree()
    {
        root=null;
    }
    /*
    //RECURSIVE IMPLEMENTATIONS:
    
    private TreeNode<Integer> addHelper(int item, TreeNode<Integer> node)
    {
        if (node==null)
        {
            return new TreeNode<Integer>(item, null, null);
        }
        else if (item<node.getValue())
        {
            node.setLeft(addHelper(item, node.getLeft()));
            return node;
        }
        else
        {
            node.setRight(addHelper(item, node.getRight()));
            return node;
        }
    }
    private int removeHelper(TreeNode<Integer> node) //node.getleft is not null
    {
        if (node.getLeft().getLeft()==null)
        {
            int returnme=node.getLeft().getValue();
            node.setLeft(node.getLeft().getRight());
            return returnme;
        }
        return removeHelper(node.getLeft());
    }
    public int remove()
    {
        if (root==null)
        {
            return 0;
        }
        if (root.getLeft()==null)
        {
            int returnme=root.getValue();
            root=root.getRight();
            return returnme;
        }
        return removeHelper(root);
    }
    public boolean add(int item)
    {
        root=addHelper(item, root);
        return true;
    }
    */
    private void printLevelHelper(TreeNode<Integer> node, int level)
    {
        if (node!=null)
        {
            if (level==0)
            {
                System.out.print(node.getValue()+" ");
            }
            else
            {
                printLevelHelper(node.getLeft(), level-1);
                printLevelHelper(node.getRight(), level-1);
            }
        }
    }
    
    //ITERATIVE IMPLEMENTATIONS:
    public boolean add(int item)
    {
        if (root==null)
        {
            root=new TreeNode<Integer>(item, null, null);
            return true;
        }
        else
        {
            TreeNode<Integer> node=root;
            while (true)
            {
                if (item<node.getValue())
                {
                    if (node.getLeft()==null)
                    {
                        node.setLeft(new TreeNode<Integer>(item, null, null));
                        return true;
                    }
                    else
                    {
                        node=node.getLeft();
                    }
                }
                else
                {
                    if (node.getRight()==null)
                    {
                        node.setRight(new TreeNode<Integer>(item, null, null));
                        return true;
                    }
                    else
                    {
                        node=node.getRight();
                    }
                }
            }
        }
    }
    public int remove()
    {
        if (root==null)
        {
            throw new NoSuchElementException();
        }
        else if (root.getLeft()==null)
        {
            int returnme=root.getValue();
            root=root.getRight();
            return returnme;
        }
        else
        {
            TreeNode<Integer> temp=root;
            while (temp.getLeft().getLeft()!=null)
            {
                temp=temp.getLeft();
            }
            int returnme=temp.getLeft().getValue();
            temp.setLeft(temp.getLeft().getRight());
            return returnme;
        }
    }
    private class TreeIterator implements java.util.Iterator<Integer>
    {
        private ListNode<TreeNode<Integer>> stack;
        public TreeIterator()
        {
            if (root==null)
            {
                stack=null;
            }
            else
            {
                stack=new ListNode<TreeNode<Integer>>(root, null);
                while (stack.getValue().getLeft()!=null)
                {
                    stack=new ListNode<TreeNode<Integer>>(stack.getValue().getLeft(), stack);
                }
            }
        }
        public boolean hasNext()
        {
            return stack!=null;
        }
        public Integer next()
        {
            if (stack==null)
            {
                throw new NoSuchElementException();
            }
            Integer returnme=stack.getValue().getValue();
            if (stack.getValue().getRight()==null)
            {
                while (true)
                {
                    if (stack.getNext()==null)
                    {
                        stack=null;
                        break;
                    }
                    else if (stack.getNext().getValue().getRight()==stack.getValue())
                    {
                        stack=stack.getNext();
                    }
                    else
                    {
                        stack=stack.getNext();
                        break;
                    }
                }
            }
            else
            {
                stack=new ListNode<TreeNode<Integer>>(stack.getValue().getRight(), stack);
                while (stack.getValue().getLeft()!=null)
                {
                    stack=new ListNode<TreeNode<Integer>>(stack.getValue().getLeft(), stack);
                }
            }
            return returnme;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
    public Iterator<Integer> iterator()
    {
        return new TreeIterator();
    }
}