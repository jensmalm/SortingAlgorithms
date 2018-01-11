public class Bozo extends Sort {
    public Bozo()
    {
        super("Bozo", 11);
    }
    public void sort(int[] array)
    {
        Algorithms.bozo(array);
    }
}
