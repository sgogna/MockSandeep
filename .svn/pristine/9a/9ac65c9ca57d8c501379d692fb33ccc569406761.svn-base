/* Copyright 2009 EB2 International Limited */
package it;

import fileManagers.InfoFileManager;
import it.testBase.IntegrationTestBase;

import java.util.HashMap;

import status.StatusCommunicationRequest;
import status.StatusCommunicationResponse;
import status.StatusCommunicationXMLSerializer;

public class EndTestRequestTrueITCase extends IntegrationTestBase
{
    @Override
    public void test() throws Exception
    {

        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setRunId("CompareTrueEndTest");
        setSessionId(generateSessionId());

        String response = getResponse("ShoppingApi_priceFeatures__SABREGDS_ChangeAAALLS_1");
        assert response != null : "Response for ShoppingApi_priceFeatures__SABREGDS_ChangeAAALLS_1 is null";

        response = getResponse("ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_1");
        assert response != null : "Response for ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_1 is null";

        response = getResponse("ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_2");
        assert response != null : "Response for ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_2 is null";

        StatusCommunicationRequest communicationRequest = new StatusCommunicationRequest();
        Boolean status = true;
        communicationRequest.setCorrect(status);
        String request = StatusCommunicationXMLSerializer.serializeRequest(communicationRequest);
        response = sendRequest(null, request, new HashMap<String, String>());
        StatusCommunicationResponse communicationResponse = StatusCommunicationXMLSerializer.deserializeResponse(response);
        assert communicationResponse.isCorrect() : "Incorrect response for run CompareTrueEndTest";

        assert InfoFileManager.getInfo(getPath(), InfoFileManager.TEST_EXECUTION_STATUS).equals(status.toString()) : "Incorrect " + InfoFileManager.TEST_EXECUTION_STATUS + " in .info file";
        assert InfoFileManager.getInfo(getPath(), InfoFileManager.FAILURE_REASON).equals("N/A") : "Incorrect " + InfoFileManager.FAILURE_REASON + " in .info file";
        assert InfoFileManager.getInfo(getPath(), InfoFileManager.COMPARE_ALGORITHM).equals("Compare dirs") : "Incorrect " + InfoFileManager.COMPARE_ALGORITHM + " in .info file";
        assert InfoFileManager.getInfo(getPath(), InfoFileManager.COMPARE_RESULT).equals(String.valueOf(communicationResponse.isCorrect())) : "Incorrect " + InfoFileManager.COMPARE_RESULT + " in .info file";

    }
}
