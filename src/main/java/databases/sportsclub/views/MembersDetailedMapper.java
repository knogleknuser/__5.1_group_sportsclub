package databases.sportsclub.views;

import databases.sportsclub.entities.member.Member;
import persistence.Database;
import persistence.exceptions.CustomSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembersDetailedMapper
{
    
    private final Database database;
    
    public MembersDetailedMapper( Database database )
    {
        this.database = database;
    }
    
    public List< MembersDetailed > getAllMembersDetailed() throws CustomSQLException
    {
        
        List< MembersDetailed > memberDetailedList = new ArrayList<>();
        
        String sql =
                "SELECT * " +
                "FROM " +
                "    members_and_sports " +
                "ORDER BY " +
                "    member_id";
        
        
        Connection connection = this.database.connect();
        
        try ( PreparedStatement ps = connection.prepareStatement( sql ) ) {
            
            ResultSet rs = ps.executeQuery();
            
            while ( rs.next() ) {
                int memberId = rs.getInt( "member_id" );
                String name = rs.getString( "name" );
                String address = rs.getString( "address" );
                String city = rs.getString( "city" );
                String gender = rs.getString( "gender" );
                int year = rs.getInt( "year" );
                String sport = rs.getString( "sport" );
                String team_id = rs.getString( "team_id" );
                Integer price = rs.getInt( "price" );
                
                memberDetailedList.add( new MembersDetailed( memberId, name, address, city, gender, year, sport, team_id, price ) );
            }
            
        } catch ( SQLException e ) {
            throw new CustomSQLException( "Failed to get all members with details", e );
        }
        
        
        
        return memberDetailedList;
    }
    
    public List< MembersDetailed > getAllRegistrationsDetailed() throws CustomSQLException
    {
        
        List< MembersDetailed > memberDetailedList = new ArrayList<>();
        
        String sql =
                "SELECT * " +
                "FROM " +
                "    registrations_detailed " +
                "ORDER BY " +
                "    member_id";
        
        
        Connection connection = this.database.connect();
        
        try ( PreparedStatement ps = connection.prepareStatement( sql ) ) {
            
            ResultSet rs = ps.executeQuery();
            
            while ( rs.next() ) {
                int memberId = rs.getInt( "member_id" );
                String name = rs.getString( "name" );
                String address = rs.getString( "address" );
                String city = rs.getString( "city" );
                String gender = rs.getString( "gender" );
                int year = rs.getInt( "year" );
                String sport = rs.getString( "sport" );
                String team_id = rs.getString( "team_id" );
                Integer price = rs.getInt( "price" );
                
                memberDetailedList.add( new MembersDetailed( memberId, name, address, city, gender, year, sport, team_id, price ) );
            }
            
        } catch ( SQLException e ) {
            throw new CustomSQLException( "Failed to get all members with details", e );
        }
        
        
        
        return memberDetailedList;
    }
    
}
