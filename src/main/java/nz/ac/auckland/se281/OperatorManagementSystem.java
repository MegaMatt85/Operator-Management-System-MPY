package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;
import java.util.ArrayList;

public class OperatorManagementSystem {

  private ArrayList<Operator> savedOperators = new ArrayList<Operator>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

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

    return true;
  }

  public void searchOperators(String keyword) {
    // Goes through savedOperators counting how many of them include
    int operatorsFound = 0;
    // String foundOperator = "";
    for (int i = 0; i < savedOperators.size(); i++) {
      if (keyword.equals("*")) {
        operatorsFound++;
      }
    }

    if (operatorsFound == 0) {
      // Prints for no operators
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else if (operatorsFound == 1) {
      // Prints for one operator
       MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
       System.out.println("* " + savedOperators.get(0).getName() + " ('" + savedOperators.get(0).getId() + "' located in '" 
         + savedOperators.get(0).getLocationFull() + "')");
    } else {
      // Prints for two or more operators found
      MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(2), "s", ":");
    }
  }

  public void createOperator(String operatorName, String location) {
    // Returns the location from the Locatiom enum, otherwise null
    Location locationFound = Location.fromString(location);

    if (isOperatorValid(operatorName, location, locationFound) == true) {
      // Creates an instance of the operator with its details
      Operator operator = new Operator(operatorName, locationFound);
      this.savedOperators.add(operator);

      // Prints the name of the operator created and operator ID and the location
      MessageCli.OPERATOR_CREATED.printMessage(operatorName, operator.getId(), operator.getLocationFull());
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
}
