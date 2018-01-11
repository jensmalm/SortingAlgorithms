public interface Bout
{
    public Sort play(boolean display) throws AlreadyPlayedException;
    public String matchup();
}