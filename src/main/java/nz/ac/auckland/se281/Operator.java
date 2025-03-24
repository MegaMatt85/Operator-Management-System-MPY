package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class Operator {
  
  private String name;
  private String operatorId;
  private Location location;
  private String searchResult;

  public Operator(String name, String operatorId, Location location) {
    this.name = name;
    this.operatorId = operatorId;
    this.location = location;
    this.searchResult = "* " + this.name + " ('" + this.operatorId + "' located in '" 
      + this.location.getNameEnglish() + " | " + this.location.getNameTeReo() + "')";
  }

  public String getSearchResult() {
    return this.searchResult;
  }
}