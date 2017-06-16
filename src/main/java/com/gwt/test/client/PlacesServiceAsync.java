package com.gwt.test.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

public interface PlacesServiceAsync {
    void findByCity(String city, AsyncCallback<List<Place>> async);
}
