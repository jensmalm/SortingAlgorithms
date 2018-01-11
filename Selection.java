public class Selection extends Sort {
    public Selection()
    {
        super("Selection", 102911); // The maximum length of 102911 was determined by the calculateMaxArrayLength method
    }
    public void sort(int[] array)
    {
        Algorithms.selection(array);
    }
}
