import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree
{
    private TreeNode<Integer> root;
    private Integer lastRemoved;

    /*
    private TreeNode<Integer> addHelper(TreeNode<Integer> node, int item)
    {
        if (node==null)
        {
            return new TreeNode<Integer>(item, null, null);
        }
        if (item<node.value || (item==node.value && Math.random()<.5))
        {
            node.left=addHelper(node.left, item);
        }
        else
        {
            node.right=addHelper(node.right, item);
        }
        return node;
    }
    public void add(int item)
    {
        root=addHelper(root, item);
    }
    */
    public void add(int item)
    {
        if (root==null)
        {
            root=new TreeNode<Integer>(item, null, null);
        }
        else
        {
            TreeNode<Integer> node=root;
            while (true)
            {
                if (item<node.value || (item==node.value && Math.random()<.5))
                {
                    if (node.left==null)
                    {
                        node.left=new TreeNode<Integer>(item, null, null);
                        return;
                    }
                    else
                    {
                        node=node.left;
                    }
                }
                else
                {
                    if (node.right==null)
                    {
                        node.right=new TreeNode<Integer>(item, null, null);
                        return;
                    }
                    else
                    {
                        node=node.right;
                    }
                }
            }
        }
    }

    /*
    private TreeNode<Integer> removeHelper(TreeNode<Integer> node)
    {
        if (node.left==null)
        {
            lastRemoved=node.value;
            return node.right;
        }
        node.left=removeHelper(node.left);
        return node;
    }
    public int removeSmallest()
    {
        if (root==null)
        {
            throw new NoSuchElementException();
        }
        root=removeHelper(root);
        return lastRemoved;
    }
    */
    public int removeSmallest()
    {
        if (root==null)
        {
            throw new NoSuchElementException();
        }
        if (root.left==null)
        {
            int result=root.value;
            root=root.right;
            return result;
        }
        TreeNode<Integer> node=root;
        while (node.left.left!=null)
        {
            node=node.left;
        }
        int result=node.left.value;
        node.left=node.left.right;
        return result;
    }
}