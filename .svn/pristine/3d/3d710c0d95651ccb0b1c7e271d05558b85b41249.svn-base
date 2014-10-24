/* Copyright 2009 EB2 International Limited */
package it;

import it.testBase.IntegrationTestBase;

import java.util.ArrayList;
import java.util.List;

public class MultiSessionITCase extends IntegrationTestBase
{
    @Override
    public void test() throws Exception
    {

        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        String session1 = generateSessionId();
        String session2 = generateSessionId();
        String session3 = generateSessionId();
        
        String request1 = "ShoppingApi_priceFeatures__SABREGDS_ChangeAAALLS_1";
        String request2 = "ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_1";
        String request3 = "ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_2";
        
        String response1 = "ChangeAAALLS_1";
        String response2 = "IgnoreTransactionLLS_1";
        String response3 = "IgnoreTransactionLLS_2";

        List<Pair> pairs = new ArrayList<MultiSessionITCase.Pair>();
        pairs.add(new Pair(session1, request1, response1));
        pairs.add(new Pair(session1, request2, response2));
        pairs.add(new Pair(session2, request2, response2));
        pairs.add(new Pair(session3, request1, response1));
        pairs.add(new Pair(session2, request3, response3));
        pairs.add(new Pair(session3, request2, response2));
        pairs.add(new Pair(session3, request3, response3));
        pairs.add(new Pair(session2, request1, response1));
        pairs.add(new Pair(session1, request3, response3));

        for (Pair pair : pairs)
        {
            setSessionId(pair.session);
            String response = getResponse(pair.request);
            assert response != null : "Response for " + pair.request + " is null";
            assert response.equals(pair.response) : "Response and exspected response for " + pair.request + " are different! Exspected: " + pair.response + " Received: " + response;
        }

    }

    class Pair
    {
        String session;
        String request;
        String response;

        public Pair(String session, String request, String response)
        {
            this.session = session;
            this.request = request;
            this.response = response;
        }

    }

}
