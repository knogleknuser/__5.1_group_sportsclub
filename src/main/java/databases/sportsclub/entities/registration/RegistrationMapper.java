package databases.sportsclub.entities.registration;

import databases.sportsclub.entities.member.Member;
import persistence.Database;
import persistence.exceptions.CustomSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationMapper
{
    
    private final Database database;
    
    public RegistrationMapper( Database database )
    {
        this.database = database;
    }
    
    public Registration addToTeam( Registration newRegistration ) throws CustomSQLException
    {
        String sqlCheck =
                "SELECT * " +
                "FROM " +
                "  registration " +
                "WHERE " +
                "  member_id = ?" +
                "  AND team_id = ?;";
        
        
        Connection connection = this.database.connect();
        
        int member_id = newRegistration.getMember_id();
        String team_id = newRegistration.getTeam_id();
        int price = newRegistration.getPrice();
        
        try ( PreparedStatement ps = connection.prepareStatement( sqlCheck ) ) {
            ps.setInt( 1, member_id );
            ps.setString( 2, team_id );
            
            ResultSet resultSet = ps.executeQuery();
            
            if ( resultSet.next() ) {
                throw new CustomSQLException( "Failed to insert new registration because it already exists!\n" +
                                              "Existing Registration: " +
                                              "member_id = " + resultSet.getInt( "member_id" ) +
                                              " team_id = " + resultSet.getString( "team_id" ) +
                                              " price = " + resultSet.getInt( "price" ) + "\n" +
                                              "Attempted Registration: " +
                                              "member_id = " + member_id +
                                              " team_id = " + team_id +
                                              " price = " + price,
                                              new SQLException()
                );
            }
            
        } catch ( SQLException e ) {
            if ( e instanceof CustomSQLException ) {
                throw ( CustomSQLException ) e;
            }
            
            throw new CustomSQLException( "Failed at insert new registration at the check for registration: " +
                                          "member_id = " + member_id +
                                          " team_id = " + team_id +
                                          " price = " + price,
                                          e
            );
        }
        
        
        String sqlInsert =
                "INSERT " +
                "  INTO registration ( member_id, team_id, price ) " +
                "VALUES " +
                "  ( ? , ? , ? );";
        
        try ( PreparedStatement ps = connection.prepareStatement( sqlInsert ) ) {
            ps.setInt( 1, member_id );
            ps.setString( 2, team_id );
            ps.setInt( 3, price );
            
            ps.execute();
            
            return new Registration( member_id, team_id, price );
            
        } catch ( SQLException e ) {
            throw new CustomSQLException( "Failed to insert new registration: " +
                                          "member_id = " + member_id +
                                          " team_id = " + team_id +
                                          " price = " + price, e );
        }
    }
    
    public List< Registration > getAllRegistrations() throws CustomSQLException
    {
        List< Registration > registrationList = new ArrayList<>();
        
        String sqlCheck =
                "SELECT * " +
                "FROM " +
                "  registration;";
        
        
        Connection connection = this.database.connect();
        
        try ( PreparedStatement ps = connection.prepareStatement( sqlCheck ) ) {
            
            ResultSet rs = ps.executeQuery();
            
            while ( rs.next() ) {
                int member_id = rs.getInt( "member_id" );
                String team_id = rs.getString( "team_id" );
                int price = rs.getInt( "price" );
                
                registrationList.add( new Registration( member_id, team_id, price ) );
            }
            
        } catch ( SQLException e ) {
            throw new CustomSQLException( "Failed to get all registrations", e );
        }
        
        return registrationList;
    }
    
}
