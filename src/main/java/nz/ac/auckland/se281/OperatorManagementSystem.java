package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;
import java.util.ArrayList;

public class OperatorManagementSystem {

  private ArrayList<Operator> savedOperators = new ArrayList<Operator>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {}

  public void searchOperators(String keyword) {
    // Array of indexes for each operator found in savedOperators
    ArrayList<Integer> operatorFoundIndexes = new ArrayList<Integer>();

    keyword = keyword.toLowerCase().trim();

    // Loops through savedOperators, storing the index of which operators have been found
    for (int i = 0; i < savedOperators.size(); i++) {
      Operator currentOperator = savedOperators.get(i);
      // Strings for the current operator's name, and english, te reo, and abbr location
      String operatorName = currentOperator.getName().toLowerCase();
      String operatorLocationEng = currentOperator.getLocation().getNameEnglish().toLowerCase();
      String operatorLocationTeReo = currentOperator.getLocation().getNameTeReo().toLowerCase();
      String operatorLocationAbbr = currentOperator.getLocation().getLocationAbbreviation().toLowerCase();
      // Checks if any of the strings contain the keyword or if the keyword is "*"
      if (keyword.equals("*") || operatorName.contains(keyword) 
        || operatorLocationEng.contains(keyword) || operatorLocationTeReo.contains(keyword) 
        || operatorLocationAbbr.contains(keyword)) {
        operatorFoundIndexes.add(i);
      }
    }

    if (operatorFoundIndexes.size() == 0) { // Prints for no operators
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");

    } else if (operatorFoundIndexes.size() == 1) { // Prints for one operator
      MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      // Reference to the one operator found
      Operator operatorFound = savedOperators.get(operatorFoundIndexes.get(0));
      // Prints the operator and their location
      System.out.println("* " + operatorFound.getName() + " ('" + operatorFound.getId() 
        + "' located in '" + operatorFound.getLocation().getFullName() + "')");

    } else { // Prints for the total number of operators found if there are two or more
      MessageCli.OPERATORS_FOUND.printMessage("are",
        Integer.toString(operatorFoundIndexes.size()), "s", ":");
      // Prints the operator and their location for every operator found
      for (int i = 0; i < operatorFoundIndexes.size(); i++) {
        // Reference to the current operator found
        Operator operatorFound = savedOperators.get(operatorFoundIndexes.get(i));
        // Prints the operator and their location
        System.out.println("* " + operatorFound.getName() + " ('" + operatorFound.getId() 
          + "' located in '" + operatorFound.getLocation().getFullName() + "')");
      }
    }
  }

  public void createOperator(String operatorName, String location) {
    // Returns the location from the Locatiom enum, otherwise null
    Location locationFound = Location.fromString(location);

    operatorName = operatorName.trim();

    // Checks if the operator to be created is valid
    if (isOperatorValid(operatorName, location, locationFound) == true) {
      // Creates the operator's 3-digit number
      int operatorNum = 1;
      for (Operator operator : savedOperators) {
        String currentLocation = operator.getLocation().getLocationAbbreviation();
        if (currentLocation.equalsIgnoreCase(locationFound.getLocationAbbreviation())) {
          operatorNum++;
        }
      }
      
      // Creates an instance of the operator with its details
      Operator operator = new Operator(operatorName, locationFound, operatorNum);
      this.savedOperators.add(operator);

      // Prints the name of the operator created and operator ID and the location
      MessageCli.OPERATOR_CREATED.printMessage(operatorName, operator.getId()
        , operator.getLocation().getFullName());
    }
  }

  public void viewActivities(String operatorId) {
    // TODO implement
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    /* 
     * Activity name: >= 3 characters
     * will not test for same name
     * Activity type: available types in enum, user can type in upper or lower case
     * if invalid activity type: default value should be OTHER
     * Operator ID: from operator
     * msg: "Successfully created activity 'Bethells Beach Camel Trek' ('WACT-AKL-001-001': 'Adventure') for 'West Auckland Camel Treks'." 
     * Activity ID: "<OPERATOR_ID>-<THREE_DIGIT_NUMBER>", no. for each new activity for same operator
     */
     
     // Convert activityType to activityType enum type
     ActivityType type = ActivityType.fromString(activityType.trim());

     // Continues to create the activity only if the entered details are valid
     if (isActivityValid(activityName, type, operatorId) == true) {
      // 3-digit number representing the activity number under the same operator
      int activityNum = 1;
      
      // Represents this activity's operator
      Operator operator = getOperatorFromId(operatorId);

      Activity activity = new Activity(activityName, operatorId, activityNum, type, operator);
      
      // Prints the activity name, activity ID, activity type, and activity operator for successful creation
      MessageCli.ACTIVITY_CREATED.printMessage(activity.getName(), activity.getActivityId(),
       activity.getActivityType().toString(), activity.getOperator().getName());
     }
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

  // Prevents operator creation for invalid cases
  public Boolean isOperatorValid(String operatorName, String location, Location locationFound) {
    // If operatorName is less than 3 characters
    if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return false;
    }

    // If the location does not exist
    if (locationFound == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return false;
    }

    // If an operator with the same name already exists at the same location
    for (Operator operator : savedOperators) {
      String currentName = operator.getName();
      String currentLocation = operator.getLocation().getLocationAbbreviation();
      if (currentName.equalsIgnoreCase(operatorName) 
        && currentLocation.equalsIgnoreCase(locationFound.getLocationAbbreviation())) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(operatorName, locationFound.getFullName());
        return false;
      }
    }

    // If operator is valid
    return true;
  }

  public Boolean isActivityValid(String activityName, ActivityType type, String operatorId) {
    // If activity name < 3 characters

    // If operator ID does not exist
    return true;
  }

  // Returns the operator represented by the given ID
  public Operator getOperatorFromId(String operatorId) {
    for (Operator operator : this.savedOperators) {
      if (operator.getId().contains(operatorId)) {
        return operator;
      }
    }

    // If no operator is found for the given ID
    return null;
  }
}
