package com.gwt.test.client;

import com.gwt.test.shared.FieldVerifier;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.gwt.test.shared.Place;

import java.util.List;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class TravelTest extends GWTTestCase {

  /**
   * Must refer to a valid module that sources this class.
   */
  public String getModuleName() {
    return "com.gwt.test.TravelJUnit";
  }

  /**
   * Tests the FieldVerifier.
   */
  public void testFieldVerifier() {
    assertFalse(FieldVerifier.isValidName(null));
    assertFalse(FieldVerifier.isValidName(""));
    assertFalse(FieldVerifier.isValidName("a"));
    assertFalse(FieldVerifier.isValidName("ab"));
    assertFalse(FieldVerifier.isValidName("abc"));
    assertTrue(FieldVerifier.isValidName("abcd"));
  }

  /**
   * This test will send a request to the server using the greetServer method in
   * GreetingService and verify the response.
   */
  public void testTravelService() {
    // Create the service that we will test.
    TravelPlacesServiceAsync travelService = GWT.create(TravelPlacesService.class);
    ServiceDefTarget target = (ServiceDefTarget) travelService;
    target.setServiceEntryPoint(GWT.getModuleBaseURL() + "travel/places");

    // Since RPC calls are asynchronous, we will need to wait for a response
    // after this test method returns. This line tells the test runner to wait
    // up to 10 seconds before timing out.
    delayTestFinish(10000);

    // Send a request to the server.
    travelService.findPlacesByCity("Copsa Mica", new AsyncCallback<List<Place>>() {
      public void onFailure(Throwable caught) {
        // The request resulted in an unexpected error.
        fail("Request failure: " + caught.getMessage());
      }

      public void onSuccess(List<Place> cityPlaces) {
        // Verify that the response is correct.
        assertEquals(cityPlaces.size(), 3);

        // Now that we have received a response, we need to tell the test runner
        // that the test is complete. You must call finishTest() after an
        // asynchronous test finishes successfully, or the test will time out.
        finishTest();
      }
    });
  }


}
