/* Copyright 2009 EB2 International Limited */
package it;

import it.testBase.IntegrationCompareTestBase;

import java.io.File;
import java.util.HashMap;

import status.StatusCommunicationRequest;
import status.StatusCommunicationResponse;
import status.StatusCommunicationXMLSerializer;

public class CompareWithNoBaseTestPassedITCase extends IntegrationCompareTestBase
{
    @Override
    public void test() throws Exception
    {
        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setRunId("RunTest1");
        setSessionId(generateSessionId());
        String response = getResponse("ShoppingApi_shop__SABREGDS_SSWIntellisell_1", true);

        StatusCommunicationRequest communicationRequest = new StatusCommunicationRequest();
        Boolean status = true;
        communicationRequest.setCorrect(status);
        communicationRequest.setComparator("Test1");

        String request = StatusCommunicationXMLSerializer.serializeRequest(communicationRequest);
        response = sendRequest(null, request, new HashMap<String, String>());
        StatusCommunicationResponse communicationResponse = StatusCommunicationXMLSerializer.deserializeResponse(response);

        assert communicationResponse.isCorrect() : "Incorrect response for run CompareWithNoBaseTestPassedITCase";

        assert (new File(getPath() + File.separator + "ShoppingApi_shop__SABREGDS_SSWIntellisell_1" + "_RS.xml")).exists() : "Compare directory was not create.";
        assert (new File(getBasicTestPath() + File.separator + "ShoppingApi_shop__SABREGDS_SSWIntellisell_1" + "_RS.xml")).exists() : "Files form compare folder was not copy to base.";
    }
}
