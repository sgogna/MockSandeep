/* Copyright 2009 EB2 International Limited */
package it;

import it.testBase.IntegrationTestBase;

public class SessionITCase extends IntegrationTestBase
{
    @Override
    public void test() throws Exception
    {
        
        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setSessionId(generateSessionId());
        String response = getResponse("ShoppingApi_shop__SABREGDS_SSWIntellisell_1");
        assert response != null : "Response is null";
        response = getResponse("ShoppingApi_purchase__SABREGDS_SabreCommandLLS_1");
        assert response != null : "Response is null";
        response = getResponse("ShoppingApi_purchase__SABREGDS_SabreCommandLLS_2");
        assert response != null : "Response is null";
        
    }
}
