package databases.sportsclub.entities.registration;

public class Registration
{
    private final int member_id;
    private final String team_id;
    private final int price;
    
    public Registration( int member_id, String team_id, int price )
    {
        this.member_id = member_id;
        this.team_id = team_id;
        this.price = price;
    }
    
    
    @Override
    public String toString()
    {
        return "Registration{" +
               "member_id=" + this.member_id +
               ", team_id='" + this.team_id + '\'' +
               ", price=" + this.price +
               '}';
    }
    
    //Getters
    public int getMember_id()
    {
        return this.member_id;
    }
    
    public String getTeam_id()
    {
        return this.team_id;
    }
    
    public int getPrice()
    {
        return this.price;
    }
    
}
