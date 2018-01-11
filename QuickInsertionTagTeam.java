public class QuickInsertionTagTeam extends Sort {
    private int threshold;
    public QuickInsertionTagTeam(int initThreshold)
    {
        super("QuickInsertionTagTeam"+Integer.toString(initThreshold), Double.POSITIVE_INFINITY);
        threshold=initThreshold;
    }
    public void sort(int[] array)
    {
        Algorithms.quickInsertionTagTeam(array, threshold);
    }
}
