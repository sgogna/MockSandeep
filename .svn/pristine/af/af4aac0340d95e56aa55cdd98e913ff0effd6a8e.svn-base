/* Copyright 2009 EB2 International Limited */
package it;

import it.testBase.IntegrationTestBase;

public class BasicDateReplacerITCase extends IntegrationTestBase
{
    @Override
    public void test() throws Exception
    {
        
        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setSessionId(generateSessionId());
        String response = getResponse("ShoppingApi_shop__SABREGDS_SSWIntellisell_ToSend_1");
        assert response != null : "Response is null";
        assert response.equals(readFileFromTestFolder("ShoppingApi_shop__SABREGDS_SSWIntellisell_Valid_RS.xml")) : "Response and exspected response are different!";
        
    }
    
}
