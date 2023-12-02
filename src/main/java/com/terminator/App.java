package com.terminator;

import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( ">> ======== HIBERNATE JPA CORE ========= <<" );

        Persistence.createEntityManagerFactory("firstDemo");        
    }
}
