package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class Operator {
  
  private String name;
  private String operatorId;
  private Location location;

  public Operator(String name, String operatorId, Location location) {
    this.name = name;
    this.operatorId = operatorId;
    this.location = location;
  }
}