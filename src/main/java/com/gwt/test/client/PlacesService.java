package com.gwt.test.client;

import java.util.List;

public interface PlacesService {

    List<Place> findByCity(String city);
}
