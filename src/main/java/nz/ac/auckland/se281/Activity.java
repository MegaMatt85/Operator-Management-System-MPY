package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.ActivityType;

public class Activity {

  private String name;
  private String operatorId;
  private int activityNum;
  private String activityId;
  private ActivityType type;
  private Operator operator;

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

  public String createActivityId() {
    // Turns activityNum into the 3-digit number using/removing leading 0s
    String activityNum = "00" + Integer.toString(this.activityNum);
    activityNum = activityNum.substring(activityNum.length() - 3);
    return this.operatorId + "-" + activityNum;
  }

  public void printActivity() {
    System.out.println("* " + name + ": [" + activityId + "/" + type + "] offered by " + operator.getName());
  }
}