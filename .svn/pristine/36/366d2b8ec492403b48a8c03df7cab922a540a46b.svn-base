/* Copyright 2009 EB2 International Limited */
package it;

import it.testBase.IntegrationTestBase;

public class CountingDiffApiITCase extends IntegrationTestBase
{
    @Override
    public void test() throws Exception
    {

        setTestId(this.getClass().getSimpleName());
        setAirline("Test");
        setSessionId(generateSessionId());
        String response = getResponse("SeatMapApi_getPricedSeatMaps__SABREGDS_SabreCommandLLS_1");
        assert response != null : "Response for SeatMapApi_getPricedSeatMaps__SABREGDS_SabreCommandLLS_1 is null";
        assert response.equals("SeatMapApi_getPricedSeatMaps") : "Response and exspected response for SeatMapApi_getPricedSeatMaps__SABREGDS_SabreCommandLLS_1 are different!\n Expected: SeatMapApi_getPricedSeatMaps, received: "
                + response;

        response = getResponse("ShoppingApi_priceFeatures__SABREGDS_SabreCommandLLS_1");
        assert response != null : "Response for ShoppingApi_priceFeatures__SABREGDS_SabreCommandLLS_1 is null";
        assert response.equals("ShoppingApi_priceFeatures") : "Response and exspected response for ShoppingApi_priceFeatures__SABREGDS_SabreCommandLLS_1 are different!\n Expected: ShoppingApi_priceFeatures, received: "
                + response;

        response = getResponse("ShoppingApi_purchase__SABREGDS_SabreCommandLLS_1");
        assert response != null : "Response for ShoppingApi_purchase__SABREGDS_SabreCommandLLS_1 is null";
        assert response.equals("ShoppingApi_purchase") : "Response and exspected response for ShoppingApi_purchase__SABREGDS_SabreCommandLLS_1 are different!\n Expected: ShoppingApi_purchase, received: " + response;

    }
}
