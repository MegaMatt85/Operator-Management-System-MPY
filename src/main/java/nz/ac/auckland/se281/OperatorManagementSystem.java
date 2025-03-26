package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;
import java.util.ArrayList;

public class OperatorManagementSystem {

  private ArrayList<Operator> savedOperators = new ArrayList<Operator>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    // Array of indexes for each operator found in savedOperators
    ArrayList<Integer> OperatorFoundIndexes = new ArrayList<Integer>();

    keyword = keyword.toLowerCase();

    // Loops through savedOperators, storing the index of which operators have been found
    for (int i = 0; i < savedOperators.size(); i++) {
      // Strings for the current operator's name, english location, te reo location, and abbreviated locaiton
      String operatorName = savedOperators.get(i).getName().toLowerCase();
      String operatorLocationEng = savedOperators.get(i).getLocation().getNameEnglish().toLowerCase();
      String operatorLocationTeReo = savedOperators.get(i).getLocation().getNameTeReo().toLowerCase();
      String operatorLocationAbbr = savedOperators.get(i).getLocation().getLocationAbbreviation().toLowerCase();

      // Checks if any of the strings contain the keyword or if the keyword is "*"
      if (keyword.equals("*") || operatorName.contains(keyword) || operatorLocationEng.contains(keyword)
        || operatorLocationTeReo.contains(keyword) || operatorLocationAbbr.contains(keyword)) {
        OperatorFoundIndexes.add(i);
      }
    }

    if (OperatorFoundIndexes.size() == 0) { // Prints for no operators
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");

    } else if (OperatorFoundIndexes.size() == 1) {// Prints for one operator
       MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");

       // Reference to the one operator found
       Operator OperatorFound = savedOperators.get(OperatorFoundIndexes.get(0));

       // Prints the operator and their location
       System.out.println("* " + OperatorFound.getName() + " ('" + OperatorFound.getId() 
        + "' located in '" + OperatorFound.getLocation().getFullName() + "')");

    } else { // Prints for two or more operators found
      MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(OperatorFoundIndexes.size()), "s", ":");
      
      // Prints the operator and their location for every operator found
      for (int i = 0; i < OperatorFoundIndexes.size(); i++) {
        // Reference to the current operator found
        Operator OperatorFound = savedOperators.get(OperatorFoundIndexes.get(i));

        // Prints the operator and their location
        System.out.println("* " + OperatorFound.getName() + " ('" + OperatorFound.getId() + "' located in '" 
        + OperatorFound.getLocation().getFullName() + "')");
      }
    }
  }

  public void createOperator(String operatorName, String location) {
    // Returns the location from the Locatiom enum, otherwise null
    Location locationFound = Location.fromString(location);

    // Checks if the operator is valid to be created
    if (isOperatorValid(operatorName, location, locationFound) == true) {
      // Creates an instance of the operator with its details
      Operator operator = new Operator(operatorName, locationFound);
      this.savedOperators.add(operator);

      // Prints the name of the operator created and operator ID and the location
      MessageCli.OPERATOR_CREATED.printMessage(operatorName, operator.getId(), operator.getLocation().getFullName());
    }
  }

  public void viewActivities(String operatorId) {
    // TODO implement
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // TODO implement
  }

  public void searchActivities(String keyword) {
    // TODO implement
  }

  public void addPublicReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addPrivateReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addExpertReview(String activityId, String[] options) {
    // TODO implement
  }

  public void displayReviews(String activityId) {
    // TODO implement
  }

  public void endorseReview(String reviewId) {
    // TODO implement
  }

  public void resolveReview(String reviewId, String response) {
    // TODO implement
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // TODO implement
  }

  public void displayTopActivities() {
    // TODO implement
  }

  public Boolean isOperatorValid(String operatorName, String location, Location locationFound) {
    // Does not create operator if operatorName is invalid
    if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return false;
    }

    // Does not create operator if location is invalid
    if (locationFound == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return false;
    }

    // Does not create operator if an operator with the same name already exists at the same location
    for (Operator operator : savedOperators) {
      if (operator.getName().equals(operatorName) 
        && operator.getLocation().getLocationAbbreviation().equals(locationFound.getLocationAbbreviation())) {
          MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(operatorName, locationFound.getFullName());
          return false;
      }
    }

    // If operator is valid
    return true;
  }
}
