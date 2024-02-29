package databases.sportsclub.views;

public class MembersDetailed
{
    private final int memberId;
    private final String name;
    private final String address;
    private final String city;
    private final String gender;
    private final int year;
    private final String sport;
    private final String team_id;
    private final Integer price;
    
    public MembersDetailed( int memberId, String name, String address,  String city, String gender, int year, String sport, String team_id, Integer price )
    {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.gender = gender;
        this.year = year;
        this.sport = sport;
        this.team_id = team_id;
        this.price = price;
    }
    
    @Override
    public String toString()
    {
        return "MembersDetailed{" +
               "memberId=" + memberId +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", city='" + city + '\'' +
               ", gender='" + gender + '\'' +
               ", year=" + year +
               ", sport='" + sport + '\'' +
               ", team_id='" + team_id + '\'' +
               ", price=" + price +
               '}';
    }
    
    public int getMemberId()
    {
        return this.memberId;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    public String getCity()
    {
        return this.city;
    }
    
    public String getGender()
    {
        return this.gender;
    }
    
    public int getYear()
    {
        return this.year;
    }
    
    public String getSport()
    {
        return this.sport;
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
