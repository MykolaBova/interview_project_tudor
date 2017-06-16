package com.gwt.test.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlacesServiceImplTest implements PlacesService {

    private Map<String, List<Place>> citiesWithPlaces;

    public PlacesServiceImplTest() {
        citiesWithPlaces = new HashMap<>();
        citiesWithPlaces.put("Pascani", Arrays.asList(new Place("1", "piata mijlocie"), new Place("2", "bufetul central")));
        citiesWithPlaces.put("Oras gol", null);
        citiesWithPlaces.put("Copsa Mica", Arrays.asList(
                new Place("100", "piata mijlocie"),
                new Place("101", "retro-gara"),
                new Place("102", "Aprozar special de plante fine")));
    }

    @Override
    public List<Place> findByCity(String city) {
        return citiesWithPlaces.get(city);
    }
}
