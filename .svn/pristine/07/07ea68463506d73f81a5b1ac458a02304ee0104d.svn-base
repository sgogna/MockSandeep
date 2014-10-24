/* Copyright 2009 EB2 International Limited */
package it;

import it.testBase.IntegrationTestBase;

public class UnknownServiceITCase extends IntegrationTestBase
{
    @Override
    public void test() throws Exception
    {
        
        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setSessionId(generateSessionId());
        String response = getResponse("UserDetailsServiceApi_loadUserByUsername__UNKNOWN_Unknown_1");
        assert response != null : "Response for UserDetailsServiceApi_loadUserByUsername__UNKNOWN_Unknown_1 is null";
        assert response.equals("U2") : "Response and exspected response for UserDetailsServiceApi_loadUserByUsername__UNKNOWN_Unknown_1 are different!";
        
        response = getResponse("ShoppingApi_purchase__UNKNOWN_Unknown_1");
        assert response != null : "Response for ShoppingApi_purchase__UNKNOWN_Unknown_1 is null";
        assert response.equals("U1") : "Response and exspected response for ShoppingApi_purchase__UNKNOWN_Unknown_1 are different!";
    }
}
