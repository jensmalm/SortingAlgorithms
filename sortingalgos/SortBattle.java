public class SortBattle
{
    public static int[] getArray(int length)
    {
        double maxlength=Math.sqrt(Integer.MAX_VALUE);
        int[] array=new int[length];
        for (int i=0; i<array.length; i++)
        {
            array[i]=(int)(Math.random()*maxlength);
        }
        return array;
    }
}