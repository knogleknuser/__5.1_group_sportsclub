import databases.sportsclub.DatabaseSportsclub;
import databases.sportsclub.entities.member.Member;
import databases.sportsclub.entities.registration.Registration;
import databases.sportsclub.entities.registration.RegistrationMapper;
import databases.sportsclub.views.MembersDetailed;
import databases.sportsclub.views.MembersDetailedMapper;
import persistence.Database;
import databases.sportsclub.entities.member.MemberMapper;
import persistence.exceptions.CustomSQLException;

import java.sql.SQLException;
import java.util.List;

public class Main
{
    
    public static void main( String[] args ) throws CustomSQLException
    {
        Database db = new Database( DatabaseSportsclub.getDb() );
        
        MemberMapper memberMapper = new MemberMapper( db );
        
        //Show all members
        try {
            List< Member > members = memberMapper.getAllMembers();
            
            showRows( members,"members" );
            
        } catch ( CustomSQLException e ) {
            System.out.println();
            System.err.println( e.getMessage() );
        }
        
        
        
        //Show single member
        try {
            showMemberById( memberMapper, 13 );
            
        } catch ( CustomSQLException e ) {
            System.out.println();
            System.err.println( e.getMessage() );
        }
        
        
        
        
        //Insert and Delete
        try {
            
            Member newMember = new Member(
                    "Ole Olsen",
                    "Banegade 2",
                    3700,
                    "Rønne",
                    "m",
                    1967
            );
            
            Member memberInserted = insertMember( newMember, memberMapper );
            deleteMember( memberInserted.getMemberId(), memberMapper );
            
        } catch ( CustomSQLException e ) {
            System.out.println();
            System.err.println( e.getMessage() );
        }
        
        
        
        //Update
        try {
            updateMember( 53, memberMapper );
            
        } catch ( CustomSQLException e ) {
            System.out.println();
            System.err.println( e.getMessage() );
        }
        
        
        //My custom registration stuff-----------------------------------------------------------
        
        RegistrationMapper registrationMapper = new RegistrationMapper( db );
        
        //New registration
        try {
            Registration registration = new Registration( 53, "gym01", 100 );
            insertRegistration( registration, registrationMapper );
            
        } catch ( CustomSQLException e ) {
            System.out.println();
            System.err.println( e.getMessage() );
        }
        
        
        
        //Show all registrations
        try {
            List< ? > registrations = registrationMapper.getAllRegistrations();
            
            showRows( registrations,"registrations" );
            
        } catch ( CustomSQLException e ) {
            System.out.println();
            System.err.println( e.getMessage() );
        }
        
        MembersDetailedMapper membersDetailedMapper = new MembersDetailedMapper( db );
        
        //Show all members with registrations in detail
        try {
            List< ? > registrations = membersDetailedMapper.getAllRegistrationsDetailed();
            
            showRows( registrations,"Detailed Registrations" );
            
        } catch ( CustomSQLException e ) {
            System.out.println();
            System.err.println( e.getMessage() );
            e.getException().printStackTrace();
        }
        
        //Show all members in detail
        try {
            List< ? > registrations = membersDetailedMapper.getAllMembersDetailed();
            
            showRows( registrations,"Detailed Members" );
            
        } catch ( CustomSQLException e ) {
            System.out.println();
            System.err.println( e.getMessage() );
            e.getException().printStackTrace();
        }
        
        
        
        
       //Closing down------------------------------------------------------
        try {
            db.close();
            
        } catch ( SQLException e ) {
            throw new CustomSQLException( "Failed to close the local main connection to sportsclub database", e );
        }
    }
    
    private static void deleteMember( int memberId, MemberMapper memberMapper ) throws CustomSQLException
    {
        System.out.println();
        System.out.println( "***** Deleting member nr. " + memberId + ": *******" );
        
        if ( memberMapper.deleteMember( memberId ) ) {
            System.out.println( "Member with id = " + memberId + " is removed from DB" );
        }
    }
    
    private static Member insertMember( Member newMember, MemberMapper memberMapper ) throws CustomSQLException
    {
        System.out.println();
        System.out.println( "***** Inserting a new member : *******" );
        System.out.println( "They have these values before insertion:" );
        System.out.println( newMember.toString() );
        
        Member memberInserted = memberMapper.insertMember( newMember );
        System.out.println( "Inserted a new member! :" + memberInserted.toString() );
        return memberInserted;
        
    }
    
    private static void updateMember( int memberId, MemberMapper memberMapper ) throws CustomSQLException
    {
        System.out.println();
        System.out.println( "***** Opdaterer medlem nr. " + memberId + ": *******" );
        
        Member mOriginal = memberMapper.getMemberById( memberId );
        
        System.out.println( "Originale Info, bemært at vi ændrer året til 1970" );
        System.out.println( mOriginal.toString() );
        
        Member mChanged = new Member(
                mOriginal.getMemberId(),
                mOriginal.getName(),
                mOriginal.getAddress(),
                mOriginal.getZip(),
                mOriginal.getCity(),
                mOriginal.getGender(),
                1970 );
        
        if ( memberMapper.updateMember( mChanged ) ) {
            showMemberById( memberMapper, memberId );
        }
    }
    
    private static void showMemberById( MemberMapper memberMapper, int memberId ) throws CustomSQLException
    {
        System.out.println();
        System.out.println( "***** Vis medlem nr. " + memberId + ": *******" );
        System.out.println( memberMapper.getMemberById( memberId ).toString() );
    }
    
    private static void showRows( List< ? > rows, String contains )
    {
        System.out.println();
        System.out.println( "***** Vis alle "+contains+" *******" );
        
        if ( rows == null ) {
            System.out.println( contains+" was null, maybe there are no members?" );
            return;
        }
        
        for ( Object row : rows ) {
            System.out.println( row.toString() );
        }
    }
    
    private static Registration insertRegistration( Registration newRegistration, RegistrationMapper registrationMapper ) throws CustomSQLException
    {
        System.out.println();
        System.out.println( "***** Inserting a new registration : *******" );
        System.out.println( "It has these values before insertion:" );
        System.out.println( newRegistration.toString() );
        
        Registration registrationInserted = registrationMapper.addToTeam( newRegistration );
        System.out.println( "Inserted a new registration! :" + registrationInserted.toString() );
        return registrationInserted;
        
    }
    
    
}
