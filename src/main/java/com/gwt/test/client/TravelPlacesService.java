package com.gwt.test.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.gwt.test.shared.Place;

import java.util.List;

@RemoteServiceRelativePath("places")
public interface TravelPlacesService extends RemoteService {

    List<Place> findPlacesByCity(String city);
}
