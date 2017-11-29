public class ListNode<T>
{
    private T value;
    private ListNode<T> next;
    public ListNode(T initValue, ListNode<T> initNext)
    {
        value = initValue;
        next = initNext;
    }
    public ListNode(T initValue)
    {
        this(initValue, null);
    }
    public void setValue(T theNewValue)
    {
        value = theNewValue;
    }
    public void setNext(ListNode<T> theNewNext)
    {
        next = theNewNext;
    }
    public T getValue()
    {
        return value;
    }
    public ListNode<T> getNext()
    {
        return next;
    }
    public String toString()
    {
        if (getNext()==null)
        {
            return getValue()+"";
        }
        return getValue()+" "+getNext().toString();
    }
    public ListNode<T> copy(ListNode<T> li)
    {
        if (li==null)
        {
            return null;
        }
        else
        {
            return new ListNode<T>(li.getValue(), copy(li.getNext()));
        }
    }
    public ListNode<T> copy()
    {
        return copy(this);
    }
}