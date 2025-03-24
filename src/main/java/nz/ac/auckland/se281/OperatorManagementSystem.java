package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;
import java.util.ArrayList;

public class OperatorManagementSystem {

  private ArrayList<Operator> savedOperators = new ArrayList<Operator>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    // Prints for no operators
    MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");

    // Prints for one operator
    // MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");

    // Prints for two or more operators found
    // MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(2), "s", ":");
  }

  public void createOperator(String operatorName, String location) {
    // Returns the location from the Locatiom enum, otherwise null
    Location locationFound = Location.fromString(location);

    // Does not create operator if operatorName is invalid
    if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

    // Does not create operator if location is invalid
    if (locationFound == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return;
    }

    // Creates initials for the operatorName
    String initials = "";
    String[] words = operatorName.split(" ");
    for (String word : words) { // Creates a string of the first letter in each word
        initials = initials + word.charAt(0);
    }
    initials = initials.toUpperCase();

    // Creates Operator ID
    String locationAbbr = locationFound.getLocationAbbreviation();
    String operatorNum = "001";
    String operatorId = initials + "-" + locationAbbr + "-" + operatorNum;

    String locationFull = locationFound.getFullName();

    // Creates an instance of the operator with its details
    Operator operator = new Operator(operatorName, operatorId, locationFound);
    this.savedOperators.add(operator);

    // Prints the operator created at the location
    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorId, locationFull);
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
}
