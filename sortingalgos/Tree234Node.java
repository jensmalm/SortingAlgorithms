public class Tree234Node
{
    public Tree234Node parent;
    public int items;
    public int item1;
    public int item2;
    public int item3;
    public Tree234Node child1;
    public Tree234Node child2;
    public Tree234Node child3;
    public Tree234Node child4;
    public Tree234Node(Tree234Node p, int k)
    {
        parent=p;
        item1=k;
        items=1;
    }
    public Tree234Node(Tree234Node p, Tree234Node c1, int k1, Tree234Node c2)
    {
        parent=p;
        items=1;
        child1=c1;
        child2=c2;
        if (child1!=null)
        {
            child1.parent=this;
        }
        if (child2!=null)
        {
            child2.parent=this;
        }
        item1=k1;
    }
    public Tree234Node(Tree234Node p, Tree234Node c1, int k1, Tree234Node c2, int k2, Tree234Node c3)
    {
        parent=p;
        items=2;
        child1=c1;
        child2=c2;
        child3=c3;
        if (child1!=null)
        {
            child1.parent=this;
        }
        if (child2!=null)
        {
            child2.parent=this;
        }
        if (child3!=null)
        {
            child3.parent=this;
        }
        item1=k1;
        item2=k2;
    }
    public Tree234Node(Tree234Node p, Tree234Node c1, int k1, Tree234Node c2, int k2, Tree234Node c3, int k3, Tree234Node c4)
    {
        parent=p;
        items=3;
        child1=c1;
        child2=c2;
        child3=c3;
        child4=c4;
        if (child1!=null)
        {
            child1.parent=this;
        }
        if (child2!=null)
        {
            child2.parent=this;
        }
        if (child3!=null)
        {
            child3.parent=this;
        }
        if (child4!=null)
        {
            child4.parent=this;
        }
        item1=k1;
        item2=k2;
        item3=k3;
    }
}