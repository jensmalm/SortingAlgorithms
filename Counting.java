public class Counting extends Sort {
    public Counting()
    {
        super("Counting", 134217726);
    } // longer than this, there is a danger of OutOfMemoryErrors
    public void sort(int[] array)
    {
        Algorithms.counting(array);
    }
}
