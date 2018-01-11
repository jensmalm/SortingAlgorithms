public class Insertion extends Sort
{
    public Insertion()
    {
        super("Insertion", 138225);
    } //The maximum length of 138225 was determined by the calculateMaxArrayLength method
    public void sort(int[] array)
    {
        Algorithms.insertion(array);
    }
}