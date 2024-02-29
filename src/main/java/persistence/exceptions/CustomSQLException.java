package persistence.exceptions;

import java.sql.SQLException;

public class CustomSQLException extends SQLException
{
    private final Exception e;
    
    public CustomSQLException( String reason, Exception e )
    {
        super( reason );
        
        this.e = e;
    }
    
    //Getters
    public Exception getException()
    {
        return this.e;
    }
    
}
