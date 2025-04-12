package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class Operator {
  
  private String name;
  private Location location;
  private int operatorNum;
  private String id;

  public Operator(String name, Location location, int operatorNum) {
    this.name = name;
    this.location = location;
    this.operatorNum = operatorNum;
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

    // Turns operatorNum into the 3-digit number using leading 0s
    String operatorNum = "00" + Integer.toString(this.operatorNum);
    operatorNum = operatorNum.substring(operatorNum.length() - 3);

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

  public void printOperator() {
    System.out.println("* " + name + " ('" + id 
        + "' located in '" + location.getFullName() + "')");
  }
}