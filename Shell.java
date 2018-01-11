public class Shell extends Sort {
    public Shell()
    {
        super("Shell", Double.POSITIVE_INFINITY);
    }
    @Override
    public void sort(int[] array) {
        Algorithms.shell(array);
    }
}
