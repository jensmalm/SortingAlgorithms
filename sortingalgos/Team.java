public class Team
{
    private String[] members;
    private String name;
    public Team(String n, String st1, String st2, String st3, String st4)
    {
        members=new String[4];
        name=n;
        members[0]=st1;
        members[1]=st2;
        members[2]=st3;
        members[3]=st4;
    }
    public String get(int n)
    {
        return members[n];
    }
    public String getName()
    {
        return name;
    }
    public boolean playAgains(Team other)
    {
        return (TeamMadness.getWinner(this, other)==this);
    }
}