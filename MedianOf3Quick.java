public class MedianOf3Quick extends Sort
{
    public MedianOf3Quick() {super("MedianOf3Quick", Double.POSITIVE_INFINITY);}
    public void sort(int[] array)
    {
        Algorithms.medianOf3Quick(array);
    }
}
