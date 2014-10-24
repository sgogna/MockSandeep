/* Copyright 2009 EB2 International Limited */
package it;

import fileManagers.InfoFileManager;
import it.testBase.IntegrationTestBase;

import java.util.HashMap;

import status.StatusCommunicationRequest;
import status.StatusCommunicationResponse;
import status.StatusCommunicationXMLSerializer;

public class EndTestRequestTect2ComparatorITCase extends IntegrationTestBase
{
    @Override
    public void test() throws Exception
    {

        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setRunId("CompareTect2ComparatorTest");
        setSessionId(generateSessionId());

        String response = getResponse("ShoppingApi_priceFeatures__SABREGDS_ChangeAAALLS_1");
        assert response != null : "Response for ShoppingApi_priceFeatures__SABREGDS_ChangeAAALLS_1 is null";

        response = getResponse("ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_1");
        assert response != null : "Response for ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_1 is null";

        StatusCommunicationRequest communicationRequest = new StatusCommunicationRequest();
        Boolean status = true;
        communicationRequest.setCorrect(status);
        communicationRequest.setComparator("Test2");
        
        String request = StatusCommunicationXMLSerializer.serializeRequest(communicationRequest);
        response = sendRequest(null, request, new HashMap<String, String>());
        StatusCommunicationResponse communicationResponse = StatusCommunicationXMLSerializer.deserializeResponse(response);
        assert !communicationResponse.isCorrect() : "Incorrect response for run CompareTect2ComparatorTest";

        assert InfoFileManager.getInfo(getPath(), InfoFileManager.COMPARE_ALGORITHM).equals("Test2") : "Incorrect " + InfoFileManager.COMPARE_ALGORITHM + " in .info file";
        assert InfoFileManager.getInfo(getPath(), InfoFileManager.COMPARE_RESULT).equals(String.valueOf(communicationResponse.isCorrect())) : "Incorrect " + InfoFileManager.COMPARE_RESULT + " in .info file";

    }
}
