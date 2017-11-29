public class TreeNode<T>
{
    private T value;
    private TreeNode<T> left;
    private TreeNode<T> right;
    public TreeNode(T val, TreeNode<T> l, TreeNode<T> r)
    {
        value=val;
        left=l;
        right=r;
    }
    public void setValue(T val)
    {
        value=val;
    }
    public void setLeft(TreeNode<T> l)
    {
        left=l;
    }
    public void setRight(TreeNode<T> r)
    {
        right=r;
    }
    public T getValue()
    {
        return value;
    }
    public TreeNode<T> getLeft()
    {
        return left;
    }
    public TreeNode<T> getRight()
    {
        return right;
    }
}