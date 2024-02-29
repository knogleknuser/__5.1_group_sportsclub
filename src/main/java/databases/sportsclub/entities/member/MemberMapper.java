package databases.sportsclub.entities.member;

import persistence.Database;
import persistence.exceptions.CustomSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberMapper
{
    
    private final Database database;
    
    public MemberMapper( Database database )
    {
        this.database = database;
    }
    
    public List< Member > getAllMembers() throws CustomSQLException
    {
        
        List< Member > memberList = new ArrayList<>();
        
        String sql =
                "SELECT " +
                "    member_id, " +
                "    name, " +
                "    address, " +
                "    m.zip, " +
                "    gender, " +
                "    city, " +
                "    year " +
                "FROM " +
                "    member AS m " +
                "        INNER JOIN zip USING(zip) " +
                "ORDER BY " +
                "   member_id;";
        
        
        Connection connection = this.database.connect();
        
        try ( PreparedStatement ps = connection.prepareStatement( sql ) ) {
            
            ResultSet rs = ps.executeQuery();
            
            while ( rs.next() ) {
                int memberId = rs.getInt( "member_id" );
                String name = rs.getString( "name" );
                String address = rs.getString( "address" );
                int zip = rs.getInt( "zip" );
                String city = rs.getString( "city" );
                String gender = rs.getString( "gender" );
                int year = rs.getInt( "year" );
                
                memberList.add( new Member( memberId, name, address, zip, city, gender, year ) );
            }
            
        } catch ( SQLException e ) {
            throw new CustomSQLException( "Failed to get all members", e );
        }
        
        
        
        return memberList;
    }
    
    public Member getMemberById( int memberId ) throws CustomSQLException
    {
        String sql =
                "SELECT " +
                "    member_id, " +
                "    name, " +
                "    address, " +
                "    m.zip, " +
                "    gender, " +
                "    city, year " +
                "FROM " +
                "    member AS m " +
                "        INNER JOIN zip USING(zip) " +
                "WHERE " +
                "    member_id = ?;";
        
        
        Connection connection = this.database.connect();
        
        try ( PreparedStatement ps = connection.prepareStatement( sql ) ) {
            ps.setInt( 1, memberId );
            
            ResultSet rs = ps.executeQuery();
            
            if ( !rs.next() ) {
                return null;
            }
            
            int actualMemberId = rs.getInt( "member_id" );
            String name = rs.getString( "name" );
            String address = rs.getString( "address" );
            int zip = rs.getInt( "zip" );
            String city = rs.getString( "city" );
            String gender = rs.getString( "gender" );
            int year = rs.getInt( "year" );
            
            return new Member( actualMemberId, name, address, zip, city, gender, year );
            
        } catch ( SQLException e ) {
            throw new CustomSQLException( "Failed to get member with ID: " + memberId, e );
        }
    }
    
    public boolean deleteMember( int memberId ) throws CustomSQLException
    {
        String sql =
                "DELETE FROM member " +
                "WHERE " +
                "  member_id = ?;";
        
        
        Connection connection = this.database.connect();
        
        try ( PreparedStatement ps = connection.prepareStatement( sql ) ) {
            ps.setInt( 1, memberId );
            
            int rowsAffected = ps.executeUpdate();
            
            if ( rowsAffected == 1 ) {
                return true;
            }
            
        } catch ( SQLException e ) {
            throw new CustomSQLException( "Failed to delete member with ID: " + memberId, e );
        }
        
        return false;
    }
    
    public Member insertMember( Member member ) throws CustomSQLException
    {
        int newId;
        String sql =
                "INSERT " +
                "  INTO member ( name, address, zip, gender, year ) " +
                "VALUES " +
                "  ( ? , ? , ? , ? , ? );";
        
        
        Connection connection = this.database.connect();
        
        try ( PreparedStatement ps = connection.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS ) ) {
            ps.setString( 1, member.getName() );
            ps.setString( 2, member.getAddress() );
            ps.setInt( 3, member.getZip() );
            ps.setString( 4, member.getGender() );
            ps.setInt( 5, member.getYear() );
            
            ps.execute();
            
            ResultSet idResultset = ps.getGeneratedKeys();
            
            if ( !idResultset.next() ) {
                return null;
            }
            
            newId = idResultset.getInt( 1 );
            
            return new Member( newId, member );
            
        } catch ( SQLException e ) {
            throw new CustomSQLException( "Failed to insert member: " + member.toString(), e );
        }
    }
    
    public boolean updateMember( Member member ) throws CustomSQLException
    {
        String sql =
                "UPDATE " +
                "   member " +
                "SET " +
                "   name = ?, address = ?, zip = ?, gender = ?, year = ? " +
                "WHERE " +
                "   member_id = ?;";
        
        
        Connection connection = this.database.connect();
        
        try ( PreparedStatement ps = connection.prepareStatement( sql ) ) {
            ps.setString( 1, member.getName() );
            ps.setString( 2, member.getAddress() );
            ps.setInt( 3, member.getZip() );
            ps.setString( 4, member.getGender() );
            ps.setInt( 5, member.getYear() );
            ps.setInt( 6, member.getMemberId() );
            
            int rowsAffected = ps.executeUpdate();
            
            if ( rowsAffected == 1 ) {
                return true;
            }
            
        } catch ( SQLException e ) {
            throw new CustomSQLException( "Failed to update member with ID: " + member.getMemberId(), e );
        }
        
        
        return false;
    }
    
}
