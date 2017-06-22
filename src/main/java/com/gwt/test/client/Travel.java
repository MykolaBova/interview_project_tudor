package com.gwt.test.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwt.test.shared.Place;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Travel implements EntryPoint {
	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable placesTable = new FlexTable();
	private HorizontalPanel findPlacesPanel = new HorizontalPanel();
	private SuggestBox cityTextBox = new SuggestBox();
	private Button findPlacesButton = new Button("Find places");
	private Label cityLabel = new Label();
	private Label errorLabel = new Label();

	private TravelPlacesServiceAsync travelService;

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {

		findPlacesPanel.add(cityTextBox);
		findPlacesPanel.add(findPlacesButton);
		errorLabel.addStyleName("serverResponseLabelError");
		findPlacesPanel.add(errorLabel);

		mainPanel.add(findPlacesPanel);
		placesTable.addStyleName("placesTable");
		mainPanel.add(placesTable);
		mainPanel.add(cityLabel);

		RootPanel.get("city-places").add(mainPanel);

		cityTextBox.setFocus(true);

		addFindPlacesHandlers();
	}

	private void addFindPlacesHandlers() {
		findPlacesButton.addClickHandler(clickEvent -> findPlaces());

		cityTextBox.addKeyDownHandler(keyEvent -> {
			if (keyEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				findPlaces();
			}
		});
	}

	private void findPlaces() {
		String city = cityTextBox.getText().trim();
		if (!city.isEmpty()){
			resetPlacesTable();

            // Initialize the service proxy. //todo can this be injected when using DI
            if (travelService == null) {
                travelService = GWT.create(TravelPlacesService.class);
            }

            // Make the call to the stock price service.
            travelService.findPlacesByCity(city, createFindPlacesCallback(city));
		}

	}

	private AsyncCallback<List<Place>> createFindPlacesCallback(String city) {
		return new AsyncCallback<List<Place>>() {
			public void onFailure(Throwable caught) {
				errorLabel.setText(caught.getMessage());
			}

			@Override
			public void onSuccess(List<Place> cityPlaces) {
				errorLabel.setText("");
				updatePlaces(cityPlaces, city);
			}

		};
	}

    private void updatePlaces(List<Place> cityPlaces, String city) {
        if (cityPlaces !=null) {
            cityPlaces.forEach(place -> {
                int row = placesTable.getRowCount();
                placesTable.setText(row, 0, place.getId());
                placesTable.setText(row, 1, place.getName());
            });

            cityLabel.setText("Showing places for " + city);
        } else {
            placesTable.removeAllRows();
            cityLabel.setText("No valid city or no places " + city);

        }
    }

    private void resetPlacesTable() {
		placesTable.removeAllRows();
		placesTable.setText(0, 0, "Id");
		placesTable.setText(0, 1, "Name");
		placesTable.getRowFormatter().addStyleName(0,"tableHeader");
	}
}
