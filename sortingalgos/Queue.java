import java.util.*;
public class Queue<T>
{
    private ListNode<T> first;
    private ListNode<T> last;
    public Queue()
    {
        first=null;
        last=first;
    }
    public boolean add(T x)
    {
        if (last==null)
        {
            last=new ListNode<T>(x, null);
            first=last;
        }
        else
        {
           last.setNext(new ListNode<T>(x, null));
           last=last.getNext();
        }
        return true;
    }
    public T remove()
    {
        if (first==null)
        {
            throw new NoSuchElementException();
        }
        else if (first==last)
        {
            T t=first.getValue();
            first=null;
            last=null;
            return t;
        }
        else
        {
            T t=first.getValue();
            first=first.getNext();
            return t;
        }
    }
    public T peek()
    {
        if (first==null)
        {
            return null;
        }
        else
        {
            
            T t=first.getValue();
            return t;
        }
    }
    public boolean isEmpty()
    {
        return first==null;
    }
    public void printAll()// for the purpose of testing
    {
        ListNode<T> temp=first;
        while (temp!=null)
        {
            System.out.print(temp.getValue()+" ");
            temp=temp.getNext();
        }
        System.out.println();
    }
    public int size()
    {
        ListNode<T> temp=first;
        int size=0;
        while (temp!=null)
        {
            size++;
            temp=temp.getNext();
        }
        return size;
    }
}