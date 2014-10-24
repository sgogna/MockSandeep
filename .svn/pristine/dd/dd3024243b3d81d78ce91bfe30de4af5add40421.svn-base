/* Copyright 2009 EB2 International Limited */
package it;

import fileManagers.InfoFileManager;
import it.testBase.IntegrationTestBase;

import java.util.HashMap;

import status.StatusCommunicationRequest;
import status.StatusCommunicationXMLSerializer;

public class EndTestRequestIncorrectCompareAlgorithmITCase extends IntegrationTestBase
{

    @Override
    public void test() throws Exception
    {
        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setRunId("CompareIncorrectCompareAlgorithmEndTest");
        setSessionId(generateSessionId());

        String response = getResponse("ShoppingApi_priceFeatures__SABREGDS_ChangeAAALLS_1");
        assert response != null : "Response for ShoppingApi_priceFeatures__SABREGDS_ChangeAAALLS_1 is null";

        response = getResponse("ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_1");
        assert response != null : "Response for ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_1 is null";


        StatusCommunicationRequest communicationRequest = new StatusCommunicationRequest();
        Boolean status = false;
        communicationRequest.setCorrect(status);
        communicationRequest.setComparator("nonExistComparator");
        String failureReason = "UNKNOWN";
        communicationRequest.setFailureReason(failureReason);
        String request = StatusCommunicationXMLSerializer.serializeRequest(communicationRequest);
        try {
            response = sendRequest(null, request, new HashMap<String, String>());
            assert true : "Bad request.";
        } catch( Exception e) {
            
        }
        assert InfoFileManager.getInfo(getPath(), InfoFileManager.COMPARE_ALGORITHM).equals("") : "Incorrect " + InfoFileManager.COMPARE_ALGORITHM + " in .info file";
    }

}
