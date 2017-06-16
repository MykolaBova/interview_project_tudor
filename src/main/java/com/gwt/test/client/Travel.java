package com.gwt.test.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

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

	//todo should be injected when using DI framework (impl should be on server-side)
	private PlacesService placesService = new PlacesServiceImplTest();

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		resetPlacesTable();

		findPlacesPanel.add(cityTextBox);
		findPlacesPanel.add(findPlacesButton);

		mainPanel.add(findPlacesPanel);
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
			List<Place> cityPlaces = placesService.findByCity(city);
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

	}

	private void resetPlacesTable() {
		placesTable.removeAllRows();
		placesTable.setText(0, 0, "Id");
		placesTable.setText(0, 1, "Name");
	}
}
