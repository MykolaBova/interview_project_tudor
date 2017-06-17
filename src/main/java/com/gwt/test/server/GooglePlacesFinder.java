package com.gwt.test.server;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.maps.GeoApiContext;
import org.apache.http.client.HttpClient;

import com.gwt.test.shared.Place;

@Singleton
public class GooglePlacesFinder implements PlacesFinder {

    GeoApiContext context = new GeoApiContext().setApiKey("");

    @Inject
    public GooglePlacesFinder(HttpClient httpClient) {

    }

    @Override
    public List<Place> findPlacesByCity(String city) {


        return null;
    }
}
