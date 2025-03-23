package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

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
    // Finds the maori and abbreviated name for the location unless invalid
    Location locationFound = Location.fromString(location);

    // Creates initials for the operatorName
    String initials = "";
    String[] words = operatorName.split(" ");

    for (String word : words) {
        initials = initials + word.charAt(0);
    }

    initials = initials.toUpperCase();

    // A string of the abbreviated location name
    String locationAbbr = locationFound.getLocationAbbreviation();

    // A string for the operator number in the location
    String operatorNum = "001";

    // A string of the full location name
    String locationFull = locationFound.getFullName();

    String operatorId = initials + "-" + locationAbbr + "-" + operatorNum;

    // Prints creating an operator at the location
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
