/* Copyright 2009 EB2 International Limited */
package it;

import it.testBase.IntegrationTestBase;

public class DateReplace1ITCase extends IntegrationTestBase
{
    @Override
    public void test() throws Exception
    {

        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setSessionId(generateSessionId());
        String response = getResponse("ShoppingApi_shop__SABREGDS_SSWIntellisell_ToSend_1");
        assert response != null : "Response for ShoppingApi_shop__SABREGDS_SSWIntellisell is null";
        assert response.equals(readFileFromTestFolder("ShoppingApi_shop__SABREGDS_SSWIntellisell_Valid_RS.xml")) : "Response and exspected response for ShoppingApi_shop__SABREGDS_SSWIntellisell are different!";

        response = getResponse("ShoppingApi_purchase__SABREGDS_OTA_AirBookLLS_1");
        assert response != null : "Response for ShoppingApi_purchase__SABREGDS_OTA_AirBookLLS is null";

        assert response.equals(readFileFromTestFolder("ShoppingApi_purchase__SABREGDS_OTA_AirBookLLS_Valid_RS.xml")) : "Response and exspected response for ShoppingApi_purchase__SABREGDS_OTA_AirBookLLS are different!";
    }

}
