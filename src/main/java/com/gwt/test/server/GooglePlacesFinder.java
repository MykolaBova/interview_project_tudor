package com.gwt.test.server;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import org.apache.http.client.HttpClient;

import com.gwt.test.shared.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class GooglePlacesFinder implements PlacesFinder {
    private final Logger log = LoggerFactory.getLogger(GooglePlacesFinder.class);

    private GeoApiContext context;

    @Inject
    public GooglePlacesFinder(GeoApiContext context) {
        this.context = context;
    }

    @Override
    public List<Place> findPlacesByCity(String city) {
        try {
            PlacesSearchResponse placesSearchResponse = PlacesApi.nearbySearchQuery(context, getLatLongForCity(city)).radius(5000).await();
            return Arrays.stream(placesSearchResponse.results)
                    .map(placesSearchResult -> new Place(placesSearchResult.placeId, placesSearchResult.name))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GoogleAPICallException("Exception calling Google Places API", e);
        }
    }

    private LatLng getLatLongForCity(String city) {
        try {
            GeocodingResult[] geocodingResults = GeocodingApi.geocode(context, city).await();
            if (geocodingResults !=null && geocodingResults.length > 0) {
                System.out.println(geocodingResults[0]);
                return geocodingResults[0].geometry.location;
            } else {
                throw new UnknownLocationException(city);
            }
        } catch (ApiException | InterruptedException | IOException e) {
            throw new GoogleAPICallException("Exception calling Google Geocoding API", e);
        }
    }
}
