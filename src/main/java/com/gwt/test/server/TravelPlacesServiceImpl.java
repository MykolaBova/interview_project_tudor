package com.gwt.test.server;

import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;
import com.gwt.test.shared.Place;
import com.gwt.test.client.TravelPlacesService;

import javax.inject.Inject;

import java.util.List;

@Singleton
public class TravelPlacesServiceImpl extends RemoteServiceServlet implements TravelPlacesService {

    private PlacesFinder placesFinder;

    @Inject
    public TravelPlacesServiceImpl(PlacesFinder placesFinder) {
        this.placesFinder = placesFinder;
    }

    @Override
    public List<Place> findPlacesByCity(String city) {
        List<Place> placesByCity = placesFinder.findPlacesByCity(city);

        //todo merge with db data
        return placesByCity;
    }
}
