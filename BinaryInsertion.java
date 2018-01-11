public class BinaryInsertion extends Sort {
    public BinaryInsertion()
    {
        super("BinaryInsertion", 138225);
    }
    @Override
    public void sort(int[] array) {
        Algorithms.binaryInsertion(array);
    }
}
