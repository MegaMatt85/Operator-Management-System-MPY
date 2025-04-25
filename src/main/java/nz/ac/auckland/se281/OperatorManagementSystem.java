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
    // Check that the given operatorID exists
    if (getOperatorFromId(operatorId) == null) {
      // Prints error message if no operator found
      MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
      return;
    }

    // Saves each activity found with the given operator ID
    ArrayList<Activity> activitiesFound = new ArrayList<>();
    for (Activity activity : this.savedActivities) {
      if (activity.getOperatorId().equals(operatorId)) {
        activitiesFound.add(activity);
      }
    }

    if (activitiesFound.size() == 0) { // Prints for no activites found
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");

    } else if (activitiesFound.size() == 1) { // Prints for 1 activity found
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
      Activity activityFound = activitiesFound.get(0);
      // Prints the found activity
      activityFound.printActivity();

    } else { // Prints 2 or more activities found
      MessageCli.ACTIVITIES_FOUND.printMessage("are"
        , Integer.toString(activitiesFound.size()), "ies", ":");
      // Prints every activity found
      for (int i = 0; i < activitiesFound.size(); i++) {
        Activity activityFound = activitiesFound.get(i);
        activityFound.printActivity();
      }
    }
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
     // Convert activityType to activityType enum type
     ActivityType type = ActivityType.fromString(activityType.trim());

     // Continues to create the activity only if the entered details are valid
     if (isActivityValid(activityName, type, operatorId) == true) {
      // Creates the activity's 3-digit number
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
      
      // Prints the successful creation message
      MessageCli.ACTIVITY_CREATED.printMessage(activity.getName()
        , activity.getActivityId(), activity.getActivityType().toString()
       , activity.getOperator().getName());
     }
  }

  public void searchActivities(String keyword) {

    keyword = keyword.toLowerCase().trim();

    ArrayList<Activity> activitiesFound = new ArrayList<>();

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
      MessageCli.ACTIVITIES_FOUND.printMessage("are"
        , Integer.toString(activitiesFound.size()), "ies", ":");
      // Prints every activity found
      for (Activity activityFound : activitiesFound) {
        activityFound.printActivity();
      }
    }
  }

  public void addPublicReview(String activityId, String[] options) {
    // Check that the activity ID matches an existing one
    Activity activity = getActivityFromId(activityId);
    if (activity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);

    } else {
      // Create review and save it to the respective activity
      PublicReview review = new PublicReview(options, activity);
      activity.addReview(review);

      // Print success message
      review.printSuccess();
    }

  }

  public void addPrivateReview(String activityId, String[] options) {
     // Check that the activity ID matches an existing one
    Activity activity = getActivityFromId(activityId);
    if (activity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);

    } else {
      // Create review and save it to the respective activity
      PrivateReview review = new PrivateReview(options, activity);
      activity.addReview(review);

      // Print success message
      review.printSuccess();
    }
  }

  public void addExpertReview(String activityId, String[] options) {
    // Check that the activity ID matches an existing one
    Activity activity = getActivityFromId(activityId);
    if (activity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);

    } else {
      // Create review and save it to the respective activity
      ExpertReview review = new ExpertReview(options, activity);
      activity.addReview(review);

      // Print success message
      review.printSuccess();
    }
  }

  public void displayReviews(String activityId) {
    // Check that the activity ID matches an existing one
    Activity activity = getActivityFromId(activityId);
    if (activity == null) {
      // Error message for if the activity does not exist
      MessageCli.ACTIVITY_NOT_FOUND.printMessage(activityId);

    } else {
      // Get all the reviews from the activity
      ArrayList<Review> reviews = activity.getReviews();

      if (reviews.size() == 0) {
        // Print for no reviews found
        MessageCli.REVIEWS_FOUND.printMessage("are", "no"
          , "s", activity.getName());

      } else if (reviews.size() == 1) {
        // Print for 1 review found
        MessageCli.REVIEWS_FOUND.printMessage("is", "1"
          , "", activity.getName());
        reviews.get(0).printReview();

      } else {
        String reviewCount = Integer.toString(reviews.size());
        // Print for 2 or more reviews found
        MessageCli.REVIEWS_FOUND.printMessage("are", reviewCount
          , "s", activity.getName());
        // Print the individual reviews for every review
        for (Review review : reviews) {
          review.printReview();
        }
      }
    }
  }

  public void endorseReview(String reviewId) {
    // Check if the review ID matches an existing public review
    Review review = getReviewFromId(reviewId);
    if (review == null) {
      // Prints error message if review ID is invalid
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);

    } else if (review instanceof PublicReview) {
      PublicReview reviewPublic = (PublicReview) review;
      // Changes print message to include endorsement
      reviewPublic.endorse();
      // Prints success message for endorsing the review
      MessageCli.REVIEW_ENDORSED.printMessage(reviewId);

    } else {
      // Prints error message if the review found is not a public review
      MessageCli.REVIEW_NOT_ENDORSED.printMessage(reviewId);
    }
  }

  public void resolveReview(String reviewId, String response) {
    // Check if the review ID matches an existing private review
    Review review = getReviewFromId(reviewId);
    if (review == null) {
      // Prints error message if review ID is invalid
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);

    } else if (review instanceof PrivateReview) {
      PrivateReview reviewPrivate = (PrivateReview) review;
      // Changes print message to include resolution message
      reviewPrivate.resolve(response);
      // Prints success message for resolving the review
      MessageCli.REVIEW_RESOLVED.printMessage(reviewId);

    } else {
      // Prints error message if the review found is not a private review
      MessageCli.REVIEW_NOT_RESOLVED.printMessage(reviewId);
    }
  }

  public void uploadReviewImage(String reviewId, String imageName) {
     // Check if the review ID matches an existing expert review
    Review review = getReviewFromId(reviewId);
    if (review == null) {
      // Prints error message if review ID is invalid
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      
    } else if (review instanceof ExpertReview) {
      ExpertReview reviewExpert = (ExpertReview) review;
      // Changes the print message to include the image
      reviewExpert.addImage(imageName);
      MessageCli.REVIEW_IMAGE_ADDED.printMessage(imageName, reviewId);

    } else {
      // Prints error message if review found is not an expert review
      MessageCli.REVIEW_IMAGE_NOT_ADDED_NOT_EXPERT.printMessage(reviewId);
    }

  }

  public void displayTopActivities() {
    // Finds top activities (if any) for each location
    for (Location location : Location.values()) {
      Activity topActivity = null;
      // Goes through the activity's reviews if it is in the current location
      for (Activity activity : this.savedActivities) {
        if (activity.getOperator().getLocation().equals(location)) {
          // Updates topActivity if no other reviewed activity exists
          if ((topActivity == null) && (activity.getReviews().size() > 0)) {
            topActivity = activity;
          // Updates topActivity if a higher reviewed activity is found
          } else if ((topActivity != null) && 
            (activity.getAverageRating() > topActivity.getAverageRating())) {
            topActivity = activity;
          }
        }  
      }

      // Prints the top activity or no activity if no reviewed activities
      if (topActivity == null) {
        MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(location.getFullName());
      } else {
         MessageCli.TOP_ACTIVITY.printMessage(location.getFullName()
          , topActivity.getName(), Integer.toString(topActivity.getAverageRating()));
      }
    }
  }

  // Checks if the operator to be created is valid
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
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
          operatorName, locationFound.getFullName());
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

  // Returns the review represented by the given review ID
  public Review getReviewFromId(String reviewId) {
    for (Activity activity : this.savedActivities) {
      for (Review review : activity.getReviews()) {
        if (review.getReviewId().equals(reviewId)) {
          return review;
        }
      }
    }

    // If no review is found for the given Id
    return null;
  }
}
