package databases.sportsclub;

import persistence.Database;
import persistence.exceptions.CustomSQLException;

public class DatabaseSportsclub
{
    
    //LOGIN INFO----------
    public final static String USER = "postgres";
    public final static String PASSWORD = "postgres";
    
    //URL--------
    public final static String URL_LOCALHOST = "jdbc:postgresql://localhost:5432/sportsclub?serverTimezone=CET&useSSL=false&allowPublicKeyRetrieval=true"; //Either this
    public final static String URL_STEENS_LAPTOP = "jdbc:postgresql://87.57.196.198:5432/sportsclub?serverTimezone=CET&useSSL=false&allowPublicKeyRetrieval=true"; //OR this
    
    //Choose one of the above to use as the URL
    public final static String URL = URL_STEENS_LAPTOP;
    
    
    //All the juicy info you need for sportsclub!
    public static Database getDb() throws CustomSQLException
    {
        return new Database( USER, PASSWORD, URL );
    }
    
}
