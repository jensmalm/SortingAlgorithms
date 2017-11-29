
/**
 * Write a description of class FactorialMod here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FactorialMod
{
    public static void main(int n)
    {
        int total=1;
        for (int i=1; i<n; i++)
        {
            total=(total*i)%n;
            System.out.println(i+"! = "+total+" mod "+n);
        }
        System.out.println();
    }
    public static String translateByTwo(String st)
    {
        String r="";
        for (int i=0; i<st.length(); i++)
        {
            if (st.charAt(i)==' ')
            {
                r=r+" ";
            }
            if (st.charAt(i)=='y')
            {
                r=r+"a";
            }
            if (st.charAt(i)=='z')
            {
                r=r+"b";
            }
            else
            {
                r=r+Character.toString((char)((int)(st.charAt(i))+2));
            }
        }
        return r;
    }
}