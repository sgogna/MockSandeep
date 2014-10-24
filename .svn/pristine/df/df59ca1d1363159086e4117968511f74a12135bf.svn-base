/* Copyright 2009 EB2 International Limited */
package it;

import it.testBase.IntegrationTestBase;

public class BasicOneResponseITCase extends IntegrationTestBase
{

    @Override
    public void test() throws Exception
    {
        
        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setSessionId(generateSessionId());
        String response = getResponse("ShoppingApi_purchase__SABREGDS_EndTransactionLLS_1");

        assert response != null : "Response is null";
    }

}
