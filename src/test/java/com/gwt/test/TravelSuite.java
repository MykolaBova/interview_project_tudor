package com.gwt.test;

import com.gwt.test.client.TravelTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TravelSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for Travel");
    suite.addTestSuite(TravelTest.class);
    return suite;
  }
}
