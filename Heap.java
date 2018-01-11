public class Heap extends Sort
{
    public Heap()
    {
        super("Heap", 3606299); //The maximum length of 3606299 was determined by the calculateMaxArrayLength method
    }
    public void sort(int[] array)
    {
        Algorithms.heap(array);
    }
}
