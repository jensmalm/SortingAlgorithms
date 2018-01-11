public class Gnome extends Sort {
    public Gnome()
    {
        super("Gnome", 23456);
    }
    public void sort(int[] array)
    {
        Algorithms.gnome(array);
    }
}
