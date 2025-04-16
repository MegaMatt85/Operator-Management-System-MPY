package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.ActivityType;

public class Activity {

  private String name;
  private String operatorId;
  private int activityNum;
  private String activityId;
  private ActivityType type;
  private Operator operator;
  private ArrayList<Review> reviews = new ArrayList<>();
  private int sumRatings = 0;

  public Activity(String name, String operatorId, int activityNum, ActivityType type, Operator operator) {
    this.name = name;
    this.operatorId = operatorId;
    this.activityNum = activityNum;
    this.activityId = createActivityId();
    this.type = type;
    this.operator = operator;
  }

  public String getName() {
    return this.name;
  }

  public String getActivityId() {
    return this.activityId;
  }

  public String getOperatorId() {
    return this.operatorId;
  }

  public ActivityType getActivityType() {
    return this.type;
  }

  public Operator getOperator() {
    return this.operator;
  }

  public ArrayList<Review> getReviews() {
    return this.reviews;
  }

  public int getAverageRating() {
    if (reviews.size() != 0) {
      return this.sumRatings / this.reviews.size();
    }
    return 0; // Change?
  }

  public String createActivityId() {
    // Turns activityNum into the 3-digit number using/removing leading 0s
    String activityNum = "00" + Integer.toString(this.activityNum);
    activityNum = activityNum.substring(activityNum.length() - 3);
    return this.operatorId + "-" + activityNum;
  }

  public void printActivity() {
    System.out.println("* " + name + ": [" + activityId + "/" + type + "] offered by " + operator.getName());
  }

  // Adds review to this particular activities reviews
  public void addReview(Review review) {
    this.reviews.add(review);
    this.sumRatings += review.getRating();
  }
}