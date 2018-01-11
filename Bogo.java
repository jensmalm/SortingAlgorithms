public class Bogo extends Sort
{
    public Bogo()
    {
        super("Bogo", 11); // The maximum length of 11 was determined by the calculateMaxArrayLength method
    }
    @Override
    public void sort(int[] array)
    {
        Algorithms.bogo(array);
    }
    @Override
    protected int nextToTry(int lo, int hi)
    {
        return lo;
    }
}