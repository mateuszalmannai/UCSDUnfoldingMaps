package module2;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LifeExpectancy extends PApplet {

  UnfoldingMap map;
  // String: CountryID, Float: lifeExpectancy
  Map<String, Float> lifeExpectancyByCountry;
  List<Feature> countries;
  List<Marker> countryMarkers;


  @Override
  public void setup() {
    size(800, 600, OPENGL);
    map = new UnfoldingMap(this, 50, 70, 700, 500, new Google.GoogleMapProvider());

    // Allow user to interact with map in limited ways
    // i.e. double-clicking leads to zooming, etc.
    MapUtils.createDefaultEventDispatcher(this, map);

    lifeExpectancyByCountry = loadLifeExpectancyFromCSV("/Users/mateusz/IdeaProjects/UCSDUnfoldingMaps/data/LifeExpectancyWorldBankModule3.csv");

    // One Feature and one Marker per country
    countries = GeoJSONReader.loadData(this, "/Users/mateusz/IdeaProjects/UCSDUnfoldingMaps/data/countries.geo.json");
    countryMarkers = MapUtils.createSimpleMarkers(countries);

    map.addMarkers(countryMarkers);
    shadeCountries();
  }

  private void shadeCountries() {
    for (Marker marker : countryMarkers) {
      final String countryId = marker.getId();

      // check that map has values contained in worldbank file
      if(lifeExpectancyByCountry.containsKey(countryId)){
        final Float lifeExpectancy = lifeExpectancyByCountry.get(countryId);
        // map method maps age range to color code range
        final int colorLevel = (int) map(lifeExpectancy, 40, 90, 10, 255);
        marker.setColor(color(255-colorLevel, 100, colorLevel));
      } else {
        // use default color
        marker.setColor(color(150,150,150));
      }

    }
  }

  @Override
  public void draw() {
    map.draw();
  }

  private Map<String, Float> loadLifeExpectancyFromCSV(String fileName) {
    Map<String, Float> lifeExpectancyMap = new HashMap<>();
    String[] rows = loadStrings(fileName);
    for (String row : rows) {
      String[] columns = row.split(",");
      if (columns.length == 6 && !columns[5].equals("..")) {
        float value = Float.parseFloat(columns[5]);
        lifeExpectancyMap.put(columns[4], value);
      }
    }
    return lifeExpectancyMap;
  }
}
