package com.gwt.test.server;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;
import com.gwt.test.client.TravelPlacesService;
import com.gwt.test.shared.Place;

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
