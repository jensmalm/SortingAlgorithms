public class TournamentNode
{
    private String team;
    private TournamentNode left;
    private TournamentNode right;
    String status;
    public TournamentNode(String name)
    {
        team=name;
        left=null;
        right=null;
        status="";
    }
    public TournamentNode(TournamentNode inputLeft, TournamentNode inputRight)
    {
        left=inputLeft;
        right=inputRight;
        status="";
    }
    public String getTeam()
    {
        return team;
    }
    public boolean complete()
    {
        return team!=null;
    }
    public boolean ready()
    {
        return (left.complete() && right.complete());
    }
    public void evaluate()
    {
        assert(ready());
        if (status.equals("final"))
        {
            team=MarchMadness.getSeriesWinner(left.getTeam(), right.getTeam(), 4);
        }
        else if (status.equals("semifinal"))
        {
            team=MarchMadness.getSeriesWinner(left.getTeam(), right.getTeam(), 2);
        }
        else
        {
            team=MarchMadness.getWinner(left.getTeam(), right.getTeam());
        }
    }
    public void setStatus(String st)
    {
        status=st;
    }
    public TournamentNode getLeft()
    {
        return left;
    }
    public TournamentNode getRight()
    {
        return right;
    }
}
    