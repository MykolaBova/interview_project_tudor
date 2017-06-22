package com.gwt.test.server.config;

import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;
import com.google.maps.GeoApiContext;
import com.gwt.test.server.GooglePlacesFinder;
import com.gwt.test.server.PlacesFinder;
import com.gwt.test.server.TravelPlacesServiceImpl;

public class AppModule extends ServletModule {

    @Override
    protected void configureServlets() {
        super.configureServlets();
        bind(PlacesFinder.class).to(GooglePlacesFinder.class);
        serve("/travel/places").with(TravelPlacesServiceImpl.class);
    }

    @Provides public GeoApiContext getGoogleGeoApiContext(){
        return new GeoApiContext().setApiKey("google api key");
    }
}
