package databases.sportsclub.entities.member;

public class Member
{
    
    private final int memberId;
    private final String name;
    private final String address;
    private final int zip;
    private final String city;
    private final String gender;
    private final int year;
    
    public Member( int memberId, String name, String address, int zip, String city, String gender, int year )
    {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.gender = gender;
        this.year = year;
    }
    
    public Member( String name, String address, int zip, String city, String gender, int year )
    {
        this( -1, name, address, zip, city, gender, year );
    }
    
    public Member( int memberId, Member member )
    {
        this( memberId, member.name, member.address, member.zip, member.city, member.gender, member.year );
    }
    
    public int getMemberId()
    {
        return memberId;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public int getZip()
    {
        return zip;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public String getGender()
    {
        return gender;
    }
    
    public int getYear()
    {
        return year;
    }
    
    @Override
    public String toString()
    {
        return "Member{" +
               "memberId=" + memberId +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", zip=" + zip +
               ", city='" + city + '\'' +
               ", gender='" + gender + '\'' +
               ", year=" + year +
               '}';
    }
    
}
