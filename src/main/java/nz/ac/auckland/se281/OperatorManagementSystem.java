package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;
import java.util.ArrayList;

public class OperatorManagementSystem {

  private ArrayList<Operator> savedOperators = new ArrayList<>();
  private ArrayList<Activity> savedActivities = new ArrayList<>();

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
      operatorFound.printOperator();

    } else { // Prints for the total number of operators found if there are two or more
      MessageCli.OPERATORS_FOUND.printMessage("are",
        Integer.toString(operatorFoundIndexes.size()), "s", ":");
      // Prints the operator and their location for every operator found
      for (int i = 0; i < operatorFoundIndexes.size(); i++) {
        // Reference to the current operator found
        Operator operatorFound = savedOperators.get(operatorFoundIndexes.get(i));
        // Prints the operator and their location
        operatorFound.printOperator();
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
    /*
     * Views all activities for the specified operator ID
     * 1. check that opID exists
     * 2. count how many activities found with the opID and index them (save activities 1st)
     * 3.
     * - output for no activities found for given operator
     * - output for 1 activity found for given operator
     * - output for 2 activities found for given operator
     */

    // Check that the given operatorID exists
    if (getOperatorFromId(operatorId) == null) {
      // Prints error message if no operator found
      MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
      return;
    }

    // Save the indexes of each activity found with the given operator ID
    ArrayList<Integer> activitiesFoundIndexes = new ArrayList<>();
    for (Activity activity : this.savedActivities) {
      if (activity.getOperatorId().equals(operatorId)) {
        activitiesFoundIndexes.add(this.savedActivities.indexOf(activity));
      }
    }

    if (activitiesFoundIndexes.size() == 0) { // Prints for no activites found
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");

    } else if (activitiesFoundIndexes.size() == 1) { // Prints for 1 activity found
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      Activity activityFound = this.savedActivities.get(activitiesFoundIndexes.get(0));
      // Prints the found activity
      activityFound.printActivity();

    } else { // Prints 2 or more activities found
      MessageCli.ACTIVITIES_FOUND.printMessage("are", Integer.toString(activitiesFoundIndexes.size()), "ies", ":");
      // Prints every activity found
      for (int i = 0; i < activitiesFoundIndexes.size(); i++) {
        Activity activityFound = this.savedActivities.get(activitiesFoundIndexes.get(i));
        activityFound.printActivity();
      }
    }
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    /* 
     * Activity name: >= 3 characters
     * will not test for same name
     * Activity type: available types in enum, user can type in upper or lower case
     * if invalid activity type: default value should be OTHER
     * Operator ID: from operator, must be valid
     * msg: "Successfully created activity 'Bethells Beach Camel Trek' ('WACT-AKL-001-001': 'Adventure') for 'West Auckland Camel Treks'." 
     * Activity ID: "<OPERATOR_ID>-<THREE_DIGIT_NUMBER>", no. for each new activity for same operator
     * go through all activities, sum the ones with the same operator to get "000"
     */
     
     // Convert activityType to activityType enum type
     ActivityType type = ActivityType.fromString(activityType.trim());

     // Continues to create the activity only if the entered details are valid
     if (isActivityValid(activityName, type, operatorId) == true) {
      // Creates the 3-digit number representing the activity number under the same operator
      int activityNum = 1;
      for (Activity activity : this.savedActivities) {
        if (activity.getOperatorId().equals(operatorId)) {
          activityNum++;
        }
      }
      
      // Represents this activity's operator
      Operator operator = getOperatorFromId(operatorId);

      Activity activity = new Activity(activityName, operatorId, activityNum, type, operator);

      this.savedActivities.add(activity);
      
      // Prints the activity name, activity ID, activity type, and activity operator for successful creation
      MessageCli.ACTIVITY_CREATED.printMessage(activity.getName(), activity.getActivityId(),
       activity.getActivityType().toString(), activity.getOperator().getName());
     }
  }

  public void searchActivities(String keyword) {
    /*
     * Searches for activities - keyword in name, type, or operator location (case-insensitive)
     * 1. Check for invalid input ?????
     * 2. check if "*" is inputted -> print all activitites
     * 3. Check for name, type, or operator location (case-insensitive) - can be substring -> print found
     * - operator location can be te reo Māori, English, or abbreviation
     */

    keyword = keyword.toLowerCase().trim();

    ArrayList<Activity> activitiesFound = new ArrayList<>();
    ArrayList<String> searchList = new ArrayList<>();

    // Adds every activity to be printed
    if (keyword.equals("*")) {
      activitiesFound.addAll(this.savedActivities);

    } else {
      // Initialises strings to search through
      String name;
      String type;
      String locationEng;
      String locationTeReo;
      String locationAbbr;

      // Loops through every activity and check if they contain keyword
      for (Activity activity : this.savedActivities) {
        // Add all strings which could contain the keyword to searchList
        Location operatorLocation = activity.getOperator().getLocation();
        name = activity.getName().toLowerCase();
        type = activity.getActivityType().toString().toLowerCase();
        locationEng = operatorLocation.getNameEnglish().toLowerCase();
        locationTeReo = operatorLocation.getNameTeReo().toLowerCase();
        locationAbbr = operatorLocation.getLocationAbbreviation().toLowerCase();

        // Check if the keyword is in any of the strings
        if (name.contains(keyword) || type.contains(keyword) 
          || locationEng.contains(keyword) || locationTeReo.contains(keyword)
          || locationAbbr.contains(keyword)) {
          activitiesFound.add(activity);
        }

        // Clears searchList for the next activity
        searchList.clear();
      }
    }

    if (activitiesFound.isEmpty()) { // Prints for no activities found
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");

    } else if (activitiesFound.size() == 1) { // Prints for one activity found
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      Activity activityFound = activitiesFound.get(0);
      // Prints the found activity
      activityFound.printActivity();

    } else { // Prints for 2 or more activities found
      MessageCli.ACTIVITIES_FOUND.printMessage("are", Integer.toString(activitiesFound.size()), "ies", ":");
      // Prints every activity found
      for (Activity activityFound : activitiesFound) {
        activityFound.printActivity();
      }
    }
  }

  public void addPublicReview(String activityId, String[] options) {
    /*
     * Public reviews are visible to everyone, added by customers who have experienced activity
     * include: reviwer's name, rating, comments = included in options array
     * Rating must be rounded to be 1-5
     * anonymous: "y" or "n" exactly
     * can endorse public reviews, becomes marked when displayed
     * # any review should be added to the system tied to a particular activity #
     * -> error message if activity ID invalid
     * -> success message: ...
     * # review ID added: "<ACTIVITY_ID>-R<REVIEW_NUMBER>", starts at 1, +1 for each new review for same activity #
     */
    /*
     * public review subclass:
     * - anonymous
     * - methods
     * - getters
    */

    // 1. Check activity exists, 2. Create review (Sort out stuff inside review), 3. Add to activity

    // Check that the activity ID matches an existing one
    Activity activity = getActivityFromId(activityId);

    if (activity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
    }

  }

  public void addPrivateReview(String activityId, String[] options) {
    /*
     * Private reviews are only visible to operator, for customers who have experienced - feedback to operator
     * include: reviewer's name, email, rating, comments = included in options array
     * Rating must be rounded to be 1-5
     * customer can request follow up response otherwise marked as resolved
     * if response requested, response should be printed
     * if response not made yet: "Need to email 'felicia@email.com' for follow-up."
     * # any review should be added to the system tied to a particular activity #
     * -> error message if activity ID invalid
     * -> success message: ...
     * # review ID added: "<ACTIVITY_ID>-R<REVIEW_NUMBER>", starts at 1, +1 for each new review for same activity #
     */
    /*
     * private review subclass:
     * - email
     * - response requested
     * - methods
     * - getters
     */

     // 1. Check activity exists, 2. Create review (Sort out stuff inside review), 3. Add to activity
  }

  public void addExpertReview(String activityId, String[] options) {
    /*
     * Expert reviews are added by experts in the field, visible to everyone
     * include: reviewer's name, rating, comments, reccomend activity or not = included in options array
     * Rating must be rounded to be 1-5
     * can also upload images
     * # any review should be added to the system tied to a particular activity #
     * -> error message if activity ID invalid
     * -> success message: ...
     * # review ID added: "<ACTIVITY_ID>-R<REVIEW_NUMBER>", starts at 1, +1 for each new review for same activity #
     */
    /*
     * expert review subclass:
     * - reccomend/not reccomend
     * - methods
     * - getters
     */

     // 1. Check activity exists, 2. Create review (Sort out stuff inside review), 3. Add to activity
  }
  /*
   * Review parent class:
   * - reviewer's name
   * - rating
   * - comments
   * - getters
   */

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

  // Checks that the activity to be created is valid
  public Boolean isActivityValid(String activityName, ActivityType type, String operatorId) {
    // Invalid if activity name < 3 characters
    if (activityName.length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return false;
    }

    // If operator ID does not exist
    Operator operator = getOperatorFromId(operatorId);
    if (operator == null) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return false;
    }

    return true;
  }

  // Returns the operator represented by the given operator ID
  public Operator getOperatorFromId(String operatorId) {
    for (Operator operator : this.savedOperators) {
      if (operator.getId().contains(operatorId)) {
        return operator;
      }
    }

    // If no operator is found for the given ID
    return null;
  }

  // Returns the activity represented by the given activity ID
  public Activity getActivityFromId(String activityId) {
    for (Activity activity : this.savedActivities) {
      if (activity.getActivityId().equals(activityId)) {
        return activity;
      }
    }

    // If no activity is found for the given ID
    return null;
  }
}
