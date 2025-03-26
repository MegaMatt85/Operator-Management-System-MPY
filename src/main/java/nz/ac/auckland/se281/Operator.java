package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class Operator {
  
  private String name;
  private Location location;
  private String id;
  // private String searchResult;

  public Operator(String name, Location location) {
    this.name = name;
    this.location = location;
    this.id = createId();
  }

  private String createId() {
    // Creates initials for the name
    String initials = "";
    String[] words = this.name.split(" "); // Creates an array for each word
    for (String word : words) { 
        initials = initials + word.charAt(0); // Creates a string of the first letter in each word
    }
    initials = initials.toUpperCase();

    // Creates Operator ID string
    String locationAbbr = this.location.getLocationAbbreviation();
    String operatorNum = "001"; // Change later
    return initials + "-" + locationAbbr + "-" + operatorNum;
  }

  public String getName() {
    return this.name;
  }

  public String getId() {
    return this.id;
  }

  public Location getLocation() {
    return this.location;
  }
}