package persistence;

import persistence.exceptions.CustomSQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    
    private Connection connection = null;
    private final String USER;
    private final String PASSWORD;
    private final String URL;
    
    public Database( String user, String password, String url ) throws CustomSQLException
    {
        this.USER = user;
        this.PASSWORD = password;
        this.URL = url;
        
        try {
            Class.forName( "org.postgresql.Driver" );
            
        } catch ( ClassNotFoundException e ) {
            throw new CustomSQLException( "Fejl ved instantiering af Driver klasse", e );
        }
    }
    
    public Database( Database database ) throws CustomSQLException
    {
        this(
                database.USER,
                database.PASSWORD,
                database.URL
        );
    }
    
    public Connection connect() throws CustomSQLException
    {
        try {
            
            if ( this.connection != null && !this.connection.isClosed() ) {
                return this.connection;
            }
            
            
            this.connection = DriverManager.getConnection( this.URL, this.USER, this.PASSWORD );
            
        } catch ( SQLException e ) {
            throw new CustomSQLException( "Fejl under etablering af forbindelse til database", e );
        }
        
        return this.connection;
    }
    
    public void close() throws SQLException
    {
        this.connection.close();
    }
    
}
