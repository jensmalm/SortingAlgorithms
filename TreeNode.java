public class TreeNode<T>
{
    T value;
    TreeNode<T> left;
    TreeNode<T> right;
    public TreeNode(T item, TreeNode<T> myLeft, TreeNode<T> myRight)
    {
        value=item;
        left=myLeft;
        right=myRight;
    }
}
