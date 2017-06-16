package com.gwt.test.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("places")
public interface PlacesService extends RemoteService {

    List<Place> findByCity(String city);
}
