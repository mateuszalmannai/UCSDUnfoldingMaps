package module6;

import demos.Airport;

public class LinearSearchExample {

  public static String findAirportCodeMyVersion(String toFind, Airport[] airports) {
    String found = "No match";

    for (Airport airport : airports) {
      final int airportID = airport.getAirportID();
      final String airportIDString = String.valueOf(airportID);
      if (airportIDString.equals(toFind)) {
        found = airportIDString;
        break;
      }
    }

    return found;
  }

  public static String findAirportCode(String toFind, Airport[] airports) {
    int index = 0;
    while (index < airports.length) {
      Airport airport = airports[index];
      if (toFind.equals(airport.getCity())) {
        return airport.getCode3();
      }
      index++;
    }
    return "Not found";
  }


  /**
   * Binary search pseudo code:
   * initialize low = 0, high = size of list (length of array -1)
   * while ???:
   * mid = (high+low)/2
   * if the city to find equals the city at mid,
   * return the airport code
   * if the city is less than the city at mid
   * high = mid - 1
   * else low = mid + 1
   * <p/>
   * return a value to indicate not found
   */
  // to find a city name
  public static String findAirportCodeBS(String toFind, Airport[] airports) {
    int low = 0;
    int high = airports.length;
    int mid;
    while (low <= high) {
      mid = ((high - low) / 2);
      int compare = toFind.compareTo(airports[mid].getCity());
      if (compare < 0) {
        high = mid - 1;
      } else if (compare > 0) {
        low = mid + 1;
      } else {
        return airports[mid].getCode3();
      }
    }
    return null;
  }

  // selection sort
  public static void selectionSort(int[] values) {
    int indexMin;
    for (int i = 0; i < values.length - 1; i++) {
      indexMin = i;
      for (int j = i + 1; j < values.length; j++) {
        if (values[j] < values[indexMin]) {
          indexMin = j;
        }
      }
      swap(values, indexMin, i);

    }
  }

  // merge sort
  public static void mergeSort(int[] values) {
    int currentIndex;
    for (int pos = 1; pos < values.length; pos++) {
      currentIndex = pos;
      while (currentIndex > 0 && values[currentIndex] < values[currentIndex - 1]) {
        swap(values, currentIndex, currentIndex - 1);
        currentIndex = currentIndex - 1;
      }

    }
  }

  private static void swap(int[] values, int indexMin, int i) {

  }

}
