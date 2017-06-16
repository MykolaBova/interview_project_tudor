package com.gwt.test.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.gwt.test.client.Place;
import com.gwt.test.client.PlacesService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlacesServiceImpl extends RemoteServiceServlet implements PlacesService {

    private Map<String, List<Place>> citiesWithPlaces;

    public PlacesServiceImpl() {
        citiesWithPlaces = new HashMap<>();
        citiesWithPlaces.put("q", Arrays.asList(new Place("1", "piata mijlocie"), new Place("2", "bufetul central")));
        citiesWithPlaces.put("Oras gol", null);
        citiesWithPlaces.put("Copsa Mica", Arrays.asList(
                new Place("100", "PIATA mijlocie"),
                new Place("101", "retro-gara"),
                new Place("105", "Aprozar special de plante fine")));
    }

    @Override
    public List<Place> findByCity(String city) {
        return citiesWithPlaces.get(city);
    }
}
