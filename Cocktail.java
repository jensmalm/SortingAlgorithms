public class Cocktail extends Sort {
    public Cocktail()
    {
        super("Cocktail", 131072);
    }
    public void sort(int[] array)
    {
        Algorithms.cocktail(array);
    }
}
