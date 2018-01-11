public class Quick extends Sort {
    public Quick()
    {
        super("Quick", Double.POSITIVE_INFINITY);
    }
    @Override
    public void sort(int[] array) {
        Algorithms.quick(array);
    }
}
