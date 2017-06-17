package com.gwt.test.server;

import com.gwt.test.shared.Place;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class DummyPlacesFinder implements PlacesFinder {

    private Map<String, List<Place>> citiesWithPlaces;

    @Inject
    public DummyPlacesFinder() {
        citiesWithPlaces = new HashMap<>();
        citiesWithPlaces.put("q", Arrays.asList(new Place("1", "piata mijlocie"), new Place("2", "bufetul central")));
        citiesWithPlaces.put("Oras gol", null);
        citiesWithPlaces.put("Copsa Mica", Arrays.asList(
                new Place("100", "PIATA mijlocie"),
                new Place("101", "retro-gara"),
                new Place("105", "Aprozar special de plante fine")));
    }

    @Override
    public List<Place> findPlacesByCity(String city) {
        return citiesWithPlaces.get(city);
    }
}
