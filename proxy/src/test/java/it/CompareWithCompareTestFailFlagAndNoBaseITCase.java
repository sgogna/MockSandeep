/* Copyright 2009 EB2 International Limited */
package it;

import fileManagers.InfoFileManager;
import it.testBase.IntegrationCompareTestBase;

import java.util.HashMap;

import status.StatusCommunicationRequest;
import status.StatusCommunicationResponse;
import status.StatusCommunicationXMLSerializer;

public class CompareWithCompareTestFailFlagAndNoBaseITCase extends IntegrationCompareTestBase
{
    @Override
    public void test() throws Exception
    {
        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setRunId("RunTest1");
        setSessionId(generateSessionId());
        String response = getResponse("ShoppingApi_priceFeatures__SABREGDS_ChangeAAALLS_1", true);

        response = getResponse("ShoppingApi_priceFeatures__SABREGDS_IgnoreTransactionLLS_1", true);

        StatusCommunicationRequest communicationRequest = new StatusCommunicationRequest();
        Boolean status = true;
        communicationRequest.setCorrect(status);
        communicationRequest.setComparator("Test1");
        communicationRequest.setFlowDiffFailsTest(true);

        String request = StatusCommunicationXMLSerializer.serializeRequest(communicationRequest);
        response = sendRequest(null, request, new HashMap<String, String>());
        StatusCommunicationResponse communicationResponse = StatusCommunicationXMLSerializer.deserializeResponse(response);
        assert communicationResponse.isCorrect() == true : "Incorrect comparison result." ;
        assert InfoFileManager.getInfo(getPath(), InfoFileManager.TEST_EXECUTION_STATUS).equals(String.valueOf(true)) : "Incorrect \"" + InfoFileManager.TEST_EXECUTION_STATUS + "\" in .info file";
        
    }
}
