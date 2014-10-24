/* Copyright 2009 EB2 International Limited */
package it;

import it.testBase.IntegrationCompareTestBase;

import java.io.File;

public class SimpleCompareITCase extends IntegrationCompareTestBase
{

    @Override
    public void test() throws Exception
    {
        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setRunId("RunTest1");
        setSessionId(generateSessionId());
        String response = getResponse("ShoppingApi_shop__SABREGDS_SSWIntellisell_1");
        String oryginalResponse = readFile(new File(getBasicTestPath() + File.separator + "ShoppingApi_shop__SABREGDS_SSWIntellisell_1" + "_RS.xml"));
        assert oryginalResponse.equals(response) : "Oryginal response and response form server are different! ";
        assert (new File(getPath() + File.separator + "ShoppingApi_shop__SABREGDS_SSWIntellisell_1" + "_RS.xml")).exists() : "Compare directory was not create.";
    }

}
