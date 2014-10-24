/* Copyright 2009 EB2 International Limited */
package it;

import it.testBase.IntegrationTestBase;

public class CountingOneApiITCase extends IntegrationTestBase
{
    @Override
    public void test() throws Exception
    {

        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setSessionId(generateSessionId());
        for (int i = 0; i < 7; i++)
        {
            String name = "ShoppingApi_purchase__SABREGDS_AddRemarkLLS_" + (i + 1);
            String response = getResponse(name);
            assert response != null : "Response for " + name + " is null";
            assert response.equals("R" + (i + 1)) : "Response and exspected response for " + name + " are different! Expected: R" + (i + 1) + ", received: " + response;
        }

    }
}
