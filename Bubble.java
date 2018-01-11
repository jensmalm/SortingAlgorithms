public class Bubble extends Sort {
    public Bubble()
    {
        super("Bubble", 100353); //The maximum length of 100353 was determined by the calculateMaxArrayLength method
    }
    public void sort(int[] array)
    {
        Algorithms.bubble(array);
    }
}
