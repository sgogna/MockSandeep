/* Copyright 2009 EB2 International Limited */
package it;

import fileManagers.InfoFileManager;
import it.testBase.IntegrationCompareTestBase;

import java.util.HashMap;

import status.StatusCommunicationRequest;
import status.StatusCommunicationResponse;
import status.StatusCommunicationXMLSerializer;

public class CompareWithCompareTestFailFlagITCase extends IntegrationCompareTestBase
{
    @Override
    public void test() throws Exception
    {
        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setRunId("RunTest1");
        setSessionId(generateSessionId());
        String response = getResponse("ShoppingApi_priceFeatures__SABREGDS_ChangeAAALLS_1");
        assert response != null : "Response for ShoppingApi_priceFeatures__SABREGDS_ChangeAAALLS_1 is null";

        response = getResponse("ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_1");
        assert response != null : "Response for ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_1 is null";

        StatusCommunicationRequest communicationRequest = new StatusCommunicationRequest();
        Boolean status = true;
        communicationRequest.setCorrect(status);
        communicationRequest.setComparator("Test1");
        communicationRequest.setFlowDiffFailsTest(true);

        String request = StatusCommunicationXMLSerializer.serializeRequest(communicationRequest);
        response = sendRequest(null, request, new HashMap<String, String>());
        StatusCommunicationResponse communicationResponse = StatusCommunicationXMLSerializer.deserializeResponse(response);
        assert communicationResponse.isCorrect() == false : "Incorrect comparison result." ;
        assert InfoFileManager.getInfo(getPath(), InfoFileManager.TEST_EXECUTION_STATUS).equals(String.valueOf(false)) : "Incorrect \"" + InfoFileManager.TEST_EXECUTION_STATUS + "\" in .info file";
        
    }
}
