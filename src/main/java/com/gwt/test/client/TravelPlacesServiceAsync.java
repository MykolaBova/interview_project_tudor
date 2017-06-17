package com.gwt.test.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwt.test.shared.Place;

import java.util.List;

public interface TravelPlacesServiceAsync {
    void findPlacesByCity(String city, AsyncCallback<List<Place>> async);
}
