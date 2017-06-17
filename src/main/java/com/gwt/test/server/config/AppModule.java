package com.gwt.test.server.config;

import com.gwt.test.server.GooglePlacesFinder;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;
import com.gwt.test.server.DummyPlacesFinder;
import com.gwt.test.server.PlacesFinder;
import com.gwt.test.server.TravelPlacesServiceImpl;

public class AppModule extends ServletModule {

    @Override
    protected void configureServlets() {
        super.configureServlets();
        bind(PlacesFinder.class).to(DummyPlacesFinder.class);
        serve("/travel/places").with(TravelPlacesServiceImpl.class);
    }

    @Provides public HttpClient getHttpClient(){
        return HttpClientBuilder.create().build();
    }
}
