package com.gwt.test.server;

import com.gwt.test.shared.Place;

import java.util.List;

public interface PlacesFinder {

    public List<Place> findPlacesByCity(String city);
}
